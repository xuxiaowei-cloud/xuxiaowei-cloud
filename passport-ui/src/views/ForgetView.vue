<template>
  <el-container>
    <el-header class="cloud-header m-20px">
      <h1>重置密码</h1>
      <div v-if="type == null">请通过输入用户名/手机号码/绑定邮箱重置你的帐号密码</div>
    </el-header>

    <el-main class="cloud-main">
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
          <el-input v-model="cloudFormTypePhone.code" placeholder="请输入验证码" />

          <div class="h-20px"/>

          <div>
            {{ typeMsg }}
          </div>

          <div class="h-20px"/>

          <el-button class="w-100%">验证</el-button>

        </el-form>
      </div>

    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { ElMessage } from 'element-plus'
import { User } from '@element-plus/icons-vue'
import { reactive, ref } from 'vue'
import settings from '../settings'
import { forget } from '../api/user'

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
      forget({
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
  usersId: null,
  code: null
})

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
