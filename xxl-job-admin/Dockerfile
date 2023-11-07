# FROM docker.io/alibabadragonwell/dragonwell:8-anolis-slim

# 使用 极狐GitLab 加速镜像构建，网站：https://jihulab.com/xuxiaowei-cloud/xuxiaowei-cloud/container_registry
FROM registry.jihulab.com/xuxiaowei-cloud/xuxiaowei-cloud/alibabadragonwell/dragonwell:8-anolis-slim

WORKDIR /home

# 使用 pom.xml 中的 buildArgs 获取 groupId、artifactId、version 来确定 jar 包名称
# 使用 GitLab Runner 中的 CI_PIPELINE_ID、CI_JOB_ID 来确定流水线ID（CI_PIPELINE_ID）与作业ID（CI_JOB_ID）

ARG GROUP_ID=${GROUP_ID}
ARG ARTIFACT_ID=${ARTIFACT_ID}
ARG VERSION=${VERSION}
ARG CI_PIPELINE_ID=${CI_PIPELINE_ID}
ARG CI_JOB_ID=${CI_JOB_ID}
ARG JAVA_OPTS=${JAVA_OPTS}

RUN printf "GROUP_ID: %s\n" "$GROUP_ID" \
    && printf "ARTIFACT_ID: %s\n" "$ARTIFACT_ID" \
    && printf "VERSION: %s\n" "$VERSION" \
    && printf "CI_PIPELINE_ID: %s\n" "$CI_PIPELINE_ID" \
    && printf "CI_JOB_ID: %s\n" "$CI_JOB_ID" \
    && printf "JAVA_OPTS: %s\n" "$JAVA_OPTS" \
    && printf "APP_ARGS: %s\n" "$APP_ARGS"

# JAVA_OPTS
# 可在构建docker镜像时，指定环境变量，指定后，会记录在docker镜像中环境 JAVA_OPTS 中
# 如果在启动容器时，不想使用构建时的环境变量，可自行指定
# 说明：构建时指定环境变量名是 CI_JAVA_OPTS，而docker镜像记录后的名称为 JAVA_OPTS，避免因为重名而造成未知问题

# APP_ARGS
# 可在构建docker镜像时，指定环境变量，指定后，会记录在docker镜像中环境 APP_ARGS 中
# 如果在启动容器时，不想使用构建时的环境变量，可自行指定
# 说明：构建时指定环境变量名是 CI_APP_ARGS，而docker镜像记录后的名称为 APP_ARGS

COPY target/$ARTIFACT_ID-$VERSION.jar app.jar

EXPOSE 5101

ENV TZ=Asia/Shanghai \
    LANG=C.UTF-8 \
    GROUP_ID=${GROUP_ID} \
    ARTIFACT_ID=${ARTIFACT_ID} \
    VERSION=${VERSION} \
    CI_PIPELINE_ID=${CI_PIPELINE_ID} \
    CI_JOB_ID=${CI_JOB_ID} \
    JAVA_OPTS=${JAVA_OPTS} \
    APP_ARGS=${APP_ARGS}

CMD java $JAVA_OPTS -jar app.jar $APP_ARGS
