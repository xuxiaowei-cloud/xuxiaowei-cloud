<template>
  <el-container>
    <el-form :model="param" label-position="left" label-width="160px" id="cloud-el-form">
      <el-form-item label="oauthClientDetailsId" v-if="props.edit">
        <el-input v-model="param.oauthClientDetailsId" disabled/>
      </el-form-item>
      <el-form-item label="clientId">
        <el-input v-if="props.edit" v-model="param.clientId" disabled/>
        <el-input v-else v-model="param.clientId"/>
      </el-form-item>
      <el-form-item label="clientSecret">
        <el-input class="cloud-el-password" v-model="param.clientSecret"/>
        <el-button class="cloud-el-password-generate" @click="passwordGenerate">生成随机凭证</el-button>
      </el-form-item>
      <el-form-item label="grantTypes">
        <el-input v-model="param.authorizedGrantTypes"/>
      </el-form-item>
      <el-form-item label="accessTokenValidity">
        <el-input v-model="param.accessTokenValidity"/>
      </el-form-item>
      <el-form-item label="refreshTokenValidity">
        <el-input v-model="param.refreshTokenValidity"/>
      </el-form-item>
      <el-form-item label="scope">
        <el-input v-model="param.scope"/>
      </el-form-item>
      <el-form-item label="autoapprove">
        <el-input v-model="param.autoapprove"/>
      </el-form-item>
      <el-form-item label="redirectUri">
        <el-input v-model="param.webServerRedirectUri"/>
      </el-form-item>
      <el-form-item label="resourceIds">
        <el-input v-model="param.resourceIds"/>
      </el-form-item>
      <el-form-item label="authorities">
        <el-input v-model="param.authorities"/>
      </el-form-item>
      <el-form-item label="additionalInformation">
        <el-input v-model="param.additionalInformation"/>
      </el-form-item>
      <el-form-item>
        <el-button class="cloud-el-button" v-if="props.edit" @click="cloudUpdate">更新</el-button>
        <el-button class="cloud-el-button" v-else @click="cloudSave">保存</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>

<script setup lang="ts">
import { defineEmits, defineProps, reactive, ref } from 'vue'
import { getById, save, updateById } from '../../../api/authorization-server'
import { codeRsa } from '../../../api/user'
import { randomPassword } from '../../../utils/generate'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'
// TS 未能识别，其实不存在问题
// @ts-ignore
import JsEncrypt from 'jsencrypt/bin/jsencrypt.min'

// 缓存
const store = useStore()

const props = defineProps({
  dialogVisible: {
    type: Boolean
  },
  edit: { // 是否编辑
    type: Boolean
  },
  clientId: { // 编辑客户主键
    type: Number
  }
})

// 参数
const param = reactive({
  oauthClientDetailsId: null,
  clientId: null,
  clientSecret: null,
  authorizedGrantTypes: null,
  accessTokenValidity: null,
  refreshTokenValidity: null,
  scope: null,
  autoapprove: null,
  webServerRedirectUri: null,
  resourceIds: null,
  authorities: null,
  additionalInformation: null,
  // 识别码
  code: null
})

// 公钥
const publicKey = ref(null)

// 获取识别码与公钥
codeRsa().then(response => {
  if (response.code === store.state.settings.okCode) {
    if (response.code === store.state.settings.okCode) {
      const data = response.data
      if (data) {
        param.code = data.code
        publicKey.value = data.publicKey
      }
    } else {
      ElMessage.error(response.msg)
    }
  }
})

// 初始化数据
const initData = () => {
  if (props.edit && props.clientId) {
    getById(props.clientId).then(response => {
      if (response.code === store.state.settings.okCode) {
        const data = response.data
        if (data) {
          param.oauthClientDetailsId = data.oauthClientDetailsId
          param.clientId = data.clientId
          param.authorizedGrantTypes = data.authorizedGrantTypes
          param.accessTokenValidity = data.accessTokenValidity
          param.refreshTokenValidity = data.refreshTokenValidity
          param.scope = data.scope
          param.autoapprove = data.autoapprove
          param.webServerRedirectUri = data.webServerRedirectUri
          param.resourceIds = data.resourceIds
          param.authorities = data.authorities
          param.additionalInformation = data.additionalInformation
        }
      } else {
        ElMessage.error(response.msg)
      }
    })
  }
}

const emit = defineEmits(['dialogVisibleClose'])

// 初始化数据
initData()

// 生成随机密码
const passwordGenerate = () => {
  param.clientSecret = randomPassword()
}

// 保存
const cloudSave = () => {
  const paramEncryption = JSON.parse(JSON.stringify(param))
  JsEncrypt.prototype.setPublicKey(publicKey.value)
  paramEncryption.clientSecret = JsEncrypt.prototype.encrypt(param.clientSecret)
  save(paramEncryption).then(response => {
    console.log(response)
    if (response.code === store.state.settings.okCode) {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'success',
        onClose: () => {
          emit('dialogVisibleClose')
        }
      })
    } else {
      ElMessage.error(response.msg)
    }
  })
}

// 更新
const cloudUpdate = () => {
  const paramEncryption = JSON.parse(JSON.stringify(param))
  JsEncrypt.prototype.setPublicKey(publicKey.value)
  paramEncryption.clientSecret = JsEncrypt.prototype.encrypt(param.clientSecret)
  updateById(paramEncryption).then(response => {
    console.log(response)
    if (response.code === store.state.settings.okCode) {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'success',
        onClose: () => {
          emit('dialogVisibleClose')
        }
      })
    } else {
      ElMessage.error(response.msg)
    }
  })
}

</script>

<style scoped>

#cloud-el-form,
.cloud-el-button {
  width: 100%;
}

.cloud-el-password {
  width: calc(100% - 116px - 4px);
}

.cloud-el-password-generate {
  margin-left: 4px;
}

</style>
