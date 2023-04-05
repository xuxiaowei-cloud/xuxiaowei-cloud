import request from '../../utils/request'
import Resp from '../common'

/**
 * 分页查询县
 * @param data
 */
export const page = function (data: any) {
  return request.post('/master-data/county-handle/page', data).then(response => {
    return response.data as Resp<any>
  })
}
