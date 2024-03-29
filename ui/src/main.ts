import { createApp } from 'vue'
import './style.css'
import permissionDirective from './utils/permission'

// 防止 ElMessageBox 样式无效
// 防止 ElMessageBox 的样式在 Table 之下
// 不可放在 style.css 中
import 'element-plus/es/components/message-box/style/css'

// https://github.com/unocss/unocss/#vite
import 'uno.css'

import App from './App.vue'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import router from './router'
import { createPinia } from 'pinia'
import { ipv4, ipv6 } from './api/ipify'
import request from './utils/request'

const pinia = createPinia()

const app = createApp(App)

// 动态引入图标（图标需要全局注册才行）
// https://element-plus.gitee.io/zh-CN/component/icon.html#%E6%B3%A8%E5%86%8C%E6%89%80%E6%9C%89%E5%9B%BE%E6%A0%87
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}

app.use(pinia)

// 需要放在 mount 之前
app.use(router)

// 在 Vue 实例中注册自定义指令
app.directive('permission', permissionDirective)

app.mount('#app')

app.config.globalProperties.$request = request
app.config.globalProperties.$ipv4 = ipv4
app.config.globalProperties.$ipv6 = ipv6
