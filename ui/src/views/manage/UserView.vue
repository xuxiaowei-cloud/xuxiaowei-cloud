<template>
  <div id="cloud-el-search">
    <el-input class="cloud-el-input" clearable v-model="param.usersId" placeholder="Please input usersId"/>
    <el-input class="cloud-el-input" clearable v-model="param.username" placeholder="Please input username"/>
    <el-input class="cloud-el-input" clearable v-model="param.email" placeholder="Please input email"/>
    <el-input class="cloud-el-input" clearable v-model="param.emailValid" placeholder="Please input emailValid"/>
    <el-input class="cloud-el-input" clearable v-model="param.nickname" placeholder="Please input nickname"/>
    <el-button class="cloud-el-search" @click="cloudSearch">搜索</el-button>
    <el-button class="cloud-el-reset" @click="cloudClearable">重置</el-button>
    <el-button class="cloud-el-remove" @click="cloudRemove" v-permission="/^manage_user:(delete|\*)$/">删除</el-button>
    <el-button class="cloud-el-add" @click="cloudAdd" v-permission="/^manage_user:(add|\*)$/">添加
    </el-button>
    <el-button class="cloud-el-manage_user_token_delete" @click="cloudTokenDelete" v-permission="/^manage_user:(token_delete|\*)$/">
      删除Token
    </el-button>
  </div>

  <!-- 用户弹窗 -->
  <el-dialog v-if="userDialogVisible" v-model="userDialogVisible" :title="userDialogVisibleTitle" width="40%"
             :before-close="userDialogHandleClose">
    <UserDialog :dialogVisible="userDialogVisible" :edit="edit" :usersId="dialogVisibleUsersId"
                @dialogVisibleClose="userDialogVisibleClose"/>
  </el-dialog>

  <!-- 用户权限弹窗 -->
  <el-dialog v-if="userAuthorityDialogVisible" v-model="userAuthorityDialogVisible" title="用户权限" width="60%"
             :before-close="userAuthorityDialogHandleClose">
    <UserAuthorityDialog :dialogVisible="userAuthorityDialogVisible" :usersId="dialogVisibleUsersId"
                         @dialogVisibleClose="userAuthorityDialogVisibleClose"/>
  </el-dialog>

  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460" @selection-change="handleSelectionChange"
              @cell-dblclick="rowDblClick">
      <el-table-column type="expand">
        <template #default="props">
          <el-form label-width="260px" v-if="props.row.authorityList.length > 0">
            <div v-for="(item, i) in props.row.authorityList" :key="i">
              <el-form-item :label="item.authority">
                <el-input v-model="item.explain" class="cloud-el-expand-input" disabled/>
              </el-form-item>
            </div>
          </el-form>
          <div v-else class="text-center">
            <span>暂无权限</span>
          </div>
        </template>
      </el-table-column>
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="tenantId" label="tenantId" width="80" v-if="useStore.getSuperTenant"/>
      <el-table-column prop="usersId" label="usersId" width="80"/>
      <el-table-column prop="username" label="username" width="100"/>
      <el-table-column prop="email" label="email" width="220" :show-overflow-tooltip="true"/>
      <el-table-column prop="emailValid" label="emailValid" width="100"/>
      <el-table-column prop="nickname" label="nickname" width="100"/>
      <el-table-column prop="enabled" label="enabled" width="80"/>
      <el-table-column prop="accountNonExpired" label="accountNonExpired" width="160"/>
      <el-table-column prop="credentialsNonExpired" label="credentialsNonExpired" width="180"/>
      <el-table-column prop="accountNonLocked" label="accountNonLocked" width="160"/>
      <el-table-column prop="createDate" label="createDate" width="160"/>
      <el-table-column prop="updateDate" label="updateDate" width="160"/>

      <el-table-column fixed="right" label="Operations" width="230"
                       v-permission="[/^manage_user:(delete|edit|authority|\*)$/]">
        <template #default="scope">
          <el-button size="small" @click="deleteUsersId(scope.row)" v-permission="/^manage_user:(delete|\*)$/">Delete
          </el-button>
          <el-button size="small" @click="editUsersId(scope.row.usersId)" v-permission="/^manage_user:(edit|\*)$/">Edit
          </el-button>
          <el-button size="small" @click="editUsersAuthorityId(scope.row.usersId)"
                     v-permission="/^manage_user:(authority|\*)$/">Authority
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
import { reactive, ref } from 'vue'
import useClipboard from 'vue-clipboard3'
import { ElMessage, ElMessageBox } from 'element-plus'
import { page, removeById, removeByIds } from '../../api/user'
import settings from '../../settings'
import { useStore } from '../../store'

// 用户添加、编辑弹窗内容
import UserDialog from './dialog/UserDialog.vue'
// 用户权限管理弹窗内容
import UserAuthorityDialog from './dialog/UserAuthorityDialog.vue'

// 复制
const { toClipboard } = useClipboard()

