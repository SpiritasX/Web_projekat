<template>
  <div class="home-page">
    <h1 class="heading">BetterReads</h1>
    <div class="bookshelf-image">
      <img src="assets\knjige.jpg" alt=" knjige">
    </div>
    <nav class="nav-links">
      <router-link class="nav-link" to="/prijavi-se">Prijavi se</router-link>
      <router-link class="nav-link" to="/registruj-se">Registruj se</router-link>
      <router-link class="nav-link" to="/lista-knjiga">Lista knjiga</router-link>
      <router-link class="nav-link" to="/korisnici">Korisnici Stranice</router-link>
      <router-link class="nav-link" to="/podnesi-zahtev">Zahtev za autora</router-link>
    </nav>
  </div>
</template>

<style>
.home-page {
  text-align: center;
  background-color: #cfe9f9;
  padding: 20px;
}

.heading {
  font-size: 28px;
  margin-bottom: 20px;
  color: #333;
  text-shadow: 2px 2px 4px rgba(0, 0, 0, 0.3);
}

.bookshelf-image img {
  width: 400px;
  height: auto;
  margin-bottom: 20px;
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
  color: #1e8cbf;
  font-weight: bold;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #ff8080;
}

.nav-link:nth-child(1) {
  color: #0099cc;
}

.nav-link:nth-child(2) {
  color: #00b8a9;
}

.nav-link:nth-child(3) {
  color: #00a4d3;
}

.nav-link:nth-child(4) {
  color: #0089b3;
}

.nav-link:nth-child(5) {
  color: #00a7b5;
}
</style>

<script>
export default {
  name: 'HomePage'
}
</script>
