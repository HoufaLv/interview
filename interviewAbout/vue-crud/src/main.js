// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
// 导入ElementUI
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 导入axios
import axios from 'axios'

Vue.use(ElementUI);
Vue.prototype.$http=axios

// ajax 默认提交地址,默认url 的前缀
axios.defaults.baseURL="http://localhost:9090"

Vue.config.productionTip = false

// 路由的前置过滤,查看当前的路由是否需要认证
// 如果需要认证就从localStorage中获取Token,如果有值,就跳转,如果Token 不存在,就跳转到登陆路由去登陆去

router.beforeEach((to,from,next)=>{
  // 如贵如果需要认证
  if(to.meta.reqiredAuth){
    //如果localStorage.getItem("jwtToken") 存在,就继续,否则就去登陆页面
    var token = localStorage.getItem("jwtTokend")
    // 如果不存在
    if(!token){
      router.push("/login");
      return;
    }
  }

  next();
});

// axios 请求拦截器,将Token 放入到HttpHeader 中发送到服务器
axios.interceptors.request.use(config=>{
  var token = localStorage.getItem("jwtToken");
  // 如果Token 存在的话
  if(token){
    // 将token 放入header
    config.headers.Authorization = token;
  }
  return config;
},error=>{
  return Promise.reject(error);
});

 // axios 响应拦截器,判断服务器是否成功 200 或者 401 什么的
 axios.interceptors.response.use(response=>{
   return response;
 },error=>{
    if(error.response){
      if(error.response.status == 401){
        // 响应不成功,删除token
        localStorage.removeItem("jwtToken");
        router.push("/");
      }
    }
    return Promise.reject(error.response.data);
 });
 

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
