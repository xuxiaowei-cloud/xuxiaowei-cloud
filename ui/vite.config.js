import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'

import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'
import { resolve } from 'path'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver()]
    }),
    Components({
      resolvers: [ElementPlusResolver()]
    })
  ],
  resolve: {
    alias: {
      '@': resolve('./src')
    }
  },
  build: {
    terserOptions: {
      compress: {
        // 生产环境移除console日志
        drop_console: true,
        // 生产环境移除debugger测试
        drop_debugger: true
      }
    }
  },
  server: {
    // 代理
    proxy: {
      '/actuator': {
        target: 'http://127.0.0.1:1101'
      },
      '/baidu': {
        target: 'https://www.baidu.com',
        // 将主机标头的来源更改为目标 URL。
        changeOrigin: true,
        // 重写地址
        rewrite: (path) => path.replace(/^\/baidu/, '')
      }
    }
  }
})
