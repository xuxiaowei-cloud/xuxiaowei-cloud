import request from '../utils/request'
import store from '../store'
import settings from '../settings'
import { ElMessage } from 'element-plus'

/**
 * 退出登录
 */
export const signout = function () {
  return request.post('/user/signout').then(response => {
    console.log('退出登录', response)
    const responseData = response.data
    if (responseData.code === settings.state.okCode) {
      ElMessage({
        message: responseData.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 3000,
        type: 'success',
        onClose: () => {
          location.href = settings.state.loginPage
        }
      })
    } else {
      ElMessage({
        message: responseData.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 3000,
        type: 'error',
        onClose: () => {
          location.reload()
        }
      })
    }
  })
}

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

      store.commit('setUsersId', usersId)
      store.commit('setUsername', username)
      store.commit('setNickname', nickname)
    } else {
      ElMessage({
        message: responseData.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 3000,
        type: 'error'
      })
    }
  })
}
