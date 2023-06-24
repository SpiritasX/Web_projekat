<template>
  <knjiga-comp v-for="knjiga in knjige" :key="knjiga.id" :knjiga="knjiga"/>
</template>

<script>
import axios from 'axios';
import KnjigaComp from '../components/KnjigaComp.vue'
export default {
  name: 'HomePage',
  components: {
    KnjigaComp
  },
  data: function () {
    return {
      knjige: []
    }
  },
  mounted: function () {
    console.log(this.$route.query.pretraga)
    axios
    .get('http://localhost:8880/api/pretrazi?pretraga=' + this.$route.query.pretraga)
    .then(response => {
      this.knjige = response.data
    })
    .catch(error => { console.error(error) })
  }
}
</script>