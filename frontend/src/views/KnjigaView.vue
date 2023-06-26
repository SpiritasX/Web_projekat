<template>
    <h1>{{ knjiga.naslov }}</h1>
    <!-- <h3>{{ knjiga.autor.ime }} {{ knjiga.autor.prezime }}</h3> -->
    {{ knjiga.ocena }}
    <br />
    {{ knjiga.opis }}
    <br />
    <h3>Zanrovi</h3>
    <p v-for="zanr in knjiga.zanrovi" :key="zanr.id" :zanr="zanr">{{ zanr.naziv }}</p>
    <div class="dodaj_na_policu">
        <form>
            <label>Polica na koju zelis da dodas:</label>
            <input v-model="naziv_police">
            <button v-on:click="dodaj()">Dodaj</button>
        </form>
        <div class="poruka">
            {{ this.amsg }}
        </div>
    </div>
    <div class="ukloni_sa_police">
        <form>
            <label>Polica sa koje brises</label>
            <input v-model="naziv_police">
            <button v-on:click="obrisi()">Obrisi</button>
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