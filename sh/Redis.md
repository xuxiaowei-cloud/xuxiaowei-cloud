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
        ```
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
        ```
        /usr/local/bin/redis-server
        /usr/local/bin/redis-benchmark
        /usr/local/bin/redis-cli
        ```
    - 服务脚本示例文件
        ```
        /software/redis-6.2.6/utils/systemd-redis_multiple_servers@.service
        ```
        内容大致如下：
        ```shell
        # example systemd template service unit file for multiple redis-servers
        #
        # You can use this file as a blueprint for your actual template service unit
        # file, if you intend to run multiple independent redis-server instances in
        # parallel using systemd's "template unit files" feature. If you do, you will
        # want to choose a better basename for your service unit by renaming this file
        # when copying it.
        #
        # Please take a look at the provided "systemd-redis_server.service" example
        # service unit file, too, if you choose to use this approach at managing
        # multiple redis-server instances via systemd.
        
        [Unit]
        Description=Redis data structure server - instance %i
        Documentation=https://redis.io/documentation
        # This template unit assumes your redis-server configuration file(s)
        # to live at /etc/redis/redis_server_<INSTANCE_NAME>.conf
        AssertPathExists=/etc/redis/redis_server_%i.conf
        #Before=your_application.service another_example_application.service
        #AssertPathExists=/var/lib/redis
        
        [Service]
        ExecStart=/usr/local/bin/redis-server /etc/redis/redis_server_%i.conf
        LimitNOFILE=10032
        NoNewPrivileges=yes
        #OOMScoreAdjust=-900
        #PrivateTmp=yes
        Type=notify
        TimeoutStartSec=infinity
        TimeoutStopSec=infinity
        UMask=0077
        #User=redis
        #Group=redis
        #WorkingDirectory=/var/lib/redis
        
        [Install]
        WantedBy=multi-user.target
        ```
    - 编写服务脚本
        1. 复制`/software/redis-6.2.6/redis.conf`到`/etc/redis/redis_server_6379.conf`：
            ```
            mkdir -p /etc/redis
            cp /software/redis-6.2.6/redis.conf /etc/redis/redis_server_6379.conf
            ```
        1. 复制`/software/redis-6.2.6/utils/systemd-redis_multiple_servers@.service`
           到`/usr/lib/systemd/system/redis_6379.service`：
            ```
            cp /software/redis-6.2.6/utils/systemd-redis_multiple_servers@.service /usr/lib/systemd/system/redis_6379.service
            ```
           将`/usr/lib/systemd/system/redis_6379.service`中的参数`%i`改为`6379`，并将`Type=notify`修改为`Type=forking`：
            ```shell
            # example systemd template service unit file for multiple redis-servers
            #
            # You can use this file as a blueprint for your actual template service unit
            # file, if you intend to run multiple independent redis-server instances in
            # parallel using systemd's "template unit files" feature. If you do, you will
            # want to choose a better basename for your service unit by renaming this file
            # when copying it.
            #
            # Please take a look at the provided "systemd-redis_server.service" example
            # service unit file, too, if you choose to use this approach at managing
            # multiple redis-server instances via systemd.
            
            [Unit]
            Description=Redis data structure server - instance 6379
            Documentation=https://redis.io/documentation
            # This template unit assumes your redis-server configuration file(s)
            # to live at /etc/redis/redis_server_<INSTANCE_NAME>.conf
            AssertPathExists=/etc/redis/redis_server_6379.conf
            #Before=your_application.service another_example_application.service
            #AssertPathExists=/var/lib/redis
            
            [Service]
            ExecStart=/usr/local/bin/redis-server /etc/redis/redis_server_6379.conf
            LimitNOFILE=10032
            NoNewPrivileges=yes
            #OOMScoreAdjust=-900
            #PrivateTmp=yes
            Type=forking
            TimeoutStartSec=infinity
            TimeoutStopSec=infinity
            UMask=0077
            #User=redis
            #Group=redis
            #WorkingDirectory=/var/lib/redis
            
            [Install]
            WantedBy=multi-user.target
            ```
        1. 开放远程连接与后台运行：
            ```
            vim /etc/redis/redis_server_6379.conf
            ```
            ```
            # bind 127.0.0.1 -::1
            protected-mode no
            daemonize yes
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
        1. 服务命令
            - 查看状态
                ```
                systemctl status redis_6379.service
                ```
            - 启动
                ```
                systemctl start redis_6379.service
                ```
            - 停止
                ```
                systemctl stop redis_6379.service
                ```
            - 重启
                ```
                systemctl restart redis_6379.service
                ```
            - 设置开机自启
                ```
                systemctl enable redis_6379.service
                ```
            - 查看开机自启
                ```
                systemctl list-unit-files | grep redis_6379.service
                ```
            - 关闭开机自启
                ```
                systemctl disable redis_6379.service
                ```
