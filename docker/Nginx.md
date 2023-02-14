# Nginx

## [docker hub](https://hub.docker.com/_/nginx)

- 拉取镜像

```shell
docker pull nginx:1.23.3
```

- 创建容器
    - 说明
        1. 将容器内 `/software/ssl` 映射到主机 `/software/ssl`，创建文件夹：`mkdir /software/ssl -p`
        2. 将容器内 `/etc/nginx/conf.d` 映射到主机 `/etc/nginx-1.23.3/conf.d`，创建文件夹：`mkdir /etc/nginx-1.23.3/conf.d/ -p`
    - 执行创建命令
        ```shell
        docker run \
        -itd \
        --restart=always \
        --privileged=true \
        -p 80:80 -p 443:443 \
        -v /software/ssl:/software/ssl \
        -v /etc/nginx-1.23.3/conf.d:/etc/nginx/conf.d \
        -v /home/gitlab-runner/node:/home/gitlab-runner/node \
        --name nginx-1.23.3 \
        -d nginx:1.23.3
      ```
    1. 测试配置
       ```shell
       docker exec -it nginx-1.23.3 nginx -t
       ```
    2. 重新加载
       ```shell
       docker exec -it nginx-1.23.3 nginx -s reload
       ```
    3. 查看日志
       ```shell
       docker logs -f nginx-1.23.3
       ```
    4. 进入容器
       ```shell
       docker exec -it nginx-1.23.3 bash
       ```
    5. 开放端口
        - CentOS
            ```shell
            firewall-cmd --zone=public --add-port=8848/tcp --permanent
            firewall-cmd --reload
            firewall-cmd --list-all
            ```
        - Ubuntu
            ```shell
            sudo ufw allow 8848
            ```
