<template>
    <div id="cart">
        <h1>购物车</h1>
        <table>
            <thead>
            <tr>
                <th width="7%">选择</th>
                <th width="54%">商品</th>
                <th width="15%">单价（元）</th>
                <th width="15%">数量</th>
                <th width="15%">金额（元）</th>
            </tr>
            </thead>
            <tbody id="cart-goods-list">
            <tr v-for="cart in productList">
                <td>
                    <input type="checkbox" name="good-id" :value="1" v-model="cart.select">
                </td>
                <td class="goods">
                    <div class="goods-image">
                        <img v-bind:src="'/api/img/'+cart.imagePath">
                    </div>
                    <div class="goods-information">
                        <h3>{{ cart.name }}</h3>
                        <!--                        <p>{{ cart.summary }}</p>-->
                        <p>余货：{{ cart.count === null ? "无限" : cart.count }}</p>
                    </div>
                </td>
                <td>
                    <span class="price">￥<em class="price-em">{{ cart.price.toFixed(2) }}</em></span>
                </td>
                <td>
                    <div class="combo">
                        <input type="button" name="minus" value="-" class="combo-minus" @click="comboMinus(cart)">
                        <input type="text" name="count" v-model.number="cart.number" class="combo-value">
                        <input type="button" name="plus" value="+" class="combo-plus" @click="comboPlus(cart)">
                    </div>
                </td>
                <td>
                    <strong class="amount">￥<em
                            class="amount-em">{{ (cart.price * cart.number).toFixed(2) }}</em></strong>
                </td>
            </tr>
            </tbody>
            <tfoot v-show="productList.length!=0">
            <tr>
                <td colspan="2">
                    <label>
                        <input type="checkbox" name="all" v-model="isSelectAll">
                        <span @click="">全选</span>
                    </label>
                    <!--                    <el-button id="cart-delete" @click="del()">删除</el-button>-->
                    <el-popconfirm
                            confirm-button-text="是的"
                            cancel-button-text="不了"
                            title="确定删除所选商品吗？"
                            @confirm="del()">
                        <template #reference>
                            <el-button id="cart-delete">删除</el-button>
                        </template>
                    </el-popconfirm>
                </td>
                <td colspan="3">
                    <span>合计：</span>
                    <strong id="total-amount"><em id="total-amount-em">{{ getTotal }}</em></strong>
                    <el-button id="settlement" @click="submitCart()">立即结算</el-button>
                </td>
            </tr>
            </tfoot>
        </table>
        <div v-show="productList.length===0">
            <p>购物车还是空的哦~快来购物吧~</p>
        </div>
    </div>
</template>

<script>
import {Carts} from "@/js/Carts";
import axios from "axios";

export default {
    name: "Cart",
    data() {
        return {
            productList: [],
        };
    },
    computed: {
        Carts() {
            return Carts
        },
        getTotal() {
            let newArr = this.productList.filter(function (val) {
                return val.select === true;
            })
            let price = 0;
            for (let i = 0; i < newArr.length; i++) {
                price += newArr[i].number * newArr[i].price;
            }
            return price.toFixed(2)
        },
        isSelectAll: {
            get() {
                return this.productList.every(function (val) {
                    return val.select === true;
                })
            },
            set(newValue) {
                for (let i = 0; i < this.productList.length; i++) {
                    this.productList[i].select = newValue;
                }
            }
        }
    },
    methods: {
        del() {
            for (let i in this.productList) {
                if (this.productList[i].select === true) {
                    Carts.remove(this.productList[i].id);
                }
            }
            this.productList = Carts.get();
        },
        comboMinus(cart) {
            if (cart.number > 1) {
                Carts.setnum(cart, cart.number - 1);
                cart.number--;
            }
        },
        comboPlus(cart) {
            if (cart.count === null || cart.number < cart.count) {
                Carts.setnum(cart, cart.number + 1);
                cart.number++;
            }
        },
        submitCart() {
            // console.log(JSON.parse(sessionStorage.getItem('user')))
            if (JSON.parse(sessionStorage.getItem('user')) === null) {
                this.$toast.error('未登录', {
                    duration: 2000,
                    maxToasts: 4,
                    position: "top",
                })
                return;
            }
            let list = [];
            for (let i in this.productList) {
                // console.log(this.productList[i].select)
                if (this.productList[i].select === true) {
                    list.push({
                        count: this.productList[i].number,
                        itemId: this.productList[i].id,
                        price: this.productList[i].price * this.productList[i].number,
                    })
                    Carts.remove(this.productList[i].id);
                }
            }
            this.productList = Carts.get();
            if (list.length === 0) {
                this.$toast.error('未选择商品', {
                    duration: 2000,
                    maxToasts: 4,
                })
                return;
            }

            this.axios
                .post("/api/order/addlist", list)
                .then((request) => {
                    let data = request.data;
                    if (data.code === 200) {
                        this.$toast.success(data.msg, {
                            duration: 2000,
                            maxToasts: 4,
                        })
                        // Carts.clear();
                        // this.productList = [];
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
        }
    },
    mounted() {
        this.productList = Carts.get();
        // console.log(this.productList)
    }
}
</script>

<style scoped>
#cart {
    margin: 0;
    padding: 80px 150px 60px;
    overflow: hidden;
    width: calc(100vw - 300px);
}

#cart h1 {
    line-height: 40px;
    padding: 0 0 10px 0;
    color: white;
}

th, li, h3, span, p {
    color: white;
}

table {
    border-collapse: collapse;
    empty-cells: show;
    margin: 20px 0;
    padding: 0;
    table-layout: fixed;
    width: 100%;
    min-width: 900px;
    background-color: rgba(255, 255, 255, 0.1);
}

table th, table td {
    border-bottom: 1px solid #DDDDDD;
    padding: 15px 10px;
    text-align: left;
}

table {
    border-top: 3px solid #DDDDDD;
}

.goods .goods-image img {
    border: 1px solid #DDDDDD;
    float: left;
    height: 120px;
    max-width: 120px;
    margin: 0 20px 0 0;
}

.goods .goods-information {
    float: left;
}

.goods .goods-information ul {
    color: #666666;
    font-size: 12px;
    line-height: 20px;
    margin: 10px 0 0 0;
}

.price, .amount, #total-amount {
    color: #E00000;
}

#total-amount {
    font-size: 22px;
}

.price em, .amount em, #total-amount em {
    font-style: normal;
}

.combo .combo-minus, .combo .combo-value, .combo .combo-plus {
    background: #FFFFFF;
    border: 1px solid #DDDDDD;
    color: #333333;
    float: left;
    font-weight: bold;
    margin: 0;
    outline: none;
    text-align: center;
}

.combo .combo-minus, .combo .combo-plus {
    font-size: 16px;
    height: 26px;
    line-height: 26px;
    padding: 0;
    width: 24px;
}

.combo .combo-value {
    border-left: none;
    border-right: none;
    height: 20px;
    line-height: 20px;
    padding: 2px;
    width: 40px;
}

#cart-delete {
    margin-left: 20px;
}

#settlement {
    background: #E00000;
    border: none;
    color: #FFFFFF;
    float: right;
    font-size: 16px;
    height: 40px;
    line-height: 40px;
    margin: 0;
    outline: none;
    padding: 0;
    width: 160px;
}
</style>