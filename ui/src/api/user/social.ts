import request from '../../utils/request'

/**
 * 获取社交绑定
 */
export const social = function () {
  return request.post('/user/social').then(response => {
    return response.data
  })
}
