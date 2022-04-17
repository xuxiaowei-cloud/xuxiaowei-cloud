import axios from 'axios'
import store from '../store'
import settings from '../settings'
import { ElMessage } from 'element-plus'

// create an axios instance
const service = axios.create({
  baseURL: import.meta.env.VITE_APP_BASE_API, // BASE URL
  withCredentials: true, // 携带 Cookie
  timeout: 5000 // 请求超时
})

// request interceptor
service.interceptors.request.use(
  config => {
    if (!config.headers) {
      config.headers = {}
    }
    config.headers.authorization = 'Bearer ' + store.getters.accessToken
    return config
  },
  error => {
    return error
  }
)

// response interceptor
service.interceptors.response.use(
  response => {
    if (settings.state.loginRequiredCode.indexOf(response.data.code) == -1) {
      return response
    } else {
      ElMessage({
        message: response.data.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 3000,
        type: 'error',
        onClose: () => {
          location.href = settings.state.loginPage
        }
      })
    }
  },
  error => {
    return error
  }
)

export default service
