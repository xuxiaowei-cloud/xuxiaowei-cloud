import request from '../../utils/request'

/**
 * 分页查询授权码
 * @param data
 */
export const page = function (data: any) {
  return request.post('/audit/oauth-code/page', data).then(response => {
    return response.data
  })
}
