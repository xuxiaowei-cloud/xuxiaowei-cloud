import request from '../utils/request'

/**
 * actuator
 */
export let actuator = function () {
  return request.get('/actuator/health', {
    baseURL: null // Ϊ�գ���ʹ�ô�����Ϊ�գ�����ʹ�ô���
  }).then(response => {
    return response.data
  })
}

/**
 * baidu
 */
export let baidu = function () {
  return request.get('/baidu', {
    baseURL: null, // Ϊ�գ���ʹ�ô�����Ϊ�գ�����ʹ�ô���
    withCredentials: false
  }).then(response => {
    return response.data
  })
}

export default {
  actuator,
  baidu
}
