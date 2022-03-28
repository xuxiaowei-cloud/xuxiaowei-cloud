# Gitlab Runner

## CentOS 安装 Gitlab Runner

- 安装

```shell
curl -L https://packages.gitlab.com/install/repositories/runner/gitlab-runner/script.rpm.sh | sudo bash
yum -y install gitlab-runner
```

- 命令

```
# 查看版本
gitlab-runner -v

# 注册
gitlab-runner register

# 查看所有注册
gitlab-runner list

# 查看状态
gitlab-runner status
# 停止
gitlab-runner stop
# 启动
gitlab-runner start
# 重启
gitlab-runner restart
```

- 调整 gitlab-runner 同时运行的任务
    - 修改文件
        ```
        vim /etc/gitlab-runner/config.toml
        ```
        ```
        # 实时生效，默认值：1，设置为同时最多可运行 10 个任务
        concurrent = 10
        ```

- 调整运行 gitlab-runner 的用户
    - 默认运行 gitlab-runner 服务的用户名为 gitlab-runner，下面将调整成 root，避免避免某些目录无权限
        1. 查看当前 gitlab-runner 用户
            ```
            ps aux | grep gitlab-runner
            ```
            - 响应示例
                ```
                root     19115  1.2  0.0 153760 22552 ?        Ssl  08:41   0:11 /usr/bin/gitlab-runner run --working-directory /home/gitlab-runner --config /etc/gitlab-runner/config.toml --service gitlab-runner --user gitlab-runner
                ```
        1. 卸载 gitlab-runner
            ```
            sudo gitlab-runner uninstall
            ```
        1. 安装并设置 gitlab-runner 用户为 root
            ```
            sudo gitlab-runner install --working-directory /home/gitlab-runner --user root
            ```
            - working-directory
                - 工作空间
        1. 重启 gitlab-runner
            ```
            sudo service gitlab-runner restart
            ```
        1. 再次查看当前 gitlab-runner 用户
            ```
            ps aux | grep gitlab-runner
            ```
            - 响应示例
                ```
                root       2525  0.1  1.2 152224 22812 ?        Ssl  08:55   0:00 /usr/bin/gitlab-runner run --working-directory /home/gitlab-runner --config /etc/gitlab-runner/config.toml --service gitlab-runner --user root
                ```
