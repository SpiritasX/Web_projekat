import { createApp, h } from 'vue'
import App from './App.vue'
import router from './router'
import cookies from 'vue-cookies'

const app = createApp({
  setup() {
    app.config.globalProperties.$prijavljen = false;
    app.config.globalProperties.$admin = false;
  },
  render: () => h(App),
})

app.use(router).use(cookies).mount('#app')
