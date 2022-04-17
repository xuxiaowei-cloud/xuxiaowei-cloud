import request from '../utils/request'
import store from '../store'
import settings from '../settings'

/**
 * 用户信息
 */
export const info = function () {
  return request.post('/user/info').then(response => {
    console.log('用户信息', response)
    const responseData = response.data
    if (responseData.code === settings.state.okCode) {
      const data = responseData.data
      const usersId = data.usersId
      const username = data.username
      const nickname = data.nickname
      const authoritiesList = data.authoritiesList

      store.commit('setUsersId', usersId)
      store.commit('setUsername', username)
      store.commit('setNickname', nickname)
      store.commit('setAuthoritiesList', authoritiesList)
    }
  })
}
