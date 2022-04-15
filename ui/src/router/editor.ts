const audit = [
  {
    path: '/editor/tui-ui-editor',
    name: 'tui-editor',
    component: () => import('../views/editor/ToastUiEditorView.vue')
  },
  {
    path: '/editor/wangeditor',
    name: 'wangeditor',
    component: () => import('../views/editor/WangEditorView.vue')
  }
]

export default audit
