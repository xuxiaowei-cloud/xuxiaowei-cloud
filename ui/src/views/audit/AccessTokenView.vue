<template>
  <div id="cloud-el-search">
    <el-input class="cloud-el-input" clearable v-model="param.oauthAccessTokenId" placeholder="Please input oauthAccessTokenId" />
    <el-input class="cloud-el-input" clearable v-model="param.refreshToken" placeholder="Please input refreshToken" />
    <el-input class="cloud-el-input" clearable v-model="param.userName" placeholder="Please input userName" />
    <el-input class="cloud-el-input" clearable v-model="param.clientId" placeholder="Please input clientId" />
    <el-input class="cloud-el-input" clearable v-model="param.remoteAddress" placeholder="Please input remoteAddress" />
    <el-input class="cloud-el-input" clearable v-model="param.scope" placeholder="Please input scope" />
    <el-input class="cloud-el-input" clearable v-model="param.redirectUri" placeholder="Please input redirectUri" />
    <el-input class="cloud-el-input" clearable v-model="param.responseType" placeholder="Please input responseType" />
    <el-input class="cloud-el-input" clearable v-model="param.accessToken" placeholder="Please input accessToken" />
    <el-input class="cloud-el-input" clearable v-model="param.authenticationId" placeholder="Please input authenticationId" />
    <el-input class="cloud-el-input" clearable v-model="param.jti" placeholder="Please input jti" />
    <el-input class="cloud-el-input" clearable v-model="param.refreshTokenEncryption" placeholder="Please input refreshTokenEncryption" />
    <el-input class="cloud-el-input" clearable v-model="param.tokenId" placeholder="Please input tokenId" />
    <el-input class="cloud-el-input" clearable v-model="param.sessionId" placeholder="Please input sessionId" />
    <el-input class="cloud-el-input" clearable v-model="param.state" placeholder="Please input state" />
    <el-button class="cloud-el-search" @click="cloudSearch">搜索</el-button>
  </div>
  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460">
      <el-table-column prop="oauthAccessTokenId" label="oauthAccessTokenId" width="165"/>
      <el-table-column prop="refreshToken" label="refreshToken" width="290" :show-overflow-tooltip="true"/>
      <el-table-column prop="userName" label="userName" width="130"/>
      <el-table-column prop="clientId" label="clientId" width="160"/>
      <el-table-column prop="remoteAddress" label="remoteAddress" width="130"/>
      <el-table-column prop="createDate" label="createDate" width="160"/>
      <el-table-column prop="updateDate" label="updateDate" width="160"/>
      <el-table-column prop="deleted" label="deleted" width="100"/>
      <el-table-column prop="scope" label="scope" width="130"/>
      <el-table-column prop="redirectUri" label="redirectUri" width="260" :show-overflow-tooltip="true"/>
      <el-table-column prop="responseType" label="responseType" width="130"/>
      <el-table-column prop="tokenJson" label="tokenJson" width="160" :show-overflow-tooltip="true"/>
      <el-table-column prop="tokenType" label="tokenType" width="95"/>
      <el-table-column prop="accessToken" label="accessToken" width="130" :show-overflow-tooltip="true"/>
      <el-table-column prop="expiration" label="expiration" width="160"/>
      <el-table-column prop="authenticationId" label="authenticationId" width="270"/>
      <el-table-column prop="jti" label="jti" width="270"/>
      <el-table-column prop="refreshTokenEncryption" label="refreshTokenEncryption" width="190" :show-overflow-tooltip="true"/>
      <el-table-column prop="refreshTokenExpiration" label="refreshTokenExpiration" width="190"/>
      <el-table-column prop="tokenId" label="tokenId" width="300"/>
      <el-table-column prop="sessionId" label="sessionId" width="300"/>
      <el-table-column prop="state" label="state" width="300"/>
      <el-table-column prop="authoritiesJson" label="authorities" width="160" :show-overflow-tooltip="true"/>
      <el-table-column prop="authenticationJson" label="authentication" width="160" :show-overflow-tooltip="true"/>
      <el-table-column fixed="right" label="Operations" width="100">
        <template #default="scope">
          <el-button type="text" size="small" v-if="scope.row.deleted" disabled>Delete</el-button>
          <el-button type="text" size="small" v-else @click="deleteAccessTokenId(scope.row.oauthAccessTokenId)">Delete</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-container>
  <el-container>
    <el-pagination layout="prev, pager, next, total" @current-change="currentChange" :total="param.total" />
  </el-container>
</template>

<script setup lang="ts">
import { page, removeById } from '../../api/audit/access-token'
import { ref, reactive } from 'vue'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'

const store = useStore()

const tableData = ref([])
const param = reactive({
  current: 1,
  size: 10,
  total: 0,
  oauthAccessTokenId: null,
  refreshToken: null,
  userName: null,
  clientId: null,
  remoteAddress: null,
  scope: null,
  redirectUri: null,
  responseType: null,
  accessToken: null,
  authenticationId: null,
  jti: null,
  refreshTokenEncryption: null,
  tokenId: null,
  sessionId: null,
  state: null
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

// 删除授权Token
const deleteAccessTokenId = (e: number) => {
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
