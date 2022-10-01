import request from '../../utils/request'

/**
 * 获取安全设置
 */
export const security = function () {
  return request.post('/user/security').then(response => {
    return response.data
  })
}

/**
 * 发送修改手机号的短信验证码
 */
export const securityPhone = function (phone: string) {
  return request.post('/user/security/phone', { phone }).then(response => {
    return response.data
  })
}

/**
 * 修改手机号
 */
export const securityPhoneUpdate = function (phone: string, code: string, identification: string) {
  return request.post('/user/security/phone/update', { phone, code, identification }).then(response => {
    return response.data
  })
}

/**
 * 发送修改邮箱的验证码
 */
export const securityEmail = function (email: string) {
  return request.post('/user/security/email', { email }).then(response => {
    return response.data
  })
}

/**
 * 修改邮箱
 */
export const securityEmailUpdate = function (email: string, code: string, identification: string) {
  return request.post('/user/security/email/update', { email, code, identification }).then(response => {
    return response.data
  })
}
