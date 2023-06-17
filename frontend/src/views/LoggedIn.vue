<template>
  <polica-comp v-for="polica in police" :key="polica.id" :polica="polica" />
</template>

<script>
import PolicaComp from '../components/PolicaComp.vue'
export default {
  name: 'LoggedIn',
  components: { PolicaComp },
  data: function () {
    return {
      police: []
    }
  },
  mounted: function () {
    fetch('http://localhost:8880/api/korisnici/' + this.$route.query.id + '/police')
      .then(res => res.json())
      .then(data => { this.police = data })
      .catch(error => console.error(error))
  }
}
</script>
