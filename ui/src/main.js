import { createApp } from 'vue'
import App from './App.vue'
import router from './router'

const app = createApp(App)

// 需要放在 mount 之前
app.use(router)

app.mount('#app')
