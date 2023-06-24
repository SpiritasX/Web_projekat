<template>
    <div class="korisnik">
      <div class="slika">
        <img src="../assets/user.png">
      </div>
      <div class="podaci">
        <h1>{{ korisnik.ime }} {{ korisnik.prezime }}</h1>
        <hr />
        <div class="rodjendan_polje">
          Rodjendan
        </div>
        <div class="rodjendan_datum">
          {{ korisnik.datumRodjenja }}
        </div>
        <div class="opis_polje">
          Opis
        </div>
        <div class="opis_tekst">
          <p v-if="korisnik.opis != null">{{ korisnik.opis }}</p>
          <p v-else>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus et mauris et justo mattis semper. Aliquam semper purus turpis, sed varius nibh porta eu. Morbi ac eros non neque rutrum tincidunt porttitor ac lacus. Nulla aliquet diam ipsum, non dictum libero tincidunt vitae.</p>
        </div>
      </div>
      <br />
      <div class="police">
        Police
        <hr />
        <table class="tabela">
          <td v-for="polica in police" :key="polica.id" :polica="polica">
            <router-link :to="{ name: 'polica', params: { id: polica.id }}">{{ polica.naziv }}</router-link>
          </td>
        </table>
      </div>
      <!-- ovo se ne vidi ako nisi prijavljen? -->
      <div class="dodavanje_police">
        <form>
          <label>Nova polica:</label>
          <input v-model="naziv_police">
          <button v-on:click="nova_polica()">Napravi policu</button>
        </form>
        <div v-if="res" class="poruka">
          {{ res }}
        </div>
      </div>
      
      <button v-on:click="this.$router.push('/azuriraj')">Azuriraj profil</button>
    </div>
  </template>

<script>
import axios from 'axios'
export default {
    name: 'ProfilView',
    data: function () {
        return {
            korisnik: {},
            police: [],
            naziv_police: '',
            res: ''
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/korisnici/prijavljen', {withCredentials:true})
        .then(response => { this.korisnik = response.data })
        .catch(error => { console.error(error) })

        axios
        .get('http://localhost:8880/api/korisnici/' + this.$cookies.get('ID') + '/police')
        .then(response => { this.police = response.data })
        .catch(error => { console.error(error) })
    },
    methods: {
        nova_polica() {
          axios
          .post('http://localhost:8880/api/police', 'nazivPolice=' + this.naziv_police, { withCredentials: true })
          .then(response => {
              if (response.status != 200) {
                this.res = response.data
              }
          })
          .catch(error => { console.error(error) })
        }
    }
}
</script>