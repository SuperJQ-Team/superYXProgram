<template>
    <div :class="{'signin': true, 'is-show':isShow,'is-showed':isShowed, 'is-signin':isSignin}">
        <p>账号</p>
        <el-input class="signin-input" type="text" v-model="Account" placeholder="账号"/>
        <p>密码</p>
        <el-input class="signin-input" type="text" v-model="Password" placeholder="密码"/>
        <!--        <button @click="onShow">开始验证</button>-->
        <Vcode :show="isShowVcode" @success="onSuccess" @close="onClose"/>
        <!--        验证码通过后登录-->
        <el-button class="signin-button" type="primary" @click="onShow()">登录</el-button>
        <el-button class="signup-button" @click="change_page('Signup')">注册</el-button>
    </div>
</template>

<script>
import Vcode from "vue3-puzzle-vcode";

export default {
    name: "Signin",
    data() {
        return {
            Account: "",
            Password: "",
            isShowVcode: false,
            isShow: false,
            isShowed: false,
            isSignin: false,
        }
    },
    components: {
        Vcode
    },
    mounted() {
        setTimeout(() => {
            this.showit();
        }, 500);
    },
    props: {
        sign_in: {
            type: Function,
            default: null
        },
        change_page: {
            type: Function,
            default: null
        }
    },
    methods: {
        onShow() {
            this.isShowVcode = true;
        },
        onSuccess() {
            this.isShowVcode = false; // 通过验证后，需要手动隐藏模态框
            //通过验证后的操作
            this.is_sign_in();
        },
        // 用户点击遮罩层，应该关闭模态框
        onClose() {
            this.isShowVcode = false;
        },
        showit() {
            this.isShow = true;
        },
        hideit() {
            this.isShow = false;
        },
        is_sign_in() {
            this.isSignin = true;
            this.sign_in(this.Account, this.Password);
        },
    }
}
</script>

<style scoped>

.signin {
    z-index: 800;
    width: 65%;
    bottom: 50px;
    opacity: 0;
    transition: 0.5s ease-in-out;
}

.is-show {
    opacity: 1;
}

p {
    margin: 0 0 10px 0;
}

.signin-input, .signin-vcode, .signin-button, .signup-button {
    width: 100%;
}

.signin-input {
    margin: 0 0 20px 0;
}

.signin-vcode {
    width: 40%;
    margin-right: 5%;
}

.signin-button {
    margin: 50px 0 0 0;
}

.signup-button {
    margin: 15px 0 0 0;
}

.is-signin {
    transition: none;
}

</style>