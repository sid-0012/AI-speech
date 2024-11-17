import { createApp } from 'vue'
import './style.css'
import App from './App.vue'
import Antd from 'ant-design-vue';
import 'ant-design-vue/dist/reset.css';
import * as Icons from '@ant-design/icons-vue';
import router from "./router"
import axios from "axios";
import store from "./store/index.js";

const app = createApp(App);
app.use(Antd).use(router).mount('#app');

// 全局使用图标
const icons
  = Icons;
for (const i in icons) {
  app.component(i, icons[i]);
}

/**
 * axios拦截器
 */
axios.interceptors.request.use(function (config) {
  console.log('请求参数：', config);
  let _token = store.state.user.token;
  if (_token) {
    config.headers.token = _token;
    console.log("请求headers增加token: ", _token);
  }
  return config;
}, error => {
  return Promise.reject(error);
});
axios.interceptors.response.use(function (response) {
  console.log('返回结果：', response);
  return response;
}, error => {
  console.log('返回错误：', error);
  const response = error.response;
  const status = response.status;
  if (status === 401) {
    // 判断状态码是401 跳转到登录
    console.log("未登录，跳到登录页面");
    store.commit("setMember", {});
    router.push("/login");
  }
  return Promise.reject(error);
});

console.log("服务端：", import.meta.env.VITE_SERVER);
axios.defaults.baseURL = import.meta.env.VITE_SERVER;
