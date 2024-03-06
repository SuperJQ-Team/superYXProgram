<template>
    <div :class="{'nav':true,'nav-background':NavBG}">
        <ul @click="toTop">
            <li>
                <router-link to="/">首 页</router-link>
            </li>
            <li>
                <router-link to="/mall">瞧一瞧</router-link>
            </li>
            <li>
                <router-link to="/cart">看一看</router-link>
            </li>
            <li>
                <router-link to="/orders">走过路过</router-link>
            </li>
            <li>
                <router-link to="/setting">不要错过</router-link>
            </li>
            <li style="float: right" @click="clickFn()">
                <a>{{ User ? (User.virtualName ?? "匿名用户") : "登录" }}</a>
            </li>
        </ul>
    </div>
    <SigninSignup v-if="showSigninSignup" :setUserInformation="setUserInformation"></SigninSignup>
    <div class="signin-cover" v-if="showSigninSignup" @click="()=>{this.showSigninSignup=false;}"></div>
</template>

<script>
import SigninSignup from './SigninSignup.vue'
import {Cookies} from "@/js/Cookies";

export default {
    name: "Nav",
    data() {
        return {
            NavBG: false,//控制导航栏背景
            showSigninSignup: false,//显示登录组件
            User: null, // 用户信息
            clickTimes: 0,
        }
    },
    props: {
        reload: {
            type: Function,
            default: null
        },
    },
    components: {
        SigninSignup,
    },
    watch: {
        $route(to, from) {
            this.NavBG = to.path !== '/index';
        }
    },
    methods: {
        //点击时加载登录注册组件
        asyncDownloadSigninSignup() {
            if (this.User === null)//未登录时
                this.showSigninSignup = true;
            else {
                this.$toast.info('双击登出', {
                    duration: 1000,
                    maxToasts: 1,
                    position: "top",
                })
            }
        },
        logOut() {
            // console.log('登出')
            if (this.User === null) {
                this.asyncDownloadSigninSignup();
                return;
            }
            this.axios.get("api/user/logout")
                .then((response) => {
                    let data = response.data;
                    if (data.code === 200) {
                        this.$toast.success('登出成功', {
                            duration: 1000,
                            maxToasts: 2,
                            position: "top",
                        });
                        Cookies.remove("carts");
                        sessionStorage.setItem('user', JSON.stringify(null));
                        this.User = null;
                        this.showSigninSignup = false;
                        this.reload();
                    } else {
                        this.$toast.error('登出失败', {
                            duration: 1000,
                            maxToasts: 2,
                            position: "top",
                        })
                    }
                }).catch((error) => {
                console.log(error);
            })
        },
        clickFn() {
            let _this = this
            _this.clickTimes++;
            if (_this.clickTimes === 2) {
                _this.clickTimes = 0;
                //  处理双击事件
                _this.logOut();
            }
            setTimeout(() => {
                if (_this.clickTimes === 1) {
                    _this.clickTimes = 0;
                    //  处理单击事件
                    _this.asyncDownloadSigninSignup();
                }
            }, 200)
        },
        //设置用户信息
        setUserInformation(User) {
            this.showSigninSignup = false;
            this.User = User;
            // console.log(this.User.virtualName)
            sessionStorage.setItem('user', JSON.stringify(User));
            if (this.$route.path !== "/") this.reload();
        },
        toTop() {
            // console.log('to top')
            let top = document.documentElement.scrollTop || document.body.scrollTop;
            const timeTop = setInterval(() => {
                document.body.scrollTop = document.documentElement.scrollTop = top -= 50;
                if (top <= 0) {
                    clearInterval(timeTop);
                }
            }, 10);
        },
    },
    mounted() {
        let user = null;
        this.axios
            .get("/api/session/user")
            .then((response) => {
                // console.log(response);
                let data = response.data;
                if (data.code === 200) {
                    user = data.data;
                    // console.log(user)
                    this.setUserInformation(user);
                } else {
                    Cookies.remove("carts");
                    sessionStorage.setItem('user', JSON.stringify(null));
                }
            }).catch((error) => {
            console.log(error);
        })
    }
}
</script>

<style scoped>
.nav {
    transition: 1s ease-in-out;
    height: 100%;
    width: 100%;
    margin: 0;
    padding: 0;
    background-color: rgba(0, 0, 0, 0);
}

.nav-background {
    background-color: rgba(0, 0, 0, 0.6);
}

ul {
    height: 100%;
    width: 100%;
    /*width: calc(100vw - 40px);*/
    margin: 0;
    padding: 0;
    /*padding: 0 20px 0 20px;*/
}

li {
    list-style: none;
    user-select: none;
    width: auto;
    height: 100%;
    white-space: nowrap;
    display: inline-block;
}

ul li a {
    text-decoration: none;
    color: white;
    font-size: 16px;
    text-align: center;
    display: inline-block;
    width: auto;
    min-width: 60px;
    line-height: 60px;
    padding: 0 15px 0 15px;
    color: rgba(255, 255, 255, 0.8);
}

ul li a:hover {
    background-color: rgba(0, 0, 0, 0.1);
    cursor: pointer
}

.signin-cover {
    background-color: rgba(0, 0, 0, 0.5);
    position: absolute;
    z-index: -1;
    width: 100vw;
    height: 100vh;
    padding: 0;
    margin: 0;
}
</style>