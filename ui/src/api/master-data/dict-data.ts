import request from '../../utils/request'

/**
 * 根据字典代码查询字典列表
 * @param dictCode 字典代码
 */
export const listByDictCode = function (dictCode: string) {
  return request.get('/master-data/dict-data/' + dictCode).then(response => {
    return response.data
  })
}

/**
 * 分页查询字典数据
 * @param data
 */
export const page = function (data: any) {
  return request.post('/master-data/dict-data/page', data).then(response => {
    return response.data
  })
}
