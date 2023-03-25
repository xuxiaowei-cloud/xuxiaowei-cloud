import { Directive } from 'vue'

import { hasAuthority, hasAnyAuthority } from './authority'

// 自定义权限指令
const permissionDirective: Directive = {
  mounted (el, binding) {
    const { value } = binding
    // 判断是否拥有权限
    if (!checkPermission(value)) {
      el.style.display = 'none'
    }
  }
}

// 检查权限
function checkPermission (permission: RegExp | RegExp[]) {
  if (Array.isArray(permission)) {
    // 多个参数的情况
    return hasAnyAuthority(permission)
  } else {
    return hasAuthority(permission)
  }
}

export default permissionDirective
