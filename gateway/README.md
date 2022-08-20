# gateway

网关

## 过滤器

- `WebFilter`过滤器的优先级比`GlobalFilter`过滤器高

### WebFilter 过滤器

按照优先级：从高到底排序

| 过滤器                               | 优先级                        | 作用                               |
|-----------------------------------|----------------------------|----------------------------------|
| LogWebFilter                      | Ordered.HIGHEST_PRECEDENCE | 将用户IP等信息放入MDC                    |
| ActuatorWebFilter                 | + 10000                    | 禁止通过网关访问 服务的端点                   |
| AdminServerWebFilter              | + 20000                    | 禁止通过网关访问 监控（管理）服务                |
| BlackListWebFilter                | + 30000                    | 指定 IP、URL、用户、客户、域名（非授权域名解析）等禁止访问 |
| HeaderWebFilter                   | + 40000                    | 将请求ID传递给服务                       |
| XxlJobWebFilter                   | + 50000                    | 禁止通过网关访问 XXL-Job 服务              |
| CorsBeforeWebFilter               | + 60000                    | 在 CORS 之前执行                      |
| CurrentTimeMillisWebFilter        | + 70000                    | 时间戳 过滤器                          |
| RequestBodyDecryptBeforeWebFilter | + 80000                    | 请求体 Body 解密之前 过滤器                |
| RequestBodyDecryptWebFilter       | + 90000                    | 请求体 Body 解密 过滤器                  |

### GlobalFilter 过滤器

按照优先级：从高到底排序

| 过滤器                                 | 优先级                          | 作用                          |
|-------------------------------------|------------------------------|-----------------------------|
| ResponseHeadersGlobalFilter         | + Ordered.HIGHEST_PRECEDENCE | 响应体 {@link HttpHeaders} 过滤器 |
| ResponseBodyEncryptionGlobalFilter  | + 10000                      | 响应 Body 加密 过滤器              |
