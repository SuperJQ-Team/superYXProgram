<template>
    <div :class="{'frame': true, 'is-folded':isFolded, 'is-sign-in':isSignin}">
        <component :class="{'signin-up': true}"
                   ref="child"
                   :change_page="change_page"
                   :sign_in="sign_in"
                   :is="comName"></component>
    </div>
</template>

<script>
import Signin from "@/components/Signin.vue";
import Signup from "@/components/Signup.vue";
import CheckMark from "@/components/CheckMark.vue";
import HeadImg from "@/components/HeadImg.vue";

export default {
    name: "SigninSignup",
    data() {
        return {
            comName: 'Signin',
            isFolded: true,
            isSignin: false,
        }
    },
    components: {
        Signin,
        Signup,
        CheckMark,
        HeadImg,
    },
    props: {
        setUserInformation: {
            type: Function,
            default: null
        }
    },
    mounted() {
        setTimeout(() => {
            this.isFolded = false;
        }, 0)
    },
    methods: {
        change_page(comname) {//切换登录、注册
            this.$refs.child.hideit();
            setTimeout(() => {
                this.comName = comname;
                this.$nextTick(() => {
                    this.$refs.child.showit();
                })
            }, 500);
        },
        sign_in(Account, Password) {//登录函数
            // console.log(Account, Password);
            let User = null;

            this.axios
                .get("/api/user/login",
                    {
                        params: {
                            username: Account,
                            password: Password,
                        }
                    })
                .then((response) => {
                    // console.log(response);
                    let data = response.data;

                    if (data.code === 200) {//ajax succeed
                        User = data.data;
                        this.$refs.child.hideit();//登录动画
                        this.isSignin = true
                        setTimeout(() => {
                            this.comName = 'CheckMark';
                            setTimeout(() => {
                                this.isFolded = true;
                                this.setUserInformation(User);
                            }, 1100);
                        }, 400);
                    } else {//ajax error
                        this.$toast.error(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                    }
                })
                .catch((error) => {
                    console.log(error);
                });
        },
    }
}
</script>

<style scoped>
.frame {
    background: rgba(255, 255, 255, 0.95);
    border-radius: 10%;
    margin: auto;
    z-index: 600;
    display: flex;
    align-items: center;
    justify-content: center;
    /*transition: 0.5s ease-in-out;*/
    transition: 0.5s cubic-bezier(.49, .07, .29, 1.44);
    opacity: 1;
    overflow: hidden;
    height: 500px;
    width: 400px;
    position: absolute;
    top: 50vh;
    left: 0;
    right: 0;
    bottom: 0;
}

.is-folded {
    opacity: 0;
    /*transition: 0.5s ease-in-out;*/
    transition: 0.5s cubic-bezier(.49, .07, .29, 1.44);
    border-radius: 50%;
    transform: scale(0);
}

.is-sign-in {
    border-radius: 50%;
    height: 100px;
    width: 100px;
}

</style>