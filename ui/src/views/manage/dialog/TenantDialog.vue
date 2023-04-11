<template>
  <el-container>
    <el-form :model="param" ref="cloudFormRef" label-position="left" label-width="210px" id="cloud-el-form">
      <el-form-item v-if="props.edit" label="tenantId" prop="tenantId">
        <el-input v-model="param.tenantId" disabled/>
      </el-form-item>
      <el-form-item label="tenantName" prop="tenantName"
                    :rules="[{ required: true, message: 'tenantName is required' }]">
        <el-input v-model="param.tenantName"/>
      </el-form-item>
      <el-form-item label="usersId" prop="usersId" :rules="[{ required: true, message: 'usersId is required' }]">
        <el-input v-model="param.usersId"/>
      </el-form-item>
      <el-form-item label="enabled" prop="enabled" :rules="[{ required: true, message: 'enabled is required' }]">
        <el-switch v-model="param.enabled"/>
      </el-form-item>
      <el-form-item label="domainName" prop="domainName">
        <el-input v-model="param.domainName"/>
      </el-form-item>
      <el-form-item label="remark" prop="remark">
        <el-input v-model="param.remark"/>
      </el-form-item>
      <el-form-item>
        <el-button class="cloud-el-button" v-if="props.edit" @click="cloudUpdate">更新</el-button>
        <el-button class="cloud-el-button" v-else @click="cloudSave">保存</el-button>
      </el-form-item>
    </el-form>
  </el-container>
</template>

<script setup lang="ts">
import { reactive, ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { getById, save, updateById } from '../../../api/passport/tenant'
import settings from '../../../settings'

const props = defineProps({
  dialogVisible: {
    type: Boolean
  },
  edit: { // 是否编辑
    type: Boolean
  },
  id: { // 编辑客户主键
    type: Number
  }
})

// 参数
const param = reactive({
  tenantId: null,
  tenantName: null,
  usersId: null,
  enabled: null,
  domainName: null,
  remark: null
})

// 初始化数据
const initData = () => {
  if (props.edit && props.id) {
    getById(props.id).then(response => {
      if (response.code === settings.okCode) {
        const data = response.data
        if (data) {
          for (const name in data) {
            // @ts-ignore
            param[name] = data[name]
          }
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

// 表单验证
const cloudFormRef = ref()

// 保存
const cloudSave = () => {
  cloudFormRef.value.validate((valid: boolean) => {
    if (valid) {
      ElMessageBox.confirm('确认添加？', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        save(param).then(response => {
          console.log(response)
          if (response.code === settings.okCode) {
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
      }).catch(() => {

      })
    }
  })
}

// 更新
const cloudUpdate = () => {
  cloudFormRef.value.validate((valid: boolean) => {
    if (valid) {
      ElMessageBox.confirm('确认更新？', '警告', {
        confirmButtonText: '确认',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        updateById(param).then(response => {
          console.log(response)
          if (response.code === settings.okCode) {
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
      }).catch(() => {

      })
    }
  })
}

</script>

<style scoped>

#cloud-el-form,
.cloud-el-button {
  width: 100%;
}

</style>
