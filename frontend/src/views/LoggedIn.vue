<template>
  <button @click="dodajNovuPolicu"> = Dodaj novu policu</button>
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
  mounted () {
    this.ucitajPolice()
  },

  methods: {
    ucitajPolice () {
      fetch('http://localhost:8880/api/korisnici/' + this.$route.query.id + '/police')
        .then(res => res.json())
        .then(data => { this.police = data })
        .catch(error => console.error(error))
    },
    dodajNovuPolicu () {
      const novaPolica = {
        nazivPolice: 'Naziv nove police',
        primarna: false
      }
      fetch('http://localhost:8880/police/', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify(novaPolica)
      })
        .then(res => res.json())
        .then(data => {
          console.log('Uspela konacno dodata polica:', data)
          this.ucitajPolice()
        })
        .catch(error => console.error(error))
    }
  }
}
</script>
