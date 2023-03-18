# Nacos

- [GitHub](https://github.com/alibaba/nacos)
    - [表结构](https://github.com/alibaba/nacos/blob/develop/distribution/conf/mysql-schema.sql)
- [GitCode 镜像仓库](https://gitcode.net/mirrors/alibaba/nacos)
    - [表结构](https://gitcode.net/mirrors/alibaba/nacos/-/blob/develop/distribution/conf/mysql-schema.sql)
- [Gitee 镜像仓库](https://gitee.com/mirrors/Nacos)
    - [表结构](https://gitee.com/mirrors/Nacos/blob/develop/distribution/conf/mysql-schema.sql)

- 本项目使用版本
    - [2.2.1](https://github.com/alibaba/nacos/releases/tag/2.2.1)
    - [nacos-server-2.2.1.tar.gz](https://github.com/alibaba/nacos/releases/download/2.2.1/nacos-server-2.2.1.tar.gz)
    - 解压：`tar -zxvf nacos-server-2.2.1.tar.gz`
    - 安装目录
        - /software/nacos-server-2.2.1
- 在 `startup.sh` 文件中添加 `JAVA_HOME`，防止存在多个 Java 环境时启动异常
    ```shell
    #!/bin/bash
    
    # Copyright 1999-2018 Alibaba Group Holding Ltd.
    # Licensed under the Apache License, Version 2.0 (the "License");
    # you may not use this file except in compliance with the License.
    # You may obtain a copy of the License at
    
    #      http://www.apache.org/licenses/LICENSE-2.0
    #
    # Unless required by applicable law or agreed to in writing, software
    # distributed under the License is distributed on an "AS IS" BASIS,
    # WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    # See the License for the specific language governing permissions and
    # limitations under the License.
    
    JAVA_HOME=/software/dragonwell-8.14.15
    
    cygwin=false
    darwin=false
    os400=false
    ```

- [开机启动脚本](nacos.service)
