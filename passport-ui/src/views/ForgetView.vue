<template>
  <el-container>
    <el-header class="cloud-header m-20px">
      <h1>重置密码</h1>
      <div>请通过输入用户名/手机号码/绑定邮箱重置你的帐号密码</div>
    </el-header>

    <el-main class="cloud-main">
      <el-form class="cloud-form" ref="cloudFormRef" :model="cloudForm">
        <el-form-item prop="username"
                      :rules="[{ required: true, message: '用户名/手机号码/绑定邮箱必填' }]">
          <el-input v-model.trim="cloudForm.username" :prefix-icon="User" placeholder="用户名/手机号码/绑定邮箱"/>
        </el-form-item>
        <el-form-item>
          <el-button class="submit-cloud-form" @click="submitCloudForm()">重置密码</el-button>
        </el-form-item>
      </el-form>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { User } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import { forget } from '../api/user'

// 表单中的值
const cloudForm = reactive({
  username: ''
})

// 表单验证
const cloudFormRef = ref(null)

// 提交表单
const submitCloudForm = () => {
  // @ts-ignore
  cloudFormRef.value.validate(valid => {
    if (valid) {
      forget({
        username: cloudForm.username
      }).then((response: any) => {
        console.log(response)
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
