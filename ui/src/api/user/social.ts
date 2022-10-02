import request from '../../utils/request'

/**
 * 获取社交绑定
 */
export const social = function () {
  return request.post('/user/social').then(response => {
    return response.data
  })
}

/**
 * 社交解绑
 */
export const socialUnbinding = function (socialCode: string) {
  return request.post('/user/social/unbinding', { socialCode }).then(response => {
    return response.data
  })
}
