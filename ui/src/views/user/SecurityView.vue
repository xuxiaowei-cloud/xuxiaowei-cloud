<template>
  <el-container>

    <el-header class="cloud-header m-20px text-center"/>

    <el-main class="cloud-main">
      <el-form class="cloud-form" label-width="70px">
        <el-form-item label="手机号">
          <el-input v-model.trim="phone" readonly>
            <template #append>
              <el-button :icon="Edit" @click="phoneEdit"/>
            </template>
          </el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model.trim="email" readonly>
            <template #append>
              <el-button :icon="Edit" @click="emailEdit"/>
            </template>
          </el-input>
        </el-form-item>
      </el-form>
    </el-main>

    <!-- 修改手机号弹窗 -->
    <el-dialog v-if="securityPhoneDialogVisible" v-model="securityPhoneDialogVisible" title="修改手机号" width="500px"
               :before-close="securityPhoneDialogHandleClose">
      <SecurityPhone :dialogVisible="securityPhoneDialogVisible" @securityPhoneDialogVisibleClose="securityPhoneDialogVisibleClose"/>
    </el-dialog>

  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { ElMessage } from 'element-plus'
import { Edit } from '@element-plus/icons-vue'
import { security } from '../../api/user'
import settings from '../../settings'
import SecurityPhone from './dialog/SecurityPhoneDialog.vue'

const phone = ref()
const email = ref()

// 初始化数据
const initData = () => {
  security().then(response => {
    console.log(response)
    if (response.code === settings.okCode) {
      phone.value = response.data?.phone
      email.value = response.data?.email
    } else {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'error',
        onClose: () => {

        }
      })
    }
  })
}

initData()

// 修改手机号弹窗：是否打开
const securityPhoneDialogVisible = ref(false)

// 修改手机号弹窗关闭：弹窗右上角的 x
const securityPhoneDialogHandleClose = (done: () => void) => {
  console.log('关闭修改手机号弹窗')
  done()
  // 关闭窗口后，重新搜索
  initData()
}

// 个人中心弹窗关闭：子窗口使用
const securityPhoneDialogVisibleClose = () => {
  // 个人中心弹窗：打开
  securityPhoneDialogVisible.value = false
  // 关闭窗口后，重新搜索
  initData()
}

const phoneEdit = () => {
  securityPhoneDialogVisible.value = true
}

const emailEdit = () => {
  alert('修改邮箱功能未开发')
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

}

/* sm≥768px 响应式栅格数或者栅格属性对象 */
@media only screen and (min-width: 768px) {

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
