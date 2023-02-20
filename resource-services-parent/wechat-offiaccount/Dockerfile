FROM xuxiaoweicomcn/dragonwell8:8.14.15-Standard

WORKDIR /home

COPY target/wechat-offiaccount-0.0.1-SNAPSHOT.jar wechat-offiaccount-0.0.1-SNAPSHOT.jar

EXPOSE 2101

ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8

CMD java -jar wechat-offiaccount-0.0.1-SNAPSHOT.jar
