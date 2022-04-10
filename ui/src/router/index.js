import { createRouter, createWebHashHistory } from 'vue-router'
import { queryToken } from '@/store'
import home from '@/router/home'
import audit from '@/router/audit'

import ConsoleView from '@/views/home/ConsoleView.vue'

let routes = [
  {
    path: '/',
    name: 'console',
    // 首页不能使用 import
    component: ConsoleView
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

routes = routes.concat(home)
routes = routes.concat(audit)

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  queryToken(to.query, router)

  next()
})

export default router
