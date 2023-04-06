<template>
  <el-container>
    <el-header class="cloud-header m-20px">
      <h1>重置密码</h1>
    </el-header>

    <el-main class="cloud-main" v-if="main">
      <el-form class="cloud-form" ref="cloudFormRef" :model="cloudForm">
        <el-form-item prop="password" :rules="[{ required: true, message: '新密码必填' }]">
          <el-input type="password" v-model.trim="cloudForm.password" :prefix-icon="Key" placeholder="请输入新密码"/>
        </el-form-item>
        <el-form-item prop="confirmPassword"
                      :rules="[{ required: true, message: '确认密码必填' }, { validator: confirmPasswordValidator, message: '确认密码不匹配', trigger: ['change', 'blur'] }]">
          <el-input type="password" v-model.trim="cloudForm.confirmPassword" :prefix-icon="Key"
                    placeholder="请输入确认密码"/>
        </el-form-item>
        <el-form-item>
          <el-button class="submit-cloud-form" @click="submitCloudForm()">重置密码</el-button>
        </el-form-item>
      </el-form>
    </el-main>

  </el-container>
</template>

<script setup lang="ts">
import { useRoute, useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'
import { Key } from '@element-plus/icons-vue'
import { JSEncrypt } from 'jsencrypt'
import { checkResetPasswordToken, resetPassword } from '../api/forget'
import settings from '../settings'
import Resp from '../api/common'

const route = useRoute()
const router = useRouter()

// 主要内容：是否显示
const main = ref<boolean>(true)

const usersId = ref()
const resetPasswordToken = ref()

router.isReady().then(() => {
  usersId.value = route.query.usersId
  resetPasswordToken.value = route.query.reset_password_token

  let header = 'header'
  let token = 'token'
  if (process.env.NODE_ENV === 'production') {
    const csrfHTMLElementHeaderHTMLElement = document.head.querySelector('[name=_csrfHTMLElement_header][content]') as HTMLElement | null
    if (csrfHTMLElementHeaderHTMLElement) {
      header = csrfHTMLElementHeaderHTMLElement.getAttribute('content') || ''
    }

    const csrfHTMLElement = document.head.querySelector('[name=_csrf][content]') as HTMLElement | null
    if (csrfHTMLElement) {
      token = csrfHTMLElement.getAttribute('content') || ''
    }
  }

  checkResetPasswordToken(header, token, {
    usersId: usersId.value,
    resetPasswordToken: resetPasswordToken.value
  }).then((response: Resp<any>) => {
    console.log(response)
    if (response.code !== settings.okCode) {
      ElMessage.error(response.msg)
    }
  })
})

// 表单中的值
const cloudForm = reactive({
  password: '',
  confirmPassword: ''
})

// 保证确认密码与新密码一致
const confirmPasswordValidator = () => {
  return cloudForm.password === cloudForm.confirmPassword
}

// 表单验证
const cloudFormRef = ref()

const submitCloudForm = () => {
  cloudFormRef.value.validate((valid: boolean) => {
    if (valid) {
      let header = 'header'
      let token = 'token'
      let password = cloudForm.password
      if (process.env.NODE_ENV === 'production') {
        const csrfHeaderHTMLElement = document.head.querySelector('[name=_csrf_header][content]') as HTMLElement | null
        if (csrfHeaderHTMLElement) {
          header = csrfHeaderHTMLElement.getAttribute('content') || ''
        }

        const csrfHTMLElement = document.head.querySelector('[name=_csrf][content]') as HTMLElement | null
        if (csrfHTMLElement) {
          token = csrfHTMLElement.getAttribute('content') || ''
        }

        const rsaPublicKeyBase64HTMLElement = document.head.querySelector('[name=rsa_public_key_base64][content]') as HTMLElement | null
        if (rsaPublicKeyBase64HTMLElement) {
          const publicKey = rsaPublicKeyBase64HTMLElement.getAttribute('content') || ''

          const jsEncrypt = new JSEncrypt()
          jsEncrypt.setPublicKey(publicKey)
          const encrypt = jsEncrypt.encrypt(password)
          if (encrypt === false) {
            ElMessage.error('密码加密失败')
            return
          }

          password = encrypt
        }
      }

      resetPassword(header, token, {
        usersId: usersId.value,
        resetPasswordToken: resetPasswordToken.value,
        password
      }).then((response: Resp<any>) => {
        console.log(response)
        if (response.code === settings.okCode) {
          ElMessage({
            message: response.msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 1500,
            type: 'success',
            onClose: () => {
              // 关闭主要内容
              main.value = false
            }
          })
        } else {
          ElMessage({
            message: response.msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 3000,
            type: 'error'
          })
        }
      })
    }
  })
}

</script>

<style scoped>

.cloud-form {
  /* form 居中 */
  margin-left: auto;
  margin-right: auto;
}

/* xs<768px 响应式栅格数或者栅格属性对象 */
@media only screen and (max-width: 768px) {

  /* xs 主内容无内边距 */
  .cloud-main {
    --el-main-padding: 0;
    margin-left: 0;
    margin-right: 0;
  }

  /* 表单宽度 */
  .cloud-form {
    width: 300px;
  }

  .cloud-form .el-input,
  .remember-me,
  .submit-cloud-form {
    /* xs ：输入框、按钮宽度 */
    width: 280px;
  }

}

/* sm≥768px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 768px) {

  .cloud-form .el-input,
  .remember-me,
  .submit-cloud-form {
    /* 非 xs ：输入框、按钮宽度 */
    width: 335px;
  }

  .cloud-form {
    /* 非 xs ：表单宽度 */
    width: 375px;
  }
}

/* md≥992px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 992px) {

}

/* lg≥1200px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 1200px) {

}

/* xl≥1920px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 1920px) {

}

</style>
