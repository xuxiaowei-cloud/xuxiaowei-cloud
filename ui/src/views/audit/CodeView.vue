<template>
  <div id="cloud-el-search">
    <el-input class="cloud-el-input" clearable v-model="param.codeId" placeholder="Please input codeId" />
    <el-input class="cloud-el-input" clearable v-model="param.code" placeholder="Please input code" />
    <el-input class="cloud-el-input" clearable v-model="param.username" placeholder="Please input username" />
    <el-input class="cloud-el-input" clearable v-model="param.clientId" placeholder="Please input clientId" />
    <el-input class="cloud-el-input" clearable v-model="param.remoteAddress" placeholder="Please input remoteAddress" />
    <el-button class="cloud-el-search" @click="cloudSearch">搜索</el-button>
  </div>
  <el-container>
    <el-table :data="tableData" v-loading="loading" height="460">
      <el-table-column prop="codeId" label="codeId" width="70"/>
      <el-table-column prop="code" label="code" width="310"/>
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
      <el-table-column fixed="right" label="Operations" width="100">
        <template #default="scope">
          <el-button type="text" size="small" v-if="scope.row.deleted" disabled>Delete</el-button>
          <el-button type="text" size="small" v-else @click="deleteCodeId(scope.row.codeId)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-container>
  <el-container>
    <el-pagination layout="prev, pager, next, total" @current-change="currentChange" :total="param.total" />
  </el-container>
</template>

<script setup lang="ts">
import { page, removeById } from '../../api/audit/code'
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
const currentChange = (e: number) => {
  param.current = e
  cloudSearch()
}

// 删除授权Code
const deleteCodeId = (e: number) => {
  removeById(e).then(response => {
    if (response.code === store.state.settings.okCode) {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'success',
        onClose: () => {
          cloudSearch()
        }
      })
    } else {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'error',
        onClose: () => {
          cloudSearch()
        }
      })
    }
  })
}

</script>

<style scoped>

.cloud-el-input {
  width: 300px;
}

.cloud-el-input, .cloud-el-search {
  margin-left: 5px;
  margin-right: 5px;
}

</style>
