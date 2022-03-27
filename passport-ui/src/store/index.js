import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import Cookies from 'js-cookie'

export default createStore({
  state: { // 单一状态树
    domain: '.xuxiaowei.cloud', // Cookie 主域
    token: null, // Token
    authorizeUri: null, // 授权 URI
    checkTokenUri: null // 检查 Token URI
  },
  getters: {
    /**
     * 获取 Token
     * @param state 单一状态树
     * @returns 返回 Token
     */
    token (state) {
      return state.token
    },
    /**
     * 获取 Token 值
     * @param state 单一状态树
     * @returns {null|*} Token 值
     */
    accessToken (state) {
      if (state.token == null) {
        return null
      }
      return state.token.access_token
    },
    /**
     * 获取 授权 URI
     * @param state 单一状态树
     * @returns 返回 授权 URI
     */
    authorizeUri (state) {
      return state.authorizeUri
    },
    /**
     * 获取 授权 URI
     * @param state 单一状态树
     * @returns 返回 授权 URI
     */
    checkTokenUri (state) {
      return state.checkTokenUri
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

      const domain = state.domain
      const expiration = token.expiration
      const expires = new Date(expiration)

      Cookies.set('access_token', token.access_token, { path: '/', domain: domain, expires: expires })
      Cookies.set('expiration', expiration, { path: '/', domain: domain, expires: expires })
      Cookies.set('jti', token.jti, { path: '/', domain: domain, expires: expires })
      Cookies.set('refresh_token', token.refresh_token, { path: '/', domain: domain, expires: expires })
      Cookies.set('scope', token.scope, { path: '/', domain: domain, expires: expires })
      Cookies.set('token_type', token.token_type, { path: '/', domain: domain, expires: expires })
    },
    /**
     * 设置 授权 URI
     * @param state 单一状态树
     * @param authorizeUri 授权 URI
     */
    setAuthorizeUri (state, authorizeUri) {
      state.authorizeUri = authorizeUri
      Cookies.set('authorizeUri', authorizeUri, { path: '/', domain: state.domain, expires: 365 })
    },
    /**
     * 设置 检查 Token URI
     * @param state 单一状态树
     * @param checkTokenUri 检查 Token URI
     */
    setCheckTokenUri (state, checkTokenUri) {
      state.checkTokenUri = checkTokenUri
      Cookies.set('checkTokenUri', checkTokenUri, { path: '/', domain: state.domain, expires: 365 })
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
