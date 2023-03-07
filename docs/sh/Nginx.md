# Nginx

- [历史版本下载](https://nginx.org/download/)
    - 本项目使用版本
        - [nginx-1.23.3.tar.gz](https://nginx.org/download/nginx-1.23.3.tar.gz)
    - 解压目录
        - 解压：`tar -zxvf nginx-1.23.3.tar.gz`
        - /software/nginx-1.23.3
    - CentOS 安装
        ```shell
        # checking for C compiler ... not found
        yum -y install gcc
        # using --with-pcre=<path> option.
        yum -y install pcre-devel
        # using --with-zlib=<path> option.
        yum -y install zlib-devel
        # using --with-openssl=<path> option.
        yum -y install openssl-devel
        ./configure --with-http_ssl_module --with-http_v2_module
        make
        make install
        ```
    - Ubuntu 安装
        ```shell
        # checking for C compiler ... not found
        apt-get -y install gcc
        # using --with-pcre=<path> option.
        apt-get -y install libpcre3-dev
        # using --with-zlib=<path> option.
        apt-get -y install zlib1g-dev
        # using --with-openssl=<path> option.
        apt-get -y install libssl-dev
        ./configure --with-http_ssl_module --with-http_v2_module
        # Command 'make' not found
        apt-get -y install make
        make
        make install
        ```
    - 安装目录
        ```shell
        Configuration summary
        + using system PCRE library
        + using system OpenSSL library
        + using system zlib library
        
        nginx path prefix: "/usr/local/nginx"
        nginx binary file: "/usr/local/nginx/sbin/nginx"
        nginx modules path: "/usr/local/nginx/modules"
        nginx configuration prefix: "/usr/local/nginx/conf"
        nginx configuration file: "/usr/local/nginx/conf/nginx.conf"
        nginx pid file: "/usr/local/nginx/logs/nginx.pid"
        nginx error log file: "/usr/local/nginx/logs/error.log"
        nginx http access log file: "/usr/local/nginx/logs/access.log"
        nginx http client request body temporary files: "client_body_temp"
        nginx http proxy temporary files: "proxy_temp"
        nginx http fastcgi temporary files: "fastcgi_temp"
        nginx http uwsgi temporary files: "uwsgi_temp"
        nginx http scgi temporary files: "scgi_temp"
        ```
    - 服务脚本示例文件
        ```shell
        /usr/lib/systemd/system/nginx.service
        ```
        ```shell
        [Unit]
        Description=nginx
        After=network.target
        
        [Service]
        Type=forking
        ExecStart=/usr/local/nginx/sbin/nginx
        ExecReload=/usr/local/nginx/sbin/nginx -s reload
        ExecStop=/usr/local/nginx/sbin/nginx -s quit
        PrivateTmp=true
        
        [Install]
        WantedBy=multi-user.target
        ```
    - 开放端口
        - CentOS
            ```shell
            firewall-cmd --zone=public --add-port=80/tcp --permanent
            firewall-cmd --zone=public --add-port=443/tcp --permanent
            firewall-cmd --reload
            firewall-cmd --list-all
            ```
        - Ubuntu
            ```shell
            sudo ufw allow 80
            sudo ufw allow 443
            ```
    - 服务命令

        - 查看状态
            ```shell
            systemctl status nginx.service
            ```
        - 启动
            ```shell
            systemctl start nginx.service
            ```
        - 停止
            ```shell
            systemctl stop nginx.service
            ```
        - 重启
            ```shell
            systemctl restart nginx.service
            ```
        - 设置开机自启
            ```shell
            systemctl enable nginx.service
            ```
        - 查看开机自启
            ```shell
            systemctl list-unit-files | grep nginx.service
            ```
            ```shell
            systemctl is-enabled nginx.service
            ```
        - 关闭开机自启
            ```shell
            systemctl disable nginx.service
            ```
