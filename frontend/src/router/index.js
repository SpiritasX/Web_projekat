import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import LoggedIn from '../views/LoggedIn.vue'
import PrijaviSe from '../views/PrijaviSe.vue'
import RegistrujSe from '../views/RegistrujeSe.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
  },
  {
    path: '/profil',
    name: 'profil',
    component: LoggedIn
  },
  {
    path: '/prijavi-se',
    name: 'login',
    component: PrijaviSe
  },
  {
    path: '/registruj-se',
    name: 'register',
    component: RegistrujSe
  }
]

const router = createRouter({
  history: createWebHashHistory(),
  routes
})

export default router
