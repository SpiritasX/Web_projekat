import { createRouter, createWebHistory } from 'vue-router'
import HomePage from '../views/HomePage.vue'
import KorisnikView from '../views/KorisnikView.vue'
import PrijaviSe from '../views/PrijaviSe.vue'
import RegistrujSe from '../views/RegistrujeSe.vue'
import ListaKnjiga from '../views/ListaKnjiga.vue'
import KorisniciAplikacije from '../views/Korisnici.vue'
import PodnesiZahtev from '../views/Zahtev.vue'
import AutorView from '../views/AutorView.vue'
import PolicaView from '../views/PolicaView.vue'
import ZanrView from '../views/ZanrView.vue'
import KnjigaView from '../views/KnjigaView.vue'

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
  },
  {
    path: '/knjige/:id',
    name: 'knjiga',
    component: KnjigaView
  },
  {
    path: '/zanrovi/:id',
    name: 'zanr',
    component: ZanrView
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
