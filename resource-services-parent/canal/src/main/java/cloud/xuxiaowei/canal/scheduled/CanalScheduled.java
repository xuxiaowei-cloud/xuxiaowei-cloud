package cloud.xuxiaowei.canal.scheduled;

import cloud.xuxiaowei.canal.properties.CloudCanalProperties;
import cloud.xuxiaowei.canal.service.CanalService;
import cloud.xuxiaowei.utils.DateUtils;
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

import static cloud.xuxiaowei.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

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

    @Scheduled(initialDelay = 1000, fixedRate = Integer.MAX_VALUE)
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
                    deleteLogicSql(entry);
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
                    CanalEntry.Column column = columnList.get(i);
                    String mysqlType = column.getMysqlType();
                    String value = column.getValue();
                    if ("mediumblob".equals(mysqlType)) {
                        continue;
                    } else if ("datetime(0)".equals(mysqlType) && "".equals(value)) {
                        continue;
                    } else if ("json".equals(mysqlType) && "".equals(value)) {
                        continue;
                    }

                    sql.append(column.getName());
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }

                String s1 = sql.toString();
                int s1Length = s1.length();
                String s1Substring = s1.substring(s1Length - 1, s1Length);
                if (",".equals(s1Substring)) {
                    sql = new StringBuilder(s1.substring(0, s1Length - 1));
                }

                sql.append(") VALUES (");
                for (int i = 0; i < columnList.size(); i++) {
                    CanalEntry.Column column = columnList.get(i);
                    String mysqlType = column.getMysqlType();
                    String value = column.getValue();
                    if ("mediumblob".equals(mysqlType)) {
                        continue;
                    } else if ("datetime(0)".equals(mysqlType) && "".equals(value)) {
                        continue;
                    } else if ("json".equals(mysqlType) && "".equals(value)) {
                        continue;
                    }

                    sql.append("'").append(value).append("'");
                    if (i != columnList.size() - 1) {
                        sql.append(",");
                    }
                }

                String s2 = sql.toString();
                int s2Length = s2.length();
                String s2Substring = s2.substring(s2Length - 1, s2Length);
                if (",".equals(s2Substring)) {
                    sql = new StringBuilder(s2.substring(0, s2Length - 1));
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

                    CanalEntry.Column newColumn = newColumnList.get(i);
                    String mysqlType = newColumn.getMysqlType();
                    String value = newColumn.getValue();
                    if ("mediumblob".equals(mysqlType)) {
                        continue;
                    } else if ("datetime(0)".equals(mysqlType) && "".equals(value)) {
                        continue;
                    } else if ("json".equals(mysqlType) && "".equals(value)) {
                        continue;
                    }

                    sql.append(" ").append(newColumn.getName()).append(" = '").append(value).append("'");
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

    private void deleteLogicSql(CanalEntry.Entry entry) {

        CanalEntry.Header header = entry.getHeader();
        long executeTime = header.getExecuteTime();

        String executeTimeFormat = DateUtils.format(executeTime, DEFAULT_DATE_TIME_FORMAT);

        try {
            CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(entry.getStoreValue());
            List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
            for (CanalEntry.RowData rowData : rowDatasList) {
                StringBuilder sql = new StringBuilder("update " + entry.getHeader().getTableName());
                sql.append(" set deleted = 1, update_date = '").append(executeTimeFormat).append("' where ");
                List<CanalEntry.Column> oldColumnList = rowData.getBeforeColumnsList();
                for (CanalEntry.Column column : oldColumnList) {
                    if (column.getIsKey()) {
                        // 暂时只支持单一主键
                        sql.append(column.getName()).append("=").append(column.getValue());
                        break;
                    }
                }

                log.info("准备逻辑删除：{}", sql);
                int update = canalService.update(sql.toString());
                log.info("逻辑删除结果：{}", update);

            }
        } catch (InvalidProtocolBufferException e) {
            log.error("Calan 逻辑删除异常：", e);
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
