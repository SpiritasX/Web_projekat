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
import axios from 'axios'
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
      .post('http://localhost:8880/api/korisnici/prijavi-se', this.LoginDto, {
        timeout: 5000,
        withCredentials: true,
        headers: {
          'Cookie': 'JSESSIONID=test'//document.cookie.match(/JSESSIONID=([^;]+)/)[1]
        }
      })
      .then(response => {
        if (response.ok) {
          return response.json();
        }
      })
      .then(body => {
        this.$router.push('/korisnik/' + body.id);
      })
      .catch(error => {
        console.error(error);
      });
      // fetch('http://localhost:8880/api/korisnici/prijavi-se', {
      //   method: 'POST',
      //   headers: {
      //     Accept: 'application/json',
      //     'Content-Type': 'application/json'
      //   },
      //   body: JSON.stringify(this.LoginDto)
      // })
      //   .then(response => {
      //     if (response.ok) {
      //       return response.json()
      //     }
      //   }).then(body => {
      //     this.$router.push('/korisnik/' + body.id)
      //   }).catch(error => {
      //     console.error(error)
      //   })
    }
  }
}
</script>
