FROM xuxiaoweicomcn/dragonwell8:8.14.15-Standard

WORKDIR /home

COPY target/passport-0.0.1-SNAPSHOT.jar passport-0.0.1-SNAPSHOT.jar

EXPOSE 1401

ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8

CMD java -jar passport-0.0.1-SNAPSHOT.jar
