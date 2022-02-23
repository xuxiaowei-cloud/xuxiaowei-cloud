# WinSW 脚本说明

## Java

- 请使用 [dragonwell8](https://github.com/alibaba/dragonwell8)
    - 本项目使用版本
        - [dragonwell-8.10.11_jdk8u322-ga](https://github.com/alibaba/dragonwell8/releases/tag/dragonwell-8.10.11_jdk8u322-ga)
        - [Alibaba_Dragonwell_8.10.11_x64_windows.zip](https://github.com/alibaba/dragonwell8/releases/download/dragonwell-8.10.11_jdk8u322-ga/Alibaba_Dragonwell_8.10.11_x64_windows.zip)
    - 安装目录
        - D:\Program Files\Java\dragonwell-8.10.11
    - 环境变量
        - 添加环境变量
          ```
          JAVA_HOME=D:\Program Files\Java\dragonwell-8.10.11
          CLASSPATH=.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
          ```
            - Path 中添加：
                ```
                %JAVA_HOME%\bin
                ```

## [Nacos](https://github.com/alibaba/nacos)

- 本项目使用版本
    - [2.0.4](https://github.com/alibaba/nacos/releases/tag/2.0.4)
    - [nacos-server-2.0.4.zip](https://github.com/alibaba/nacos/releases/download/2.0.4/nacos-server-2.0.4.zip)
    - 安装目录
        - D:\Alibaba\nacos-server-2.0.4
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

## [WinSW](https://github.com/winsw/winsw)

- 本项目使用版本
    - [v2.11.0](https://github.com/winsw/winsw/releases/tag/v2.11.0)
    - [WinSW-x64.exe](https://github.com/winsw/winsw/releases/download/v2.11.0/WinSW-x64.exe)
- WinSW 的 `.exe` 可执行文件需要与 WinSW `.xml` 配置文件放在同一个文件夹中，并且名字相同（除后缀名外）
