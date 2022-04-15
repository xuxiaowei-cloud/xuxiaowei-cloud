<template>
  <el-container id="cloud-el-search">
    <el-input class="cloud-el-input" clearable v-model="param.codeId" placeholder="Please input codeId" />
    <el-input class="cloud-el-input" clearable v-model="param.code" placeholder="Please input code" />
    <el-input class="cloud-el-input" clearable v-model="param.username" placeholder="Please input username" />
    <el-input class="cloud-el-input" clearable v-model="param.clientId" placeholder="Please input clientId" />
    <el-input class="cloud-el-input" clearable v-model="param.remoteAddress" placeholder="Please input remoteAddress" />
    <el-button class="cloud-el-search" @click="cloudSearch">搜索</el-button>
  </el-container>
  <el-container>
    <el-table :data="tableData" v-loading="loading" height="445">
      <el-table-column prop="codeId" label="codeId" width="70"/>
      <el-table-column prop="code" label="code" width="300"/>
      <el-table-column prop="username" label="username" width="130"/>
      <el-table-column prop="clientId" label="clientId" width="160"/>
      <el-table-column prop="remoteAddress" label="remoteAddress" width="130"/>
      <el-table-column prop="createDate" label="createDate" width="160"/>
      <el-table-column prop="updateDate" label="updateDate" width="160"/>
      <el-table-column prop="deleted" label="deleted" width="100"/>
      <el-table-column prop="scope" label="scope" width="130"/>
      <el-table-column prop="redirectUri" label="redirectUri" width="260" :show-overflow-tooltip="true"/>
      <el-table-column prop="responseType" label="responseType" width="130"/>
      <el-table-column prop="sessionId" label="sessionId" width="300" :show-overflow-tooltip="true"/>
      <el-table-column prop="state" label="state" width="300" :show-overflow-tooltip="true"/>
      <el-table-column prop="authoritiesJson" label="authorities" width="160" :show-overflow-tooltip="true"/>
      <el-table-column prop="authenticationJson" label="authentication" width="160" :show-overflow-tooltip="true"/>
    </el-table>
  </el-container>
  <el-container>
    <el-pagination layout="prev, pager, next, total" @current-change="currentChange" :total="param.total" />
  </el-container>
</template>

<script setup lang="ts">
import { page } from '../../api/audit/code'
import { ref, reactive } from 'vue'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'

const store = useStore()

const tableData = ref([])
const param = reactive({
  current: 1,
  size: 10,
  total: 0,
  codeId: null,
  code: null,
  username: null,
  clientId: null,
  remoteAddress: null
})
const loading = ref(true)

const cloudSearch = () => {
  loading.value = true
  page(param).then(response => {
    console.log(response)
    if (response.code === store.state.settings.okCode) {
      const data = response.data
      tableData.value = data.records
      param.total = data.total
      loading.value = false
    } else {
      ElMessage.error(response.msg)
    }
  })
}

// 初始搜索
cloudSearch()

// 改变时触发
const currentChange = (e: any) => {
  param.current = e
  cloudSearch()
}

</script>

<style scoped>

.cloud-el-input, .cloud-el-search {
  margin-left: 5px;
  margin-right: 5px;
}

</style>
