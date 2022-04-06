<template>
  <el-container>

    <el-aside width="200px">

      <el-menu :default-active="defaultActive" class="el-menu-vertical" :collapse="isCollapse" :router="true"
               @open="handleOpen" @close="handleClose">
        <el-sub-menu index="1">
          <template #title>
            <el-icon><home-filled /></el-icon>
            <span>主页</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/" @click="menuItem">控制台</el-menu-item>
            <el-menu-item index="/home/homepage1" @click="menuItem">主页一</el-menu-item>
            <el-menu-item index="/home/homepage2" @click="menuItem">主页二</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>
      </el-menu>

    </el-aside>
    <el-container>
      <el-header>

        <el-button v-if="isCollapse" @click="isCollapseClick"><el-icon><expand /></el-icon></el-button>
        <el-button v-else @click="isCollapseClick"><el-icon><fold /></el-icon></el-button>

      </el-header>
      <el-main>

        <router-view/>

      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>

  </el-container>
</template>

<script setup>
import { HomeFilled, Expand, Fold } from '@element-plus/icons-vue'
import { ref } from 'vue'
import store from '@/store'

// 默认激活菜单
const defaultActive = ref(store.getters.defaultActive)
// 是否折叠菜单
const isCollapse = ref(store.getters.isCollapse)

const handleOpen = (key, keyPath) => {
  console.log(key, keyPath)
}

const handleClose = (key, keyPath) => {
  console.log(key, keyPath)
}

// 默认激活菜单
const menuItem = (key) => {
  store.commit('setDefaultActive', key.index)
}

// 是否折叠菜单
const isCollapseClick = () => {
  isCollapse.value = !isCollapse.value
  store.commit('setIsCollapse', isCollapse)
}

</script>

<style scoped>

.el-menu-vertical:not(.el-menu--collapse) {
  width: 200px;
  min-height: 400px;
}

</style>
