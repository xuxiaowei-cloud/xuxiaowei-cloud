import request from '../utils/request'

/**
 * 列出所有的数据源及状态
 */
export const listDataSources = () => {
  return request.post('/generate/list-data-sources').then((response: any) => {
    return response.data
  })
}

/**
 * 列出所有的表信息
 */
export const listTables = (data: any) => {
  return request.post('/generate/list-tables', data).then((response: any) => {
    return response.data
  })
}
