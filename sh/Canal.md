# canal

- [QuickStart](https://github.com/alibaba/canal/wiki/QuickStart)

```
show variables like 'log_bin';
```

- 修改 my.cnf，开启二进制行级日志（修改完成后，重启数据库）

```
[mysqld]
# 开启 binlog
log-bin=mysql-bin
# 选择 ROW 模式
binlog-format=ROW
# 配置 MySQL replaction 需要定义，不要和 canal 的 slaveId 重复
server_id=1
```

- 创建账户并授权（注意密码格式）

```
CREATE USER canal IDENTIFIED BY 'canal';  
GRANT SELECT, REPLICATION SLAVE, REPLICATION CLIENT ON *.* TO 'canal'@'%';
-- GRANT ALL PRIVILEGES ON *.* TO 'canal'@'%' ;
# 修改密码模式
ALTER USER `canal`@`%` IDENTIFIED WITH mysql_native_password BY 'canal';
FLUSH PRIVILEGES;
```

- 解压文件

```
mkdir canal.deployer-1.1.5
tar zxvf canal.deployer-1.1.5.tar.gz  -C canal.deployer-1.1.5
```

- 修改配置文件

```
vim conf/example/instance.properties
```

```
# position info，需要改成自己的数据库信息
canal.instance.master.address = 127.0.0.1:3306
# username/password，需要改成自己的数据库信息
canal.instance.dbUsername = canal  
canal.instance.dbPassword = canal
# 匹配自己需要的数据库
canal.instance.filter.regex=.*\\..*
```
