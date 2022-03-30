# Java

- 请使用 [dragonwell8](https://github.com/alibaba/dragonwell8)
    - 本项目使用版本
        - [dragonwell-8.10.11_jdk8u322-ga](https://github.com/alibaba/dragonwell8/releases/tag/dragonwell-8.10.11_jdk8u322-ga)
        - [Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz](https://github.com/alibaba/dragonwell8/releases/download/dragonwell-8.10.11_jdk8u322-ga/Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz)
        - [下载镜像](https://github.com/alibaba/dragonwell8/wiki/%E4%B8%8B%E8%BD%BD%E9%95%9C%E5%83%8F(Mirrors-for-download))        
        - [阿里云 OSS 下载地址 Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz](https://dragonwell.oss-cn-shanghai.aliyuncs.com/8.10.11/Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz)
    - 安装目录
        - 解压：`tar -zxvf Alibaba_Dragonwell_8.10.11_x64_linux.tar.gz`
        - /software/dragonwell-8.10.11
    - 环境变量
        - 修改 `/etc/profile` 文件
            ```shell
            # CentOS 安装 vim
            # yum -y install -y vim
            # Ubuntu 安装 vim
            # sudo apt-get install -y vim
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
