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
