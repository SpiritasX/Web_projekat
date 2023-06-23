<template>
    <div class="knjiga-view">
      <h1>{{ knjiga.naslov }}</h1>
      <!-- <h3>{{ knjiga.autor.ime }} {{ knjiga.autor.prezime }}</h3> -->
      {{ knjiga.ocena }}
      <br />
      {{ knjiga.opis }}
      <br />
      <h3>Zanrovi</h3>
      <p v-for="zanr in knjiga.zanrovi" :key="zanr.id" :zanr="zanr">{{ zanr.naziv }}</p>
      <h3>Recenzije</h3>
      <recenzija-comp v-for="recenzija in recenzije" :key="recenzija.id" :recenzija="recenzija" />
      <div class="slike-knjiga">
        <img src="../assets/knjige6.jpg" alt="Slika knjiga" />
        <img src="../assets/knjige7.jpg" alt="Slika knjiga" />
        <img src="../assets/knjige8.jpg" alt="Slika knjiga" />
        <img src="../assets/knjige9.jpg" alt="Slika knjiga" />
      </div>
    </div>
  </template>
  
  <script>
  import axios from 'axios';
  import RecenzijaComp from '../components/RecenzijaComp.vue'
  export default {
    name: 'KnjigaView',
    components: {
      RecenzijaComp
    },
    data: function () {
      return {
        knjiga: {},
        recenzije: []
      }
    },
    mounted: function () {
      axios
        .get('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2])
        .then(response => {
          this.knjiga = response.data
        })
        .catch(error => {
          console.error(error)
        })
  
      axios
        .get('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2] + '/recenzije')
        .then(response => {
          this.recenzije = response.data
        })
        .catch(error => {
          console.error(error)
        })
    }
  }
  </script>
  
  <style>
  .knjiga-view {
    color: #333;
    background-color: #d2f1f4;
    padding: 20px;
    border-radius: 5px;
  }
  
  h1 {
    font-size: 24px;
    font-weight: bold;
  }
  
  h3 {
    font-size: 18px;
    font-weight: bold;
  }
  
  p {
    margin-bottom: 10px;
  }
  
  .slike-knjiga {
    display: flex;
    flex-direction: column;
    align-items: flex-end;
    margin-top: 20px;
  }
  
  .slike-knjiga img {
    width: 200px;
    height: auto;
    margin-bottom: 10px;
    border-radius: 5px;
  }
  </style>
  