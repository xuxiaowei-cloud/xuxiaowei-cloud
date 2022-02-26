<div align="center">
    <h1>xuxiaowei-cloud</h1>
    <h3>徐晓伟微服务</h3>
    <a href="https://github.com/996icu/996.ICU/blob/master/LICENSE">
        <img alt="License-Anti" src="https://img.shields.io/badge/License-Anti 996-blue.svg">
    </a>
    <a href="https://996.icu/#/zh_CN">
        <img alt="Link-996" src="https://img.shields.io/badge/Link-996.icu-red.svg">
    </a>
</div>

<p align="center">
  为简化开发工作、提高生产率、解决常见问题而生
</p>


<p align="center">

  <a href="https://blog.csdn.net/qq_32596527">
    <img alt="CSDN 码龄" src="https://img.shields.io/badge/dynamic/xml?color=orange&label=CSDN&query=%2F%2Fdiv%5B%40class%3D%27person-code-age%27%5D%5B1%5D%2Fspan%5B1%5D%2Ftext%28%29%5B1%5D&url=https%3A%2F%2Fblog.csdn.net%2Fqq_32596527">
  </a>

  <a href="https://blog.csdn.net/qq_32596527">
    <img alt="CSDN 粉丝" src="https://img.shields.io/badge/dynamic/xml?color=orange&label=CSDN&prefix=%E7%B2%89%E4%B8%9D&query=%2F%2Fli%5B4%5D%2Fa%5B1%5D%2Fdiv%5B%40class%3D%27user-profile-statistics-num%27%5D%5B1%5D%2Ftext%28%29%5B1%5D&url=https%3A%2F%2Fblog.csdn.net%2Fqq_32596527">
  </a>

  <a href="https://blog.csdn.net/qq_32596527">
    <img alt="CSDN 访问" src="https://img.shields.io/badge/dynamic/xml?color=orange&label=CSDN&prefix=%E8%AE%BF%E9%97%AE&query=%2F%2Fli%5B1%5D%2Fdiv%5B%40class%3D%27user-profile-statistics-num%27%5D%5B1%5D%2Ftext%28%29%5B1%5D&url=https%3A%2F%2Fblog.csdn.net%2Fqq_32596527">
  </a>

  <a href="https://blog.csdn.net/qq_32596527">
    <img alt="CSDN 博客" src="https://img.shields.io/badge/dynamic/json?color=orange&label=CSDN&prefix=%E5%8D%9A%E5%AE%A2&query=%24.data.blog&suffix=%E7%AF%87&url=https%3A%2F%2Fblog.csdn.net%2Fcommunity%2Fhome-api%2Fv1%2Fget-tab-total%3Fusername%3Dqq_32596527">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="GitHub stars" src="https://img.shields.io/github/stars/xuxiaowei-cloud/xuxiaowei-cloud?logo=github">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="GitHub forks" src="https://img.shields.io/github/forks/xuxiaowei-cloud/xuxiaowei-cloud?logo=github">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="GitHub watchers" src="https://img.shields.io/github/watchers/xuxiaowei-cloud/xuxiaowei-cloud?logo=github">
  </a>

  <a href="https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="码云Gitee stars" src="https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud/badge/star.svg?theme=blue">
  </a>

  <a href="https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="码云Gitee forks" src="https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud/badge/fork.svg?theme=blue">
  </a>

  <a href="https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="Gitlab stars" src="https://badgen.net/gitlab/stars/xuxiaowei-cloud/xuxiaowei-cloud?icon=gitlab">
  </a>

  <a href="https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="Gitlab forks" src="https://badgen.net/gitlab/forks/xuxiaowei-cloud/xuxiaowei-cloud?icon=gitlab">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud">
    <img alt="total lines" src="https://tokei.rs/b1/github/xuxiaowei-cloud/xuxiaowei-cloud">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud/blob/main/pom.xml">
    <img alt="Spring Boot" src="https://img.shields.io/static/v1?logo=Spring Boot&message=2.6.3">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud/blob/main/pom.xml">
    <img alt="Alibaba Cloud" src="https://img.shields.io/static/v1?logo=Alibaba Cloud&message=2021.1">
  </a>

  <a href="https://www.apache.org/licenses/LICENSE-2.0">
    <img alt="code style" src="https://img.shields.io/badge/license-Apache 2-blue">
  </a>
</p>

## 模块

```
cloud.xuxiaowei
├──xuxiaowei-cloud-starter-parent           // 微服务父模块
│    └──gateway                             // 网关
│    └──passport                            // 登录
│    └──admin-server                        // 管理（监控）
│    └──cloud-commons-parent                // 微服务公共组件父模块
│        └──cloud-starter-loadbalancer      // 负载均衡组件
│        └──cloud-starter-redis             // Redis 组件
│        └──cloud-starter-session-redis     // Session Redis 组件
```

## [Linux 脚本说明](./sh)

## [Windows 脚本说明](./winsw)

## [常用 exe 说明](./exe)

## [Docker 说明](./docker)

## 其他说明

- 如果打包后，发现`src/main/java`中的源码报错，请在项目根目录中运行`mvn clean`或手动删除对应模块的`target/generated-sources/delombok`文件夹

## Stargazers over time

[![Stargazers over time](https://starchart.cc/xuxiaowei-cloud/xuxiaowei-cloud.svg)](https://starchart.cc/xuxiaowei-cloud/xuxiaowei-cloud)
