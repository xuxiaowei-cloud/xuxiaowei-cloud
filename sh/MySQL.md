# MySQL

- 使用版本
    - MySQL 8.0.28
- [CentOS 8 EOL 寿命终止切换源](https://blog.csdn.net/qq_32596527/article/details/122902901)

- 安装 MySQL
    1. 安装前准备
        1. Tab 提示
            ```shell
            yum -y install bash-completion
            # 刷新环境变量
            source /etc/profile
            ```
        1. 安装 vim
            ```shell
            yum -y install vim
            ```
        1. 安装 wget
            ```shell
            yum -y install wget
            ```
        1. CentOS 7 下载 `.rpm` 包
            ```shell
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-client-8.0.28-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-client-plugins-8.0.28-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-common-8.0.28-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-icu-data-files-8.0.28-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-libs-8.0.28-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-libs-compat-8.0.28-1.el7.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/7/x86_64/mysql-community-server-8.0.28-1.el7.x86_64.rpm
            ```
        1. CentOS 8 下载 `.rpm` 包
            ```shell
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-client-8.0.28-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-client-plugins-8.0.28-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-common-8.0.28-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-icu-data-files-8.0.28-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-libs-8.0.28-1.el8.x86_64.rpm
            wget https://repo.mysql.com/yum/mysql-8.0-community/el/8/x86_64/mysql-community-server-8.0.28-1.el8.x86_64.rpm
            ```
        1. 卸载maria
            - 查看maria是否安装
                ```shell
                rpm -qa | grep maria
                ```
            - 执行卸载maria
                ```shell
                yum -y remove mariadb*
                ```
    1. 执行安装
        ```shell
        # 在上述已全部下载的文件所在目录中执行
        yum -y localinstall mysql-community-*.rpm
        ```
        1. 启动数据库
            ```shell
            systemctl start mysqld
            ```
        1. 查看数据库状态
            ```shell
            systemctl status mysqld
            ```
        1. 设置数据库开机自启
            ```shell
            systemctl enable mysqld
            ```
        1. 查看数据库默认密码
            ```shell
            grep 'temporary password' /var/log/mysqld.log
            ```
        1. 登录数据库
            ```
            mysql -uroot -p
            ```
        1. 修改数据库密码
            ```sql
            # 首次登录需要修改密码
            ALTER USER 'root'@'localhost' IDENTIFIED BY 'MyNewPass4!';
            ```
        1. 授权远程登录
            ```sql
            RENAME USER `root`@`localhost` TO `root`@`%`;
            ```
        1. 开放端口
            ```shell
            firewall-cmd --zone=public --add-port=3306/tcp --permanent
            firewall-cmd --reload
            firewall-cmd --list-all
            ```
    1. 修改 MySQL 数据储存位置（默认：`/var/lib/mysql`，修改后的位置：`/data/mysql`）
        1. 查看 SELinux 状态
            ```shell
            getenforce
            ```
        1. 临时关闭 SELinux
            ```shell
            setenforce 0
            ```
        1. 永久禁用 SELinux
            ```shell
            sudo sed -i 's/^SELINUX=permissive$/SELINUX=disabled/' /etc/selinux/config
            sudo sed -i 's/^SELINUX=enforcing$/SELINUX=disabled/' /etc/selinux/config
            ```
        1. 停止 MySQL
            ```shell
            systemctl stop mysqld
            ```
        1. 移动 MySQL 数据
            ```shell
            mkdir /data
            mv /var/lib/mysql /data
            ```
        1. 修改 MySQL 配置文件
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
        1. 重启 MySQL 数据库
            ```shell
            systemctl restart mysqld
            ```
        1. 查看 MySQL 数据库状态
            ```shell
            systemctl status mysqld
            ```
    1. 开启 MySQL 二进制日志
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
        1. 重启 MySQL 数据库
            ```shell
            systemctl restart mysqld
            ```
        1. 查看 MySQL 数据库状态
            ```shell
            systemctl status mysqld
            ```
        1. 登录数据库，查询是否生效
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
    1. 修改编码与排序
        1. 该版本已默认编码为`utf8mb4`
            ```sql
            show VARIABLES like 'character%';
            ```
        1. 修改默认排序
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
                ```
            1. 重启 MySQL 数据库
                ```shell
                systemctl restart mysqld
                ```
            1. 查看 MySQL 数据库状态
                ```shell
                systemctl status mysqld
                ```
            1. 查看是否有效
                ```sql
                # 新建数据库
                CREATE DATABASE test_collation;
                ```
                - 连接到`test_collation`数据库进行查询
                    ```sql
                    # 查看数据库编码与排序
                    SELECT @@character_set_database, @@collation_database
                    ```
