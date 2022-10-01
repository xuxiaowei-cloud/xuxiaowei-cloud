<template>
  <el-container>

    <el-main class="cloud-main">
      <el-form :model="param" ref="cloudFormRef" label-position="left" class="cloud-form">

        <el-form-item prop="phone"
                      :rules="[{ required: true, message: '新手机号不能为空' }, { validator: phoneValidator, message: '新手机号不正确', trigger: ['change', 'blur'] }]">
          <el-input id="phone" ref="phoneRef" v-model="param.phone" type="number" placeholder="请输入新手机号">
            <template #append>
              <el-button @click="sms">获取短信验证码</el-button>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item prop="code" :rules="[{ required: true, message: '短信验证码不能为空' }]">
          <el-input v-model="param.code" placeholder="请输入短信验证码"/>
        </el-form-item>

        <el-form-item>
          <el-button class="w-100%" @click="submitCloudForm">更新</el-button>
        </el-form-item>

      </el-form>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { ref, reactive } from 'vue'
import { securityPhone, securityPhoneUpdate } from '../../../api/user/security'
import settings from '../../../settings'

// 参数
const param = reactive({
  phone: '',
  code: ''
})

const phoneValidator = () => {
  return param.phone && param.phone.length === 11
}

const phoneRef = ref()

// 识别码
const identification = ref('')

// 获取短信验证码
const sms = () => {
  if (param.phone === '') {
    // 光标先进入手机号输入框
    phoneRef.value.focus()
    // 需要有延时，否则无法触发验证
    setTimeout(function () {
      // 光标再移开，触发验证
      phoneRef.value.blur()
      // 光标再进入手机号输入框，方便用户输入
      phoneRef.value.focus()
    }, 1)
  } else {
    securityPhone(param.phone).then(response => {
      console.log(response)
      if (response.code === settings.okCode) {
        identification.value = response.data?.identification
        ElMessage({
          message: response.msg,
          // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
          duration: 1500,
          type: 'success',
          onClose: () => {

          }
        })
      } else {
        ElMessage.error(response.msg)
      }
    })
  }
}

// 表单验证
const cloudFormRef = ref()

const emit = defineEmits(['securityPhoneDialogVisibleClose'])

const submitCloudForm = () => {
  cloudFormRef.value.validate((valid: boolean) => {
    if (valid) {
      securityPhoneUpdate(param.phone, param.code, identification.value).then(response => {
        console.log(response)
        if (response.code === settings.okCode) {
          ElMessage({
            message: response.msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 1500,
            type: 'success',
            onClose: () => {
              emit('securityPhoneDialogVisibleClose')
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

/*数字输入框不显示右侧的箭头*/
#phone::-webkit-outer-spin-button,
#phone::-webkit-inner-spin-button {
  -webkit-appearance: none !important;
}

/*数字输入框不显示右侧的箭头*/
#phone[type='number'] {
  -moz-appearance: textfield;
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

}

/* sm≥768px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 768px) {

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
