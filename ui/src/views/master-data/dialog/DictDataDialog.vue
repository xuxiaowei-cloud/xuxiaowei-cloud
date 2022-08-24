<template>
  <el-container>
    <el-form :model="param" ref="cloudFormRef" label-position="left" label-width="210px" id="cloud-el-form">

      <el-form-item label="dictCode" v-if="props.edit">
        <el-input v-model="param.dictCode" disabled/>
      </el-form-item>
      <el-form-item label="dictCode" prop="dictCode" v-else
                    :rules="[{ required: true, message: 'dictCode is required' }]">
        <el-input v-model="param.dictCode"/>
      </el-form-item>

      <el-form-item label="dictDataCode" v-if="props.edit">
        <el-input v-model="param.dictDataCode" disabled/>
      </el-form-item>
      <el-form-item label="dictDataCode" prop="dictDataCode" v-else
                    :rules="[{ required: true, message: 'dictDataCode is required' }]">
        <el-input v-model="param.dictDataCode"/>
      </el-form-item>

      <el-form-item label="dictDataLabel" prop="dictDataLabel"
                    :rules="[{ required: true, message: 'dictDataLabel is required' }]">
        <el-input v-if="props.edit" v-model="param.dictDataLabel"/>
        <el-input v-else v-model="param.dictDataLabel"/>
      </el-form-item>

      <el-form-item label="dictDataSort" prop="dictDataSort">
        <el-input v-if="props.edit" v-model="param.dictDataSort"/>
        <el-input v-else v-model="param.dictDataSort"/>
      </el-form-item>

      <el-form-item label="dictDataExplain" prop="dictDataExplain"
                    :rules="[{ required: true, message: 'dictDataExplain is required' }]">
        <el-input v-if="props.edit" v-model="param.dictDataExplain"/>
        <el-input v-else v-model="param.dictDataExplain"/>
      </el-form-item>

      <el-form-item label="externalCodeOne" prop="externalCodeOne">
        <el-input v-if="props.edit" type="number" v-model="param.externalCodeOne"/>
        <el-input v-else v-model="param.externalCodeOne" type="number"/>
      </el-form-item>

      <el-form-item label="externalLabelOne" prop="externalLabelOne">
        <el-input v-if="props.edit" v-model="param.externalLabelOne"/>
        <el-input v-else v-model="param.externalLabelOne"/>
      </el-form-item>

      <el-form-item label="remark" prop="remark">
        <el-input v-if="props.edit" v-model="param.remark"/>
        <el-input v-else v-model="param.remark"/>
      </el-form-item>

      <el-form-item>
        <el-button class="cloud-el-button" v-if="props.edit" @click="cloudUpdate">更新</el-button>
        <el-button class="cloud-el-button" v-else @click="cloudSave">保存</el-button>
      </el-form-item>

    </el-form>
  </el-container>
</template>

<script setup lang="ts">
import { ElMessage, ElMessageBox } from 'element-plus'
import { getById, save, updateById } from '../../../api/master-data/dict-data'
import settings from '../../../settings'
import { reactive, ref } from 'vue'

const props = defineProps({
  dialogVisible: {
    type: Boolean
  },
  edit: { // 是否编辑
    type: Boolean
  },
  dictCode: {
    type: String
  },
  dictDataCode: {
    type: String
  }
})

// 参数
const param = reactive({
  dictCode: null,
  dictDataCode: null,
  dictDataLabel: null,
  dictDataSort: null,
  dictDataExplain: null,
  externalCodeOne: null,
  externalLabelOne: null,
  remark: null
})

// 初始化数据
const initData = () => {
  if (props.edit && props.dictCode && props.dictDataCode) {
    getById({ dictCode: props.dictCode, dictDataCode: props.dictDataCode }).then(response => {
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

initData()

// 表单验证
const cloudFormRef = ref(null)

// 保存
const cloudSave = () => {
  // @ts-ignore
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
  // @ts-ignore
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
