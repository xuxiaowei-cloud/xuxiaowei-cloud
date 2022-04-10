import request from '@/utils/request'

/**
 * 分页查询授权Token
 * @param data
 * @returns {*}
 */
export const page = function (data) {
  return request.post('/audit/oauth-access-token/page', data).then(response => {
    return response.data
  })
}
