import axios from 'axios'
import store from '../store'

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
      config.headers = {};
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
    return response
  },
  error => {
    return error
  }
)

export default service
