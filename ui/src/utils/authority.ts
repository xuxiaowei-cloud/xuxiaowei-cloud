import { useStore } from '../store'

/**
 * 判断是否拥有某项权限
 * @param authority 权限
 */
// 检查权限
export const hasAuthority = function (authority: RegExp | string | string[]) {
  if (authority instanceof RegExp) {
    // 正则表达式的情况
    return useStore.getAuthorities.some(str => authority.test(str))
  } else if (Array.isArray(authority)) {
    // 多个参数的情况
    return authority.every(str => useStore.getAuthorities.includes(str))
  } else {
    // 单个参数的情况
    return useStore.getAuthorities.includes(authority)
  }
}

/**
 * 判断是否拥有某项权限
 * @param authorities 权限
 */
export const hasAnyAuthority = function (authorities: RegExp[] | string[]) {
  if (useStore.getAuthorities === undefined) {
    return false
  }
  for (const i in authorities) {
    const some = hasAuthority(authorities[i])
    if (some) {
      return true
    }
  }
  return false
}
