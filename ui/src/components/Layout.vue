<template>
  <el-container>

    <el-aside id="cloud-el-aside">

      <el-menu :default-active="defaultActive" class="el-menu-vertical" :collapse="isCollapse" :router="true"
               @open="handleOpen" @close="handleClose" id="cloud-aside-el-menu">
        <el-sub-menu index="1">
          <template #title>
            <el-icon>
              <house/>
            </el-icon>
            <span>主页</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/" @click="menuItem">控制台</el-menu-item>
            <el-menu-item index="/home/homepage1" @click="menuItem">主页一</el-menu-item>
            <el-menu-item index="/home/homepage2" @click="menuItem">主页二</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>

        <el-sub-menu index="2">
          <template #title>
            <el-icon>
              <notebook/>
            </el-icon>
            <span>富文本</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/editor/wangeditor" @click="menuItem">WangEditor</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>

        <el-sub-menu index="3">
          <template #title>
            <el-icon>
              <aim/>
            </el-icon>
            <span>审计</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/audit/code" @click="menuItem">授权Code</el-menu-item>
            <el-menu-item index="/audit/access-token" @click="menuItem">授权Token</el-menu-item>
            <el-menu-item index="/audit/refresh-token" @click="menuItem">刷新Token</el-menu-item>
          </el-menu-item-group>
        </el-sub-menu>

      </el-menu>

    </el-aside>
    <el-container>
      <el-header id="cloud-el-header">

        <!-- 左侧菜单打开/关闭按钮 -->
        <el-button v-if="isCollapse" @click="isCollapseClick">
          <el-icon>
            <expand/>
          </el-icon>
        </el-button>
        <el-button v-else @click="isCollapseClick">
          <el-icon>
            <fold/>
          </el-icon>
        </el-button>

        <!-- 刷新 -->
        <el-button @click="refreshClick">
          <el-icon>
            <refresh/>
          </el-icon>
        </el-button>

        <!-- 全屏 -->
        <el-button @click="fullScreenClick">
          <el-icon>
            <full-screen/>
          </el-icon>
        </el-button>

        <!-- 用户菜单 -->
        <el-dropdown id="cloud-el-dropdown" @command="handleCommand">
          <span class="el-dropdown-link">
            {{ nickname }}
            <el-icon class="el-icon--right">
              <arrow-down/>
            </el-icon>
          </span>
          <template #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item command="personal">个人中心</el-dropdown-item>
              <el-dropdown-item command="security">安全设置</el-dropdown-item>
              <el-dropdown-item command="account">账户绑定</el-dropdown-item>
              <el-dropdown-item command="social">社交绑定</el-dropdown-item>
              <el-dropdown-item command="signout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>

      </el-header>
      <el-main>

        <router-view/>

      </el-main>
      <el-footer>Footer</el-footer>
    </el-container>

  </el-container>
</template>

<script setup lang="ts">
import { House, Expand, Fold, Refresh, FullScreen, ArrowDown, Aim, Notebook } from '@element-plus/icons-vue'
import { ref } from 'vue'
import store from '../store'

// 默认激活菜单
// 当缓存中的默认菜单与路径中不同时，使用路径中对应的菜单
const hash = location.hash
const path = hash.split('#')[1].split('?')[0]
const defaultActive = ref(store.getters.defaultActive === path ? path : path)

// 是否折叠菜单
const isCollapse = ref(store.getters.isCollapse)
// 昵称
const nickname = ref(store.getters.nickname)

const handleOpen = (key: number, keyPath: string) => {
  console.log('handleOpen：', key, keyPath)
}

const handleClose = (key: number, keyPath: string) => {
  console.log('handleClose：', key, keyPath)
}

// 默认激活菜单
const menuItem = (key: any) => {
  store.commit('setDefaultActive', key.index)
}

// 是否折叠菜单
const isCollapseClick = () => {
  isCollapse.value = !isCollapse.value
  store.commit('setIsCollapse', isCollapse)
}

// 刷新当前页面（局部刷新）
const refreshClick = () => {
  console.log('局部刷新，未完成')
}

// 全屏
const fullScreenClick = () => {
  document.documentElement.requestFullscreen()
}

// 用户菜单
const handleCommand = (command: any, number: any) => {
  console.log(command, number)
}

</script>

<style scoped>

#cloud-el-aside {
  /* 最侧边框无宽度（内部填充） */
  --el-aside-width: none;
}

/* 左侧菜单 */
.el-menu-vertical:not(.el-menu--collapse) {
  /* 宽度 */
  width: 200px;
  /* 最小高度 */
  min-height: 400px;
}

/* 左侧菜单 */
#cloud-aside-el-menu {
  --el-menu-item-height: 50px;
}

/* 左侧菜单 */
#cloud-aside-el-menu .el-menu-item {
  height: 40px;
}

/* 顶部导航 */
#cloud-el-header {
  --el-header-height: 50px;
  line-height: var(--el-header-height);
}

/* 用户菜单 */
#cloud-el-dropdown {
  height: 34px;
  line-height: 34px;
  padding: 8px;
  float: right;
}

</style>
