# Nacos

## [docker hub](https://hub.docker.com/r/nacos/nacos-server)

- 拉取镜像

```shell
docker pull nacos/nacos-server:v2.1.2
```

- 创建容器
    - 说明
        1. 连接 MySQL 时，需要指定连接串、用户名、密码，直接放在命令中存在风险（可使用命令`history`查看历史命令），故将其放在环境变量文件中`/etc/profile`
        2. 表结构
            - [GitHub](https://github.com/alibaba/nacos/blob/develop/distribution/conf/nacos-mysql.sql)
            - [GitCode 镜像仓库](https://gitcode.net/mirrors/alibaba/nacos/-/tree/develop/distribution/conf/nacos-mysql.sql)
            - [Gitee 镜像仓库](https://gitee.com/mirrors/Nacos/blob/develop/distribution/conf/nacos-mysql.sql)
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
            - 添加参数
                ```shell
                MYSQL_SERVICE_HOST=192.168.5.4
                # 默认：3306
                MYSQL_SERVICE_PORT=3306
                MYSQL_SERVICE_DB_NAME=nacos_config
                # 默认：characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false
                MYSQL_SERVICE_DB_PARAM="characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useSSL=false"
                MYSQL_SERVICE_USER=root
                MYSQL_SERVICE_PASSWORD=xuxiaowei.com.cn
                ```
            - 刷新环境变量
                ```shell
                source /etc/profile
                ```
            - 查看是否生效
                ```shell
                echo $MYSQL_SERVICE_HOST
                echo $MYSQL_SERVICE_PORT
                echo $MYSQL_SERVICE_DB_NAME
                echo $MYSQL_SERVICE_DB_PARAM
                echo $MYSQL_SERVICE_USER
                echo $MYSQL_SERVICE_PASSWORD
                ```

        2. 执行创建命令
            ```shell
            docker run \
            -itd \
            --restart always \
            --privileged=true \
            --name nacos-server-v2.1.2 \
            -p 8848:8848 \
            -p 9848:9848 \
            -e MODE=standalone \
            -e SPRING_DATASOURCE_PLATFORM=mysql \
            -e MYSQL_SERVICE_HOST=$MYSQL_SERVICE_HOST \
            -e MYSQL_SERVICE_PORT=$MYSQL_SERVICE_PORT \
            -e MYSQL_SERVICE_DB_NAME=$MYSQL_SERVICE_DB_NAME \
            -e MYSQL_SERVICE_DB_PARAM=$MYSQL_SERVICE_DB_PARAM \
            -e MYSQL_SERVICE_USER=$MYSQL_SERVICE_USER \
            -e MYSQL_SERVICE_PASSWORD=$MYSQL_SERVICE_PASSWORD \
            -d nacos/nacos-server:v2.1.2
            ```
        3. 查看日志
            ```shell
            docker logs -f nacos-server-v2.1.2
            ```
        4. 进入容器
            ```shell
            docker exec -it nacos-server-v2.1.2 bash
            ```
        5. 开放端口
            - CentOS
                ```shell
                firewall-cmd --zone=public --add-port=8848/tcp --permanent
                firewall-cmd --zone=public --add-port=9848/tcp --permanent
                firewall-cmd --reload
                firewall-cmd --list-all
                ```
            - Ubuntu
                ```shell
                sudo ufw allow 8848
                ```
