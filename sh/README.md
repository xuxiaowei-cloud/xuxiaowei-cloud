# Linux 脚本说明

## Java

- 请使用 [dragonwell8](https://github.com/alibaba/dragonwell8)
    - 本项目使用版本
        - [dragonwell-8.10.11_jdk8u322-ga](https://github.com/alibaba/dragonwell8/releases/tag/dragonwell-8.10.11_jdk8u322-ga)
        - [Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz](https://github.com/alibaba/dragonwell8/releases/download/dragonwell-8.10.11_jdk8u322-ga/Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz)
    - 安装目录
        - /software/dragonwell-8.10.11
    - 环境变量
        - 修改 `/etc/profile` 文件
          ```shell
          vim /etc/profile        
          ```
          ```shell
          JAVA_HOME=/software/dragonwell-8.10.11
          JRE_HOME=$JAVA_HOME/jre
          CLASSPATH=.:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JRE_HOME/lib
          
          PATH=$JAVA_HOME/bin:$JRE_HOME/bin:$PATH
          
          export JAVA_HOME JRE_HOME CLASSPATH PATH
          ```
          ```shell
          source /etc/profile
          java -version
          javac -version
          ```

## [Nacos](https://github.com/alibaba/nacos)

- 本项目使用版本
    - [2.0.4](https://github.com/alibaba/nacos/releases/tag/2.0.4)
    - [nacos-server-2.0.4.tar.gz](https://github.com/alibaba/nacos/releases/download/2.0.4/nacos-server-2.0.4.tar.gz)
    - 安装目录
        - /software/nacos-server-2.0.4
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
    
    JAVA_HOME=/software/dragonwell-8.10.11
    
    cygwin=false
    darwin=false
    os400=false
    ```
