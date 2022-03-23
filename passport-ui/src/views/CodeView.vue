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
import { ElMessage } from 'element-plus'

const route = useRoute()
const code = route.query.code
const state = route.query.state

console.log('code', code)
console.log('state', state)

// 清空参数
useRouter().push({ query: {} })

if (!code && !state) {
  ElMessage.error('错误，授权码、状态码不存在')
} else if (code && !state) {
  ElMessage.error('错误，状态码不存在')
} else if (!code && state) {
  ElMessage.error('错误，授权码不存在')
} else {
  // 参数存在时，获取 Token
  authorizationCode(code, state).then(response => {
    console.log(response)
  })
}

</script>

<style scoped>

</style>
