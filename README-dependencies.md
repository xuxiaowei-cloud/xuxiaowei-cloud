# dependencies

```
cloud.xuxiaowei:xuxiaowei-cloud-starter-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT
│        └──com.google.guava:guava:jar:31.1-jre:provided
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.2.2:provided
│        └──com.fasterxml.jackson.datatype:jackson-datatype-jsr310:jar:2.13.2:provided
│        └──io.projectreactor:reactor-core:jar:3.4.16:provided
│        └──org.springframework.security:spring-security-core:jar:5.6.2:provided
│        └──org.springframework.security:spring-security-web:jar:5.6.2:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.60:provided
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.0.RELEASE:provided
│        └──ch.qos.logback:logback-classic:jar:1.2.11:provided
│        └──org.projectlombok:lombok:jar:1.18.22:compile


cloud.xuxiaowei:cloud-commons-parent:pom:0.0.1-SNAPSHOT
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT
│        └──com.fasterxml.jackson.core:jackson-core:jar:2.13.2:compile
│        └──com.fasterxml.jackson.core:jackson-databind:jar:2.13.2.2:provided
│        └──com.fasterxml.jackson.core:jackson-annotations:jar:2.13.2:provided
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.60:provided
│        └──org.springframework.session:spring-session-core:jar:2.6.2:provided
│        └──org.springframework.security:spring-security-web:jar:5.6.2:provided
│        └──org.springframework.security:spring-security-config:jar:5.6.2:provided
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT
│        └──org.springframework:spring-web:jar:5.3.18:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.cloud:spring-cloud-starter-loadbalancer:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT
│        └──org.mybatis.spring.boot:mybatis-spring-boot-starter:jar:2.2.2:compile
│        └──com.baomidou:mybatis-plus-boot-starter:jar:3.5.1:compile
│        └──com.baomidou:dynamic-datasource-spring-boot-starter:jar:3.5.1:compile
│        └──p6spy:p6spy:jar:3.9.1:compile
│        └──com.baomidou:mybatis-plus-generator:jar:3.5.2:test
│        └──org.springframework.boot:spring-boot-starter-velocity:jar:1.4.7.RELEASE:test
│        └──mysql:mysql-connector-java:jar:8.0.28:runtime
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──com.baomidou:mybatis-plus-extension:jar:3.5.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei:gateway:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──commons-io:commons-io:jar:2.11.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.6.6:compile
│        └──org.springframework.cloud:spring-cloud-starter-gateway:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile
│        └──org.projectlombok:lombok:jar:1.18.22:compile


cloud.xuxiaowei:admin-server:jar:0.0.1-SNAPSHOT
│        └──de.codecentric:spring-boot-admin-starter-server:jar:2.6.3:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──commons-io:commons-io:jar:2.11.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-data-redis:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-json:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.session:spring-session-data-redis:jar:2.6.2:compile
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.60:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT
│        └──cloud.xuxiaowei.commons:cloud-starter-core:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-log:jar:0.0.1-SNAPSHOT:compile
│        └──org.apache.tomcat.embed:tomcat-embed-core:jar:9.0.60:provided
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT
│        └──com.google.guava:guava:jar:31.1-jre:compile
│        └──org.springframework.boot:spring-boot-starter-validation:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:provided
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.0.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei:authorization-server:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──commons-io:commons-io:jar:2.11.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.security.oauth.boot:spring-security-oauth2-autoconfigure:jar:2.6.6:compile
│        └──org.bouncycastle:bcpkix-jdk15on:jar:1.70:compile
│        └──org.springframework.boot:spring-boot-starter-security:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──org.thymeleaf.extras:thymeleaf-extras-springsecurity5:jar:3.0.4.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──mysql:mysql-connector-java:jar:8.0.28:runtime
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile
│        └──org.projectlombok:lombok:jar:1.18.22:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test


cloud.xuxiaowei:passport-ui:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:compile


cloud.xuxiaowei:passport:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-security:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──commons-io:commons-io:jar:2.11.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-loadbalancer:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-session-redis:jar:0.0.1-SNAPSHOT:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-system:jar:0.0.1-SNAPSHOT:compile
│        └──org.thymeleaf.extras:thymeleaf-extras-springsecurity5:jar:3.0.4.RELEASE:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-validation:jar:0.0.1-SNAPSHOT:compile
│        └──mysql:mysql-connector-java:jar:8.0.28:runtime
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile
│        └──org.projectlombok:lombok:jar:1.18.22:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test


cloud.xuxiaowei:resource-server:jar:0.0.1-SNAPSHOT
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-discovery:jar:2021.1:compile
│        └──commons-io:commons-io:jar:2.11.0:compile
│        └──com.alibaba.cloud:spring-cloud-starter-alibaba-nacos-config:jar:2021.1:compile
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei.commons:cloud-starter-mybatis:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-starter-data-redis:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-jdbc:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-oauth2-resource-server:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-security:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:compile
│        └──mysql:mysql-connector-java:jar:8.0.28:runtime
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile
│        └──org.projectlombok:lombok:jar:1.18.22:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test


cloud.xuxiaowei:ui:jar:0.0.1-SNAPSHOT
│        └──org.springframework.boot:spring-boot-starter-thymeleaf:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:compile


cloud.xuxiaowei.commons:cloud-starter-openfeign:jar:0.0.1-SNAPSHOT
│        └──org.springframework.cloud:spring-cloud-starter-openfeign:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-test:jar:2.6.6:test
│        └──org.springframework.cloud:spring-cloud-starter-bootstrap:jar:3.1.1:compile
│        └──org.springframework.boot:spring-boot-starter-actuator:jar:2.6.6:compile
│        └──cloud.xuxiaowei:utils:jar:0.0.1-SNAPSHOT:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile


cloud.xuxiaowei.example:oauth2-client:jar:0.0.1-SNAPSHOT
│        └──org.springframework.security.oauth:spring-security-oauth2:jar:2.5.0.RELEASE:compile
│        └──org.springframework.boot:spring-boot-starter-web:jar:2.6.6:compile
│        └──org.springframework.boot:spring-boot-configuration-processor:jar:2.6.6:compile
│        └──org.projectlombok:lombok:jar:1.18.22:compile


cloud.xuxiaowei.example:example-parent:pom:0.0.1-SNAPSHOT


cloud.xuxiaowei.plugins:bos-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.22:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.1.0:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.5.2:compile


cloud.xuxiaowei.plugins:cos-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.22:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.1.0:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.5.2:compile


cloud.xuxiaowei.plugins:obs-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.22:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.1.0:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.5.2:compile


cloud.xuxiaowei.plugins:oss-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.22:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.1.0:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.5.2:compile


cloud.xuxiaowei.plugins:rar-maven-plugin:maven-plugin:0.0.1-SNAPSHOT
│        └──ch.qos.logback:logback-classic:jar:1.2.11:compile
│        └──org.projectlombok:lombok:jar:1.18.22:provided
│        └──org.apache.maven:maven-plugin-api:jar:3.1.0:compile
│        └──org.apache.maven.plugin-tools:maven-plugin-annotations:jar:3.5.2:compile
│        └──org.apache.maven:maven-archiver:jar:3.1.0:compile


cloud.xuxiaowei:plugins:pom:0.0.1-SNAPSHOT
```
