# Redis

## [docker hub](https://hub.docker.com/_/redis)

- 拉取镜像

```
docker pull redis:6.2.6
```

- 创建容器
    - 说明
        1. 创建 Redis 容器时，需要指定密码，直接将密码放在命令中存在风险（可使用命令`history`查看历史命令），故将密码放在环境变量文件中`/etc/profile`

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
                REDIS_PASSWORD=xuxiaowei.com.cn
                ```
            - 刷新环境变量
                ```shell
                source /etc/profile
                ```
            - 查看是否生效
                ```shell
                echo $REDIS_PASSWORD
                ```
        1. 执行创建命令
            ```shell
            docker run \
            -itd \
            --restart always \
            --privileged=true \
            --name redis-6.2.6 \
            -p 6379:6379 \
            -d redis:6.2.6 \
            --requirepass $REDIS_PASSWORD
            ```
        1. 查看日志
            ```shell
            docker logs -f redis-6.2.6
            ```
        1. 进入容器
            ```shell
            docker exec -it redis-6.2.6 bash
            ```
        1. 开放端口
            - CentOS
                ```shell
                firewall-cmd --zone=public --add-port=6379/tcp --permanent
                firewall-cmd --reload
                firewall-cmd --list-all
                ```
            - Ubuntu
                ```shell
                sudo ufw allow 6379
                ```
