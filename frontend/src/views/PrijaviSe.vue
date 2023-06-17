<template>
    <form>
        <label>Email</label>
        <input v-model="LoginDto.email"/>
        <label>Lozinka</label>
        <input v-model="LoginDto.lozinka"/>
        <button v-on:click="prijava()">Prijavi se</button>
    </form>
</template>

<script>
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
      fetch('http://localhost:8880/api/korisnici/prijavi-se', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(this.LoginDto)
      }).then(response => {
        if (response.ok) {
          return response.json()
        }
      }).then(body => {
        this.$router.push('/profil?id=' + body.id)
      }).catch(error => {
        console.error(error)
      })
    }
  }
}
</script>
