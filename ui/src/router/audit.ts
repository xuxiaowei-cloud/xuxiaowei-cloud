const audit = [
  {
    path: '/audit/code',
    name: 'code',
    component: () => import('@/views/audit/CodeView.vue')
  },
  {
    path: '/audit/access-token',
    name: 'accessToken',
    component: () => import('@/views/audit/AccessTokenView.vue')
  },
  {
    path: '/audit/refresh-token',
    name: 'refreshToken',
    component: () => import('@/views/audit/RefreshTokenView.vue')
  }
]

export default audit
