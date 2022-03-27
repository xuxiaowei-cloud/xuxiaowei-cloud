import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import Cookies from 'js-cookie'

export default createStore({
  state: { // 单一状态树
    token: null // Token
  },
  getters: {
    /**
     * 获取 Token
     * @param state 单一状态树
     * @returns 返回 Token
     */
    token (state) {
      return state.token
    }
  },
  mutations: { // 更改 Vuex 的 store 中的状态的唯一方法是提交 mutation
    /**
     * 设置 Token
     * @param state 单一状态树
     * @param token Token
     */
    setToken (state, token) {
      state.token = token // 设置 Token

      const expiration = token.expiration

      const domain = '.xuxiaowei.cloud'
      const expires = new Date(expiration)

      Cookies.set('access_token', token.access_token, { path: '/', domain: domain, expires: expires })
      Cookies.set('expiration', expiration, { path: '/', domain: domain, expires: expires })
      Cookies.set('jti', token.jti, { path: '/', domain: domain, expires: expires })
      Cookies.set('refresh_token', token.refresh_token, { path: '/', domain: domain, expires: expires })
      Cookies.set('scope', token.scope, { path: '/', domain: domain, expires: expires })
      Cookies.set('token_type', token.token_type, { path: '/', domain: domain, expires: expires })
    }
  },
  actions: {
  },
  modules: {
  },
  plugins: [
    createPersistedState({
      storage: localStorage
    })
  ]
})
