import request from '../utils/request'
import Resp from './common'

/**
 * 登录
 * @param tenantId 租户ID
 * @param clientId 客户ID
 * @param username 用户名
 * @param password 密码
 * @param rememberMe 记住我
 * @param header CSRF Token Name
 * @param token CSRF Token Value
 * @param rememberMeParameter 记住我参数名
 * @param redirectUri 授权重定向地址
 * @param homePage 登录成功主页
 */
export const login = function (tenantId: string, clientId: string, username: string, password: string, rememberMe: string, header: string, token: string,
                               rememberMeParameter: string, redirectUri: string, homePage: string) {
  // 以 form 提交
  const formData = new FormData()
  formData.append('tenantId', tenantId)
  formData.append('clientId', clientId)
  formData.append('username', username)
  formData.append('password', password)
  formData.append(rememberMeParameter, rememberMe)
  formData.append('redirectUri', redirectUri)
  formData.append('homePage', homePage)
  const headers: Record<string, string> = {}
  headers[header] = token
  return request.post('/passport/login', formData, {
    headers
  }).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 配置
 */
export const configuration = function () {
  return request.post('/passport/configuration').then(response => {
    return response.data as Resp<any>
  })
}
