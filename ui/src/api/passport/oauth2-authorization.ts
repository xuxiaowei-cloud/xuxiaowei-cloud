import request from '../../utils/request'
import Resp from '../common'

/**
 * 分页查询授权
 * @param data
 */
export const page = function (data: any) {
  return request.post('/passport/oauth2-authorization/page', data).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 主键 删除
 * @param id 主键
 */
export const removeById = function (id: string) {
  return request.post('/passport/oauth2-authorization/removeById/' + id).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 主键 删除
 * @param ids 主键
 */
export const removeByIds = function (ids: string[]) {
  return request.post('/passport/oauth2-authorization/removeByIds', ids).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 客户ID 删除
 * @param ids 客户ID
 */
export const removeByRegisteredClientIds = function (ids: string[]) {
  return request.post('/passport/oauth2-authorization/removeByRegisteredClientIds', ids).then(response => {
    return response.data as Resp<any>
  })
}
