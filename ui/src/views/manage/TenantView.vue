<template>
  <div id="cloud-el-search">
    <el-input class="cloud-el-input" clearable v-model="param.tenantId" placeholder="Please input tenantId"/>
    <el-input class="cloud-el-input" clearable v-model="param.tenantName" placeholder="Please input tenantName"/>
    <el-button class="cloud-el-search" @click="cloudSearch">搜索</el-button>
    <el-button class="cloud-el-reset" @click="cloudClearable">重置</el-button>
    <el-button class="cloud-el-remove" @click="cloudRemove" v-permission="/^manage_tenant:(delete|\*)$/">删除</el-button>
    <el-button class="cloud-el-add" @click="cloudAdd" v-permission="/^manage_tenant:(add|\*)$/">添加
    </el-button>
    <el-button class="cloud-el-manage_tenant_token_delete" @click="cloudTokenDelete"
               v-permission="/^manage_tenant:(token_delete|\*)$/">删除Token
    </el-button>
  </div>

  <!-- 租户弹窗 -->
  <el-dialog v-if="tenantDialogVisible" v-model="tenantDialogVisible" :title="tenantDialogVisibleTitle" width="40%"
             :before-close="tenantDialogHandleClose">
    <TenantDialog :dialogVisible="tenantDialogVisible" :edit="edit" @dialogVisibleClose="tenantDialogVisibleClose"
                  :id="dialogVisibleId"/>
  </el-dialog>

  <el-container>
    <el-table stripe :data="tableData" v-loading="loading" height="460" @selection-change="handleSelectionChange"
              @cell-dblclick="rowDblClick">
      <el-table-column type="selection" width="55"/>
      <el-table-column prop="tenantId" label="tenantId" width="100" :show-overflow-tooltip="true"/>
      <el-table-column prop="tenantName" label="tenantName" width="200" :show-overflow-tooltip="true"/>
      <el-table-column prop="usersId" label="usersId" width="100"/>
      <el-table-column prop="enabled" label="enabled" width="100"/>
      <el-table-column prop="domainName" label="domainName" width="220" :show-overflow-tooltip="true"/>
      <el-table-column prop="remark" label="remark" width="190" :show-overflow-tooltip="true"/>
      <el-table-column prop="createDate" label="createDate" width="160"/>
      <el-table-column prop="updateDate" label="updateDate" width="160"/>
      <el-table-column prop="createIp" label="createIp" width="140"/>
      <el-table-column prop="updateIp" label="updateIp" width="140"/>

      <el-table-column fixed="right" label="Operations" width="140"
                       v-permission="[/^manage_tenant:(delete|edit|authority|\*)$/]">
        <template #default="scope">
          <el-button size="small" @click="deleteId(scope.row)" v-permission="/^manage_tenant:(delete|\*)$/">Delete</el-button>
          <el-button size="small" @click="editId(scope.row.tenantId)" v-permission="/^manage_tenant:(edit|\*)$/">Edit</el-button>
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
import { page, removeByIds, removeById } from '../../api/passport/tenant'
import settings from '../../settings'
// 租户添加、编辑弹窗内容
import TenantDialog from './dialog/TenantDialog.vue'

// 复制
const { toClipboard } = useClipboard()

// 租户弹窗：是否打开
const tenantDialogVisible = ref(false)

// 租户弹窗中的租户ID（修改时使用）
const dialogVisibleId = ref<number>()

// 租户弹窗标题
const tenantDialogVisibleTitle = ref<String>()
// 租户弹窗是否编辑
const edit = ref(false)

// 添加租户
const cloudAdd = () => {
  // 租户弹窗类型：添加
  edit.value = false
  tenantDialogVisibleTitle.value = '添加租户'
  dialogVisibleId.value = undefined
  // 租户弹窗：打开
  tenantDialogVisible.value = true
}

// 修改租户
const editId = (tenantId: number) => {
  // 租户弹窗类型：编辑
  edit.value = true
  tenantDialogVisibleTitle.value = '编辑租户'
  // 编辑租户的ID
  dialogVisibleId.value = tenantId
  // 租户弹窗：打开
  tenantDialogVisible.value = true
}

// 租户弹窗关闭：弹窗右上角的 x
const tenantDialogHandleClose = (done: () => void) => {
  console.log('关闭租户弹窗')
  done()
}

// 租户弹窗关闭：子窗口使用
const tenantDialogVisibleClose = () => {
  // 租户弹窗：打开
  tenantDialogVisible.value = false
  // 关闭窗口后，重新搜索
  cloudSearch()
}

// 表格数据
const tableData = ref([])

// 多选
const multipleSelection = ref<any[]>([])
// 多选主键
const ids = ref<number[]>([])

// 搜索参数
const param = reactive({
  current: 1,
  size: 10,
  total: 0,
  tenantId: null,
  tenantName: null
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
  param.tenantId = null
  param.tenantName = null
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
      removeByIds(ids.value).then(response => {
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

// 删除租户
const deleteId = (e: any) => {
  ElMessageBox.confirm(`确认删除【${e.tenantName}】？`, '警告', {
    confirmButtonText: '确认',
    cancelButtonText: '取消',
    type: 'warning'
  }).then(() => {
    removeById(e.tenantId).then(response => {
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
    removeByIds(ids.value).then(response => {
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

          }
        })
      }
    })
  }
}

// 选择事件
const handleSelectionChange = (val: any[]) => {
  multipleSelection.value = val

  // 清空
  ids.value = []
  for (const i in val) {
    ids.value[i] = multipleSelection.value[i].tenantId
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
.cloud-el-manage_tenant_token_delete {
  margin-left: 5px;
  margin-right: 5px;
}

.cloud-el-expand-input,
.cloud-el-expand-textarea {
  /*max-width: 1100px;*/
}

</style>
