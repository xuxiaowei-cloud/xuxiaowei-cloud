import request from '../utils/request'

/**
 * 分页查询租户-登录页面
 * @param data
 */
export const pageLogin = function (data: any) {
  return request.get('/passport/tenant/page/login', data).then(response => {
    return response.data
  })
}
