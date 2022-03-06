# Java

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
