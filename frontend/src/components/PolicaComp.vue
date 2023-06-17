<template>
  <div>
    <h1>{{ polica.naziv }}</h1>
    <knjiga-comp v-for="stavka in polica.stavke" :key="stavka.knjiga.id" :knjiga="stavka.knjiga" />
  </div>
</template>

<script>
import KnjigaComp from './KnjigaComp.vue'
export default {
  name: 'PolicaComp',
  props: ['polica'],
  components: { KnjigaComp }
}
</script>
