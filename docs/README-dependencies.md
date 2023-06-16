# dependencies

- 更新日期：2023-06-16

```
cloud.xuxiaowei:xuxiaowei-cloud-starter-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei:admin-server:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──de.codecentric:spring-boot-admin-starter-server:jar:2.7.10:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile


cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT
│        └──cn.hutool:hutool-all:jar:5.8.19:provided
│        └──com.google.guava:guava:jar:32.0.1-jre:provided
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.5:provided
│        └──com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.5:provided
│        └──io.projectreactor:reactor-core:jar:3.4.29:provided
│        └──org.springframework.security:spring-security-core:jar:5.7.8:provided
│        └──org.springframework.security:spring-security-web:jar:5.7.8:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.75:provided
│        └──org.springframework.security:spring-security-oauth2-jose:jar:5.7.8:provided
│        └──ch.qos.logback:logback-classic:jar:1.2.12:provided
│        └──org.springframework:spring-web:jar:5.3.27:provided
│        └──commons-io:commons-io:jar:2.13.0:provided
│        └──org.apache.commons:commons-text:jar:1.10.0:provided
│        └──org.dom4j:dom4j:jar:2.1.4:provided
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.gitlab4j:gitlab4j-api:jar:5.2.0:test
│        └──org.kohsuke:github-api:jar:1.315:test


cloud.xuxiaowei:cloud-commons-parent:pom:0.0.1-SNAPSHOT
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT
│        └──org.springframework.cloud:spring-cloud-context:jar:3.1.6:provided
│        └──org.springframework:spring-web:jar:5.3.27:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.75:provided
│        └──org.springframework.session:spring-session-core:jar:2.7.1:provided
│        └──org.springframework.security:spring-security-web:jar:5.7.8:provided
│        └──org.springframework.security:spring-security-config:jar:5.7.8:provided
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-data-redis:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-idempotent:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework:spring-web:jar:5.3.27:compile
│        └──org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.mybatis.spring.boot:mybatis-spring-boot-starter:jar:2.3.1:compile
│        └──com.baomidou:mybatis-plus-boot-starter:jar:3.5.3.1:compile
│        └──com.baomidou:dynamic-datasource-spring-boot-starter:jar:3.6.1:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──com.baomidou:mybatis-plus-generator:jar:3.5.3.1:test
│        └──org.springframework.boot:spring-boot-starter-velocity:jar:1.4.7.RELEASE:test
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──com.mysql:mysql-connector-j:jar:8.0.33:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──com.baomidou:mybatis-plus-extension:jar:3.5.3.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.security:spring-security-test:jar:5.7.8:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-openfeign:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──org.springframework.cloud:spring-cloud-starter-openfeign:jar:3.1.7:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.session:spring-session-data-redis:jar:2.7.1:compile
│        └──org.springframework.security:spring-security-web:jar:5.7.8:compile
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.75:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT
│        └──com.google.guava:guava:jar:32.0.1-jre:compile
│        └──org.springframework.boot:spring-boot-starter-validation:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:provided
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.5:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──commons-io:commons-io:jar:2.13.0:compile
│        └──com.aliyun:dysmsapi20170525:jar:2.0.23:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.7.12:provided
│        └──com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.5:provided
│        └──com.fasterxml.jackson.core:jackson-annotations:jar:2.13.5:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.75:provided
│        └──org.springframework.security:spring-security-core:jar:5.7.8:provided
│        └──org.springframework:spring-webmvc:jar:5.3.27:compile
│        └──org.springframework.boot:spring-boot-starter-aop:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-mail:jar:2.7.12:compile
│        └──org.springframework.security:spring-security-oauth2-jose:jar:5.7.8:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──cn.hutool:hutool-all:jar:5.8.19:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile


cloud.xuxiaowei.example:cxf-client:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.cxf:cxf-rt-frontend-jaxws:jar:3.5.6:compile
│        └──org.apache.cxf:cxf-rt-transports-http:jar:3.5.6:compile
│        └──org.apache.cxf:cxf-rt-features-logging:jar:3.5.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile


cloud.xuxiaowei.example:oauth2-client:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──com.nimbusds:nimbus-jose-jwt:jar:9.31:compile
│        └──org.springframework.security:spring-security-oauth2-jose:jar:5.7.8:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile


cloud.xuxiaowei.example:example-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei:gateway:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-webflux-ui:jar:1.7.0:compile
│        └──org.springframework.security:spring-security-oauth2-authorization-server:jar:0.4.2:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.7.12:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-alibaba-sentinel-gateway:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──org.springframework.cloud:spring-cloud-starter-gateway:jar:3.1.7:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile


cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT


cloud.xuxiaowei:passport:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.7.12:compile
│        └──org.springframework.security:spring-security-oauth2-authorization-server:jar:0.4.2:compile
│        └──cn.com.xuxiaowei.boot:spring-security-oauth2-authorization-server-redis:jar:0.1.0-SNAPSHOT:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-oauth2:jar:0.1.0-SNAPSHOT:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:resource-services-parent:pom:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:canal:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.otter:canal.client:jar:1.1.6:compile
│        └──com.alibaba.otter:canal.protocol:jar:1.1.6:compile
│        └──com.mysql:mysql-connector-j:jar:8.0.33:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:file:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:master-data:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:user:jar:0.0.1-SNAPSHOT
│        └──com.github.bingoohuang:patchca:jar:0.0.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:webservice:jar:0.0.1-SNAPSHOT
│        └──org.apache.cxf:cxf-rt-frontend-jaxws:jar:3.5.6:compile
│        └──org.apache.cxf:cxf-rt-transports-http:jar:3.5.6:compile
│        └──org.apache.cxf:cxf-rt-features-logging:jar:3.5.6:compile
│        └──com.fasterxml.jackson.dataformat:jackson-dataformat-xml:jar:2.13.5:compile
│        └──org.dom4j:dom4j:jar:2.1.4:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:websocket:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-websocket:jar:2.7.12:compile
│        └──com.alibaba:fastjson:jar:2.0.33:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:wechat-miniprogram:jar:0.0.1-SNAPSHOT
│        └──com.github.binarywang:wx-java-miniapp-spring-boot-starter:jar:4.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:wechat-offiaccount:jar:0.0.1-SNAPSHOT
│        └──com.github.binarywang:wx-java-mp-spring-boot-starter:jar:4.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2021.0.5.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.springdoc:springdoc-openapi-ui:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-javadoc:jar:1.7.0:compile
│        └──org.springdoc:springdoc-openapi-security:jar:1.7.0:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.12:test


cloud.xuxiaowei:ui:jar:0.0.1-SNAPSHOT


cloud.xuxiaowei:xxl-job-admin:jar:0.0.1-SNAPSHOT
│        └──com.xuxueli:xxl-job-core:jar:2.4.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.0.5.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.0.5.0:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.6:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-freemarker:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-mail:jar:2.7.12:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.12:compile
│        └──org.mybatis.spring.boot:mybatis-spring-boot-starter:jar:2.3.1:compile
│        └──com.mysql:mysql-connector-j:jar:8.0.33:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.12:compile
│        └──org.projectlombok:lombok:jar:1.18.26:compile
```
