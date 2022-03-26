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
    <img alt="Spring Boot" src="https://img.shields.io/static/v1?logo=Spring Boot&message=2.6.5">
  </a>

  <a href="https://github.com/xuxiaowei-cloud/xuxiaowei-cloud/blob/main/pom.xml">
    <img alt="Spring Cloud" src="https://img.shields.io/static/v1?label=Spring Cloud&message=2021.0.1">
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
│    └──gateway                             // 网关服务
│    └──admin-server                        // 监控（管理）服务
│    └──authorization-server-ui             // 授权服务UI
│    └──authorization-server                // 授权服务
│    └──passport-ui                         // 登录服务UI
│    └──passport                            // 登录服务
│    └──resource-server                     // 资源服务
│    └──ui                                  // UI
│    └──utils                               // 工具类
│    └──cloud-commons-parent                // 微服务公共组件父模块
│        └──cloud-starter-core              // 核心组件
│        └──cloud-starter-loadbalancer      // 负载均衡组件
│        └──cloud-starter-redis             // Redis 组件
│        └──cloud-starter-session-redis     // Session Redis 组件
│    └──example-parent                      // 示例模块（独立模块）
│        └──oauth2-client                   // OAuth 2.0 客户端（独立服务）
```

### 端口

| 模块 | 模块名称 | 域名（虚拟） | 端口 |
| ---- | ---- | ---- | ---- |
| Nacos | 注册、配置中心 | nacos.xuxiaowei.cloud | 8848 |
| MySQL | 数据库 | mysql.xuxiaowei.cloud | 3306 |
| Redis | 数据库 | redis.xuxiaowei.cloud | 6379 |
|  |  |  |  |
| ui | UI |  | 1001 |
| gateway | 网关 | gateway.xuxiaowei.cloud | 1101 |
| admin-server | 监控（管理） | admin-server.xuxiaowei.cloud | 1201 |
| authorization-server | 授权 | authorization-server.xuxiaowei.cloud | 1301 |
| authorization-server-ui | 授权UI |  | 1311 |
| passport | 登录 | passport.xuxiaowei.cloud | 1401 |
| passport-ui | 登录UI |  | 1411 |
| resource-server | 资源 | resource-server.xuxiaowei.cloud | 1501 |
| oauth2-client | OAuth 2.0 客户端（独立服务） |  | 2001 |

### 用户名与密码

| 账户名 | 密码 | 启用 |
| ---- | ---- | ---- |
| xuxiaowei | 123 | 1 |

### 客户ID与秘钥

| 字段 | client_id | client_secret | resource_ids | authorized_grant_types | authorities | access_token_validity | refresh_token_validity | scope | web_server_redirect_uri | autoapprove |
| ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- | ---- |
| 说明 | 客户 | 秘钥 | 资源 | 授权类型 | 权限 | Token有效时间 | 刷新Token有限时间 | 范围 | 重定向 | 自动授权范围 |
|  | xuxiaowei_client_id | xuxiaowei_client_secret  |  | authorization_code,refresh_token,client_credentials |  |  |  | snsapi_base,snsapi_userinfo | http://127.0.0.1:123 | true |

- authorized_grant_types
    - 默认值：authorization_code,refresh_token
        ```
        org.springframework.security.oauth2.provider.client.BaseClientDetails#BaseClientDetails(String, String, String, String, String, String) 
        ```
    - 授权码模式 authorization_code
    - 刷新Token权限 refresh_token
    - 客户端凭证模式 client_credentials
    - 密码模式 password
- access_token_validity
    - 默认值：43200秒，即12小时，参见：
        ```
        org.springframework.security.oauth2.provider.token.DefaultTokenServices#accessTokenValiditySeconds
        ```
- refresh_token_validity
    - 默认值：2592000秒，即30天，参见：
        ```
        org.springframework.security.oauth2.provider.token.DefaultTokenServices#refreshTokenValiditySeconds
        ```
- autoapprove
    - true
        - 全部自动授权
    - 填写scope
        - 指定范围自动授权

### [Linux 脚本说明](./sh)

### [Windows 脚本说明](./winsw)

### [常用 exe 说明](./exe)

### [Docker 说明](./docker)

### [SQL](./sql)

## 仓库与分支？

1. 使用了哪四个仓库？
    1. [Gitee](https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud)
    1. [GitCode](https://gitcode.net/xuxiaowei-cloud/xuxiaowei-cloud)
    1. [GitHub](https://github.com/xuxiaowei-cloud/xuxiaowei-cloud)
    1. [GitLab](https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud)

1. 上述四个仓库以哪个为准？其余仓库合适同步？
    1. 以 [Gitee](https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud) 为准
    1. 其余仓库每日同步

1. 各个仓库的作用？
    1. [Gitee](https://gitee.com/xuxiaowei-cloud/xuxiaowei-cloud) 供国内用户访问、提问、下载资源
    1. [GitCode](https://gitcode.net/xuxiaowei-cloud/xuxiaowei-cloud)
       用于在将来替换 [GitLab](https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud)
       的 [CICD 流水线](https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud/-/pipelines) ，
       [GitCode](https://gitcode.net/xuxiaowei-cloud/xuxiaowei-cloud) 流水线配额 `1000` 分钟（已申请到内侧资格）
    1. [GitHub](https://github.com/xuxiaowei-cloud/xuxiaowei-cloud)
       使用 [CodeQL](https://github.com/xuxiaowei-cloud/xuxiaowei-cloud/actions/workflows/codeql-analysis.yml)
       每日扫描项目中的 `main` 分支是否存在漏洞
    1. [GitLab](https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud) 使用 GitLab
       的 [CICD 流水线](https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud/-/pipelines) 自动构建资源（`.jar`、`.jar.asc`
       、`.pom`、`.pom.asc`、`-javadoc.jar`、`-javadoc.jar.asc`、`-sources.jar`、`-sources.jar.asc`），
       自动将资源发布（目前仅[打包](https://gitlab.com/xuxiaowei-cloud/xuxiaowei-cloud/-/blob/main/.gitlab-ci.yml)，未发布）到
       [中央仓库](https://s01.oss.sonatype.org/content/repositories/releases/cloud/xuxiaowei)

1. 各分支的作用？
    1. main
        - 主分支
        - 不可推送，只能合并
    1. xuxiaowei
        - 个人分支，按功能`PR`到主分支`main`
    1. pages
        - 文档分支

## 参考文档

1. [alibaba p3c](https://github.com/alibaba/p3c)
    - [GitCode 镜像仓库](https://gitcode.net/mirrors/alibaba/p3c)
    - [Gitee 镜像仓库](https://gitee.com/mirrors/P3C)
1. [十二要素应用宣言](https://12factor.net/zh_cn/)

## 开发工具

1. IDEA 2021.1.3
1. Alibaba dragonwell 8.10.11
1. Apache Maven 3.6.3
1. node v16.14.0
1. npm 8.3.1
1. @vue/cli-plugin-eslint 5.0.3 Standard、eslint 7.32.0

## 鸣谢

1. 感谢 [![墨菲安全](./static/murphysec.png)](https://www.murphysec.com/) 提供安全技术支持，邀请码：[http://xxw.ac.cn/murphysec](http://xxw.ac.cn/murphysec)

## Stargazers over time

[![Stargazers over time](https://starchart.cc/xuxiaowei-cloud/xuxiaowei-cloud.svg)](https://starchart.cc/xuxiaowei-cloud/xuxiaowei-cloud)
