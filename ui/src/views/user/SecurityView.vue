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

  </el-container>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { Edit } from '@element-plus/icons-vue'

const phone = ref()
const email = ref()

const phoneEdit = () => {
  alert('修改手机号功能未开发')
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
