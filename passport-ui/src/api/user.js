import request from '@/utils/request'
import store from '@/store/index'
import { ElMessage } from 'element-plus'

/**
 * 登录
 * @param username 用户名
 * @param password 密码
 * @param rememberMe 记住我
 * @returns {*}
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

/**
 * 登录成功主页
 * @returns {*}
 */
export const homePage = function () {
  return request.get("/login/home-page").then(response => {
    return response.data
  })
}

/**
 * 检查 Token
 * @returns {Promise<AxiosResponse<any>>}
 */
export const checkToken = function () {
  const checkTokenUri = store.getters.checkTokenUri
  const accessToken = store.getters.accessToken
  if (checkTokenUri != null && accessToken != null) {
    request.post(checkTokenUri + accessToken).then(response => {
      if (response.data.active === true) {
        ElMessage({ message: '已成功授权', type: 'success' })
        request.get("/login/home-page").then(response => {
          location.href=response.data.data
        })
      }
    })
  }
}
