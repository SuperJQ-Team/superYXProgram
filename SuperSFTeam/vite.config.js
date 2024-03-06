import {fileURLToPath, URL} from 'node:url'

import {defineConfig} from 'vite'
import vue from '@vitejs/plugin-vue'
import AutoImport from 'unplugin-auto-import/vite'
import Components from 'unplugin-vue-components/vite'
import {ElementPlusResolver} from 'unplugin-vue-components/resolvers'
import copyPlugin from 'rollup-plugin-copy'

// https://vitejs.dev/config/
export default defineConfig({
    plugins: [
        vue(),
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
    },
    build: {
        terserOptions: {
            compress: {
                drop_console: true, // 生产环境移除console
            },
        },
        emptyOutDir: false, // 将此配置项设为false即可
        rollupOptions: {
            plugins: [
                copyPlugin({
                    targets: [{src: 'WEB-INF/*', dest: 'dist/WEB-INF'}],
                }),
            ],
        },
        //指定输出路径
        outDir: 'dist',
        //生成静态资源的存放路径
        assetsDir: 'assets',
        //小于此阈值的导入或引用资源将内联为 base64 编码，以避免额外的 http 请求。设置为 0 可以完全禁用此项
        assetsInlineLimit: 4096,
    },

    server: {
        host: "0.0.0.0",
        cors: true,
        port: 5173,
        open: false, 
        proxy: {
            "^/api": {
                target: "http://localhost:8080/",
                changeOrigin: true, // 允许跨域
                rewrite: (path) => path.replace(/^\/api/, ""),
            },
        },
    },
})
