import request from '../utils/request'

/**
 * actuator
 */
export let actuator = function () {
  return request.get('/actuator/health', {
    baseURL: null // 为空：可使用代理，不为空：不能使用代理
  }).then(response => {
    return response.data
  })
}

/**
 * baidu
 */
export let baidu = function () {
  return request.get('/baidu', {
    baseURL: null, // 为空：可使用代理，不为空：不能使用代理
    withCredentials: false
  }).then(response => {
    return response.data
  })
}

export default {
  actuator,
  baidu
}
