# [Nacos](https://github.com/alibaba/nacos)

- 本项目使用版本
    - [2.2.1](https://github.com/alibaba/nacos/releases/tag/2.2.1)
    - [nacos-server-2.2.1.zip](https://github.com/alibaba/nacos/releases/download/2.2.1/nacos-server-2.2.1.zip)
    - 安装目录
        - D:\Alibaba\nacos-server-2.2.1
        ```
        # 连接 MySQL 数据库
        # 表结构：nacos-server-2.2.1/conf/nacos-mysql.sql
        # 数据库名：nacos_config
        # 修改：nacos-server-2.2.1/conf/application.properties（注意，一下内容要去掉开头的“#”）
        # spring.datasource.platform=mysql
        # db.num=1
        # db.url.0=jdbc:mysql://192.168.5.4:3306/nacos_config?characterEncoding=utf8&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
        # db.user.0=root
        # db.password.0=root
        #
        # 修改 nacos.core.auth.plugin.nacos.token.secret.key、nacos.core.auth.server.identity.key、nacos.core.auth.server.identity.value
        # 文档：https://nacos.io/zh-cn/docs/v2/guide/user/auth.html
        # 如：
        # 以下值需要自行修改，否则有安全隐患
        # 版本号 2.2.0.1 后无默认值
        # nacos.core.auth.plugin.nacos.token.secret.key=SecretKey012345678901234567890123456789012345678901234567890123456789
        # 版本号 2.2.1 后无默认值
        # nacos.core.auth.server.identity.key=serverIdentity
        # 版本号 2.2.1 后无默认值
        # nacos.core.auth.server.identity.value=security
        ```

- 在 `startup.cmd` 文件中添加 `JAVA_HOME`，防止存在多个 Java 环境时启动异常
    ```shell
    @echo off
    rem Copyright 1999-2018 Alibaba Group Holding Ltd.
    rem Licensed under the Apache License, Version 2.0 (the "License");
    rem you may not use this file except in compliance with the License.
    rem You may obtain a copy of the License at
    rem
    rem      http://www.apache.org/licenses/LICENSE-2.0
    rem
    rem Unless required by applicable law or agreed to in writing, software
    rem distributed under the License is distributed on an "AS IS" BASIS,
    rem WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    rem See the License for the specific language governing permissions and
    rem limitations under the License.
  
    set "JAVA_HOME=D:\Program Files\Java\dragonwell-8.14.15"
    
    if not exist "%JAVA_HOME%\bin\java.exe" echo Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
    set "JAVA=%JAVA_HOME%\bin\java.exe"
    ```
