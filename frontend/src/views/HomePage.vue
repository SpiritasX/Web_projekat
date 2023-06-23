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
  </div>
</template>

<style>
.home-page {
  text-align: center;
}

.heading {
  font-size: 28px;
  margin-bottom: 20px;
  color: #333;
}

.nav-links {
  display: flex;
  justify-content: center;
}

.nav-link {
  margin: 0 10px;
  text-decoration: none;
  color: #333;
  font-weight: bold;
  transition: color 0.3s ease;
}

.nav-link:hover {
  color: #ff6600;
}

.nav-link:nth-child(1) {
  color: #0099cc;
}

.nav-link:nth-child(2) {
  color: #9933cc;
}

.nav-link:nth-child(3) {
  color: #ff3366;
}

.nav-link:nth-child(4) {
  color: #66cc00;
}

.nav-link:nth-child(5) {
  color: #ff9900;
}
</style>

<script>
export default {
  name: 'HomePage'
}
</script>