// 用户弹窗：是否打开
const userDialogVisible = ref(false)
// 用户权限弹窗：是否打开
const userAuthorityDialogVisible = ref(false)

// 用户弹窗中的用户ID（修改时使用）、用户权限弹窗中的用户ID
const dialogVisibleUsersId = ref<number>()

// 用户弹窗标题
const userDialogVisibleTitle = ref<String>()
// 用户弹窗是否编辑
const edit = ref(false)

// 添加用户
const cloudAdd = () => {
  // 用户弹窗类型：添加
  edit.value = false
  userDialogVisibleTitle.value = '添加用户'
  dialogVisibleUsersId.value = undefined
  // 用户弹窗：打开
  userDialogVisible.value = true
}

// 修改用户
const editUsersId = (usersId: number) => {
  // 用户弹窗类型：编辑
  edit.value = true
  userDialogVisibleTitle.value = '编辑用户'
  // 编辑用户的ID
  dialogVisibleUsersId.value = usersId
  // 用户弹窗：打开
  userDialogVisible.value = true
}

/**
 * 编辑用户权限，进行授权
 * @param usersId 用户ID
 */
const editUsersAuthorityId = (usersId: number) => {
  // 编辑用户的ID
  dialogVisibleUsersId.value = usersId
  // 用户弹窗权限：打开
  userAuthorityDialogVisible.value = true
}

// 用户弹窗关闭：弹窗右上角的 x
const userDialogHandleClose = (done: () => void) => {
  console.log('关闭用户弹窗')
  done()
}

// 用户权限弹窗关闭：弹窗右上角的 x
const userAuthorityDialogHandleClose = (done: () => void) => {
  console.log('关闭用户权限弹窗')
  done()
}

// 用户弹窗关闭：子窗口使用
const userDialogVisibleClose = () => {
  // 用户弹窗：打开
  userDialogVisible.value = false
  // 关闭窗口后，重新搜索
  cloudSearch()
}

// 用户权限弹窗关闭：子窗口使用
const userAuthorityDialogVisibleClose = () => {
  // 用户弹窗权限：打开
  userAuthorityDialogVisible.value = false
  // 关闭窗口后，重新搜索
  cloudSearch()
}

// 表格数据
const tableData = ref([])

// 多选
const multipleSelection = ref<any[]>([])
// 多选主键
const usersIds = ref<number[]>([])
// 多选用户名
const usernames = ref<string[]>([])

// 搜索参数
const param = reactive({
  current: 1,
  size: 10,
  total: 0,
  usersId: null,
  username: null,
  email: null,
  emailValid: null,
  nickname: null
})

// 加载
const loading = ref(true)

// 搜索
const cloudSearch = () => {
  loading.value = true
  page(param).then(response => {
    console.log(response)
    if (response.code === settings.okCode) {
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
  param.email = null
  param.emailValid = null
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
    ElMessageBox.confirm('确认批量删除？', '警告', {
      confirmButtonText: '确认',
      cancelButtonText: '取消',
      type: 'warning'
    }).then(() => {
      removeByIds(usersIds.value).then(response => {
        if (response.code === settings.okCode) {
          ElMessage({
            message: response.msg,
            // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
            duration: 1500,
            type: 'success',
            onClose: () => {
              // 重新搜索
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
              // 重新搜索
              cloudSearch()
            }
          })
        }
      })
    }).catch(() => {

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
const deleteUsersId = (e: any) => {
  ElMessageBox.confirm(`确认删除【${e.username}】？`, '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    removeById(e.usersId).then(response => {
      if (response.code === settings.okCode) {
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
  }).catch(() => {

  })
}

// 删除Token
const cloudTokenDelete = () => {
  if (multipleSelection.value.length === 0) {
    ElMessage({
      message: '请先选择数据',
      // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
      duration: 1500,
      type: 'error'
    })
  } else {
    ElMessage({
      message: '该功能未开发',
      // 显示时间，单位为毫秒。设为 0 则不会自动关闭，类型：number，默认值：3000
      duration: 1500,
      type: 'error'
    })
  }
}

// 选择事件
const handleSelectionChange = (val: any[]) => {
  multipleSelection.value = val

  // 清空
  usersIds.value = []
  usernames.value = []
  for (const i in val) {
    usersIds.value[i] = multipleSelection.value[i].usersId
    usernames.value[i] = multipleSelection.value[i].username
  }
}

// 当某个单元格被双击击时会触发该事件
const rowDblClick = async (row: any, column: any, cell: any, event: any) => {
  const columnValue = row[column.property]
  console.log(columnValue)
  try {
    await toClipboard(columnValue + '')
    ElMessage({
      message: '已复制到剪贴板。',
      type: 'success'
    })
  } catch (e) {
    console.error(e)
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
.cloud-el-remove,
.cloud-el-add,
.cloud-el-manage_user_token_delete {
  margin-left: 5px;
  margin-right: 5px;
}

.cloud-el-expand-input,
.cloud-el-expand-textarea {
  /*max-width: 1100px;*/
}

</style>
