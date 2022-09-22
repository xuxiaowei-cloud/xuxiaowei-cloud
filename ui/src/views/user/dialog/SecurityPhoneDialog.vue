<template>
  <el-container>

    <el-main class="cloud-main">
      <el-form :model="param" ref="cloudFormRef" label-position="left" class="cloud-form">

        <el-form-item>
          <el-input v-model="param.phone" placeholder="请输入新手机号">
            <template #append>
              <el-button @click="sms">获取短信验证码</el-button>
            </template>
          </el-input>
        </el-form-item>

        <el-form-item>
          <el-input v-model="param.captcha" placeholder="请输入短信验证码"/>
        </el-form-item>

        <el-form-item>
          <el-button class="w-100%">更新</el-button>
        </el-form-item>

      </el-form>
    </el-main>
  </el-container>
</template>

<script setup lang="ts">
import { reactive } from 'vue'

// 参数
const param = reactive({
  phone: null,
  captcha: null
})

// 获取短信验证码
const sms = () => {

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
