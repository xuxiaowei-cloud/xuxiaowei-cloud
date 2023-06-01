FROM docker.io/alibabadragonwell/dragonwell:8-anolis

WORKDIR /home

# 使用 pom.xml 中的 buildArgs 获取 groupId、artifactId、version 来确定 jar 包名称
# 使用 GitLab Runner 中的 CI_PIPELINE_ID、CI_JOB_ID 来确定流水线ID（CI_PIPELINE_ID）与作业ID（CI_JOB_ID）

ARG GROUP_ID=${GROUP_ID}
ARG ARTIFACT_ID=${ARTIFACT_ID}
ARG VERSION=${VERSION}
ARG CI_PIPELINE_ID=${CI_PIPELINE_ID}
ARG CI_JOB_ID=${CI_JOB_ID}

RUN echo $GROUP_ID
RUN echo $ARTIFACT_ID
RUN echo $VERSION
RUN echo $CI_PIPELINE_ID
RUN echo $CI_JOB_ID

# 可在构建docker镜像时，指定环境变量，指定后，会记录在docker镜像中环境 JAVA_OPTS 中
# 如果在启动容器时，不想使用构建时的环境变量，可自行指定
# 说明：构建时指定环境变量名是 CI_JAVA_OPTS，而docker镜像记录后的名称为 JAVA_OPTS，避免因为重名而造成未知问题
RUN echo $JAVA_OPTS
# 可在构建docker镜像时，指定环境变量，指定后，会记录在docker镜像中环境 APP_ARGS 中
# 如果在启动容器时，不想使用构建时的环境变量，可自行指定
# 说明：构建时指定环境变量名是 CI_APP_ARGS，而docker镜像记录后的名称为 APP_ARGS
RUN echo $APP_ARGS

COPY target/$ARTIFACT_ID-$VERSION.jar app.jar

EXPOSE 1101

ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8

ENV GROUP_ID=${GROUP_ID}
ENV ARTIFACT_ID=${ARTIFACT_ID}
ENV VERSION=${VERSION}
ENV CI_PIPELINE_ID=${CI_PIPELINE_ID}
ENV CI_JOB_ID=${CI_JOB_ID}
ENV JAVA_OPTS=${JAVA_OPTS}
ENV APP_ARGS=${APP_ARGS}

CMD java $JAVA_OPTS -jar app.jar $APP_ARGS
