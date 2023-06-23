<template>
  <div class="container">
    <div class="welcome-message">
      <h2 class="ombre-text">Welcome back!</h2>
    </div>
    <div class="form-container">
      <form>
        <input v-model="LoginDto.email" placeholder="Email" />
        <input v-model="LoginDto.lozinka" placeholder="Lozinka" />
        <button v-on:click="prijava()">Prijavi se</button>
      </form>
    </div>
    <div class="image-container">
      <img src="../assets/knjige2.jpg" alt="Slika knjiga" />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
export default {
  name: 'PrijaviSe',
  data() {
    return {
      LoginDto: {
        email: '',
        lozinka: ''
      }
    };
  },
  methods: {
    prijava() {
      axios
      .post('http://localhost:8880/api/korisnici/prijavi-se', this.LoginDto)
      .then(response => {
        console.log(response)
        if (response.status === 200) {
          this.$router.push('/korisnik/' + response.data.id);
        }
      })
      .catch(error => {
        console.error(error);
      });
    }
  }
};
</script>

<style>
@import url('https://fonts.googleapis.com/css2?family=Courgette&display=swap');

.container {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #f2e8da;
  padding: 10px;
}

.welcome-message {
  margin-bottom: 16px;
}

.ombre-text {
  font-family: 'Courgette', cursive;
  font-size: 24px;
  background: linear-gradient(45deg, #8a6d3b, #f2e8da);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.form-container {
  width: 40%;
}

form {
  display: flex;
  flex-direction: column;
}

input {
  padding: 8px;
  margin-bottom: 8px;
  border: 1px solid #8a6d3b;
}

button {
  padding: 8px 16px;
  background-color: #8a6d3b;
  color: #fff;
  border: none;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #724e28;
}

.image-container {
  width: 60%;
  text-align: right;
}

img {
  max-width: 100%;
  height: auto;
}
</style>
