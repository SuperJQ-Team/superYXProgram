<template>
    <div id="mall">
        <ul>
            <li class="item" v-for="(item,index) in Data">
                <div class="img_box"><img v-bind:src="'/api/img/'+item.imagePath" alt=""></div>
                <div class="introduction">
                    <p v-html="item.name" class="item-name"></p>
                    <p v-html="item.summary"></p>
                    <div class="cart-div">
                        <span :style="{color:'red'}">{{ item.price * item.number }}</span>
                        <el-button @click="()=>{if(item.number>1) item.number--;}">-</el-button>
                        <el-input type="text" v-model="item.number"></el-input>
                        <el-button @click="()=>{item.number++;}">+</el-button>
                        <el-button class="add-to-cart" @click="addtoCart(item)">添加到购物车</el-button>
                    </div>
                </div>
            </li>
        </ul>
    </div>
</template>

<script>
import {Carts} from "@/js/Carts";

var oldBottomOfPage = false;
export default {
    name: "Mall",
    data() {
        return {
            Data: [],
            perPage: 10, // 每次加载的项目数量
            currentPage: 1, // 当前页码
        }
    },
    mounted() {
        this.currentPage = 1;
        this.loadMoreItems();
        addEventListener('scroll', this.handleScroll);
    },
    methods: {
        //添加到购物车
        addtoCart(item) {
            // console.log(item);
            let ret = Carts.add(item);
            // if (ret === "AlreadyHave") {
            //     this.$toast.error('购物车内已有此商品', {
            //         duration: 2000,
            //         maxToasts: 4,
            //     })
            // } else
            if (ret === true) {
                this.$toast.success('添加成功', {
                    duration: 2000,
                    maxToasts: 4,
                })
            }
        },
        loadMoreItems() {
            this.axios.get("/api/goods/list", {
                params: {
                    pageNum: this.currentPage,
                    pageSize: this.perPage,
                }
            }).then((response) => {
                let data = response.data;
                if (data.code === 200) {
                    // console.log(this.currentPage)
                    if (data.data.list.length === 0) {
                        // console.log('no new goods')
                        this.$toast.info('已经到底啦', {
                            duration: 2000,
                            maxToasts: 4,
                            position: 'bottom',
                        })
                    } else {
                        for (let i in data.data.list) {
                            data.data.list[i].number = 1;
                        }
                        this.Data = this.Data.concat(data.data.list);
                        if (this.currentPage > 1)
                            this.$toast.info('点击导航栏回到顶部', {
                                duration: 2000,
                                maxToasts: 4,
                                position: 'bottom',
                            });
                        this.currentPage++;
                    }
                } else {
                    this.$toast.error(data.msg, {
                        duration: 4000,
                        maxToasts: false,
                    })
                }
            }).catch((error) => {
                console.log(error);
            });
        },
        handleScroll() {
            const scrollY = window.scrollY;
            const windowHeight = window.innerHeight;
            const documentHeight = document.documentElement.scrollHeight;
            const bottomOfPage = windowHeight + scrollY >= documentHeight;
            // console.log(bottomOfPage)
            if (bottomOfPage && !oldBottomOfPage) {
                this.loadMoreItems();
            }
            oldBottomOfPage = bottomOfPage;
        }
    },
    beforeDestroy() {
        // console.log('remove')
        window.removeEventListener('scroll', this.handleScroll);
    },
    beforeRouteLeave(to, from) {
        window.removeEventListener('scroll', this.handleScroll);
    },
}

</script>

<style scoped>
#mall {
    margin: 0;
    padding-top: 80px;
    padding-bottom: 60px;
    overflow: hidden;
    width: 100vw;
}

#mall ul {
    padding: 0;
    width: calc(100vw - 200px);
    margin-left: 100px;
    margin-right: 100px;
}

li.item {
    /*float: left;*/
    width: 100%;
    min-width: 300px;
    min-height: 200px;
    /*margin: 0 5px 8px 5px;*/
    /*text-align: center;*/
    background: rgba(255, 255, 255, 0.9);
    list-style: none;
    margin-bottom: 10px;
}

.item .img_box {
    width: 120px;
    height: 120px;
    margin: 30px;
    left: 0;
    top: 0;
    bottom: 0;
    float: left;
}

.img_box img {
    width: 100%;
    height: 100%;
}

.item .introduction {
    width: calc(100vw - 200px - 200px);
    margin-left: 10px;;
    margin-right: 10px;
    min-height: 180px;
    float: right;
}

.item p:not(:first-child) {
    font-size: 16px;
    line-height: 20px;
    height: 50px;
    padding: 0 60px 0 0;
    display: block;
    display: -webkit-box;
    overflow-x: hidden;
    overflow-y: auto;
    text-overflow: ellipsis;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}

.item p:not(:first-child)::-webkit-scrollbar {
    display: none;
}

.item-name {
    font-size: 30px;
    line-height: 40px;
    margin: 30px 0 10px 0;
}

.cart-div {
    float: right;
}

.cart-div .el-button {
    color: rgba(255, 255, 255, 1);
    background-color: red;
    margin: 0 5px 0 5px;
}

.cart-div .el-input {
    width: 40px;
    /*margin: 0 5px 0 5px;*/
}

.introduction .add-to-cart {
    margin-right: 60px;
}

</style>