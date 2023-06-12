import request from '../utils/request'

/**
 * 列出所有的数据源信息
 */
export const listDataSource = () => {
  return request.post('/generate/list-data-source').then((response: any) => {
    return response.data
  })
}
