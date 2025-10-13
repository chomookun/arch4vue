import { createApp } from 'vue'
import App from './App.vue'
import { createRouter, createWebHistory } from 'vue-router'

// 1. 자동으로 views 폴더의 .vue 파일을 라우트로 등록
const pages = require.context('./views', true, /\.vue$/)

const routes = pages.keys().map((path) => {
    const name = path.replace(/^\.\/(.*)\.vue$/, '$1')
    return {
        path: `/${name.toLowerCase()}`,
        name,
        component: pages(path).default
    }
})

// 2. 라우터 생성
const router = createRouter({
    history: createWebHistory(),
    routes
})

// 3. 앱 생성 및 마운트
createApp(App).use(router).mount('#app')
