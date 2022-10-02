# Node

## [docker hub](https://hub.docker.com/_/node)

- 拉取镜像

```shell
docker pull node:16.14.0
```

- 创建容器
    ```shell
    docker run \
    -itd \
    --restart always \
    --privileged=true \
    --name node-16.14.0 \
    -d node:16.14.0
    ```

- 查看日志
   ```shell
   docker logs -f node-16.14.0
   ```

- 进入容器
   ```shell
   docker exec -it node-16.14.0 bash
   ```

- 查看版本
    ```shell
    docker exec -it node-16.14.0 node -v
    docker exec -it node-16.14.0 npm -v
    docker exec -it node-16.14.0 npx -v
    ```
