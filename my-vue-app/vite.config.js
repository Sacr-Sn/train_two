import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { fileURLToPath, URL } from 'node:url'


import {CONTEXT_PATH} from './src/common/constants.js'

// ElementPlus相关
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'


// https://vitejs.dev/config/
export default defineConfig({
  server: {  
    port: 6602,   // 本地vue3启动端口
    https:false,
    host: '127.0.0.1',  // 本地vue3url
    open: true,
    proxy: {  
      // 代理配置  
      '/api': {  // 它等同于下面的target，发送axios请求时用它来代替target
        target: CONTEXT_PATH,
        changeOrigin: true, // 开启代理服务器，将前端请求转发到目标服务器  
        rewrite: (path) => path.replace(/^\/api/, '') // 路径重写，移除请求路径中的"/api"  
      }  
    }  
  },
  base: "./",
  plugins: [
    vue(),
    // 自动导入组件相关配置
    // 配置插件
    AutoImport({
      resolvers: [ElementPlusResolver()],
    }),
    Components({
      resolvers: [ElementPlusResolver()],
    }),
  ],
  resolve: {
    alias: {
      '@': fileURLToPath(new URL('./src', import.meta.url))
    }
  }
})
