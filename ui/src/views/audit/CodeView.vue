<template>
  <el-container>
    授权代码
  </el-container>
</template>

<script setup>
import { page } from '@/api/audit/code'

page({}).then(response => {
  console.log(response)
})

</script>

<style scoped>

</style>
