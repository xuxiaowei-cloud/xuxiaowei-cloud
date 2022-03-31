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

// 参数存在时，获取 Token
authorizationCode(code, state).then(response => {
  console.log(response)
  const code = response.code
  if (code === store.state.settings.okCode) {
    ElMessage({ message: '已成功授权', type: 'success' })
    store.commit('setToken', response.data.oauth2AccessToken)
    location.href = response.data.homePage
  } else {
    ElMessage.error(response.msg)
  }
})

</script>

<style scoped>

</style>
