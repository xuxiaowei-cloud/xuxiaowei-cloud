import request from '../utils/request'

/**
 * 检查 Token
 * @param token
 */
export const checkToken = function (token: String) {
  return request.post('/authorization-server/oauth/check_token?token=' + token).then(response => {
    return response.data
  })
}
