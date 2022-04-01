import { createStore } from 'vuex'
import createPersistedState from 'vuex-persistedstate'
import settings from '@/settings'

export default createStore({
  state: { // 单一状态树
    token: null, // Token
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
