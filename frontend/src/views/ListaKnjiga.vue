<template>
    <knjiga-comp v-for="knjiga in knjige" :key="knjiga.id" :knjiga="knjiga" />
  </template>

<script>
import KnjigaComp from '../components/KnjigaComp.vue'
export default {
  name: 'ListaKnjiga',
  components: { KnjigaComp },
  data: function () {
    return {
      knjige: []
    }
  },
  mounted: function () {
    fetch('http://localhost:8880/api/knjige/')
      .then(res => res.json())
      .then(data => { this.knjige = data })
      .catch(error => console.error(error))
  }
}
</script>
