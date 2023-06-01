# MySQL

- 使用版本
    - MySQL 8.0.33
- [CentOS 8 EOL 寿命终止切换源](https://blog.csdn.net/qq_32596527/article/details/122902901)

- 安装 MySQL
    1. 安装前准备
        1. Tab 提示
            ```shell
            yum -y install bash-completion
            # 刷新环境变量
            source /etc/profile
            ```
        2. 安装 vim
            ```shell
            yum -y install vim
            ```
        3. 安装 wget
            ```shell
            yum -y install wget
            ```
        4. CentOS 7 下载 `.rpm` 包
            ```shell
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-client-8.0.33-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-client-plugins-8.0.33-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-common-8.0.33-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-icu-data-files-8.0.33-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-libs-8.0.33-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-libs-compat-8.0.33-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-server-8.0.33-1.el7.x86_64.rpm
            ```
        5. CentOS 8 下载 `.rpm` 包
            ```shell
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-client-8.0.33-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-client-plugins-8.0.33-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-common-8.0.33-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-icu-data-files-8.0.33-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-libs-8.0.33-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-server-8.0.33-1.el8.x86_64.rpm
            ```
        6. 卸载maria
            - 查看maria是否安装
                ```shell
                rpm -qa | grep maria
                ```
            - 执行卸载maria
                ```shell
                yum -y remove mariadb*
                ```
    2. 执行安装
        ```shell
        # 在上述已全部下载的文件所在目录中执行
        yum -y localinstall mysql-community-*.rpm
        ```
        1. 启动数据库
            ```shell
            systemctl start mysqld
            ```
        2. 查看数据库状态
            ```shell
            systemctl status mysqld
            ```
        3. 设置数据库开机自启
            ```shell
            systemctl enable mysqld
            ```
        4. 查看数据库默认密码
            ```shell
            grep 'temporary password' /var/log/mysqld.log
            ```
        5. 登录数据库
            ```
            mysql -uroot -p
            ```
        6. 修改数据库密码
            ```sql
            # 首次登录需要修改密码
            ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
            ```
        7. 授权远程登录
            ```sql
            RENAME USER `root`@`localhost` TO `root`@`%`;
            ```
        8. 开放端口
            ```shell
            firewall-cmd --zone=public --add-port=3306/tcp --permanent
            firewall-cmd --reload
            firewall-cmd --list-all
            ```
        9. 修改密码策略
            ```mysql
            set global validate_password_policy=0;
            set global validate_password_mixed_case_count=0;
            set global validate_password_number_count=0;
            set global validate_password_special_char_count=0;
            set global validate_password_length=4;
            ```
           或
            ```mysql
            set global validate_password.policy=0;
            set global validate_password.mixed_case_count=0;
            set global validate_password.number_count=0;
            set global validate_password.special_char_count=0;
            set global validate_password.length=4;
            ```
    3. 修改 MySQL 数据储存位置（默认：`/var/lib/mysql`，修改后的位置：`/data/mysql`）
        1. 查看 SELinux 状态
            ```shell
            getenforce
            ```
        2. 临时关闭 SELinux
            ```shell
            setenforce 0
            ```
        3. 永久禁用 SELinux
            ```shell
            sudo sed -i 's/^SELINUX=permissive$/SELINUX=disabled/' /etc/selinux/config
            sudo sed -i 's/^SELINUX=enforcing$/SELINUX=disabled/' /etc/selinux/config
            ```
        4. 停止 MySQL
            ```shell
            systemctl stop mysqld
            ```
        5. 移动 MySQL 数据
            ```shell
            mkdir /data
            mv /var/lib/mysql /data
            ```
        6. 修改 MySQL 配置文件
            ```shell
            vim /etc/my.cnf
            ```
            ```shell
            # 默认值
            # datadir=/var/lib/mysql
            # socket=/var/lib/mysql/mysql.sock
            
            # 调整后的值
            datadir=/data/mysql
            socket=/data/mysql/mysql.sock
            ```
        7. 重启 MySQL 数据库
            ```shell
            systemctl restart mysqld
            ```
        8. 查看 MySQL 数据库状态
            ```shell
            systemctl status mysqld
            ```
    4. 开启 MySQL 二进制日志
        1. 修改 MySQL 配置文件
            ```shell
            vim /etc/my.cnf
            ```
            ```shell
            [mysqld]
            # MySQL 二进制日志文件名前缀
            log-bin=mysql-bin
            server-id=1
            # MySQL 二进制日志过期天数
            expire_logs_days = 14
            ```
        2. 重启 MySQL 数据库
            ```shell
            systemctl restart mysqld
            ```
        3. 查看 MySQL 数据库状态
            ```shell
            systemctl status mysqld
            ```
        4. 登录数据库，查询是否生效
            ```sql
            # 查看是否开启二进制日志
            show variables like 'log_bin';
            # 查看二进制日志过期天数
            show variables like 'expire_logs_days';
            # 查看二进制文件
            show binary logs;
            # 当前二进制文件名
            show master status;
            ```
    5. 修改编码与排序
        1. 该版本已默认编码为`utf8mb4`
            ```sql
            show VARIABLES like 'character%';
            ```
        2. 修改默认排序
            1. 修改 MySQL 配置文件
                ```shell
                vim /etc/my.cnf
                ```
                ```shell
                [mysql]
                # 该版本可缺省
                default-character-set=utf8mb4
                [mysqld]
                # 该版本可缺省
                character_set_server=utf8mb4
                # 指定默认排序
                collation_server=utf8mb4_general_ci
                # 最大连接数
                max_connections=500
                ```
            2. 重启 MySQL 数据库
                ```shell
                systemctl restart mysqld
                ```
            3. 查看 MySQL 数据库状态
                ```shell
                systemctl status mysqld
                ```
            4. 查看是否有效
                ```sql
                # 新建数据库
                CREATE DATABASE test_collation;
                ```
                ```sql
                # 使用数据库
                use test_collation;
                ```
                - 连接到`test_collation`数据库进行查询
                    ```sql
                    # 查看数据库编码与排序
                    SELECT @@character_set_database, @@collation_database
                    ```
