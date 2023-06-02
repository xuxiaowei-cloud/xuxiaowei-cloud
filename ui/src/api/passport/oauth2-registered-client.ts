import request from '../../utils/request'
import Resp from '../common'

/**
 * 授权类型选项
 */
export const grantTypeOptions = function () {
  return request.get('/passport/oauth2-registered-client/grant-type-options').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 客户端身份验证方法选项
 */
export const authenticationMethodOptions = function () {
  return request.get('/passport/oauth2-registered-client/authentication-method-options').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 授权范围选项
 */
export const scopeOptions = function () {
  return request.get('/passport/oauth2-registered-client/scope-options').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 令牌端点认证签名算法选项
 */
export const tokenSigningAlgorithmOptions = function () {
  return request.get('/passport/oauth2-registered-client/token-signing-algorithm-options').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * id 令牌签名算法选项
 */
export const tokenSignatureAlgorithmOptions = function () {
  return request.get('/passport/oauth2-registered-client/token-signature-algorithm-options').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 授权Token格式选项
 */
export const accessTokenFormatOptions = function () {
  return request.get('/passport/oauth2-registered-client/access-token-format-options').then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 分页查询客户
 * @param data
 */
export const page = function (data: any) {
  return request.post('/passport/oauth2-registered-client/page', data).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 客户主键 删除
 * @param clientId 客户主键
 */
export const removeById = function (clientId: number) {
  return request.post('/passport/oauth2-registered-client/removeById/' + clientId).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 客户主键 删除
 * @param clientIds 客户主键
 */
export const removeByIds = function (clientIds: string[]) {
  return request.post('/passport/oauth2-registered-client/removeByIds', clientIds).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 根据 客户主键 查询
 * @param usersId 客户主键
 */
export const getById = function (usersId: string) {
  return request.get('/passport/oauth2-registered-client/getById/' + usersId).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 保存客户
 * @param data 客户
 */
export const save = function (data: any) {
  return request.post('/passport/oauth2-registered-client/save', data).then(response => {
    return response.data as Resp<any>
  })
}

/**
 * 更新客户
 * @param data 客户
 */
export const updateById = function (data: any) {
  return request.post('/passport/oauth2-registered-client/updateById', data).then(response => {
    return response.data as Resp<any>
  })
}
