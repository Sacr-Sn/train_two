import {createRouter, createWebHashHistory} from 'vue-router'
import routes from './routes'
import {HEADER_TOKEN} from '../common/constants'
import {getLocalToken} from '../common/token'

const router = createRouter({
	routes,
    history: createWebHashHistory()
})

// 白名单
const whiteList = ['/login','/']
router.beforeEach((to, from, next) => {
    // 拿token
    if(getLocalToken()) {
        // 如果有token，还要去login页面，不允许
        if (to.path === '/login') {
            next('/')
        } else {
            next()
        }
    } else {
        // 没有token
        if (whiteList.includes(to.path)) {
            next()
        } else {
            next('/login')
        }
    }
})

export default router
