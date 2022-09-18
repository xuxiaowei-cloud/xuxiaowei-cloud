import request from '../utils/request'

/**
 * 登录
 * @param username 用户名
 * @param password 密码
 * @param rememberMe 记住我
 * @param header CSRF Token Name
 * @param token CSRF Token Value
 * @param rememberMeParameter 记住我参数名
 * @param redirectUri 授权重定向地址
 * @param homePage 登录成功主页
 */
export const login = function (username: string, password: string, rememberMe: string, header: string, token: string,
  rememberMeParameter: string, redirectUri: string, homePage: string) {
  // 以 form 提交
  const formData = new FormData()
  formData.append('username', username)
  formData.append('password', password)
  formData.append(rememberMeParameter, rememberMe)
  formData.append('redirectUri', redirectUri)
  formData.append('homePage', homePage)
  const headers = {}
  // @ts-ignore
  headers[header] = token
  return request.post('/passport/login', formData, {
    headers
  }).then(response => {
    return response.data
  })
}

/**
 * 忘记密码
 * @param data
 */
export const forget = function (data: any) {
  return request.post('/user/forget', data).then(response => {
    return response.data
  })
}

/**
 * 检查重置密码凭证
 * @param data
 */
export const checkResetPasswordToken = function (data: any) {
  return request.post('/user/check-reset-password-token', data).then(response => {
    return response.data
  })
}

/**
 * 重置密码
 * @param header
 * @param token
 * @param data
 */
export const resetPassword = function (header: String, token: String, data: any) {
  const headers = {}
  // @ts-ignore
  headers[header] = token
  return request.post('/user/reset-password', data, {
    headers
  }).then(response => {
    return response.data
  })
}
