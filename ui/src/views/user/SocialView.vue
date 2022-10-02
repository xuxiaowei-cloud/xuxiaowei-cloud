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

// 绑定
const binding = (row: { socialCode: string; }) => {
  location.href = 'http://gateway.example.xuxiaowei.cloud:1101/passport/wechat-oplatform/website/authorize/wx5b3079e6eba267ac?binding=true&access_token=' + useStore.getAccessToken
  // const login = new WxLogin({
  //   self_redirect: true,
  //   id: 'login_container',
  //   appid: 'wx5b3079e6eba267ac',
  //   scope: 'snsapi_login',
  //   redirect_uri: 'http://gateway.example.xuxiaowei.cloud:1101/passport/wechat-oplatform/website/code/wx5b3079e6eba267ac',
  //   state: '',
  //   style: '',
  //   href: ''
  // })
}

</script>

<style scoped>

</style>
