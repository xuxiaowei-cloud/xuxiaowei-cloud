<template>
  <el-container>
    <el-header>
      <h1>About</h1>
    </el-header>
  </el-container>
</template>

<script setup lang="ts">
import { actuator, baidu } from '../api/actuator'
import { getCurrentInstance } from 'vue'

const currentInstance = getCurrentInstance()
if (currentInstance != null) {
  const globalProperties = currentInstance.appContext.config.globalProperties
  globalProperties.$ipv4().then((response: any) => {
    console.log(response)
  })
}

actuator().then(response => {
  console.log(response)
})
baidu().then(response => {
  console.log(response)
})
</script>

<style scoped>

</style>
