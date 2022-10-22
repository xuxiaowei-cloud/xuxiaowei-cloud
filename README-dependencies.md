# dependencies

```
cloud.xuxiaowei:xuxiaowei-cloud-starter-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei:admin-server:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:3.1.4:compile
│        └──de.codecentric:spring-boot-admin-starter-server:jar:2.7.6:compile


cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT
│        └──cn.hutool:hutool-all:jar:5.8.8:provided
│        └──com.google.guava:guava:jar:31.1-jre:provided
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.4.2:provided
│        └──com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.4:provided
│        └──io.projectreactor:reactor-core:jar:3.4.24:provided
│        └──org.springframework.security:spring-security-core:jar:5.7.4:provided
│        └──org.springframework.security:spring-security-web:jar:5.7.4:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.68:provided
│        └──org.springframework.security:spring-security-oauth2-jose:jar:5.7.4:provided
│        └──ch.qos.logback:logback-classic:jar:1.2.11:provided
│        └──org.springframework:spring-web:jar:5.3.23:provided
│        └──commons-io:commons-io:jar:2.11.0:provided
│        └──org.dom4j:dom4j:jar:2.1.3:provided
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei:cloud-commons-parent:pom:0.0.1-SNAPSHOT
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT
│        └──cn.hutool:hutool-all:jar:5.8.8:provided
│        └──org.springframework.cloud:spring-cloud-context:jar:3.1.4:provided
│        └──org.springframework:spring-web:jar:5.3.23:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.68:provided
│        └──org.springframework.session:spring-session-core:jar:2.7.0:provided
│        └──org.springframework.security:spring-security-web:jar:5.7.4:provided
│        └──org.springframework.security:spring-security-config:jar:5.7.4:provided
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-data-redis:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-idempotent:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework:spring-web:jar:5.3.23:compile
│        └──org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT
│        └──org.mybatis.spring.boot:mybatis-spring-boot-starter:jar:2.2.2:compile
│        └──com.baomidou:mybatis-plus-boot-starter:jar:3.5.2:compile
│        └──com.baomidou:dynamic-datasource-spring-boot-starter:jar:3.5.2:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──com.baomidou:mybatis-plus-generator:jar:3.5.3:test
│        └──org.springframework.boot:spring-boot-starter-velocity:jar:1.4.7.RELEASE:test
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──com.mysql:mysql-connector-j:jar:8.0.31:runtime
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──com.baomidou:mybatis-plus-extension:jar:3.5.2:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-openfeign:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:compile
│        └──org.springframework.cloud:spring-cloud-starter-openfeign:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.session:spring-session-data-redis:jar:2.7.0:compile
│        └──org.springframework.security:spring-security-web:jar:5.7.4:compile
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.68:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT
│        └──com.google.guava:guava:jar:31.1-jre:compile
│        └──org.springframework.boot:spring-boot-starter-validation:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:provided
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.4.2:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cn.hutool:hutool-all:jar:5.8.8:compile
│        └──commons-io:commons-io:jar:2.11.0:compile
│        └──com.aliyun:dysmsapi20170525:jar:2.0.22:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.7.5:provided
│        └──com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.4:provided
│        └──com.fasterxml.jackson.core:jackson-annotations:jar:2.13.4:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.68:provided
│        └──org.springframework.security:spring-security-core:jar:5.7.4:provided
│        └──org.springframework:spring-webmvc:jar:5.3.23:compile
│        └──org.springframework.boot:spring-boot-starter-aop:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-mail:jar:2.7.5:compile
│        └──org.springframework.security:spring-security-oauth2-jose:jar:5.7.4:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile


cloud.xuxiaowei.example:cxf-client:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.cxf:cxf-rt-frontend-jaxws:jar:3.5.3:compile
│        └──org.apache.cxf:cxf-rt-transports-http:jar:3.5.3:compile
│        └──org.apache.cxf:cxf-rt-features-logging:jar:3.5.3:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.4:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei.example:oauth2-client:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.4:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.4:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei.example:example-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei:gateway:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cn.hutool:hutool-all:jar:5.8.8:compile
│        └──org.springframework.security:spring-security-oauth2-authorization-server:jar:0.4.0-M2:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.7.5:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-alibaba-sentinel-gateway:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──org.springframework.cloud:spring-cloud-starter-gateway:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT


cloud.xuxiaowei:passport:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.7.5:compile
│        └──org.springframework.security:spring-security-oauth2-authorization-server:jar:0.4.0-M2:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-wechat-miniprogram:jar:0.1.0-SNAPSHOT:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-wechat-offiaccount:jar:0.1.0-SNAPSHOT:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-wechat-oplatform:jar:0.1.0-SNAPSHOT:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-gitee:jar:0.1.0-SNAPSHOT:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-qq-connect:jar:0.1.0-SNAPSHOT:compile
│        └──cn.com.xuxiaowei.boot:spring-boot-starter-weibo:jar:0.1.0-SNAPSHOT:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──cn.hutool:hutool-all:jar:5.8.8:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:resource-services-parent:pom:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:canal:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.otter:canal.client:jar:1.1.6:compile
│        └──com.alibaba.otter:canal.protocol:jar:1.1.6:compile
│        └──com.mysql:mysql-connector-j:jar:8.0.31:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:master-data:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:user:jar:0.0.1-SNAPSHOT
│        └──com.github.bingoohuang:patchca:jar:0.0.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:webservice:jar:0.0.1-SNAPSHOT
│        └──org.apache.cxf:cxf-rt-frontend-jaxws:jar:3.5.4:compile
│        └──org.apache.cxf:cxf-rt-transports-http:jar:3.5.4:compile
│        └──org.apache.cxf:cxf-rt-features-logging:jar:3.5.4:compile
│        └──com.fasterxml.jackson.dataformat:jackson-dataformat-xml:jar:2.13.4:compile
│        └──org.dom4j:dom4j:jar:2.1.3:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:websocket:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-websocket:jar:2.7.5:compile
│        └──com.alibaba:fastjson:jar:2.0.15:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:wechat-miniprogram:jar:0.0.1-SNAPSHOT
│        └──com.github.binarywang:wx-java-miniapp-spring-boot-starter:jar:4.4.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:wechat-offiaccount:jar:0.0.1-SNAPSHOT
│        └──com.github.binarywang:wx-java-mp-spring-boot-starter:jar:4.4.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-sentinel:jar:2.2.9.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.commons:commons-lang3:jar:3.12.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.5:test


cloud.xuxiaowei:ui:jar:0.0.1-SNAPSHOT


cloud.xuxiaowei:xxl-job-admin:jar:0.0.1-SNAPSHOT
│        └──com.xuxueli:xxl-job-core:jar:2.3.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2.2.9.RELEASE:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2.2.9.RELEASE:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.4:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-freemarker:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-mail:jar:2.7.5:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.5:compile
│        └──org.mybatis.spring.boot:mybatis-spring-boot-starter:jar:2.2.2:compile
│        └──com.mysql:mysql-connector-j:jar:8.0.31:runtime
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.5:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
```
