const home = () => import('../components/home.vue')
const echartsTest = () => import('../view/echartsTest.vue')
const radar = () => import('../view/radar.vue')
const elemTest = () => import('../view/elemTest.vue')
const login = () => import('../view/login.vue')



const routes = [
    {
        path: '/',
        redirect: '/home'
    },
    {
        name: 'home',
        path: '/home',
        component: home
    },
    {
        name: 'echartsTest',
        path: '/echartsTest',
        component: echartsTest
    },
    {
        name: 'radar',
        path: '/radar',
        component: radar
    },
    {
        name: 'elemTest',
        path: '/elemTest',
        component: elemTest
    },
    {
        name: 'login',
        path: '/login',
        component: login
    }
];
export default routes