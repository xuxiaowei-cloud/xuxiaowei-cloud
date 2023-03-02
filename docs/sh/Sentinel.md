# Sentinel

- [github](https://github.com/alibaba/Sentinel)
- [国内jar包下载](https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud/issues/I5BEO2)
- 用户/密码：sentinel/sentinel

```shell
java -Dserver.port=22222 -Dcsp.sentinel.dashboard.server=localhost:22222 -Dproject.name=sentinel-dashboard -jar sentinel-dashboard-1.8.5.jar
```

```shell
[Unit]
Description=sentinel-dashboard
After=syslog.target

[Service]
Type=oneshot
ExecStart=/software/dragonwell-8.12.13/bin/java -Dserver.port=22222 -Dcsp.sentinel.dashboard.server=localhost:22222 -Dproject.name=sentinel-dashboard -jar /software/sentinel-dashboard-1.8.5.jar
ExecReload=/bin/kill -s HUP $MAINPID
RemainAfterExit=yes

[Install]
WantedBy=multi-user.target
```
