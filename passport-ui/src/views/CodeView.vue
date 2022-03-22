<template>
  <div>
    Code
  </div>
</template>

<script setup>
import { useRoute } from 'vue-router'
import { authorizationCode } from '@/api/code'

const route = useRoute()
const code = route.query.code
const state = route.query.code

console.log('code', code)
console.log('state', state)

authorizationCode(code, state).then(response => {
  console.log(response)
})

</script>

<style scoped>

</style>
