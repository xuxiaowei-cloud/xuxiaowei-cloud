<template>
  <el-header class="cloud-el-header"></el-header>
  <el-container>
    <el-form :model="cloudForm" label-width="120px">
      <el-form-item label="用户名">{{ cloudForm.username }}</el-form-item>
      <el-form-item label="用户昵称">{{ cloudForm.nickname }}</el-form-item>
      <el-form-item label="性别">{{ cloudForm.sexLabel }}</el-form-item>
      <el-form-item label="出生日期">{{ cloudForm.birthday }}</el-form-item>
      <el-form-item label="地址">{{ getAddress() }}</el-form-item>
    </el-form>
  </el-container>
</template>

<script setup lang="ts">
import { reactive } from 'vue'
import { info, UsersVo } from '../../api/user'
import { AjaxResponse } from '../../utils/request'

const cloudForm = reactive({
  username: null,
  nickname: null,
  sex: null,
  sexLabel: null,
  birthday: null,

  provinceCode: null,
  provinceName: null,
  cityCode: null,
  cityName: null,
  countyCode: null,
  countyName: null,
  townCode: null,
  townName: null,
  villageCode: null,
  villageName: null,

  detailAddress: null
})

info().then((response: AjaxResponse<UsersVo>) => {
  const usersVo = response?.data
  for (const name in cloudForm) {
    // @ts-ignore
    cloudForm[name] = usersVo[name]
  }
})

const getAddress = () => {
  let address = ''
  if (cloudForm.provinceName) {
    address += cloudForm.provinceName + ' '
  }
  if (cloudForm.cityName) {
    address += cloudForm.cityName + ' '
  }
  if (cloudForm.countyName) {
    address += cloudForm.countyName + ' '
  }
  if (cloudForm.townName) {
    address += cloudForm.townName + ' '
  }
  if (cloudForm.villageName) {
    address += cloudForm.villageName + ' '
  }
  if (cloudForm.detailAddress) {
    address += cloudForm.detailAddress
  }
  return address
}

</script>

<style scoped>

.cloud-el-header {
  --el-header-height: 30px;
}

</style>
