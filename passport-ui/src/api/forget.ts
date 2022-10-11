import request from '../utils/request'

/**
 * 忘记密码
 * @param header
 * @param token
 * @param data
 */
export const forget = function (header: String, token: String, data: any) {
  const headers = {}
  // @ts-ignore
  headers[header] = token
  return request.post('/passport/forget', data, {
    headers
  }).then(response => {
    return response.data
  })
}

/**
 * 检查重置密码凭证
 * @param header
 * @param token
 * @param data
 */
export const checkResetPasswordToken = function (header: String, token: String, data: any) {
  const headers = {}
  // @ts-ignore
  headers[header] = token
  return request.post('/passport/forget/check-reset-password-token', data, {
    headers
  }).then(response => {
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
  return request.post('/passport/forget/reset-password', data, {
    headers
  }).then(response => {
    return response.data
  })
}

/**
 * 重置密码（手机验证码）
 * @param header
 * @param token
 * @param data
 */
export const resetTypePhonePassword = function (header: String, token: String, data: any) {
  const headers = {}
  // @ts-ignore
  headers[header] = token
  return request.post('/passport/forget/reset-type-phone-password', data, {
    headers
  }).then(response => {
    return response.data
  })
}
