<template>
    <el-container style="height: 100%">
        <el-header>
            <!--导航栏-->
            <NavBar :reload="reload" ref="NavBar"></NavBar>
        </el-header>
        <el-main :class="{'el-main-overflow': ElMainOverflow}">
            <!--主内容-->
            <router-view v-if="isRouterActive" @setUserInformation="setUserInformation"></router-view>
        </el-main>
    </el-container>
</template>

<script>
import SigninSignup from "@/components/SigninSignup.vue";
import NavBar from "@/components/NavBar.vue";
import {nextTick} from 'vue'

export default {
    data() {
        return {
            ElMainOverflow: false,//控制是否禁止滚动(防止首页滚动问题)
            isRouterActive: true,
            // comName: 'SigninSignup',
        };
    },
    watch: {
        $route(to, from) {
            window.scrollTo(0, 0);
            // document.body.scrollTop = document.documentElement.scrollTop = 0;
            // console.log(from.path);//从哪来
            // console.log(to.path);//到哪去
            this.ElMainOverflow = to.path === '/index';
            this.reload();
            if (from.path === '/index' && to.path !== '/index') location.reload();
        }
    },
    methods: {
        reload() {
            this.isRouterActive = false
            nextTick(() => {
                this.isRouterActive = true
            })
        },
        setUserInformation(User) {
            this.$refs.NavBar.setUserInformation(User);
        }
    },
    components: {
        SigninSignup,
        NavBar,
    },
}
</script>

<style>
html, body {
    width: 100%;
    height: 100%;
    margin: 0;
    background-color: rgb(40, 40, 40);
    scrollbar-width: none;
}

body::-webkit-scrollbar {
    /*Chorme */
    display: none;
}

body {
    /*Firefox*/
    width: calc(100vw + 18px);
    /*Firefox*/
    height: 100%;
    /*IE*/
    -ms-overflow-style: none;
    /*Firefox*/
    overflow: auto;
    /*overflow: hidden;*/
}

.fp-overflow {
    height: 100%;
}

.fp-watermark {
    display: none;
}

.el-header {
    height: 60px;
    width: 100vw;
    min-width: 600px;
    position: fixed;
    margin: 0;
    padding: 0;
    top: 0;
    z-index: 999;
}

.el-main {
    padding: 0;
    height: 100%;
}

.el-main-overflow {
    overflow: hidden;
}

.c-toast--default {
    background-color: rgba(52, 58, 64, 0.9) !important;
}

.c-toast--info {
    background-color: rgba(23, 162, 184, 0.9) !important;
}

.c-toast--success {
    background-color: rgba(40, 167, 69, 0.9) !important;
}

.c-toast--warning {
    background-color: rgba(255, 193, 7, 0.9) !important;
}

.c-toast--error {
    background-color: rgba(220, 53, 69, 0.9) !important;
}
</style>