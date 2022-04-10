const home = [
  {
    path: '/home/homepage1',
    name: 'homepage1',
    component: () => import('@/views/home/homepage1.vue')
  },
  {
    path: '/home/homepage2',
    name: 'homepage2',
    component: () => import('@/views/home/homepage2.vue')
  }
]

export default home
