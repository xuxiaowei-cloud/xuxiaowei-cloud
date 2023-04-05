import request from '../utils/request'
import Resp from './common'

/**
 * 忘记密码
 * @param header
 * @param token
 * @param data
 */
export const forget = function (header: string, token: string, data: any) {
  const headers: Record<string, string> = {}
  headers[header] = token
  return request.post('/passport/forget', data, {
    headers
  }).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 检查重置密码凭证
 * @param header
 * @param token
 * @param data
 */
export const checkResetPasswordToken = function (header: string, token: string, data: any) {
  const headers: Record<string, string> = {}
  headers[header] = token
  return request.post('/passport/forget/check-reset-password-token', data, {
    headers
  }).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 重置密码
 * @param header
 * @param token
 * @param data
 */
export const resetPassword = function (header: string, token: string, data: any) {
  const headers: Record<string, string> = {}
  headers[header] = token
  return request.post('/passport/forget/reset-password', data, {
    headers
  }).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 重置密码（手机验证码）
 * @param header
 * @param token
 * @param data
 */
export const resetTypePhonePassword = function (header: string, token: string, data: any) {
  const headers: Record<string, string> = {}
  headers[header] = token
  return request.post('/passport/forget/reset-type-phone-password', data, {
    headers
  }).then(response => {
    return response.data as Resp<any>
  })
}
