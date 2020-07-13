import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import Element from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import 'element-ui/lib/theme-chalk/base.css'
import 'element-ui/lib/theme-chalk/display.css'
import axios from 'axios'
import './axios'
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
import 'github-markdown-css/github-markdown.css'
import "./permission"
import echarts from 'echarts'
import animate from "animate.css"
import './styles/font.css'
import './permission.js'
import './filter_utils'

Vue.use(animate)
Vue.prototype.$echarts = echarts
Vue.use(Element)
Vue.use(mavonEditor)
Vue.use(router)
Vue.config.productionTip = false
Vue.prototype.$axios = axios

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
