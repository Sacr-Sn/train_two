import { createApp } from 'vue'
import App from './App.vue'

import router from './router/index'


// 引入echarts.js
import echarts from './utils/echarts';

// 引入axios
// import axios from './http/index'

// 引入icon图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'


const app = createApp(App);

app.config.globalProperties.$echarts = echarts;
app.provide('$echarts', echarts);

// app.config.globalProperties.$api = axios;
// app.provide('$axios', axios);

for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

// app.use(router).use(axios).mount('#app');
app.use(router).mount('#app');


