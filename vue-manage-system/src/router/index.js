import {createRouter, createWebHashHistory} from "vue-router";
import Home from "../views/Home.vue";

const routes = [
    {
        path: '/',
        redirect: '/dashboard'
    }, {
        path: "/",
        name: "Home",
        component: Home,
        children: [
            {
                path: "/dashboard",
                name: "dashboard",
                meta: {
                    title: '系统首页'
                },
                component: () => import ( /* webpackChunkName: "dashboard" */ "../views/Dashboard.vue")
            },
            {
                path: "/data/insert",
                name: "data-insert",
                meta: {
                    title: "数据导入"
                },
                component: () => import( /* webpackChunkName: "data-insert" */ "../views/datas/insert.vue")
            },
            {
                path: "/data/modify",
                name: "data-modify",
                meta: {
                    title: "数据更改"
                },
                component: () => import( /* webpackChunkName: "data-insert" */ "../views/datas/modify.vue")
            },
            {
                path: "/data/account",
                name: "data-account",
                meta: {
                    title: "帐号管理"
                },
                component: () => import( /* webpackChunkName: "data-insert" */ "../views/datas/account.vue")
            },
            {
                path: "/bot/add",
                name: "bot-add",
                meta: {
                    title: "增加机器人"
                },
                component: () => import( /* webpackChunkName: "data-insert" */ "../views/bot/add.vue")
            },
            {
                path: "/bot/manager",
                name: "bot-manager",
                meta: {
                    title: "机器人管理"
                },
                component: () => import( /* webpackChunkName: "data-insert" */ "../views/bot/manager.vue")
            },
        ]
    }, {
        path: "/login",
        name: "Login",
        meta: {
            title: '登录'
        },
        component: () => import ( /* webpackChunkName: "login" */ "../views/Login.vue")
    }
];

const router = createRouter({
    history: createWebHashHistory(),
    routes
});

router.beforeEach((to, from, next) => {
    document.title = `${to.meta.title} | 健康打卡机器人后台管理`;
    const role = localStorage.getItem('ms_username');
    if (!role && to.path !== '/login') {
        next('/login');
    } else if (to.meta.permission) {
        // 如果是管理员权限则可进入，这里只是简单的模拟管理员权限而已
        role === 'admin'
            ? next()
            : next('/403');
    } else {
        next();
    }
});

export default router;