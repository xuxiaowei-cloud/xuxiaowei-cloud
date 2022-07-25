import { useStore } from '../store'

const store = useStore()

/**
 * 判断是否拥有某项权限
 * @param authority 权限
 */
export const hasAuthority = function (authority: string) {
  if (store.getAuthorities === undefined) {
    return false
  }
  return store.getAuthorities.indexOf(authority) !== -1
}

/**
 * 判断是否拥有某项权限
 * @param authoritys 权限
 */
export const hasAnyAuthority = function (authoritys: string[]) {
  if (store.getAuthorities === undefined) {
    return false
  }
  for (const i in authoritys) {
    if (store.getAuthorities.indexOf(authoritys[i]) !== -1) {
      return true
    }
  }
  return false
}
