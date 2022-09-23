<template>
  <el-container>

    <el-header class="cloud-header m-20px text-center">
      <div>重置密码完成后，所有已登录的应用均被强制下线</div>
    </el-header>

    <el-main class="cloud-main">
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
          <el-button class="submit-cloud-form w-100%" @click="submitCloudForm()">重置密码</el-button>
        </el-form-item>
      </el-form>
    </el-main>

  </el-container>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { reactive, ref } from 'vue'
import { Key } from '@element-plus/icons-vue'
import { passportReset } from '../../api/passport'
import settings from '../../settings'

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
      passportReset({ password: cloudForm.password }).then(response => {
        if (response.code === settings.okCode) {
          ElMessage({
            message: response.msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 1500,
            type: 'success',
            onClose: () => {
              location.reload()
            }
          })
        } else {
          ElMessage({
            message: response.msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 1500,
            type: 'error',
            onClose: () => {
              location.reload()
            }
          })
        }
      })
    }
  })
}

</script>

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
    /*width: 280px;*/
  }

}

/* sm≥768px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 768px) {

  .cloud-form .el-input,
  .submit-cloud-form {
    /* 非 xs ：输入框、按钮宽度 */
    /*width: 335px;*/
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
