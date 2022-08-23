<template>

  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460" @cell-dblclick="rowDblClick">
      <el-table-column prop="dictCode" label="dictCode" width="130" :show-overflow-tooltip="true"/>
      <el-table-column prop="dictDataCode" label="dictDataCode" width="160" :show-overflow-tooltip="true"/>
      <el-table-column prop="dictDataLabel" label="dictDataLabel" width="120" :show-overflow-tooltip="true"/>
      <el-table-column prop="dictDataSort" label="dictDataSort" width="110" :show-overflow-tooltip="true"/>
      <el-table-column prop="dictDataExplain" label="dictDataExplain" width="140" :show-overflow-tooltip="true"/>
      <el-table-column prop="externalCodeOne" label="externalCodeOne" width="140" :show-overflow-tooltip="true"/>
      <el-table-column prop="externalLabelOne" label="externalLabelOne" width="150" :show-overflow-tooltip="true"/>
      <el-table-column prop="remark" label="remark" width="80" :show-overflow-tooltip="true"/>
      <el-table-column prop="createUsersId" label="createUsersId" width="120" :show-overflow-tooltip="true"/>
      <el-table-column prop="createDate" label="createDate" width="160" :show-overflow-tooltip="true"/>
      <el-table-column prop="createIp" label="createIp" width="130" :show-overflow-tooltip="true"/>
      <el-table-column prop="updateUsersId" label="updateUsersId" width="130" :show-overflow-tooltip="true"/>
      <el-table-column prop="updateDate" label="updateDate" width="160" :show-overflow-tooltip="true"/>
      <el-table-column prop="updateIp" label="updateIp" width="130" :show-overflow-tooltip="true"/>
    </el-table>
  </el-container>
  <el-container>
    <el-pagination layout="prev, pager, next, total" @current-change="currentChange" :total="param.total"/>
  </el-container>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage } from 'element-plus/es'
import useClipboard from 'vue-clipboard3'
import { page } from '../../api/master-data/dict-data'
import settings from '../../settings'

// 复制
const { toClipboard } = useClipboard()

const tableData = ref([])

// 加载
const loading = ref(true)

const param = reactive({
  type: true,
  current: 1,
  size: 10,
  total: 0
})

// 搜索
const cloudSearch = () => {
  page({}).then(response => {
    console.log(response)
    if (response.code === settings.okCode) {
      const data = response.data
      tableData.value = data.records
      param.total = data.total
      loading.value = false
    } else {
      ElMessage.error(response.msg)
    }
  })
}

cloudSearch()

// 改变时触发
const currentChange = (e: number) => {
  param.current = e
  cloudSearch()
}

// 当某个单元格被双击击时会触发该事件
const rowDblClick = async (row: any, column: any, cell: any, event: any) => {
  const columnValue = row[column.property]
  console.log(columnValue)
  try {
    await toClipboard(columnValue + '')
    ElMessage({
      message: '已复制到剪贴板。',
      type: 'success'
    })
  } catch (e) {
    console.error(e)
  }
}

</script>

<style scoped>

</style>
