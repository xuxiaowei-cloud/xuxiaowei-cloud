# gateway

网关

## 过滤器

按照优先级从高到底排序

| 过滤器                         | 优先级                        | 作用                               |
|-----------------------------|----------------------------|----------------------------------|
| LogGlobalFilter             | Ordered.HIGHEST_PRECEDENCE | 将用户IP等信息放入MDC                    |
| ActuatorGlobalFilter        | + 10000                    | 禁止通过网关访问 服务的端点                   |
| AdminServerGlobalFilter     | + 20000                    | 禁止通过网关访问 监控（管理）服务                |
| BlackListGlobalFilter       | + 30000                    | 指定 IP、URL、用户、客户、域名（非授权域名解析）等禁止访问 |
| HeaderGlobalFilter          | + 40000                    | 将请求ID传递给服务                       |
| XxlJobGlobalFilter          | + 50000                    | 禁止通过网关访问 XXL-Job 服务              |
| CorsBeforeWebFilter         | + 60000                    | 在 CORS 之前执行                      |
| ResponseHeadersGlobalFilter | + 1000000                  | 响应体 {@link HttpHeaders} 过滤器      |
| BodyEncryptionGlobalFilter  | + 1010000                  | 响应 Body 加密 过滤器                   |
| BodyDecryptGlobalFilter     | + 1020000                  | 请求体 Body 解密 过滤器                  |
| CurrentTimeMillisWebFilter  | + 1030000                  | 时间戳 过滤器                          |
