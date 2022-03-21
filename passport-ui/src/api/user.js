import request from '@/utils/request'

/**
 * 登录
 * @param username 用户名
 * @param password 密码
 * @param rememberMe 记住我
 * @returns {Promise<AxiosResponse<any>>}
 */
export const login = function (username, password, rememberMe) {
  // 以 form 提交
  const formData = new FormData()
  formData.append('username', username)
  formData.append('password', password)
  formData.append('remember-me', rememberMe)
  return request.post('/login', formData).then(response => {
    return response.data
  })
}
