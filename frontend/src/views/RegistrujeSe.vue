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
    </form>
</template>

<script>
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
    registracija: function () {
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
    }
  }
}
</script>
