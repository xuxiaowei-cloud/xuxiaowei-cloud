<template>
  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460">
      <el-table-column prop="socialName" label="socialName" width="200"/>
      <el-table-column prop="nickname" label="nickname" width="200"/>
      <el-table-column prop="bindingDate" label="bindingDate" width="200"/>
      <el-table-column prop="headimgurl" label="headimgurl" width="200"/>
      <el-table-column prop="binding" label="binding" width="200"/>
    </el-table>
  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { social } from '../../api/user/social'
import settings from '../../settings'

// 加载
const loading = ref(true)

const tableData = ref([])

// 搜索
const cloudSearch = () => {
  loading.value = true
  social().then(response => {
    console.log(response)
    if (response.code === settings.okCode) {
      tableData.value = response.data
      loading.value = false
    } else {
      ElMessage.error(response.msg)
    }
  })
}

cloudSearch()

</script>

<style scoped>

</style>
