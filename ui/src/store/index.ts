import { ref } from 'vue'
import { defineStore, createPinia } from 'pinia'

import piniaPluginPersist from 'pinia-plugin-persist'

const pinia = createPinia()
pinia.use(piniaPluginPersist)

export default pinia

export const useStore = defineStore({
  id: 'store',
  // @ts-ignore
  persist: {
    enabled: true,
    strategies: [
      {
        key: 'xxx', // 秘钥
        storage: localStorage // 存储方式，默认：sessionStorage，可选：localStorage
      }
    ]
  },
  state: () => ({ // 单一状态树
    usersId: ref<string>(), // 用户主键
    username: ref<string>(), // 用户名
    nickname: ref<string>(), // 昵称
    authorities: ref<string[]>([]), // 权限
    accessToken: ref<any>(), // Token
    checkTokenTime: ref<number>(), // 检查Token时间
    refreshToken: ref<any>(), // 刷新Token
    isCollapse: ref<boolean>(false), // 是否折叠菜单
    keepAliveExclude: ref<string[]>([]) // keep-alive 排除页面（组件）名
  }),
  getters: {
    /**
     * 获取 用户主键
     * @param state 单一状态树
     */
    getUsersId (state) {
      return state.usersId
    },
    /**
     * 获取 用户名
     * @param state 单一状态树
     */
    getUsername (state) {
      return state.username
    },
    /**
     * 获取 昵称
     * @param state 单一状态树
     */
    getNickname (state) {
      return state.nickname
    },
    /**
     * 获取 权限
     * @param state 单一状态树
     */
    getAuthorities (state) {
      return state.authorities
    },
    /**
     * 获取 Token
     * @param state 单一状态树
     */
    getAccessToken (state) {
      return state.accessToken
    },
    /**
     * 获取 检查Token时间
     * @param state 单一状态树
     */
    getCheckTokenTime (state) {
      return state.checkTokenTime === undefined ? 0 : state.checkTokenTime
    },
    /**
     * 获取 刷新Token
     * @param state 单一状态树
     */
    getRefreshToken (state) {
      return state.refreshToken
    },
    /**
     * 是否折叠菜单
     * @param state
     */
    getIsCollapse (state) {
      return state.isCollapse
    },
    /**
     * keep-alive 排除页面（组件）名
     * @param state
     */
    getKeepAliveExclude (state) {
      return state.keepAliveExclude
    }
  },
  actions: {
    /**
     * 设置 用户名
     * @param usersId Token
     */
    setUsersId (usersId: string) {
      this.usersId = usersId
    },
    /**
     * 设置 用户名
     * @param username 用户名
     */
    setUsername (username: string) {
      this.username = username
    },
    /**
     * 设置 昵称
     * @param nickname 用户昵称
     */
    setNickname (nickname: string) {
      this.nickname = nickname
    },
    /**
     * 设置 权限
     * @param authorities 权限
     */
    setAuthorities (authorities: string[]) {
      this.authorities = authorities
    },
    /**
     * 设置 Token
     * @param accessToken 授权Token
     */
    setAccessToken (accessToken: any) {
      this.accessToken = accessToken
    },
    /**
     * 设置 检查Token时间
     * @param checkTokenTime
     */
    setCheckTokenTime (checkTokenTime: number) {
      this.checkTokenTime = checkTokenTime
    },
    /**
     * 设置 刷新Token
     * @param refreshToken 刷新Token
     */
    setRefreshToken (refreshToken: any) {
      this.refreshToken = refreshToken
    },
    /**
     * 设置是否折叠菜单
     * @param isCollapse 是否折叠菜单
     */
    setIsCollapse (isCollapse: boolean) {
      this.isCollapse = isCollapse
    },
    /**
     * 添加 keep-alive 排除页面（组件）名
     * @param keepAliveExclude keep-alive 排除页面（组件）名
     */
    addKeepAliveExclude (keepAliveExclude: string) {
      this.keepAliveExclude.push(keepAliveExclude)
    },
    /**
     * 移除 keep-alive 排除
     * @param keepAliveExclude
     */
    removeKeepAliveExclude (keepAliveExclude: string) {
      this.keepAliveExclude.splice(this.keepAliveExclude.indexOf(keepAliveExclude), 1)
    }
  }
})
