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
      <div class="dodaj-na-policu">
        <form>
          <label>Polica na koju želiš da dodaš:</label>
          <input v-model="naziv_police">
          <button v-on:click="dodaj()">Dodaj</button>
        </form>
        <div class="poruka">
            {{ this.amsg }}
        </div>
      </div>
      <div class="ukloni-sa-police">
        <form>
          <label>Polica sa koje brišeš:</label>
          <input v-model="naziv_police">
          <button v-on:click="obrisi()">Obriši</button>
        </form>
        <div class="poruka">
            {{ this.rmsg }}
        </div>
      </div>
      <h3>Recenzije</h3>
      <recenzija-comp v-for="recenzija in recenzije" :key="recenzija.id" :recenzija="recenzija" />
      <div v-if="this.$cookies.get('ULOGA') === 'ADMINISTRATOR'">
        <router-link :to="'/azuriraj_knjigu?id='+this.knjiga.id">Azuriraj knjigu</router-link>
        <button v-on:click="ukloni()">Ukloni</button>
      </div>
      <div class="slika-desno">
        <img src="../assets/knjige10.jpeg" alt="Slika knjige" class="smanjena-slika">
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
            recenzije: [],
            amsg: '',
            rmsg: '',
            naziv_police: '',
            id: ''
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2])
        .then(response => {
            this.knjiga = response.data
        })
        .catch(error => { console.error(error) })

        axios
        .get('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2] + '/recenzije')
        .then(response => {
            this.recenzije = response.data
        })
        .catch(error => { console.error(error) })
    },
    methods: {
        async dodaj() {
            await axios
            .get('http://localhost:8880/api/police/ponazivu/' + this.naziv_police, { withCredentials:true})
            .then(response => {
                this.id = response.data.id
            })
            .catch(error => { console.error(error) })

            await axios
            .get('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2] + '/polica/' + this.id, { withCredentials: true })
            .then(response => {
                this.amsg = response.body
            })
            .catch(error => { console.error(error) })
        },
        async obrisi() {
            await axios
            .get('http://localhost:8880/api/police/ponazivu/' + this.naziv_police, { withCredentials:true})
            .then(response => {
                this.id = response.data.id
            })
            .catch(error => { console.error(error) })

            await axios
            .delete('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2] + '/polica/' + this.id, { withCredentials: true })
            .then(response => {
                this.rmsg = response.body
            })
            .catch(error => { console.error(error) })
        },
        ukloni() {
            axios
            .delete('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2], {withCredentials:true})
            .catch(error => { console.error(error) })
        }
    }
}
</script>
<style>
.knjiga-view {
  font-family: "Arial", sans-serif;
  color: #333;
  background-color: #f7f9fa;
  padding: 20px;
}

h1 {
  font-size: 24px;
  font-weight: bold;
  color: #008080;
}

h3 {
  font-size: 18px;
  font-weight: bold;
  color: #a0522d;
}

p {
  color: #333;
}

input,
textarea {
  width: 100%;
  padding: 8px;
  margin-top: 5px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

button {
  background-color: #008080;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

button:hover {
  background-color: #006666;
}

.dodaj-na-policu,
.ukloni-sa-police {
  margin-top: 20px;
}

.poruka {
  color: #a0522d;
}

.slika-desno {
  float: right;
  margin-left: 20px;
  margin-top: -600px;
}

.smanjena-slika {
  max-width: 300px;
  height: auto;
}

</style>