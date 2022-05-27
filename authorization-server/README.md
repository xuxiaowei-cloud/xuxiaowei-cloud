# authorization-server

授权服务

## 文档

- [Authorization Server](https://docs.spring.io/spring-boot/docs/2.7.x/reference/htmlsingle/#web.security.oauth2.authorization-server)
    - 目前，Spring Security 不支持实现 OAuth 2.0 授权服务器。
    - 但是，此功能可从 [Spring Security OAuth](https://spring.io/projects/spring-security-oauth) 项目中获得，该项目最终将被 Spring Security
      完全取代。
    - 在此之前，您可以使用该 `spring-security-oauth2-autoconfigure` 模块轻松设置 OAuth 2.0
      授权服务器；有关说明，请参阅其 [文档](https://docs.spring.io/spring-security-oauth2-boot/)。
- [spring-security-oauth2-boot 2.6.x](https://docs.spring.io/spring-security-oauth2-boot/docs/2.6.x/reference/html5/)
    - OAuth2 配置

## 页面

- 使用了`CDN`引入`element-plus`、`vue`进行网页编写自定义授权、自定义异常页面（由于页面是使用频率低，涉及内容有限，无需使用 node 编写并打包）
