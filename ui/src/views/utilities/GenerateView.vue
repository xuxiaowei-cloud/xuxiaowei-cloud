<script setup lang="ts">
import { ref, watch } from 'vue'
import { ElMessage, TabPaneName } from 'element-plus'
import { listDataSources, listTableColumns, listTables } from '../../api/generate'

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

interface TableColumn {
  tableName: string;
  tableComment: string;
}

interface EditableTab {
  title: TabPaneName,
  name: TabPaneName,
  content: any
}

const dataSourceOptions = ref<DataSourceOption[]>([])
const tableOptions = ref<TableOption[]>([])
const tableColumnOptions = ref<TableColumn[]>([])

const jdbcUrl = ref()
const dataSourceName = ref('')
const tableNames = ref<string[]>([])
const loading = ref(false)

const editableTabsValue = ref()
const editableTabs = ref<EditableTab[]>([])

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
watch(() => tableColumnOptions.value, (newValue, oldValue) => {
  // 删除全部
  for (const tab of editableTabs.value) {
    removeTabs(tab.name)
  }

  for (const tableColumn of newValue) {
    console.log('tableColumn', tableColumn)
    // 重新添加
    addTabs(tableColumn.tableName, tableColumn)
  }
})

listDataSources().then((response: any) => {
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
  if (jdbcUrl.value) {
    listTables({ jdbcUrl: jdbcUrl.value, tableName }).then((response: any) => {
      if (response.code === settings.okCode) {
        tableOptions.value = response.data.records
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
}

const selectTableColumns = (tableNames: string[]) => {
  if (tableNames.length > 0) {
    listTableColumns({ jdbcUrl: jdbcUrl.value, tableNames }).then((response: any) => {
      if (response.code === settings.okCode) {
        tableColumnOptions.value = response.data
      } else {
        ElMessage({
          message: response.msg,
          // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
          duration: 1500,
          type: 'error'
        })
      }
    })
  } else {
    tableColumnOptions.value = []
  }
}

const addTabs = (targetName: TabPaneName, tableColumn: TableColumn) => {
  editableTabs.value.push({
    title: targetName,
    name: targetName,
    content: tableColumn.tableComment
  })
  editableTabsValue.value = targetName
}

const removeTabs = (targetName: TabPaneName) => {
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

</script>

<template>
  <div>
    <el-select clearable filterable v-model="jdbcUrl" placeholder="请选择数据源">
      <el-option v-for="item in dataSourceOptions" :key="item.jdbcUrl" :value="item.jdbcUrl"
                 :label="item.dataSourceName"/>
    </el-select>
    <el-select clearable filterable remote reserve-keyword multiple :loading="loading" :remote-method="selectTables"
               remote-show-suffix v-model="tableNames" :collapse-tags="true" placeholder="请选择表"
               :disabled="!jdbcUrl">
      <el-option v-for="item in tableOptions" :key="item.tableName" :value="item.tableName" :label="item.tableName"/>
    </el-select>
  </div>

  <el-container>
    <el-tabs v-model="editableTabsValue" type="card" class="table-tabs">
      <el-tab-pane v-for="item in editableTabs" :key="item.name" :label="item.title" :name="item.name">
        {{ item.content }}
      </el-tab-pane>
    </el-tabs>
  </el-container>

</template>

<style scoped>

</style>
