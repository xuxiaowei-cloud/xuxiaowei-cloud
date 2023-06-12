<script setup lang="ts">
import { ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
import { listDataSources, listTables } from '../../api/generate'
import settings from '../../settings'

interface DataSourceOption {
  dataSourceName: string;
  driverClassName: number;
  jdbcUrl: string;
  username: string;
  testResult: string;
  errorMessage: String;
}

interface TableOption {
  jdbcUrl: string;
  tableComment: string;
  tableName: string;
}

const dataSourceOptions = ref<DataSourceOption[]>([])
const tableOptionOptions = ref<TableOption[]>([])

const jdbcUrl = ref()
const dataSourceName = ref('')
const tableName = ref('')
const loading = ref(false)

watch(() => jdbcUrl.value, (newValue, oldValue) => {
  console.log('jdbcUrl', newValue)

  selectTables('')

  // 从jdbcUrlOptions中查找被选中的JDBC URL对应的数据源名称dataSourceName
  const selectedOption = dataSourceOptions.value.find(item => item.jdbcUrl === newValue)
  if (selectedOption) {
    dataSourceName.value = selectedOption.dataSourceName
  } else {
    dataSourceName.value = ''
  }
})
watch(() => dataSourceName.value, (newValue, oldValue) => {
  console.log('dataSourceName', newValue)
})
watch(() => tableName.value, (newValue, oldValue) => {
  console.log('tableName', newValue)
})

listDataSources().then(response => {
  if (response.code === settings.okCode) {
    dataSourceOptions.value = response.data
  } else {
    ElMessage({
      message: response.msg,
      // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
      duration: 1500,
      type: 'error'
    })
  }
})

const selectTables = (tableName: string) => {
  listTables({ jdbcUrl: jdbcUrl.value, tableName }).then(response => {
    if (response.code === settings.okCode) {
      tableOptionOptions.value = response.data.records
    } else {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'error'
      })
    }
  })
}

</script>

<template>
  <div>
    <el-select clearable filterable v-model="jdbcUrl" placeholder="请选择数据源">
      <el-option v-for="item in dataSourceOptions" :key="item.jdbcUrl" :value="item.jdbcUrl"
                 :label="item.dataSourceName"/>
    </el-select>
    <el-select clearable filterable remote reserve-keyword :loading="loading" :remote-method="selectTables"
               remote-show-suffix v-model="tableName" placeholder="请选择表">
      <el-option v-for="item in tableOptionOptions" :key="item.tableName" :value="item.tableName"
                 :label="item.tableName"/>
    </el-select>
  </div>
</template>

<style scoped>

</style>
