import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import settings from '../settings'
import { checkToken } from '../api/authorization-server'
import { LocationQuery, Router } from 'vue-router'

const store = createStore({
  state: { // 单一状态树
    username: 'xuxiaowei', // 用户名
    nickname: '徐晓伟', // 昵称
    accessToken: null, // Token
    refreshToken: null, // 刷新Token
    jti: null, // 票据
    defaultActive: '/', // 获取默认激活菜单
    isCollapse: false // 是否折叠菜单
  },
  getters: {
    /**
     * 获取 用户名
     * @param state 单一状态树
     * @returns 返回 用户名
     */
    username (state) {
      return state.username
    },
    /**
     * 获取 昵称
     * @param state 单一状态树
     * @returns 返回 昵称
     */
    nickname (state) {
      return state.nickname
    },
    /**
     * 获取 Token
     * @param state 单一状态树
     * @returns 返回 Token
     */
    accessToken (state) {
      return state.accessToken
    },
    /**
     * 获取 刷新Token
     * @param state 单一状态树
     * @returns 返回 刷新Token
     */
    refreshToken (state) {
      return state.refreshToken
    },
    /**
     * 获取 票据
     * @param state 单一状态树
     * @returns 返回 票据
     */
    jti (state) {
      return state.jti
    },
    /**
     * 获取默认激活菜单
     * @param state
     * @returns {string}
     */
    defaultActive (state) {
      return state.defaultActive
    },
    /**
     * 是否折叠菜单
     * @param state
     * @returns {any}
     */
    isCollapse (state) {
      return state.isCollapse
    }
  },
  mutations: { // 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation
    /**
     * 设置 用户名
     * @param state 单一状态树
     * @param username Token
     */
    setUsername (state, username) {
      state.username = username
    },
    /**
     * 设置 昵称
     * @param state 单一状态树
     * @param nickname Token
     */
    setNickname (state, nickname) {
      state.nickname = nickname
    },
    /**
     * 设置 Token
     * @param state 单一状态树
     * @param accessToken Token
     */
    setAccessToken (state, accessToken) {
      state.accessToken = accessToken
    },
    /**
     * 设置 刷新Token
     * @param state 单一状态树
     * @param refreshToken 刷新Token
     */
    setRefreshToken (state, refreshToken) {
      state.refreshToken = refreshToken
    },
    /**
     * 设置 票据
     * @param state 单一状态树
     * @param jti 票据
     */
    setJti (state, jti) {
      state.jti = jti
    },
    /**
     * 设置默认激活菜单
     * @param state
     * @param defaultActive
     */
    setDefaultActive (state, defaultActive) {
      state.defaultActive = defaultActive
    },
    /**
     * 设置是否折叠菜单
     * @param state
     * @param isCollapse
     */
    setIsCollapse (state, isCollapse) {
      state.isCollapse = isCollapse
    }
  },
  actions: {
  },
  modules: {
    settings
  },
  plugins: [
    createPersistedState({
      storage: localStorage
    })
  ]
})

export default store

export const queryToken = function (query: LocationQuery, router: Router) {
  if (query.store === 'true') {
    const accessToken = query.accessToken
    const refreshToken = query.refreshToken
    const jti = query.jti

    delete query.store

    delete query.accessToken
    delete query.refreshToken
    delete query.jti

    console.log('获取到URL中的accessToken', accessToken)
    console.log('获取到URL中的refreshToken', refreshToken)
    console.log('获取到URL中的jti', jti)

    store.commit('setAccessToken', accessToken)
    store.commit('setRefreshToken', refreshToken)
    store.commit('setJti', jti)

    console.log('已完成store中的accessToken缓存：', store.getters.accessToken)
    console.log('已完成store中的refreshToken缓存：', store.getters.refreshToken)
    console.log('已完成store中的jti缓存：', store.getters.jti)

    checkToken(store.getters.accessToken).then(response => {
      console.log('完成store中的Token缓存后检查Token', response)
    })

    router.push({ query: query }).then(() => {

    })
  } else {
    checkToken(store.getters.accessToken).then(response => {
      console.log('检查Token', response)
    })
  }
}
