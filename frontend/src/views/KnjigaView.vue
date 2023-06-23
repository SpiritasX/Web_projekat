<template>
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
        .catch(error => { console.error(error) })

        axios
        .get('http://localhost:8880/api/knjige/' + this.$router.currentRoute._value.path.split('/')[2] + '/recenzije')
        .then(response => {
            this.recenzije = response.data
        })
        .catch(error => { console.error(error) })
    }
}
</script>