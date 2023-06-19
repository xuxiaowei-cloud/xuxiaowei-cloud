import { ref } from 'vue'
import { createPinia, defineStore } from 'pinia'

export const timeDefineStore = defineStore('time', {
  state: () => ({ // 单一状态树
    currentTimeMillis: ref<number>() // 当前时间戳
  }),
  getters: {},
  actions: {
    /**
     * 设置 当前时间戳
     * @param currentTimeMillis 当前时间戳
     */
    setCurrentTimeMillis (currentTimeMillis: number) {
      this.currentTimeMillis = currentTimeMillis
    }
  }
})

const timeStore = timeDefineStore(createPinia())

// 订阅缓存的修改
timeStore.$subscribe((mutation, state) => {
  // 将缓存的修改放入本地缓存中
  localStorage.setItem(timeStore.$id, JSON.stringify({ ...state }))
})

// 获取历史缓存
const timeStoreOld = localStorage.getItem(timeStore.$id)
if (timeStoreOld) {
  // 返回已存在的缓存
  timeStore.$state = JSON.parse(timeStoreOld)
}

// 注意，在使用时，不用构造方法，直接调用即可
export {
  timeStore
}
