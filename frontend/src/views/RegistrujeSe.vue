<template>
  <div class="container">
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
    <div class="image-container">
      <div class="welcome-message">
      <h2 class="ombre-text">Welcome</h2>
    </div>
      <div class="image-wrapper">
        <img src="../assets/knjige4.jpeg" alt="Slika knjiga" />
        <img src="../assets/knjige5.jpeg" alt="Slika knjiga" />
      </div>
    </div>
  </div>
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

<style>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

form {
  width: 300px;
  padding: 20px;
  background-color: rgb(143, 242, 255);
  border-radius: 5px;
}

form label {
  display: block;
  margin-bottom: 5px;
}

form input {
  width: 100%;
  padding: 5px;
  margin-bottom: 10px;
  border: 1px solid #df7676;
  border-radius: 3px;
}

form button {
  width: 100%;
  padding: 10px;
  background-color: rgb(214, 167, 141);
  color: rgb(252, 255, 255);
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

.image-container {
  margin-left: 20px;
}
@import url('https://fonts.googleapis.com/css2?family=Courgette&display=swap');

.welcome-message {
  font-size: 30px;
  font-weight: bold;
  margin-bottom: 10px;
  text-align: center;
}
.ombre-text {
  font-family: 'Courgette', cursive;
  font-size: 24px;
  background: linear-gradient(45deg, #00e5ff, #a80707);
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
}

.image-wrapper {
  display: flex;
  justify-content: space-between;
}

.image-wrapper img {
  width: 250px;
  height: auto;
  border-radius: 5px;
  box-shadow: 0 2px 5px rgba(114, 189, 224, 0.1);
}
</style>
