<template>
  <el-container>
    <el-header class="cloud-header m-20px">
      <h1>重置密码</h1>
      <div>重置密码完成后，所有已登录的应用均被强制下线</div>
      <br>
      <div v-if="type == null">请通过输入用户名/手机号码/绑定邮箱重置你的帐号密码</div>
    </el-header>

    <el-main class="cloud-main" v-if="main">
      <el-form class="cloud-form" ref="cloudFormRef" :model="cloudForm" v-if="type == null">
        <el-form-item prop="username"
                      :rules="[{ required: true, message: '用户名/手机号码/绑定邮箱必填' }]">
          <el-input v-model.trim="cloudForm.username" :prefix-icon="User" placeholder="用户名/手机号码/绑定邮箱"/>
        </el-form-item>
        <el-form-item>
          <el-button class="submit-cloud-form" @click="submitCloudForm()">重置密码</el-button>
        </el-form-item>
      </el-form>

      <div v-else>
        <div v-if="type === 'email'">
          {{ typeMsg }}
        </div>

        <el-form class="cloud-form" ref="cloudFormTypePhoneRef" :model="cloudFormTypePhone" v-if="type === 'phone'">

          <div>
            {{ typeMsg }}
          </div>

          <div class="h-20px"/>

          <el-form-item prop="code" :rules="[{ required: true, message: '短信验证码必填' }]">
            <el-input v-model.trim="cloudFormTypePhone.code" :prefix-icon="User" placeholder="请输入短信验证码"/>
          </el-form-item>
          <el-form-item prop="password" :rules="[{ required: true, message: '新密码必填' }]">
            <el-input type="password" v-model.trim="cloudFormTypePhone.password" :prefix-icon="Key" placeholder="请输入新密码"/>
          </el-form-item>
          <el-form-item prop="confirmPassword"
                        :rules="[{ required: true, message: '确认密码必填' }, { validator: confirmPasswordValidator, message: '确认密码不匹配', trigger: ['change', 'blur'] }]">
            <el-input type="password" v-model.trim="cloudFormTypePhone.confirmPassword" :prefix-icon="Key"
                      placeholder="请输入确认密码"/>
          </el-form-item>
          <el-form-item>
            <el-button class="w-100%" @click="submitCloudTypePhoneForm()">重置密码</el-button>
          </el-form-item>

        </el-form>
      </div>

    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { User, Key } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import { JSEncrypt } from 'jsencrypt'
import settings from '../settings'
import { forget, resetTypePhonePassword } from '../api/forget'

// 主要内容：是否显示
const main = ref<boolean>(true)

// 表单中的值
const cloudForm = reactive({
  username: ''
})

// 表单验证
const cloudFormRef = ref(null)

// 重置类型
const type = ref(null)
// 重置消息
const typeMsg = ref(null)

// 重置用户主键
const usersId = ref(null)

// 提交表单
const submitCloudForm = () => {
  // @ts-ignore
  cloudFormRef.value.validate(valid => {
    if (valid) {
      let header = 'header'
      let token = 'token'
      // @ts-ignore
      if (process.env.NODE_ENV === 'production') {
        // @ts-ignore
        header = document.head.querySelector('[name=_csrf_header][content]').content
        // @ts-ignore
        token = document.head.querySelector('[name=_csrf][content]').content
      }

      forget(header, token, {
        username: cloudForm.username
      }).then((response: any) => {
        console.log(response)
        if (response.code === settings.okCode) {
          typeMsg.value = response.msg
          type.value = response.data?.type
          usersId.value = response.data?.usersId
        } else {
          ElMessage.error(response.msg)
        }
      })
    }
  })
}

const cloudFormTypePhone = reactive({
  code: '',
  password: '',
  confirmPassword: ''
})

const confirmPasswordValidator = () => {
  return cloudFormTypePhone.password === cloudFormTypePhone.confirmPassword
}

const cloudFormTypePhoneRef = ref(null)

const submitCloudTypePhoneForm = () => {
  // @ts-ignore
  cloudFormTypePhoneRef.value.validate(valid => {
    if (valid) {
      let header = 'header'
      let token = 'token'
      let password = cloudFormTypePhone.password
      // @ts-ignore
      if (process.env.NODE_ENV === 'production') {
        // @ts-ignore
        header = document.head.querySelector('[name=_csrf_header][content]').content
        // @ts-ignore
        token = document.head.querySelector('[name=_csrf][content]').content
        // @ts-ignore
        const rsaPublicKeyBase64 = document.head.querySelector('[name=rsa_public_key_base64][content]').content

        const jsEncrypt = new JSEncrypt()
        jsEncrypt.setPublicKey(rsaPublicKeyBase64)
        const encrypt = jsEncrypt.encrypt(password)
        if (encrypt === false) {
          ElMessage.error('密码加密失败')
          return
        }

        password = encrypt
      }

      resetTypePhonePassword(header, token, { usersId: usersId.value, code: cloudFormTypePhone.code, password }).then((response: any) => {
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
<style>

.cloud-form .el-form-item .el-form-item__label {
  width: 0;
}

</style>
<style scoped>

.cloud-header {
  /* 高度自动，原 el-header、el-footer 均为固定高度 */
  height: auto;
}

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

  /* 表达宽度 */
  .cloud-form {
    width: 300px;
  }

  .cloud-form .el-input,
  .submit-cloud-form {
    /* xs ：输入框、按钮宽度 */
    width: 300px;
  }

}

/* sm≥768px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 768px) {

  .cloud-form .el-input,
  .submit-cloud-form {
    /* 非 xs ：输入框、按钮宽度 */
    width: 375px;
  }

  .cloud-form {
    /* 非 xs ：表单宽度 */
    width: 375px;
  }
}

</style>
