# Java

- 请使用 [dragonwell8](https://github.com/alibaba/dragonwell8)
    - 本项目使用版本
        - [下载镜像](https://github.com/alibaba/dragonwell8/wiki/%E4%B8%8B%E8%BD%BD%E9%95%9C%E5%83%8F(Mirrors-for-download))
        - [Alibaba_Dragonwell_Standard_8.14.15_aarch64_linux.tar.gz](https://dragonwell.oss-cn-shanghai.aliyuncs.com/8.14.15/Alibaba_Dragonwell_Standard_8.14.15_aarch64_linux.tar.gz)
        - [Alibaba_Dragonwell_Standard_8.14.15_x64_linux.tar.gz](https://dragonwell.oss-cn-shanghai.aliyuncs.com/8.14.15/Alibaba_Dragonwell_Standard_8.14.15_x64_linux.tar.gz)
        - [Alibaba_Dragonwell_Standard_8.14.15_x64_windows.zip](https://dragonwell.oss-cn-shanghai.aliyuncs.com/8.14.15/Alibaba_Dragonwell_Standard_8.14.15_x64_windows.zip)
    - 安装目录
        - D:\Program Files\Java\dragonwell-8.14.15
    - 环境变量
        - 添加环境变量
          ```shell
          JAVA_HOME=D:\Program Files\Java\dragonwell-8.14.15
          CLASSPATH=.;%JAVA_HOME%\lib;%JAVA_HOME%\lib\dt.jar;%JAVA_HOME%\lib\tools.jar
          ```
            - Path 中添加：
                ```shell
                %JAVA_HOME%\bin
                ```
