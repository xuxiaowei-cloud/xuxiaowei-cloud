<template>
  <el-container>
    <el-header>
      <h1>请求授权</h1>
    </el-header>
  </el-container>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { authorizationCode } from '@/api/code'
import { checkToken } from '@/api/user'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'

const store = useStore()
const route = useRoute()
const code = route.query.code
const state = route.query.state

console.log('code', code)
console.log('state', state)

// 清空参数
useRouter().push({ query: {} })

if (!code && !state) {
  const ct = checkToken()
  if (ct != null) {
    ct.then(response => {
      console.log(response)
      if (response.active === true) {
        ElMessage({ message: '已成功授权', type: 'success' })
      } else {
        ElMessage.error('错误，授权码、状态码不存在')
      }
    })
  } else {
    ElMessage.error('错误，授权码、状态码不存在')
  }
} else if (code && !state) {
  ElMessage.error('错误，状态码不存在')
} else if (!code && state) {
  ElMessage.error('错误，授权码不存在')
} else {
  // 参数存在时，获取 Token
  authorizationCode(code, state).then(response => {
    console.log(response)
    const code = response.code
    if (code === '00000') {
      ElMessage({ message: '已成功授权', type: 'success' })
      store.commit('setToken', response.data)
    } else {
      ElMessage.error(response.msg)
    }
  })
}

</script>

<style scoped>

</style>
