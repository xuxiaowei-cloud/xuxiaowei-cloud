<template>
  <el-container>
    <el-form :model="param" label-position="left" label-width="160px" id="cloud-el-form">
      <el-form-item label="usersId" v-if="props.edit">
        <el-input v-model="param.usersId" disabled/>
      </el-form-item>
      <el-form-item label="username">
        <el-input v-model="param.username"/>
      </el-form-item>
      <el-form-item label="nickname">
        <el-input v-model="param.nickname"/>
      </el-form-item>
      <el-form-item label="password">
        <el-input class="cloud-el-password" v-model="param.password"/>
        <el-button class="cloud-el-password-generate" @click="passwordGenerate">生成随机密码</el-button>
      </el-form-item>
      <el-form-item label="enabled">
        <el-switch v-model="param.enabled"/>
      </el-form-item>
      <el-form-item label="accountNonExpired">
        <el-switch v-model="param.accountNonExpired"/>
      </el-form-item>
      <el-form-item label="credentialsNonExpired">
        <el-switch v-model="param.credentialsNonExpired"/>
      </el-form-item>
      <el-form-item label="accountNonLocked">
        <el-switch v-model="param.accountNonLocked"/>
      </el-form-item>

      <el-form-item>
        <el-button class="cloud-el-button" v-if="props.edit" @click="cloudUpdate">更新</el-button>
        <el-button class="cloud-el-button" v-else @click="cloudSave">保存</el-button>
      </el-form-item>

    </el-form>
  </el-container>
</template>

<script setup lang="ts">
import { defineProps, reactive, defineEmits } from 'vue'
import { getById, save, updateById } from '../../../api/user'
import { generatePassword } from '../../../utils/generate'
import { useStore } from 'vuex'
import { ElMessage } from 'element-plus'

// 缓存
const store = useStore()

const props = defineProps({
  dialogVisible: {
    type: Boolean
  },
  edit: { // 是否编辑
    type: Boolean
  },
  usersId: { // 编辑用户主键
    type: Number
  }
})

// 参数
const param = reactive({
  usersId: null,
  username: null,
  nickname: null,
  password: null,
  enabled: true,
  accountNonExpired: true,
  credentialsNonExpired: true,
  accountNonLocked: true
})

// 初始化数据
const initData = () => {
  if (props.edit && props.usersId) {
    getById(props.usersId).then(response => {
      if (response.code === store.state.settings.okCode) {
        const data = response.data
        if (data) {
          param.usersId = data.usersId
          param.username = data.username
          param.nickname = data.nickname
          param.enabled = data.enabled
          param.accountNonExpired = data.accountNonExpired
          param.credentialsNonExpired = data.credentialsNonExpired
          param.accountNonLocked = data.accountNonLocked
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

// 生成随机密码
const passwordGenerate = () => {
  param.password = generatePassword()
}

// 保存
const cloudSave = () => {
  save(param).then(response => {
    console.log(response)
    if (response.code === store.state.settings.okCode) {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'success',
        onClose: () => {
          emit('dialogVisibleClose')
        }
      })
    } else {
      ElMessage.error(response.msg)
    }
  })
}

// 更新
const cloudUpdate = () => {
  updateById(param).then(response => {
    console.log(response)
    if (response.code === store.state.settings.okCode) {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'success',
        onClose: () => {
          emit('dialogVisibleClose')
        }
      })
    } else {
      ElMessage.error(response.msg)
    }
  })
}

</script>

<style scoped>

#cloud-el-form,
.cloud-el-button {
  width: 100%;
}

.cloud-el-password {
  width: calc(100% - 116px - 4px);
}

.cloud-el-password-generate {
  margin-left: 4px;
}

</style>
