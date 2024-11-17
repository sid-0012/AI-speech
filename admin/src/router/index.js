import {createRouter, createWebHistory} from 'vue-router'
import Home from "../view/home.vue"
import Login from "../view/login.vue";
import Welcome from "../view/home/welcome.vue"
import Help from "../view/home/help.vue";
import Filetrans from "../view/home/filetrans.vue";

const routes = [{
  path: "/",
  redirect: "/login"
}, {
  path: "/login",
  component: Login
}, {
  path: "/home",
  component: Home,
  children: [{
    path: "filetrans",
    component: Filetrans,
  }, {
    path: "welcome",
    component: Welcome,
  }, {
    path: "help",
    component: Help,
  }]
}]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
