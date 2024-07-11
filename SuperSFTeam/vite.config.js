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
        open: false, //自动打开
        proxy: {
            // 这里的ccc可乱写, 是拼接在url后面的地址 如果接口中没有统一的后缀可自定义
            // 如果有统一后缀, 如api, 直接写api即可, 也不用rewrite了
            "^/api": {
                target: "http://192.168.43.130:8080/", // 真实接口地址, 后端给的基地址
                changeOrigin: true, // 允许跨域
                rewrite: (path) => path.replace(/^\/api/, ""), // 将ccc替换为空
            },
        },
    },
})
