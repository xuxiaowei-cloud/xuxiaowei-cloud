const audit = [
  {
    path: '/editor/tui-ui-editor',
    name: 'tui-editor',
    meta: {
      authority: true
    },
    component: () => import('../views/editor/ToastUiEditorView.vue')
  },
  {
    path: '/editor/wangeditor',
    name: 'wangeditor',
    meta: {
      authority: true
    },
    component: () => import('../views/editor/WangEditorView.vue')
  }
]

export default audit
