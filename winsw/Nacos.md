# [Nacos](https://github.com/alibaba/nacos)

- 本项目使用版本
    - [2.1.1](https://github.com/alibaba/nacos/releases/tag/2.1.1)
    - [nacos-server-2.1.1.zip](https://github.com/alibaba/nacos/releases/download/2.1.1/nacos-server-2.1.1.zip)
    - 安装目录
        - D:\Alibaba\nacos-server-2.1.1
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
  
    set "JAVA_HOME=D:\Program Files\Java\dragonwell-8.10.11"
    
    if not exist "%JAVA_HOME%\bin\java.exe" echo Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
    set "JAVA=%JAVA_HOME%\bin\java.exe"
    ```
