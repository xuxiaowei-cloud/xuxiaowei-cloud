<script setup lang="ts">
import { ref, watch } from 'vue'
import { ElMessage } from 'element-plus'
import type { TabPaneName } from 'element-plus'
import { listDataSources, listTables, listTableColumns } from '../../api/generate'

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
const tableColumnOptionOptions = ref<[]>([])

const jdbcUrl = ref()
const dataSourceName = ref('')
const tableNames = ref<string[]>([])
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
watch(() => tableNames.value, (newValue, oldValue) => {
  console.log('tableNames', newValue)

  // 列出所有的字段信息
  selectTableColumns(newValue)
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

const selectTableColumns = (tableNames: string[]) => {
  listTableColumns({ jdbcUrl: jdbcUrl.value, tableNames }).then(response => {
    if (response.code === settings.okCode) {
      tableColumnOptionOptions.value = response.data.records
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
let tabIndex = 2
const editableTabsValue = ref('2')
const editableTabs = ref([
  {
    title: 'Tab 1',
    name: '1',
    content: 'Tab 1 content'
  },
  {
    title: 'Tab 2',
    name: '2',
    content: 'Tab 2 content'
  }
])

const handleTabsEdit = (
  targetName: TabPaneName | undefined,
  action: 'remove' | 'add'
) => {
  if (action === 'add') {
    const newTabName = `${++tabIndex}`
    editableTabs.value.push({
      title: 'New Tab ' + tabIndex,
      name: newTabName,
      content: `New Tab ${tabIndex} content`
    })
    editableTabsValue.value = newTabName
  } else if (action === 'remove') {
    const tabs = editableTabs.value
    let activeName = editableTabsValue.value
    if (activeName === targetName) {
      tabs.forEach((tab, index) => {
        if (tab.name === targetName) {
          const nextTab = tabs[index + 1] || tabs[index - 1]
          if (nextTab) {
            activeName = nextTab.name
          }
        }
      })
    }

    editableTabsValue.value = activeName
    editableTabs.value = tabs.filter((tab) => tab.name !== targetName)
  }
}

</script>

<template>
  <div>
    <el-select clearable filterable v-model="jdbcUrl" placeholder="请选择数据源">
      <el-option v-for="item in dataSourceOptions" :key="item.jdbcUrl" :value="item.jdbcUrl"
                 :label="item.dataSourceName"/>
    </el-select>
    <el-select clearable filterable remote reserve-keyword multiple :loading="loading" :remote-method="selectTables"
               remote-show-suffix v-model="tableNames" placeholder="请选择表">
      <el-option v-for="item in tableOptionOptions" :key="item.tableName" :value="item.tableName"
                 :label="item.tableName"/>
    </el-select>
  </div>

  <el-container>
    <el-tabs v-model="editableTabsValue" type="card" editable class="demo-tabs" @edit="handleTabsEdit">
      <el-tab-pane v-for="item in editableTabs" :key="item.name" :label="item.title" :name="item.name">
        {{ item.content }}
      </el-tab-pane>
    </el-tabs>
  </el-container>

</template>

<style scoped>

</style>
