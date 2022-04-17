/**
 * 设置
 */
const settings = {
  state: {
    /**
     * 正常代码
     */
    okCode: '000000',
    /**
     * 登录页面
     */
    loginPage: import.meta.env.VITE_APP_LOGIN_PAGE,
    /**
     * 登录请求代码（未授权代码）
     */
    loginRequiredCode: [
      // 令牌异常
      'T10000',
      // 令牌未识别
      'T10001',
      // 令牌已过期
      'T10002',
      // 令牌缺失
      'T10003',
      // 令牌无效
      'T10004'
    ]
  },
  mutations: {},
  actions: {}
}

export default settings
