<template>
    <img src="../assets/user.png">
    <h1>{{ korisnik.ime }} {{ korisnik.prezime }}</h1>
    <hr />
    Police
    <router-link v-for="polica in police" :key="polica.id" :polica="polica" :to="{ name: 'polica', params: { id: polica.id }}">{{ polica.naziv }}</router-link>
</template>

<script>
export default {
  data: function () {
    return {
      korisnik: {},
      police: []
    }
  },
  mounted: function () {
    fetch('http://localhost:8880/api/korisnici/' + 1)
      .then(response => response.json())
      .then(body => { this.korisnik = body })
      .catch(error => { console.error(error) })

    fetch('http://localhost:8880/api/korisnici/' + 1 + '/police')
      .then(response => response.json())
      .then(body => { this.police = body })
      .catch(error => { console.error(error) })
  }
}
</script>
