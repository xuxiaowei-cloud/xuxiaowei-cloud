## CentOS 安装与配置

### [在 CentOS 上安装 Docker 引擎](https://docs.docker.com/engine/install/centos/)

```shell
# 安装Tab键提示
yum -y install bash-completion
# 刷新配置文件，使Tab键功能生效
source /etc/profile

# 支持 devicemapper 储存属性
sudo yum install -y yum-utils device-mapper-persistent-data lvm2
# 添加 Docker yum 仓库
sudo yum-config-manager --add-repo https://download.docker.com/linux/centos/docker-ce.repo
# 安装 Docker
sudo yum install -y docker-ce
# 启动
sudo systemctl start docker.service
# 查看 Docker 状态（安装完成，默认未启动）
sudo systemctl status docker.service
# 设置开机自启（安装完成，默认未启动）
sudo systemctl enable docker.service
# 查看是否开机自启
systemctl list-unit-files | grep docker
```

- 其他命令

    ```shell
    # 停止
    systemctl stop docker.service
    # 重载
    systemctl reload docker.service
    # 重启
    systemctl restart docker.service
    # 关闭开机自启
    systemctl disable docker.service
    ```

- 测试（包含：CentOS、Ubuntu）

    ```shell
    docker run \
    --restart=always \
    -itd \
    --privileged=true \
    --name httpd-2.4.48 \
    -p 180:80 \
    -p 1443:443 \
    -v /home/data/apache2/htdocs/:/usr/local/apache2/htdocs/ \
    httpd:2.4.48
    ```

    - CentOS 开放端口
        ```
        firewall-cmd --zone=public --add-port=180/tcp --permanent
        firewall-cmd --zone=public --add-port=1443/tcp --permanent
        firewall-cmd --reload
        firewall-cmd --list-all
        ```

    - Ubuntu 开放端口
        ```
        sudo ufw allow 180
        sudo ufw allow 1443
        ```

    - 访问
        ```
        curl http://宿主机IP:180
        ```

- [阿里云镜像加速](https://cr.console.aliyun.com/cn-qingdao/instances/mirrors)
    - 包含 CentOS、Ubuntu
    - 登录阿里云的上述网址，可查看到阿里云给你分配的加速地址
    - 加速网址固定不可变，使用时无需验证权限
    - 也可使用本人申请的加速地址：`https://hnkfbj7x.mirror.aliyuncs.com`
    - 如果未修改过`/etc/docker/daemon.json`文件，可使用下列命令一键修改配置文件
        ```shell
        sudo mkdir -p /etc/docker
        sudo tee /etc/docker/daemon.json <<-'EOF'
        {
          "registry-mirrors": ["https://hnkfbj7x.mirror.aliyuncs.com"]
        }
        EOF
        sudo systemctl daemon-reload
        sudo systemctl restart docker
        ```
    - 如果修改过`/etc/docker/daemon.json`文件，请手动在该文件中添加或修改`registry-mirrors`为：
        ```json
        {
          "registry-mirrors": ["https://hnkfbj7x.mirror.aliyuncs.com"]
        }
        ```

- 设置软件目录（CentOS 与 Ubuntu 相同）
    - 查看安装目录（默认：/var/lib/docker）
      ```shell
      # 查看 Docker Root Dir
      docker info
      # 或
      docker info | grep "Docker Root Dir"
      ```
    - 调整软件目录（如：将docker软件目录设置为`/home/data/docker`）
        1. 停止 docker
            ```shell
            systemctl stop docker.service
            # Ubuntu 安装安装完成后开机自启
            systemctl stop docker.socket
            ```
        1. 编辑文件
            ```shell
            # CentOS 安装 vim
            # yum -y install -y vim
            # Ubuntu 安装 vim
            # sudo apt-get install -y vim
            vim /etc/docker/daemon.json
            ```
        1. 添加 `graph`
            ```json 
            {
                "graph": "/home/data/docker"
            }
            ```
        1. 调整位置
            ```shell
            # 创建父文件夹
            mkdir /home/data
            # 移动
            mv /var/lib/docker/ /home/data
            # 建立软链接
            ln -s /home/data/docker/ /var/lib/docker
            ```
        1. 重启与查看新目录是否生效
            ```shell
            systemctl start docker
            docker info | grep "Docker Root Dir"
            ```

### [在 Ubuntu 上安装 Docker 引擎](https://docs.docker.com/engine/install/ubuntu/)

```shell
# 卸载旧版本
sudo apt-get remove docker docker-engine docker.io containerd runc
# 更新apt包索引
sudo apt-get update
# 安装工具（允许apt通过 HTTPS 使用存储库）
sudo apt-get install -y ca-certificates curl gnupg lsb-release
# 添加 Docker 的官方 GPG 密钥
curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo gpg --dearmor -o /usr/share/keyrings/docker-archive-keyring.gpg
# 更新apt包索引
sudo apt-get update
# 设置稳定存储库
echo \
  "deb [arch=$(dpkg --print-architecture) signed-by=/usr/share/keyrings/docker-archive-keyring.gpg] https://download.docker.com/linux/ubuntu \
  $(lsb_release -cs) stable" | sudo tee /etc/apt/sources.list.d/docker.list > /dev/null
# 更新apt包索引
sudo apt-get update
# 安装 Docker 引擎
sudo apt-get install -y docker-ce docker-ce-cli containerd.io

# 查看 Docker 状态（安装完成，默认启动）
sudo systemctl status docker.service
# 查看是否开机自启（安装完成，默认启动）
systemctl list-unit-files | grep docker
```

- 其他命令

    ```shell
    # 启动
    sudo systemctl start docker.service
    # 设置开机自启
    sudo systemctl enable docker.service
    # 停止
    systemctl stop docker.service
    # 重载
    systemctl reload docker.service
    # 重启
    systemctl restart docker.service
    # 关闭开机自启
    systemctl disable docker.service
    ```
