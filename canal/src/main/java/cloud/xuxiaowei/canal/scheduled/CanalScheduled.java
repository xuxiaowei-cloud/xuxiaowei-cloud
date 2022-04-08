package cloud.xuxiaowei.canal.scheduled;

import cloud.xuxiaowei.canal.properties.CloudCanalProperties;
import cloud.xuxiaowei.canal.service.CanalService;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.alibaba.otter.canal.protocol.Message;
import com.google.protobuf.InvalidProtocolBufferException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.List;

/**
 * Canal 命令行运行器
 *
 * @author xuxiaowei
 * @since 0.0.1
 */
@Slf4j
@Component
@EnableAsync
public class CanalScheduled {

    private CloudCanalProperties cloudCanalProperties;

    private CanalService canalService;

    @Autowired
    public void setCanalService(CanalService canalService) {
        this.canalService = canalService;
    }

    @Autowired
    public void setCloudCanalProperties(CloudCanalProperties cloudCanalProperties) {
        this.cloudCanalProperties = cloudCanalProperties;
    }

    @Scheduled(initialDelay = 1000, fixedRate = 1000)
    private void canal() {

        log.info("canal 启动 ……");

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
            connector.subscribe("xuxiaowei_cloud\\.oauth.*");
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
                    sql.append("\"").append(columnList.get(i).getValue()).append("\"");
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }
                sql.append(")");

                log.info("准备插入：{}", sql);
                int insert = canalService.insert(sql.toString());
                log.info("插入结果：{}", insert);

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

                log.info("准备更新：{}", sql);
                int update = canalService.update(sql.toString());
                log.info("更新结果：{}", update);

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

                log.info("准备删除：{}", sql);
                int delete = canalService.delete(sql.toString());
                log.info("删除结果：{}", delete);

            }
        } catch (InvalidProtocolBufferException e) {
            log.error("Calan 删除异常：", e);
        }
    }

}
