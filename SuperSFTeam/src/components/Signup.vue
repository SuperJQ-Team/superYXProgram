<template>
    <div :class="{'signup': true, 'is-show':isShow}">
        <p>账号</p>
        <el-input class="signup-input" type="text" v-model="Account" placeholder="账号"/>
        <p>密码</p>
        <el-input class="signup-input" type="text" v-model="Password" placeholder="密码"/>
        <p>确认密码</p>
        <el-input class="signup-input" type="text" v-model="VerifyPassword" placeholder="确认密码"/>
        <el-button class="signup-button" type="primary" @click="sign_up()">注册</el-button>
        <el-button class="signup-button" @click="change_page('Signin')">返回</el-button>
    </div>
</template>

<script>
export default {
    name: "Signup",
    data() {
        return {
            Account: "",
            Password: "",
            VerifyPassword: "",
            isShow: false,
        }
    },
    props: {
        change_page: {
            type: Function,
            default: null
        }
    },
    methods: {
        showit() {
            this.isShow = true;
        },
        hideit() {
            this.isShow = false;
        },
        sign_up() {
            if (this.Password !== this.VerifyPassword) {//确认密码不同
                console.log('寄');
                window.alert("两次密码不一致！");
            } else {//确认密码相同

                //注册
                this.axios.get("/api/user/signup",
                    {
                        params: {
                            username: this.Account,
                            password: this.Password,
                            role: "user",
                        }
                    })
                    .then((response) => {
                        let data = response.data;
                        if (data.code === 200) {
                            this.$toast.success(data.msg, {
                                duration: 2000,
                                maxToasts: 4,
                            })
                            console.log('注册成功')
                            this.change_page('Signin');//返回登录页
                        }
                        else{
                            this.$toast.error(data.msg, {
                                duration: 2000,
                                maxToasts: 4,
                            })
                        }
                    })
                    .catch((error) => {
                        console.log(error);
                    });
            }
        }
    }
}
</script>

<style scoped>
.signup {
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

.signup-input, .signup-button, .signup-button {
    width: 100%;
}

.signup-input {
    margin: 0 0 20px 0;
}

.signup-button {
    margin: 50px 0 0 0;
}

.signup-button {
    margin: 15px 0 0 0;
}
</style>