import {createApp} from 'vue'
import App from './App.vue'

//路由
import router from './route'

//axios
import axios from 'axios'
import VueAxios from 'vue-axios'

//通知消息
import Toaster from "@meforma/vue-toaster";

//Element
// import ElementPlus from 'element-plus'
// import 'element-plus/dist/index.css'

// 全屏滚动 vue-fullpage.js
import VueFullpage from 'vue-fullpage.js'
import 'vue-fullpage.js/dist/style.css'

const app = createApp(App)

app.use(router)
app.use(VueAxios, axios)
app.use(Toaster)
// app.use(ElementPlus)
app.use(VueFullpage)

app.mount('#app')
