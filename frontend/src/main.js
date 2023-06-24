import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import cookies from 'vue-cookies'

createApp(App).use(router).use(cookies).mount('#app')
