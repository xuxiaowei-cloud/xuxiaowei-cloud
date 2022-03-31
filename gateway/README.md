# gateway

网关

## 过滤器

按照优先级从高到底排序

| 过滤器 | 优先级 | 作用 |
| ---- | ---- | ---- |
| LogGlobalFilter | Ordered.HIGHEST_PRECEDENCE | 将用户IP等信息放入MDC |
| ActuatorGlobalFilter | + 10000 | 禁止通过网关访问 服务的端点 |
| AdminServerGlobalFilter | + 20000 | 禁止通过网关访问 监控（管理）服务 |
| BlackListGlobalFilter | + 30000 | 指定 IP、URL、用户、客户、域名（非授权域名解析）等禁止访问 |
| HeaderGlobalFilter | + 40000 | 将请求ID传递给服务 |
