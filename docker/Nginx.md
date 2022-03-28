# Nginx

## [docker hub](https://hub.docker.com/_/nginx)

- 拉取镜像

```shell
docker pull nginx:1.21.6
```

- 创建容器
    - 执行创建命令
      ```shell
      docker run \
      -itd \
      --restart=always \
      --privileged=true \
      -p 80:80 -p 443:443 \
      -v /software/ssl:/software/ssl \
      --name nginx-1.21.6 \
      -d nginx:1.21.6
      ```

    1. 查看日志
       ```shell
       docker logs -f nginx-1.21.6
       ```
    1. 进入容器
       ```shell
       docker exec -it nginx-1.21.6 bash
       ```
    1. 开放端口
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
