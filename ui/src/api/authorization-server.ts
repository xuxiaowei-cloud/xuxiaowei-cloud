import request from '../utils/request'
import store from '../store'
import settings from '../settings'

/**
 * 检查 Token
 * @param token
 */
export const checkToken = function (token: string) {
  return request.post('/authorization-server/oauth/check_token?token=' + token).then(response => {
    const responseData = response.data
    if (responseData.code === settings.state.okCode) {
      const data = responseData.data
      const authoritiesList = data.authoritiesList
      store.commit('setAuthoritiesList', authoritiesList)
    }
    return response.data
  })
}
