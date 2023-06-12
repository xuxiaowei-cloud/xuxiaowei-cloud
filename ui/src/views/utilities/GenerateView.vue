<script setup lang="ts">
import { listDataSource } from '../../api/generate'
import settings from '../../settings'
import { ElMessage } from 'element-plus'
import { ref, watch } from 'vue'

interface JdbcUrlOption {
  dataSourceName: string;
  driverClassName: number;
  jdbcUrl: string;
  username: string;
  testResult: string;
  errorMessage: String;
}

const jdbcUrlOptions = ref<JdbcUrlOption[]>([])

const jdbcUrl = ref()
const dataSourceName = ref('')

watch(() => jdbcUrl.value, (newValue, oldValue) => {
  console.log('jdbcUrl', newValue)

  // 从jdbcUrlOptions中查找被选中的JDBC URL对应的数据源名称dataSourceName
  const selectedOption = jdbcUrlOptions.value.find(item => item.jdbcUrl === newValue)
  if (selectedOption) {
    dataSourceName.value = selectedOption.dataSourceName
  } else {
    dataSourceName.value = ''
  }
})
watch(() => dataSourceName.value, (newValue, oldValue) => {
  console.log('dataSourceName', newValue)
})

listDataSource().then(response => {
  if (response.code === settings.okCode) {
    jdbcUrlOptions.value = response.data
  } else {
    ElMessage({
      message: response.msg,
      // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
      duration: 1500,
      type: 'error'
    })
  }
})

</script>

<template>
  <div>
    <el-select clearable filterable v-model="jdbcUrl" placeholder="请选择数据源">
      <el-option v-for="item in jdbcUrlOptions" :key="item.jdbcUrl" :value="item.jdbcUrl" :label="item.dataSourceName"/>
    </el-select>
  </div>
</template>

<style scoped>

</style>
