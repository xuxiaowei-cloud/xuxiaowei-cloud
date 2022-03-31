import { ElMessage } from 'element-plus'

import request from '@/utils/request'
import store from '@/store/index'

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

/**
 * 检查 Token
 * @returns {Promise<AxiosResponse<any>>}
 */
export const checkToken = function () {
  const checkTokenUri = store.getters.checkTokenUri
  const accessToken = store.getters.accessToken
  if (checkTokenUri != null && accessToken != null) {
    request.post(checkTokenUri + accessToken).then(response => {
      const data = response.data
      const code = data.code
      if (code === store.state.settings.okCode) {
        ElMessage({ message: '已成功授权', type: 'success' })
      } else {
        const msg = data.msg
        const clearVuex = data.data == null ? null: data.data.clearVuex
        ElMessage.error(msg + '，是否需要清空vuex：' + clearVuex)
      }
      return response
    })
  }
}
