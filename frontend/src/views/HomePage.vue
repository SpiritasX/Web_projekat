<template>
  <div class="home-page">
    <h1 class="heading">BetterReads</h1>
    <nav class="nav-links">
      <router-link class="nav-link" to="/prijavi-se">Prijavi se</router-link>
      <router-link class="nav-link" to="/registruj-se">Registruj se</router-link>
      <router-link class="nav-link" to="/lista-knjiga">Lista knjiga</router-link>
      <router-link class="nav-link" to="/korisnici">Korisnici Stranice</router-link>
      <router-link class="nav-link" to="/podnesi-zahtev">Zahtev za autora</router-link>
    </nav>
    <div class="bookshelf-image">
      <img src="../assets/knjige.jpeg"  style="width: 300px;">
      <img src="../assets/knjige2.jpg"  style="width: 300px;">
      <img src="../assets/knjige3.jpg"  style="width: 300px;">
    </div>
    <form>
      <input v-model="this.pretraga" placeholder="Search">
      <button v-on:click="this.$router.push('/pretrazi?pretraga=' + this.pretraga)">Pretrazi</button>
    </form>
    <div v-if="this.$admin">
      <router-link to="/dodaj_knjigu">Dodaj knjigu</router-link>
      <router-link to="/zahtevi">Svi zahtevi</router-link>
      <form>
        <label>Dodaj zanr</label>
        <input v-model="this.naziv">
        <button v-on:click="dodaj_zanr()"></button>
      </form>
    </div>
  </div>
</template>

<style>
.home-page {
  text-align: center;
  background-color: #f5f5dc; /* Bež */
  padding: 20px;
}

.heading {
  font-size: 28px;
  margin-bottom: 20px;
  color: #a0522d; /* Braon */
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.bookshelf-image {
  display: flex;
  justify-content: center;
  margin-top: 20px;
}

.bookshelf-image img {
  width: 300px;
  height: auto;
  margin: 0 10px;
}

.nav-links {
  display: flex;
  justify-content: center;
  list-style: none;
  padding: 0;
}

.nav-link {
  margin: 0 10px;
  text-decoration: none;
  color: #a0522d; /* Braon */
  font-weight: bold;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #8b4513; /* Braon */
}

.nav-link:nth-child(1) {
  color: #f5deb3; /* Bež */
}

.nav-link:nth-child(2) {
  color: #d2b48c; /* Bež */
}

.nav-link:nth-child(3) {
  color: #f0e68c; /* Bež */
}

.nav-link:nth-child(4) {
  color: #ffd700; /* Bež */
}

.nav-link:nth-child(5) {
  color: #daa520; /* Bež */
}
</style>

<script>
import axios from 'axios';

export default {
  name: 'HomePage',
  data: function (naziv) {
    return {
      naziv: ''
    }
  },
  methods: {
    dodaj_zanr() {
      if (this.naziv != null) {
        axios
        .post('http://localhost:8880/api/zanrovi/?naziv=' + this.naziv)
        .catch(error => { console.error(error)})
      }
    }
  }
}
</script>