import { createRouter, createWebHashHistory } from 'vue-router'
import { queryToken } from '@/store'

import ConsoleView from '@/views/home/ConsoleView.vue'

const routes = [
  {
    path: '/',
    name: 'console',
    // 首页不能使用 import
    component: ConsoleView
  },
  {
    path: '/home/homepage1',
    name: 'homepage1',
    component: import('@/views/home/homepage1.vue')
  },
  {
    path: '/home/homepage2',
    name: 'homepage2',
    component: import('@/views/home/homepage2.vue')
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

  queryToken(to.query, router)

  next()
})

export default router
