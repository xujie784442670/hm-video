import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import { ElementPlusResolver } from 'unplugin-vue-components/resolvers'

// https://vitejs.dev/config/
export default defineConfig({
  plugins: [
    vue(),
    AutoImport({
      resolvers: [ElementPlusResolver({ importStyle: false })],
    }),
    Components({
      resolvers: [ElementPlusResolver({ importStyle: false })],
    })],
  server: {
    port: 90,
    host: "0.0.0.0",
    proxy: {
      '/api': {
        target: 'http://127.0.0.1:9000',
        rewrite: (path) => path.replace(/^\/api/, ''),
      },
    },
  },
})
