import request from '../../utils/request'
import Resp from '../common'

/**
 * 分页查询区域
 * @param data
 */
export const page = function (data: any) {
  return request.post('/master-data/region/page', data).then(response => {
    return response.data as Resp<any>
  })
}
