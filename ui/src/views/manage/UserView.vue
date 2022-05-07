<template>
  <div id="cloud-el-search">
    <el-input class="cloud-el-input" clearable v-model="param.usersId" placeholder="Please input usersId"/>
    <el-input class="cloud-el-input" clearable v-model="param.username" placeholder="Please input username"/>
    <el-input class="cloud-el-input" clearable v-model="param.nickname" placeholder="Please input nickname"/>
    <el-button class="cloud-el-search" @click="cloudSearch">搜索</el-button>
    <el-button class="cloud-el-reset" @click="cloudClearable">重置</el-button>
    <el-button class="cloud-el-remove" @click="cloudRemove" v-if="hasAuthority('manage_user_delete')">删除</el-button>
  </div>
  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="usersId" label="usersId" width="80"/>
      <el-table-column prop="username" label="username" width="100"/>
      <el-table-column prop="nickname" label="nickname" width="100"/>
      <el-table-column prop="enabled" label="enabled" width="80"/>
      <el-table-column prop="accountNonExpired" label="accountNonExpired" width="160"/>
      <el-table-column prop="credentialsNonExpired" label="credentialsNonExpired" width="180"/>
      <el-table-column prop="accountNonLocked" label="accountNonLocked" width="160"/>
      <el-table-column prop="createDate" label="createDate" width="160"/>
      <el-table-column prop="updateDate" label="updateDate" width="160"/>

      <el-table-column fixed="right" label="Operations" width="100" v-if="hasAnyAuthority(['manage_user_delete'])">
        <template #default="scope">
          <el-button type="text" size="small" @click="deleteUsersId(scope.row.usersId)"
                     v-if="hasAuthority('manage_user_delete')">Delete
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-container>
  <el-container>
    <el-pagination layout="prev, pager, next, total" @current-change="currentChange" :total="param.total"/>
  </el-container>
</template>

<script setup lang="ts">
import {page, removeById, removeByIds} from '../../api/user'
import {hasAuthority, hasAnyAuthority} from '../../utils/authority'
import {reactive, ref} from 'vue'
import {useStore} from 'vuex'
import {ElMessage} from 'element-plus'

// 缓存
const store = useStore()

// 表格数据
const tableData = ref([])

// 多选
const multipleSelection = ref<any[]>([])
// 多选主键
const usersIds = ref<number[]>([])

// 搜索参数
const param = reactive({
  current: 1,
  size: 10,
  total: 0,
  usersId: null,
  username: null,
  nickname: null
})

// 加载
const loading = ref(true)

// 搜索
const cloudSearch = () => {
  loading.value = true
  page(param).then(response => {
    console.log(response)
    if (response.code === store.state.settings.okCode) {
      const data = response.data
      tableData.value = data.records
      param.total = data.total
      loading.value = false
    } else {
      ElMessage.error(response.msg)
    }
  })
}

// 重置（清空搜索条件）
// Q：为何不使用 reset？
// A：因为使用 reset 后，页面不显示了，但是值还在，影响搜索
const cloudClearable = () => {
  param.usersId = null
  param.username = null
  param.nickname = null
}

// 批量删除
const cloudRemove = () => {
  if (multipleSelection.value.length === 0) {
    ElMessage({
      message: '请先选择数据',
      // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
      duration: 1500,
      type: 'error'
    })
  } else {
    removeByIds(usersIds.value).then(response => {
      if (response.code === store.state.settings.okCode) {
        ElMessage({
          message: response.msg,
          // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
          duration: 1500,
          type: 'success',
          onClose: () => {
            cloudSearch()
          }
        })
      } else {
        ElMessage({
          message: response.msg,
          // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
          duration: 1500,
          type: 'error',
          onClose: () => {
            cloudSearch()
          }
        })
      }
    })
  }
}

// 初始搜索
cloudSearch()

// 改变时触发
const currentChange = (e: number) => {
  param.current = e
  cloudSearch()
}

// 删除授权Code
const deleteUsersId = (e: number) => {
  removeById(e).then(response => {
    if (response.code === store.state.settings.okCode) {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'success',
        onClose: () => {
          cloudSearch()
        }
      })
    } else {
      ElMessage({
        message: response.msg,
        // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
        duration: 1500,
        type: 'error',
        onClose: () => {
          cloudSearch()
        }
      })
    }
  })
}

// 选择事件
const handleSelectionChange = (val: any[]) => {
  multipleSelection.value = val

  // 清空
  usersIds.value = []
  for (const i in val) {
    usersIds.value[i] = multipleSelection.value[i].usersId
  }
}

</script>

<style scoped>

.cloud-el-input {
  width: 300px;
}

.cloud-el-input,
.cloud-el-search,
.cloud-el-reset,
.cloud-el-remove {
  margin-left: 5px;
  margin-right: 5px;
}

</style>
