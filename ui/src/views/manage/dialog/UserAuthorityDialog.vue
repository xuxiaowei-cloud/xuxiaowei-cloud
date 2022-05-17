<template>
  <el-container>
    <el-button class="cloud-el-button" @click="cloudSave">保存</el-button>
  </el-container>
</template>

<script setup lang="ts">
import { defineEmits, defineProps } from 'vue'
import { getById } from '../../../api/user'
import { ElMessage } from 'element-plus/es'
import { useStore } from 'vuex'

// 缓存
const store = useStore()

const props = defineProps({
  dialogVisible: {
    type: Boolean
  },
  usersId: { // 编辑用户权限主键
    type: Number
  }
})

// 初始化数据
const initData = () => {
  if (props.usersId) {
    getById(props.usersId).then(response => {
      if (response.code === store.state.settings.okCode) {
        const data = response.data
        if (data) {
          console.log(data.authoritiesList)
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

// 保存
const cloudSave = () => {
  emit('dialogVisibleClose')
}

</script>

<style scoped>

</style>
