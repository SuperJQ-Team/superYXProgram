<template>
    <div id="orders">
        <h1>我的订单</h1>
        <table>
            <thead>
            <tr>
                <th width="64%">订单详情</th>
                <th width="12%">金额</th>
                <th width="12%">状态</th>
                <th width="12%">操作</th>
            </tr>
            </thead>
            <tbody class="order-list">
            <tr v-for="order in Orders">
                <td class="good">
                    <div class="good-image">
                        <img v-bind:src="'/api/img/'+order.image_path">
                    </div>
                    <div class="good-information">
                        <h3>{{ order.name }}</h3>
                        <p>数量：{{ order.count }}</p>
                        <p>订单编号：{{ order.id }}</p>
                    </div>
                </td>
                <td>
                    <strong class="amount"><em
                            class="amount-em">{{ (order.price * order.count).toFixed(2) }}</em></strong>
                </td>
                <td class="state">
                    {{ order.state ?? "处理中" }}
                </td>
                <td>
                    <!--<el-button @click="delOrder(order)">取消订单</el-button>-->
                    <el-popconfirm
                            confirm-button-text="是的"
                            cancel-button-text="不了"
                            title="你真的要取消订单吗？"
                            @confirm="delOrder(order)">
                        <template #reference>
                            <el-button>取消订单</el-button>
                        </template>
                    </el-popconfirm>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</template>

<script>
export default {
    name: "Orders",
    data() {
        return {
            Orders: [],
            perPage: 10, // 每次加载的项目数量
            currentPage: 1, // 当前页码
        }
    },
    mounted() {
        this.currentPage = 1;
        this.loadMoreItems();
        window.addEventListener('scroll', this.handleScroll);
    },
    methods: {
        loadMoreItems() {
            if (JSON.parse(sessionStorage.getItem('user')) === null) {
                this.$toast.error('未登录', {
                    duration: 2000,
                    maxToasts: 4,
                    position: "top",
                })
                return;
            }
            this.axios.get("/api/order/list", {
                params: {
                    pageNum: this.currentPage,
                    pageSize: this.perPage,
                }
            }).then((response) => {
                // console.log(response)
                let data = response.data;
                if (data.code === 200) {
                    if (data.data.list.length === 0) {
                        // console.log('no new goods')
                        this.$toast.info('已经到底啦', {
                            duration: 2000,
                            maxToasts: 4,
                            position: 'bottom',
                        })
                    } else {
                        this.Orders = this.Orders.concat(data.data.list);
                        if (this.currentPage > 1)
                            this.$toast.info('点击导航栏回到顶部', {
                                duration: 2000,
                                maxToasts: 4,
                                position: 'bottom',
                            });
                        this.currentPage++;
                    }
                } else {
                    // window.alert(data.msg);
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
            if (bottomOfPage) {
                this.loadMoreItems();
            }
        },
        delOrder(order) {
            if (order.state == null) {
                //取消订单
                this.axios.get('/api/order/remove', {
                    params: {
                        id: order.id,
                    }
                }).then((response) => {
                    // console.log(response)
                    let data = response.data;
                    if (data.code === 200) {
                        for (let i in this.Orders) {
                            if (this.Orders[i].id === order.id) {
                                this.Orders.splice(i, 1);
                                break;
                            }
                        }
                        this.$toast.success('订单已取消', {
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
                });
            } else {
                this.$toast.error('订单已处理，无法取消', {
                    duration: 2000,
                    maxToasts: 4,
                })
            }
        }
    },
    beforeDestroy() {
        window.removeEventListener('scroll', this.handleScroll);
    },
    beforeRouteLeave(to, from) {
        window.removeEventListener('scroll', this.handleScroll);
    }
}
</script>

<style scoped>
#orders {
    margin: 0;
    padding: 80px 150px 60px;
    overflow: hidden;
    width: calc(100vw - 300px);
}

#orders h1 {
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

.order-list tr td {
    padding: 0;
}

.good-image {
    width: 120px;
    height: 120px;
    margin: 30px;
    left: 0;
    top: 0;
    bottom: 0;
    float: left;
}

.good-image img {
    width: 100%;
    height: 100%;
}

.amount {
    color: #E00000;
}

.amount em {
    font-style: normal;
}

.state {
    color: rgba(255, 255, 255, 1);
}

.good-information h3 {
    margin: 30px;
    font-size: 22px;
}


</style>