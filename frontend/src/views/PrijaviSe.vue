<template>
    <form>
        <label>Email</label>
        <input name="email"/>
        <label>Lozinka</label>
        <input name="lozinka"/>
        <button v-on:click="prijava">Prijavi se</button>
    </form>
</template>

<script>
import axios from "axios"
export default {
  name: 'PrijaviSe',
  data: function () {
    return {
      LoginDto: {
        email: '',
        lozinka: ''
      }
    }
  },
  methods: {
    prijava: function () {
      axios
      .post('http://localhost:8880/api/korisnici/prijavi-se/', this.LoginDto)
      .then((res) => {
        if (res.ok) {
          this.$router.push('/profil')
        }
      })
      .catch(error => {
        console.error(error)
      })
    }
  }
}
</script>
