<template>
    <form>
        <label>Email</label>
        <input v-model="ZahtevDto.email"/>
        <label>Telefon</label>
        <input v-model="ZahtevDto.telefon"/>
        <label>Poruka</label>
        <input v-model="ZahtevDto.model"/>
        <button v-on:click="podnesiZahtev()">Podnesi zahtev</button>
    </form>
</template>

<script>
export default {
  name: 'PodnesiZahtev',
  data: function () {
    return {
      ZahtevDto: {
        email: '',
        telefon: '',
        poruka: ''
      }
    }
  },
  methods: {
    prijava: function () {
      fetch('http://localhost:8880/api/zahtevi/', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.ZahtevDto)
      }).then(response => {
        if (response.ok) {
          return response.json()
        }
      }).then(body => {
        this.$router.push('/')
      }).catch(error => {
        console.error(error)
      })
    }
  }
}
</script>
