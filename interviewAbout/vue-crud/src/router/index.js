import Vue from 'vue'
import Router from 'vue-router'
import Home from '@/components/Home'
import AddMovie from '@/components/AddMovie'
import Login from '@/components/Login'

// AddMovie

Vue.use(Router)

export default new Router({
  // 配置路由
  routes: [
    {
      path:'/login',
      name:'Login',
      component: Login
    },
    {
      path: '/',
      name: 'Home',
      component: Home
    },
    {
      path: '/new',
      name: 'AddMovie',
      component: AddMovie
    }
  ]
})
