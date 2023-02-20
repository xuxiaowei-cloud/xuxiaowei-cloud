FROM xuxiaoweicomcn/dragonwell8:8.14.15-Standard

WORKDIR /home

COPY target/xxl-job-admin-0.0.1-SNAPSHOT.jar xxl-job-admin-0.0.1-SNAPSHOT.jar

EXPOSE 5101

ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8

CMD java -jar xxl-job-admin-0.0.1-SNAPSHOT.jar
