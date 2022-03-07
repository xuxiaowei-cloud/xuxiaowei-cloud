import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import { ipv4, ipv6 } from './api/ipify'
import request from './utils/request'

const app = createApp(App)

// 需要放在 mount 之前
app.use(router)

app.mount('#app')

app.config.globalProperties.$request = request
app.config.globalProperties.$ipv4 = ipv4
app.config.globalProperties.$ipv6 = ipv6
