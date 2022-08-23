import request from '../../utils/request'

/**
 * 查询字典代码列表
 */
export const list = function () {
  return request.get('/master-data/dict/list').then(response => {
    return response.data
  })
}
