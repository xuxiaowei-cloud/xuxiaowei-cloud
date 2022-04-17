import store from '../store'

/**
 * 判断是否拥有某项权限
 * @param authority 权限
 */
export const hasAuthority = function (authority: string) {
  return store.getters.authoritiesList.indexOf(authority) !== -1
}
