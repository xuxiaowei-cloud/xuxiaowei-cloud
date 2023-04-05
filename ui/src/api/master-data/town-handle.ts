import request from '../../utils/request'
import Resp from '../common'

/**
 * 分页查询镇
 * @param data
 */
export const page = function (data: any) {
  return request.post('/master-data/town-handle/page', data).then(response => {
    return response.data as Resp<any>
  })
}
