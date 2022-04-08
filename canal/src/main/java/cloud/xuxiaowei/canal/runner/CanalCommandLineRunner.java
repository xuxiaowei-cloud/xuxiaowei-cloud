package cloud.xuxiaowei.canal.runner;

import cloud.xuxiaowei.canal.properties.CloudCanalProperties;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Canal 命令行运行器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
public class CanalCommandLineRunner implements CommandLineRunner {

    private final Queue<String> SQL_QUEUE = new ConcurrentLinkedQueue<>();

    private CloudCanalProperties cloudCanalProperties;

    @Autowired
    public void setCloudCanalProperties(CloudCanalProperties cloudCanalProperties) {
        this.cloudCanalProperties = cloudCanalProperties;
    }

    @Override
    public void run(String... args) throws Exception {

        new Thread(new Runnable() {
            @Override
            public void run() {
                canal();
            }
        }).start();

    }

    private void canal() {
        String hostname = cloudCanalProperties.getHostname();
        int port = cloudCanalProperties.getPort();
        String destination = cloudCanalProperties.getDestination();
        String username = cloudCanalProperties.getUsername();
        String password = cloudCanalProperties.getPassword();
        int batchSize = cloudCanalProperties.getBatchSize();

        CanalConnector connector = CanalConnectors.newSingleConnector(new InetSocketAddress(hostname,
                port), destination, username, password);

        try {
            connector.connect();
            connector.subscribe(".*\\..*");
            connector.rollback();
            try {
                while (true) {
                    //尝试从master那边拉去数据batchSize条记录，有多少取多少
                    Message message = connector.getWithoutAck(batchSize);
                    long batchId = message.getId();
                    int size = message.getEntries().size();
                    if (batchId == -1 || size == 0) {
                        Thread.sleep(1000);
                    } else {
                        dataHandle(message.getEntries());
                    }
                    connector.ack(batchId);

                    // 当队列里面堆积的sql大于一定数值的时候就模拟执行
                    if (SQL_QUEUE.size() >= 1) {
                        executeQueueSql();
                    }
                }
            } catch (InterruptedException | InvalidProtocolBufferException e) {
                log.error("Canal异常：", e);
            }
        } finally {
            connector.disconnect();
        }
    }

    private void dataHandle(List<CanalEntry.Entry> entries) throws InvalidProtocolBufferException {
        for (CanalEntry.Entry entry : entries) {
            if (CanalEntry.EntryType.ROWDATA == entry.getEntryType()) {
                CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
                CanalEntry.EventType eventType = rowChange.getEventType();
                if (eventType == CanalEntry.EventType.DELETE) {
//                    deleteSql(entry);
                } else if (eventType == CanalEntry.EventType.UPDATE) {
                    updateSql(entry);
                } else if (eventType == CanalEntry.EventType.INSERT) {
                    insertSql(entry);
                }
            }
        }
    }

    public void executeQueueSql() {
        int size = SQL_QUEUE.size();
        for (int i = 0; i < size; i++) {
            String sql = SQL_QUEUE.poll();
            log.info("[sql]----> \n" + sql);
        }
    }

    private void insertSql(CanalEntry.Entry entry) {
        try {
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatasList) {
                List<CanalEntry.Column> columnList = rowData.getAfterColumnsList();
                StringBuilder sql = new StringBuilder("insert into " + entry.getHeader().getTableName() + " (");
                for (int i = 0; i < columnList.size(); i++) {
                    sql.append(columnList.get(i).getName());
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(") VALUES (");
                for (int i = 0; i < columnList.size(); i++) {
                    sql.append("'").append(columnList.get(i).getValue()).append("'");
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(")");
                SQL_QUEUE.add(sql.toString());
            }
        } catch (InvalidProtocolBufferException e) {
            log.error("Calan 插入异常：", e);
        }
    }

    private void updateSql(CanalEntry.Entry entry) {
        try {
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatasList) {
                List<CanalEntry.Column> newColumnList = rowData.getAfterColumnsList();
                StringBuilder sql = new StringBuilder("update " + entry.getHeader().getTableName() + " set ");
                for (int i = 0; i < newColumnList.size(); i++) {
                    sql.append(" ").append(newColumnList.get(i).getName()).append(" = '").append(newColumnList.get(i).getValue()).append("'");
                    if (i != newColumnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(" where ");
                List<CanalEntry.Column> oldColumnList = rowData.getBeforeColumnsList();
                for (CanalEntry.Column column : oldColumnList) {
                    if (column.getIsKey()) {
                        // 暂时只支持单一主键
                        sql.append(column.getName()).append("=").append(column.getValue());
                        break;
                    }
                }
                SQL_QUEUE.add(sql.toString());
            }
        } catch (InvalidProtocolBufferException e) {
            log.error("Calan 更新异常：", e);
        }
    }

    private void deleteSql(CanalEntry.Entry entry) {
        try {
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatasList) {
                List<CanalEntry.Column> columnList = rowData.getBeforeColumnsList();
                StringBuilder sql = new StringBuilder("delete from " + entry.getHeader().getTableName() + " where ");
                for (CanalEntry.Column column : columnList) {
                    if (column.getIsKey()) {
                        //暂时只支持单一主键
                        sql.append(column.getName()).append("=").append(column.getValue());
                        break;
                    }
                }
                SQL_QUEUE.add(sql.toString());
            }
        } catch (InvalidProtocolBufferException e) {
            log.error("Calan 删除异常：", e);
        }
    }

}
