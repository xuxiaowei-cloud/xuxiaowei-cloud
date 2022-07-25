import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import router, { routes } from './router'
import { ipv4, ipv6 } from './api/ipify'
import request from './utils/request'
import { createPinia } from 'pinia'
// @ts-ignore
import piniaPluginPersist from 'pinia-plugin-persist'

const store = createPinia()

store.use(piniaPluginPersist)

const app = createApp(App)

// 动态引入图标（图标需要全局注册才行）
// https://element-plus.gitee.io/zh-CN/component/icon.html#%E6%B3%A8%E5%86%8C%E6%89%80%E6%9C%89%E5%9B%BE%E6%A0%87
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(store)

// 需要放在 mount 之前
app.use(router)

app.mount('#app')

app.config.globalProperties.$routes = routes
app.config.globalProperties.$request = request
app.config.globalProperties.$ipv4 = ipv4
app.config.globalProperties.$ipv6 = ipv6
