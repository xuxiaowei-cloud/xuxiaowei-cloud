# dependencies

```
cloud.xuxiaowei:xuxiaowei-cloud-starter-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT
│        └──com.google.guava:guava:jar:31.1-jre:provided
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.3:provided
│        └──com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.3:provided
│        └──io.projectreactor:reactor-core:jar:3.4.18:provided
│        └──org.springframework.security:spring-security-core:jar:5.7.1:provided
│        └──org.springframework.security:spring-security-web:jar:5.7.1:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.63:provided
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.2.RELEASE:provided
│        └──ch.qos.logback:logback-classic:jar:1.2.11:provided
│        └──commons-io:commons-io:jar:2.11.0:provided
│        └──org.dom4j:dom4j:jar:2.1.3:provided
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei:cloud-commons-parent:pom:0.0.1-SNAPSHOT
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT
│        └──com.fasterxml.jackson.core:jackson-core:jar:2.13.3:compile
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.3:provided
│        └──com.fasterxml.jackson.core:jackson-annotations:jar:2.13.3:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.63:provided
│        └──org.springframework.session:spring-session-core:jar:2.7.0:provided
│        └──org.springframework.security:spring-security-web:jar:5.7.1:provided
│        └──org.springframework.security:spring-security-config:jar:5.7.1:provided
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT
│        └──org.springframework:spring-web:jar:5.3.20:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT
│        └──org.mybatis.spring.boot:mybatis-spring-boot-starter:jar:2.2.2:compile
│        └──com.baomidou:mybatis-plus-boot-starter:jar:3.5.1:compile
│        └──com.baomidou:dynamic-datasource-spring-boot-starter:jar:3.5.1:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──com.baomidou:mybatis-plus-generator:jar:3.5.2:test
│        └──org.springframework.boot:spring-boot-starter-velocity:jar:1.4.7.RELEASE:test
│        └──mysql:mysql-connector-java:jar:8.0.29:runtime
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──com.baomidou:mybatis-plus-extension:jar:3.5.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei:gateway:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.7.0:compile
│        └──org.springframework.cloud:spring-cloud-starter-gateway:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei:admin-server:jar:0.0.1-SNAPSHOT
│        └──de.codecentric:spring-boot-admin-starter-server:jar:2.6.7:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-data-redis:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.session:spring-session-data-redis:jar:2.7.0:compile
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.63:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:jar:2.6.8:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.7.0:provided
│        └──com.fasterxml.jackson.core:jackson-annotations:jar:2.13.3:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.63:provided
│        └──org.springframework.security:spring-security-core:jar:5.7.1:provided
│        └──org.springframework.boot:spring-boot-starter-validation:jar:2.7.0:compile
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.2.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-aop:jar:2.7.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT
│        └──com.google.guava:guava:jar:31.1-jre:compile
│        └──org.springframework.boot:spring-boot-starter-validation:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:provided
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.2.RELEASE:compile
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.3:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei:authorization-server:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:jar:2.6.8:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──org.thymeleaf.extras:thymeleaf-extras-springsecurity5:jar:3.0.4.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──mysql:mysql-connector-java:jar:8.0.29:runtime
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT


cloud.xuxiaowei:passport:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-security:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cn.hutool:hutool-all:jar:5.8.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──org.thymeleaf.extras:thymeleaf-extras-springsecurity5:jar:3.0.4.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT:compile
│        └──mysql:mysql-connector-java:jar:8.0.29:runtime
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:ui:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile


cloud.xuxiaowei.commons:cloud-starter-openfeign:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──org.springframework.cloud:spring-cloud-starter-openfeign:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei:resource-services-parent:pom:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:audit:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-openfeign:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.cloud:spring-cloud-starter-circuitbreaker-resilience4j:jar:2.1.2:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:canal:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.otter:canal.client:jar:1.1.6:compile
│        └──com.alibaba.otter:canal.protocol:jar:1.1.6:compile
│        └──mysql:mysql-connector-java:jar:8.0.29:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:user:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:webservice:jar:0.0.1-SNAPSHOT
│        └──org.apache.cxf:cxf-rt-frontend-jaxws:jar:3.5.2:compile
│        └──org.apache.cxf:cxf-rt-transports-http:jar:3.5.2:compile
│        └──org.apache.cxf:cxf-rt-features-logging:jar:3.5.2:compile
│        └──com.fasterxml.jackson.dataformat:jackson-dataformat-xml:jar:2.13.3:compile
│        └──org.dom4j:dom4j:jar:2.1.3:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:websocket:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-websocket:jar:2.7.0:compile
│        └──com.alibaba:fastjson:jar:2.0.4:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei:wechat-applet:jar:0.0.1-SNAPSHOT
│        └──com.github.binarywang:wx-java-miniapp-spring-boot-starter:jar:4.3.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-oauth2:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test


cloud.xuxiaowei.commons:cloud-starter-idempotent:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.7.0:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.2:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.7.0:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile


cloud.xuxiaowei.example:cxf-client:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.cxf:cxf-rt-frontend-jaxws:jar:3.5.2:compile
│        └──org.apache.cxf:cxf-rt-transports-http:jar:3.5.2:compile
│        └──org.apache.cxf:cxf-rt-features-logging:jar:3.5.2:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei.example:oauth2-client:jar:0.0.1-SNAPSHOT
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.2.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.7.0:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.7.0:compile
│        └──org.projectlombok:lombok:jar:1.18.24:compile


cloud.xuxiaowei.example:example-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei.plugins:bos-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.24:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.8.5:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.6.4:compile


cloud.xuxiaowei.plugins:cos-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.24:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.8.5:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.6.4:compile


cloud.xuxiaowei.plugins:obs-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.24:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.8.5:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.6.4:compile


cloud.xuxiaowei.plugins:oss-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.24:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.8.5:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.6.4:compile


cloud.xuxiaowei.plugins:rar-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.24:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.8.5:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.6.4:compile
│        └──org.apache.maven:maven-archiver:jar:3.5.1:compile


cloud.xuxiaowei:plugins:pom:0.0.1-SNAPSHOT
```
