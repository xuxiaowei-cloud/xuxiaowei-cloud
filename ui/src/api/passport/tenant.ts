import request from '../../utils/request'
import Resp from '../common'

/**
 * 分页查询租户
 * @param data
 */
export const page = function (data: any) {
  return request.post('/passport/tenant/page', data).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 主键 删除
 * @param id 主键
 */
export const removeById = function (id: string) {
  return request.post('/passport/tenant/removeById/' + id).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 主键 删除
 * @param ids 主键
 */
export const removeByIds = function (ids: number[]) {
  return request.post('/passport/tenant/removeByIds', ids).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 租户主键 查询
 * @param tenantId 租户主键
 */
export const getById = function (tenantId: number) {
  return request.get('/passport/tenant/getById/' + tenantId).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 保存租户
 * @param data 租户
 */
export const save = function (data: any) {
  return request.post('/passport/tenant/save', data).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 更新租户
 * @param data 租户
 */
export const updateById = function (data: any) {
  return request.post('/passport/tenant/updateById', data).then(response => {
    return response.data as Resp<any>
  })
}
