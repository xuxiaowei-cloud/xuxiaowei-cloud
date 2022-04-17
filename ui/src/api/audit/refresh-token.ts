import request from '../../utils/request'

/**
 * 分页查询刷新Token
 * @param data
 */
export const page = function (data: any) {
  return request.post('/audit/oauth-refresh-token/page', data).then(response => {
    return response.data
  })
}

/**
 * 根据 刷新Token主键 删除
 * @param oauthRefreshTokenId 刷新Token主键
 */
export const removeById = function (oauthRefreshTokenId: number) {
  return request.post('/audit/oauth-refresh-token/removeById/' + oauthRefreshTokenId).then(response => {
    return response.data
  })
}
