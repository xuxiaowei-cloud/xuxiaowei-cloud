import { createRouter, createWebHashHistory } from 'vue-router'
import { queryToken } from '../store'
import { hasAnyAuthority } from '../utils/authority'
import settings from '../settings'
import LayoutComponent from '../components/LayoutComponent.vue'

import ConsoleView from '../views/home/ConsoleView.vue'

export const routes = [
  {
    name: '主页',
    path: '',
    redirect: '/console',
    component: LayoutComponent,
    meta: {
      icon: 'House'
    },
    children: [
      {
        path: '/console',
        name: '控制台',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        // 首页不能使用 import
        component: ConsoleView
      },
      {
        path: '/home/homepage1',
        name: '主页一',
        meta: {
          keepAlive: true,
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('@/views/home/HomePage1View.vue')
      },
      {
        path: '/home/homepage2',
        name: '主页二',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('@/views/home/HomePage2View.vue')
      }
    ]
  },
  {
    name: '富文本',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'Notebook'
    },
    children: [
      {
        path: '/editor/tui-ui-editor',
        name: 'Tui UI Editor',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('../views/editor/ToastUiEditorView.vue')
      },
      {
        path: '/editor/wangeditor',
        name: 'WangEditor',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('../views/editor/WangEditorView.vue')
      }
    ]
  },
  {
    name: '主数据',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'Tickets'
    },
    children: [
      {
        path: '/master-data/dict',
        name: '字典',
        meta: {
          authority: [/^dict:(read|\*)$/]
        },
        component: () => import('../views/master-data/DictView.vue')
      },
      {
        path: '/master-data/dict-data',
        name: '字典数据',
        meta: {
          authority: [/^dict:(read|\*)$/]
        },
        component: () => import('../views/master-data/DictDataView.vue')
      },
      {
        path: '/master-data/region',
        name: '行政区域',
        meta: {
          authority: [/^region:(read|\*)$/]
        },
        component: () => import('../views/master-data/RegionView.vue')
      }
    ]
  },
  {
    name: '个人中心',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'User'
    },
    children: [
      {
        path: '/user/personal',
        name: '个人中心',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('@/views/user/PersonalView.vue')
      },
      {
        path: '/user/security',
        name: '安全设置',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('@/views/user/SecurityView.vue')
      },
      {
        path: '/user/social',
        name: '社交绑定',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('@/views/user/SocialView.vue')
      },
      {
        path: '/user/password',
        name: '密码设置',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        component: () => import('@/views/user/PasswordView.vue')
      }
    ]
  },
  {
    name: '系统管理',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'Setting'
    },
    children: [
      {
        path: '/manage/user',
        name: '用户管理',
        meta: {
          authority: [/^manage_user:(read|\*)$/]
        },
        component: () => import('@/views/manage/UserView.vue')
      },
      {
        path: '/manage/tenant',
        name: '租户管理',
        meta: {
          authority: [/^manage_tenant:(read|\*)$/]
        },
        component: () => import('@/views/manage/TenantView.vue')
      },
      {
        path: '/manage/client',
        name: '客户管理',
        meta: {
          authority: [/^manage_client:(read|\*)$/]
        },
        component: () => import('@/views/manage/ClientView.vue')
      }
    ]
  },
  {
    name: '审计',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'Aim'
    },
    children: [
      {
        path: '/audit/authorization',
        name: '授权记录',
        meta: {
          authority: [/^audit_authorization:(read|\*)$/]
        },
        component: () => import('@/views/audit/AuthorizationView.vue')
      },
      {
        path: '/audit/authorization-consent',
        name: '授权同意书',
        meta: {
          authority: [/^audit_authorization_consent:(read|\*)$/]
        },
        component: () => import('@/views/audit/AuthorizationConsentView.vue')
      }
    ]
  },
  {
    name: '实用工具',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'Grid'
    },
    children: [
      {
        path: '/utilities/generate',
        name: '生成代码',
        meta: {
          authority: [/^generate:(read|\*)$/]
        },
        component: () => import('@/views/utilities/GenerateCodeView.vue')
      }
    ]
  },
  {
    name: '其他',
    path: '',
    component: LayoutComponent,
    children: [
      {
        path: '/refresh',
        name: '刷新',
        meta: {
          authority: [/^user:(info|\*)$/],
          show: false
        },
        component: () => import('@/views/RefreshView.vue')
      },
      {
        path: '/non-authority',
        name: '无权限',
        meta: {
          authority: [/^user:(info|\*)$/],
          show: false
        },
        component: () => import('@/views/NonAuthorityView.vue')
      }
    ]
  },
  {
    name: '关于',
    path: '',
    component: LayoutComponent,
    meta: {
      icon: 'Document'
    },
    children: [
      {
        path: '/about',
        name: '关于',
        meta: {
          authority: [/^user:(info|\*)$/]
        },
        // route level code-splitting
        // this generates a separate chunk (about.[hash].js) for this route
        // which is lazy-loaded when the route is visited.
        component: () => import(/* webpackChunkName: "about" */ '@/views/AboutView.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

router.beforeEach((to, from, next) => {
  console.log(to)

  // 动态显示页面title
  if (to.name) {
    document.title = `${to.name.toString() + ' - ' + settings.title}`
  } else {
    document.title = settings.title
  }

  queryToken(to.path, to.query, router)
  const meta = to.meta
  const authority = meta.authority
  if (to.path === '/non-authority') {
    next()
  } else if (authority instanceof Array && hasAnyAuthority(authority)) { // 判断是否允许访问
    next()
  } else {
    // 不允许访问，跳转到无权限页面
    next('/non-authority')
  }
})

export default router
