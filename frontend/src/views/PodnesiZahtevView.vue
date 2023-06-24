<template>
    <form>
        <label>Email</label>
        <input v-model="ZahtevDto.email"/>
        <label>Telefon</label>
        <input v-model="ZahtevDto.telefon"/>
        <label>Poruka</label>
        <input v-model="ZahtevDto.poruka"/>
        <button v-on:click="podnesiZahtev()">Podnesi zahtev</button>
    </form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'PodnesiZahtev',
  data: function () {
    return {
      ZahtevDto: {
        email: '',
        telefon: '',
        poruka: '',
        idAutora: 0
      }
    }
  },
  mounted: function () {
    this.ZahtevDto.idAutora = this.$route.query.id
  },
  methods: {
    podnesiZahtev: function () {
      axios
      .post('http://localhost:8880/api/zahtevi', this.ZahtevDto)
      .then(this.$router.push('/'))
      .catch(error => {
        console.error(error)
      })
    }
  }
}
</script>
