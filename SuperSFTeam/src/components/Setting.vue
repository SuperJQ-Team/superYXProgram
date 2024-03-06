<template>
    <div id="setting">
        <div class="user-information">
            <h1 v-if="User===null">未登录</h1>
            <div v-else>
                <h1>当前用户</h1>
                <h3>昵称：{{ User.virtualName }}</h3>
                <h3>用户名：{{ User.userName }}</h3>
                <h3>UserId：{{ User.id }}</h3>
                <h3>用户权限：{{ User.role }}</h3>
            </div>
        </div>
        <div class="change-name">
            <h1>修改昵称</h1>
            <el-input v-model="newVirtualName"></el-input>
            <br>
            <el-button @click="changeName()">确认</el-button>
            <el-button @click="()=>{newVirtualName=''}">清空</el-button>
        </div>
        <div class="change-password">
            <h1>修改密码</h1>
            <p>新密码</p>
            <el-input v-model="newPassword"></el-input>
            <p>确认新密码</p>
            <el-input v-model="newVerifyPassword"></el-input>
            <br>
            <el-button @click="changePassword()">确认</el-button>
        </div>
        <div class="change-op" v-if="User?(User.role==='administrator'):false">
            <h1>修改权限</h1>
            <p>UserID</p>
            <el-input v-model="changeOPid"></el-input>
            <p>更改权限</p>
            <select name="status" id="status" v-model="selected">
                <option :value="item.statusId" v-for="(item,index) in statusArr">{{ item.statusVal }}</option>
            </select>
            <br>
            <el-button @click="changeOP()">确认</el-button>
        </div>
    </div>
</template>

<script>
export default {
    name: "Setting",
    data() {
        return {
            User: null,
            newVirtualName: "",
            newPassword: "",
            newVerifyPassword: "",
            changeOPid: "",
            statusArr: [
                {
                    statusId: '0',
                    statusVal: '普通用户'
                },
                {
                    statusId: '1',
                    statusVal: '管理员'
                },
            ],
            selected: '',
            roleList: [
                'user',
                'administrator'
            ],
        }
    },
    mounted() {
        this.User = JSON.parse(sessionStorage.getItem('user'));
        // console.log(this.User);
    },
    methods: {
        isLogin() {
            if (this.User === null) {
                this.$toast.error('未登录', {
                    duration: 2000,
                    maxToasts: 4,
                    position: "top",
                })
                return false;
            } else return true;
        },
        changeName() {
            if (!this.isLogin()) return;
            if (this.newVirtualName === this.User.virtualName) {
                this.$toast.error("与原昵称相同", {
                    duration: 2000,
                    maxToasts: 4,
                })
                return;
            }
            this.axios.post("/api/user/edit",
                {
                    virtualName: this.newVirtualName,
                })
                .then((response) => {
                    let data = response.data;
                    if (data.code === 200) {
                        this.$toast.success(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                        this.User.virtualName = this.newVirtualName;
                        this.$emit("setUserInformation", this.User);
                    } else {
                        this.$toast.error(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                    }
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        changePassword() {
            if (!this.isLogin()) return;
            if (this.newPassword !== this.newVerifyPassword) {
                this.$toast.error("两次密码不一致", {
                    duration: 2000,
                    maxToasts: 4,
                })
                return;
            }
            this.axios.post("/api/user/edit",
                {
                    password: this.newPassword,
                })
                .then((response) => {
                    let data = response.data;
                    if (data.code === 200) {
                        this.$toast.success(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                    } else {
                        this.$toast.error(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                    }
                })
                .catch((error) => {
                    console.log(error);
                })
        },
        changeOP() {
            if (!this.isLogin()) return;
            if (this.User.role !== "administrator") {
                this.$toast.error("无权限", {
                    duration: 2000,
                    maxToasts: 4,
                });
                return;
            } else {
                // console.log(this.selected)
                this.axios.get("/api/user/setRole", {
                    params: {
                        id: this.changeOPid,
                        role: this.roleList[this.selected],
                    }
                }).then((response) => {
                    let data = response.data;
                    if (data.code === 200) {
                        this.$toast.success(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                    } else {
                        this.$toast.error(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                    }
                }).catch((error) => {
                    console.log(error);
                })
            }
        },
    },
}
</script>

<style scoped>
#setting {
    margin: 0;
    padding: 80px 200px 60px;
    overflow: hidden;
    width: calc(100vw - 400px);
    transition: 0.3s;
    min-width: 300px;
}

@media screen and (max-width: 900px) {
    #setting {
        padding: 80px 100px 60px;
        width: calc(100vw - 200px);
    }
}

@media screen and (max-width: 600px) {
    #setting {
        padding: 80px 50px 60px;
        width: calc(100vw - 100px);
    }
}


.user-information, .change-name, .change-password, .change-op {
    background-color: rgba(255, 255, 255, 0.9);
    margin: 0 0 20px 0;
    padding: 0 20px 20px 20px;
    text-align: center;
}

#setting h1 {
    margin: 0;
    padding: 30px 0 30px 0;
    color: rgba(0, 0, 0, 1);
}

#setting h3 {
    margin: 0;
    padding: 0 0 10px 0;
}

#setting .el-input, #setting select {
    width: 50%;
}

#setting .el-button {
    margin-top: 16px;
}

</style>