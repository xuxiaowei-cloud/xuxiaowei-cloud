FROM nginx:1.23.3

# https://docs.github.com/zh/code-security/dependabot/dependabot-version-updates/configuration-options-for-the-dependabot.yml-file#docker
LABEL org.opencontainers.image.source=https://github.com/nginxinc/docker-nginx

WORKDIR /home

COPY target/classes/static /usr/share/nginx/html
COPY target/classes/git.json /usr/share/nginx/git.json

EXPOSE 80

ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8
