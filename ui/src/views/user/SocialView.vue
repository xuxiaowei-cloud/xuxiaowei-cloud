<template>
  <!-- 引入微信扫码登录：网站内嵌二维码微信登录 JS -->
  <!-- <component :is="'script'" src="https://res.wx.qq.com/connect/zh_CN/htmledition/js/wxLogin.js"/> -->
  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460">
      <el-table-column prop="socialName" label="socialName" width="200"/>
      <el-table-column prop="nickname" label="nickname" width="200"/>
      <el-table-column prop="headimgurl" label="headimgurl" width="200">
        <template v-slot="scope">
          <el-image style="width: 40px; height: 40px" :src="scope.row.headimgurl" fit="fill"/>
        </template>
      </el-table-column>
      <el-table-column prop="bindingDate" label="bindingDate" width="200"/>
      <el-table-column fixed="right" label="Operations" width="230">
        <template #default="scope">
          <el-button size="small" v-if="scope.row.binding" @click="unbinding(scope.row)">解绑</el-button>
          <el-button size="small" v-else @click="binding(scope.row)">绑定</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-container>

  <div id="login_container"/>

</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { social, socialUnbinding } from '../../api/user/social'
import settings from '../../settings'
import { useStore } from '../../store'
import { configuration } from '../../api/passport'

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

// 解绑
const unbinding = (row: { socialName: string; socialCode: string; }) => {
  ElMessageBox.confirm(`确认解绑${row.socialName}？`, '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    socialUnbinding(row.socialCode).then(response => {
      console.log(response)
      const msg = response.msg
      if (response.code === settings.okCode) {
        ElMessage({
          message: msg,
          // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
          duration: 1500,
          type: 'success',
          onClose: () => {
            cloudSearch()
          }
        })
      } else {
        ElMessage({
          message: msg,
          // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
          duration: 1500,
          type: 'error',
          onClose: () => {
            cloudSearch()
          }
        })
      }
    })
  }).catch(() => {

  })
}

const weChatOplatformWebsiteUrl = ref()
const giteeUrl = ref()
const qqWebsiteUrl = ref()
const weiBoWebsiteUrl = ref()
const gitlabUrl = ref()
const weChatWorkWebsiteUrl = ref()

configuration().then(response => {
  console.log(response)
  const msg = response.msg
  if (response.code === settings.okCode) {
    weChatOplatformWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/wechat-oplatform/website/authorize/${response.data.weChatOplatformWebsiteAppid}?binding=true&access_token=${useStore.getAccessToken}`
    giteeUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/gitee/authorize/${response.data.giteeAppid}&binding=true&access_token=${useStore.getAccessToken}`
    qqWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/qq/website/authorize/${response.data.qqWebsiteAppid}&binding=true&access_token=${useStore.getAccessToken}`
    weiBoWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/weibo/authorize/${response.data.weiBoWebsiteAppid}?binding=true&access_token=${useStore.getAccessToken}`
    gitlabUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/gitlab/authorize/${response.data.gitlabAppid}?binding=true&access_token=${useStore.getAccessToken}`
    weChatWorkWebsiteUrl.value = `${import.meta.env.VITE_APP_BASE_API}/passport/wechat-work/website/authorize/${response.data.weChatWorkWebsiteAppid}/${response.data.weChatWorkWebsiteAgentid}?binding=true&access_token=${useStore.getAccessToken}`
  } else {
    ElMessage.error(msg)
  }
})

// 绑定
const binding = (row: { socialCode: string; }) => {
  if (row.socialCode) {
    switch (row.socialCode) {
      case '1':
        location.href = weChatOplatformWebsiteUrl.value
        break
      case '2':
        location.href = giteeUrl.value
        break
      case '3':
        location.href = qqWebsiteUrl.value
        break
      case '4':
        location.href = weiBoWebsiteUrl.value
        break
      case '5':
        location.href = gitlabUrl.value
        break
      case '6':
        location.href = weChatWorkWebsiteUrl.value
        break
    }
  }
}

</script>

<style scoped>

</style>
