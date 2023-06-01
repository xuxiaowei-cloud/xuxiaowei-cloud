# MySQL

## [docker hub](https://hub.docker.com/_/mysql)

- 拉取镜像

```shell
docker pull mysql:8.0.33
```

- 创建容器
    - 说明
        1. 创建 MySQL 容器时，需要指定密码，直接将密码放在命令中存在风险（可使用命令`history`查看历史命令），故将密码放在环境变量文件中`/etc/profile`
        2. 为了数据安全性，打开二进制日志`log-bin`，并设置二进制日志过期天数`expire_logs_days`（防止二进制日志文件占用磁盘过多）
        3. 使用`utf8mb4`编码，简化建表，防止储存特殊字符（如：enjoy表情）时异常
        4. 使用卷，用于数据备份与恢复
    - 创建
        1. 设置密码
            - 编辑环境变量文件
                ```shell
                # CentOS 安装 vim
                # yum -y install -y vim
                # Ubuntu 安装 vim
                # sudo apt-get install -y vim
                vim /etc/profile
                ```
            - 添加密码
                ```shell
                # 将密码设置成：xuxiaowei.com.cn
                MYSQL_ROOT_PASSWORD=xuxiaowei.com.cn
                ```
            - 刷新环境变量
                ```shell
                source /etc/profile
                ```
            - 查看是否生效
                ```shell
                echo $MYSQL_ROOT_PASSWORD
                ```
        2. 执行创建命令
            ```shell
            # expire_logs_days：已过时，推荐使用 binlog_expire_logs_seconds
            docker run \
            -itd \
            --restart always \
            --privileged=true \
            --name mysql-8.0.33 \
            -v /etc/localtime:/etc/localtime \
            -v /mysql-data:/var/lib/mysql \
            -p 3306:3306 \
            -e MYSQL_ROOT_PASSWORD=$MYSQL_ROOT_PASSWORD \
            -d mysql:8.0.33 \
            --log-bin=mysql-bin \
            --server-id=1 \
            --binlog_expire_logs_seconds=1209600 \
            --max_connections=500 \
            --character-set-server=utf8mb4 \
            --collation-server=utf8mb4_general_ci
            ```
        3. 修改插件（防止JDBC无法连接）
            ```sql
            ALTER USER `root`@`%` IDENTIFIED WITH mysql_native_password BY 'MySQL密码';
            ```
        4. 查看日志
            ```shell
            docker logs -f mysql-8.0.33
            ```
        5. 进入容器
            ```shell
            docker exec -it mysql-8.0.33 bash
            ```
        6. 开放端口
            - CentOS
                ```shell
                firewall-cmd --zone=public --add-port=3306/tcp --permanent
                firewall-cmd --reload
                firewall-cmd --list-all
                ```
            - Ubuntu
                ```shell
                sudo ufw allow 3306
                ```
        7. 常用SQL
            ```sql
            # 查看环境
            show variables;
            # 查看是否开启二进制日志
            show variables like 'log_bin';
            # expire_logs_days:已过时,推荐使用 binlog_expire_logs_seconds
            # MySQL 二进制日志过期时间,单位秒,60 * 60 * 24 * 14 = 1209600
            show variables like 'binlog_expire_logs_seconds';

            # 查看编码
            show VARIABLES like 'character%';

            # 查看最大连接数
            show variables like "max_connections";
            # 设置最大连接数
            # 此处仅为临时修改,重启后失效
            # 永久修改:在MySQL配置文件中的[mysqld]节点增加设置max_connections=1000
            set global max_connections=1000;

            # 查看二进制文件
            show binary logs;
            # 当前二进制文件名
            show master status;
            ```
