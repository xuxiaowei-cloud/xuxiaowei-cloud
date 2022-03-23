<template>
  <div>
    Code
  </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { authorizationCode } from '@/api/code'

const route = useRoute()
const code = route.query.code
const state = route.query.code

console.log('code', code)
console.log('state', state)

// 清空参数
useRouter().push({ query: {} })

if (!code && !state) {
  console.log('code 与 state 都不存在')
} else if (code && !state) {
  console.log('code 存在，state 不存在')
} else if (!code && state) {
  console.log('code 不存在，state 存在')
} else {
  // 参数存在时，获取 Token
  authorizationCode(code, state).then(response => {
    console.log(response)
  })
}

</script>

<style scoped>

</style>
