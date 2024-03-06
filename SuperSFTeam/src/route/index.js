import {createRouter, createWebHistory} from 'vue-router'

const Index = () => import('../components/Index.vue')
const Mall = () => import('../components/Mall.vue')
const Cart = () => import('../components/Cart.vue')
const Orders = () => import('../components/Orders.vue')
const Setting = () => import('../components/Setting.vue')

const routes = [
    {
        path: '/',
        redirect: '/index',
    },
    {
        path: '/index',
        name: '/index',
        component: Index,
    },
    {
        path: '/mall',
        name: '/mall',
        component: Mall,
    },
    {
        path: '/cart',
        name: '/cart',
        component: Cart,
    },
    {
        path: '/orders',
        name: '/orders',
        component: Orders,
    },
    {
        path: '/setting',
        name: '/setting',
        component: Setting,
    }
];

const router = createRouter({
    history: createWebHistory(), base: '/dist/', routes
});

export default router
