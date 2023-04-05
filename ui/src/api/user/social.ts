import request from '../../utils/request'
import Resp from '../common'

/**
 * 获取社交绑定
 */
export const social = function () {
  return request.post('/user/social').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 社交解绑
 */
export const socialUnbinding = function (socialCode: string) {
  return request.post('/user/social/unbinding', { socialCode }).then(response => {
    return response.data as Resp<any>
  })
}
