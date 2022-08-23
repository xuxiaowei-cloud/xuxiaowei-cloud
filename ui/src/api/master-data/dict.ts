import request from '../../utils/request'

/**
 * 查询字典代码列表
 */
export const list = function () {
  return request.get('/master-data/dict/list').then(response => {
    return response.data
  })
}

/**
 * 分页查询字典
 * @param data
 */
export const page = function (data: any) {
  return request.post('/master-data/dict/page', data).then(response => {
    return response.data
  })
}
