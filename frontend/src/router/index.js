import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import KorisnikView from '../views/KorisnikView.vue'
import PrijaviSe from '../views/PrijaviSe.vue'
import RegistrujSe from '../views/RegistrujeSe.vue'
import AutorView from '../views/AutorView.vue'
import PolicaView from '../views/PolicaView.vue'

const routes = [
  {
    path: '/',
    name: 'home',
    component: HomePage
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
  },
  {
    path: '/korisnik/:id',
    name: 'korisnik',
    component: KorisnikView
  },
  {
    path: '/autor/:id',
    name: 'autor',
    component: AutorView
  },
  {
    path: '/polica/:id',
    name: 'polica',
    component: PolicaView
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
