import request from '../../utils/request'

/**
 * 分页查询授权Token
 * @param data
 */
export const page = function (data: any) {
  return request.post('/audit/oauth-access-token/page', data).then(response => {
    return response.data
  })
}
