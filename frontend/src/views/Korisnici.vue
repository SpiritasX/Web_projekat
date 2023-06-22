<template>
    <korisnik-comp v-for="korisnik in korisnici" :key="korisnik.id" :korisnik="korisnik" />
  </template>

<script>
import KorisnikComp from '../components/KorisnikComp.vue'
export default {
  name: 'KorisniciAplikacije',
  components: { KorisnikComp },
  data: function () {
    return {
      korisnici: []
    }
  },
  mounted: function () {
    fetch('http://localhost:8880/api/korisnci')
      .then(res => res.json())
      .then(data => { this.korisnici = data })
      .catch(error => console.error(error))
  }
}
</script>
