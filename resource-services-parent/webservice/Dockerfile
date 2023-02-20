FROM xuxiaoweicomcn/dragonwell8:8.14.15-Standard

WORKDIR /home

COPY target/webservice-0.0.1-SNAPSHOT.jar webservice-0.0.1-SNAPSHOT.jar

EXPOSE 1901

ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8

CMD java -jar webservice-0.0.1-SNAPSHOT.jar
