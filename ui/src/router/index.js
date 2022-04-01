import { createRouter, createWebHashHistory } from 'vue-router'
import HomeView from '@/views/HomeView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    // 首页不能使用 import
    component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '@/views/AboutView.vue')
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  const query = to.query
  const accessToken = query.accessToken
  if (accessToken) {
    delete query.accessToken
    console.log('获取到URL中的Token', accessToken)
    router.push({ query: query }).then(response => {
      console.log('已清空URL中的Token，准备缓存Token')
    })
  }

  next()
})

export default router
