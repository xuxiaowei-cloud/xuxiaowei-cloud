import request from '@/utils/request'

/**
 * 分页查询授权码
 * @param data
 * @returns {*}
 */
export const page = function (data) {
  return request.post('/audit/oauth-code/page', data).then(response => {
    return response.data
  })
}
