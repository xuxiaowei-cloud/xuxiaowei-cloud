<template>
  <div style="text-align: center">
    <!-- :left-default-checked="[2, 3]" :right-default-checked="[1]" -->
    <el-transfer v-model="rightValue" style="text-align: left; display: inline-block" filterable
                 :titles="['Source', 'Target']"
                 :button-texts="['To left', 'To right']" :data="data" @change="handleChange"
                 :format="{ noChecked: '${total}', hasChecked: '${checked}/${total}',}">
      <template #default="{ option }">
        <span>{{ option.key }} - {{ option.label }}</span>
      </template>
      <template #left-footer>
        <el-button class="transfer-footer" size="small">Operation</el-button>
      </template>
      <template #right-footer>
        <el-button class="transfer-footer" size="small">Operation</el-button>
      </template>
    </el-transfer>
  </div>

  <el-button class="cloud-el-button" @click="cloudSave">保存</el-button>
</template>

<script async setup lang="ts">
import { authorityList, getById } from '../../../api/user'

import { ref, defineEmits, defineProps } from 'vue'
import { ElMessage } from 'element-plus'
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

// 数据接口
interface Option {
  key: string
  label: string
  disabled: boolean
}

// 初始化系统数据
function initSystemData () : Option[] {
  const systemData: Option[] = []
  authorityList().then(response => {
    if (response.code === store.state.settings.okCode) {
      const data = response.data
      if (data) {
        for (const i in data) {
          const team = data[i]
          systemData.push({
            key: team.authority,
            label: team.explain,
            disabled: false
          })
        }
      }
    } else {
      ElMessage.error(response.msg)
    }
  })
  return systemData
}

// 左侧显示数据
const data = ref(initSystemData())

// 右侧用户数据
const rightValue = ref([])

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

const handleChange = (value: number | string, direction: 'left' | 'right', movedKeys: string[] | number[]) => {
  console.log(value, direction, movedKeys)
}

</script>

<style scoped>

</style>
