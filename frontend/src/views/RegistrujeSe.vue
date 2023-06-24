<template>
    <form>
        <label>Ime</label>
        <input v-model="RegisterDto.ime"/>
        <label>Prezime</label>
        <input v-model="RegisterDto.prezime"/>
        <label>Korisnicko ime</label>
        <input v-model="RegisterDto.korisnickoIme"/>
        <label>Email</label>
        <input v-model="RegisterDto.email"/>
        <label>Lozinka</label>
        <input v-model="RegisterDto.lozinka"/>
        <label>Ponovi lozinku</label>
        <input v-model="RegisterDto.ponovljenaLozinka"/>
        <button v-on:click="registracija()">Registruj se</button>
        <button v-if="this.$admin" v-on:click="registracijaAutora()">Registruj autora</button>
    </form>
</template>

<script>
import axios from 'axios'

export default {
  name: 'RegistrujSe',
  data: function () {
    return {
      RegisterDto: {
        ime: '',
        prezime: '',
        korisnickoIme: '',
        email: '',
        lozinka: '',
        ponovljenaLozinka: ''
      }
    }
  },
  methods: {
    registracija() {
      fetch('http://localhost:8880/api/korisnici/registruj-se', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.RegisterDto)
      }).then(async (res) => {
        if (res.ok) {
          this.$router.push('/')
        }
      }).catch(error => {
        console.error(error)
      })
    },
    registracijaAutora() {
      axios
      .post('http://localhost:8880/api/korisnici/kreiraj-autora', this.RegisterDto, { withCredentials: true })
      .then(res => {
        if (res.ok) {
          this.$router.push('/')
        }
      })
      .catch(error => { console.error(error) })
    }
  }
}
</script>
