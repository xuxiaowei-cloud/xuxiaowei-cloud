# Redis

- 请使用 [Redis](https://github.com/redis/redis)
    - 本项目使用版本
        - [redis 6.2.6](https://github.com/redis/redis/releases/tag/6.2.6)
        - [GitHub 下载 redis-6.2.6.tar.gz](https://github.com/redis/redis/archive/refs/tags/6.2.6.tar.gz)
        - [官网下载 redis-6.2.6.tar.gz](https://download.redis.io/releases/redis-6.2.6.tar.gz)
    - 解压目录
        - 解压：`tar -zxvf redis-6.2.6.tar.gz`
        - /software/redis-6.2.6
    - CetOS 安装
        ```shell
        # -bash: make: command not found
        yum -y install make
        # /bin/sh: cc: command not found
        yum -y install gcc
        #  #include <jemalloc/jemalloc.h>
        # https://github.com/redis/redis#allocator
        # https://gitcode.net/mirrors/redis/redis#allocator
        make MALLOC=libc
        # You need tcl 8.5 or newer in order to run the Redis test
        yum -y install tcl
        # 测试
        make test
        # 安装
        make install
        ```
    - Ubuntu 安装
        ```shell
        # Command 'make' not found, but can be installed with:
        sudo apt-get install -y make
        # /bin/sh: 1: cc: not found
        sudo apt-get install -y gcc
        #  #include <jemalloc/jemalloc.h>
        # https://github.com/redis/redis#allocator
        # https://gitcode.net/mirrors/redis/redis#allocator
        make MALLOC=libc
        # 测试
        make test
        # 安装
        make install
        ```
    - 安装位置
        ```shell
        /usr/local/bin/redis-server
        /usr/local/bin/redis-benchmark
        /usr/local/bin/redis-cli
        ```
    - 编写服务脚本
        1. 复制`/software/redis-6.2.6/redis.conf`到`/etc/redis/redis_server_6379.conf`：
            ```shell
            mkdir -p /etc/redis
            cp /software/redis-6.2.6/redis.conf /etc/redis/redis_server_6379.conf
            ```
        2. 新建文件`/usr/lib/systemd/system/redis_6379.service`：
            ```shell
            vim /usr/lib/systemd/system/redis_6379.service
            ```
            ```shell
            [Unit]
            Description=nacos
            After=syslog.target
 
            [Service]
            Type=oneshot
            ExecStart=/usr/local/bin/redis-server /etc/redis/redis_server_6379.conf
            ExecReload=/bin/kill -s HUP $MAINPID
            RemainAfterExit=yes
 
            [Install]
            WantedBy=multi-user.target
            ```
        3. 开放远程连接与后台运行：
            ```shell
            vim /etc/redis/redis_server_6379.conf
            ```
            ```shell
            # bind 127.0.0.1 -::1
            protected-mode no
            daemonize yes
            ```
        4. 开放端口
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
        5. 服务命令
            - 查看状态
                ```shell
                systemctl status redis_6379.service
                ```
            - 启动
                ```shell
                systemctl start redis_6379.service
                ```
            - 停止
                ```shell
                systemctl stop redis_6379.service
                ```
            - 重启
                ```shell
                systemctl restart redis_6379.service
                ```
            - 设置开机自启
                ```shell
                systemctl enable redis_6379.service
                ```shell
            - 查看开机自启
                ```shell
                systemctl list-unit-files | grep redis_6379.service
                ```
            - 关闭开机自启
                ```shell
                systemctl disable redis_6379.service
                ```
