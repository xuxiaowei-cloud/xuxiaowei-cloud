# Node

- 请使用 [Node](https://nodejs.org/dist/v16.14.0)
    - 本项目使用版本
        - [Node v16.14.0](https://nodejs.org/dist/v16.14.0)
        - [node-v16.14.0-linux-x64.tar.gz](http://mirrors.aliyun.com/nodejs-release/v16.14.0/node-v16.14.0-linux-x64.tar.gz)
        - [下载镜像](http://mirrors.aliyun.com/nodejs-release/v16.14.0)
        - [下载镜像 node-v16.14.0-linux-x64.tar.gz](http://mirrors.aliyun.com/nodejs-release/v16.14.0/node-v16.14.0-linux-x64.tar.gz)
    - 安装目录
        - 解压：`tar -zxvf node-v16.14.0-linux-x64.tar.gz`
        - /software/node-v16.14.0-linux-x64
    - 环境变量
        - 修改 `/etc/profile` 文件
            ```shell
            # CentOS 安装 vim
            # yum -y install -y vim
            # Ubuntu 安装 vim
            # sudo apt-get install -y vim
            vim /etc/profile
            ```
            ```shell
            NODE_HOME=/software/node-v16.14.0-linux-x64
            NODE_GLOBAL=$NODE_HOME/node_global
            export PATH=$NODE_HOME/bin:$NODE_GLOBAL/bin:$PATH
            ```
            ```shell
            source /etc/profile
            node -v
            npm -version
            npx -v
            ```
        - 修改配置
            ```shell
            # 查看配置
            npm config ls -l
            ```
            ```shell
            # 设置全局安装路径
            npm config set prefix /software/node-v16.14.0-linux-x64/node_global
            # 设置缓存路径
            npm config set cache /software/node-v16.14.0-linux-x64/node_cache
            ```
            ```shell
            # 查看镜像
            npm config get registry
            ```
            ```shell
            # 修改为新淘宝镜像
            # http://npm.taobao.org 和 http://registry.npm.taobao.org 将在 2022.06.30 号正式下线和停止 DNS 解析
            # http://npm.taobao.org => http://npmmirror.com
            # http://registry.npm.taobao.org => http://registry.npmmirror.com
            # https://developer.aliyun.com/mirror/NPM
            npm config set registry=http://registry.npmmirror.com
            ```
            ```shell
            # 设置软链接
            sudo ln -s /software/node-v16.14.0-linux-x64/bin/node /usr/bin/node
            sudo ln -s /software/node-v16.14.0-linux-x64/bin/npm /usr/bin/npm
            sudo ln -s /software/node-v16.14.0-linux-x64/bin/npx /usr/bin/npx
            ```
            ```shell
            # 测试
            npm install -g @vue/cli
            vue -V
            ll /software/node-v16.14.0-linux-x64/node_global/lib/node_modules/@vue/cli
            ll /software/node-v16.14.0-linux-x64/node_global/bin
            ```
