import request from '@/utils/request'

/**
 * 登录
 * @param username 用户名
 * @param password 密码
 * @param rememberMe 记住我
 * @param header CSRF Token Name
 * @param token CSRF Token Value
 * @param rememberMeParameter 记住我参数名
 * @returns {*}
 */
export const login = function (username, password, rememberMe, header, token, rememberMeParameter) {
  // 以 form 提交
  const formData = new FormData()
  formData.append('username', username)
  formData.append('password', password)
  formData.append(rememberMeParameter, rememberMe)
  const headers = {}
  headers[header] = token
  return request.post('/login', formData, {
    headers: headers
  }).then(response => {
    return response.data
  })
}
