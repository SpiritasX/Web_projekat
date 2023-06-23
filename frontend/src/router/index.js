import { createRouter, createWebHashHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import KorisnikView from '../views/KorisnikView.vue'
import PrijaviSe from '../views/PrijaviSe.vue'
import RegistrujSe from '../views/RegistrujeSe.vue'
import ListaKnjiga from '../views/ListaKnjiga.vue'
import KorisniciAplikacije from '../views/Korisnici.vue'
import PodnesiZahtev from '../views/Zahtev.vue'
import AutorView from '../views/AutorView.vue'
import PolicaView from '../views/PolicaView.vue'

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
  },
  {
    path: '/lista-knjiga',
    name: 'listaKnjiga',
    component: ListaKnjiga
  },
  {
    path: '/korisnici',
    name: 'korisnici',
    component: KorisniciAplikacije
  },
  {
    path: '/podnesi-zahtev',
    name: 'ponesiZahtev',
    component: PodnesiZahtev
  }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
