import { createApp } from 'vue'
import router from "./router.js";
import App from './App.vue'
import ElementPlus from 'element-plus'
import 'element-plus/theme-chalk/index.css'

import zhCn from 'element-plus/lib/locale/lang/zh-CN'
import Particles from 'particles.vue3'
import { createPinia } from "pinia";
import piniaPluginPersistedState from 'pinia-plugin-persistedstate'

const app = createApp(App);
const pinia = createPinia();
pinia.use(piniaPluginPersistedState)
app.use(Particles)
app.use(pinia)
app.use(router);

app.use(ElementPlus, { locale: zhCn })
app.mount('#app')
