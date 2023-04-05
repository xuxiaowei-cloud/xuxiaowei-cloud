import request from '../../utils/request'
import Resp from '../common'

/**
 * 分页查询城市
 * @param data
 */
export const page = function (data: any) {
  return request.post('/master-data/city-handle/page', data).then(response => {
    return response.data as Resp<any>
  })
}
