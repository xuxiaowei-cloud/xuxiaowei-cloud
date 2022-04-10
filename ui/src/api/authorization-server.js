import request from '@/utils/request'

/**
 * 检查 Token
 * @returns {Promise<AxiosResponse<any>>}
 */
export const checkToken = function (token) {
  return request.post('/authorization-server/oauth/check_token?token=' + token).then(response => {
    return response.data
  })
}
