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
            <button v-on:click="dodaj(naziv_police)">Dodaj</button>
        </form>
        <div class="poruka">
            {{ amsg }}
        </div>
    </div>
    <div class="ukloni_sa_police">
        <form>
            <label>Polica sa koje brises</label>
            <input v-model="naziv_police">
            <button v-on:click="obrisi(naziv_police)">Obrisi</button>
        </form>
        <div class="poruka">
            {{ rmsg }}
        </div>
    </div>
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
    },
    methods: {
        dodaj(naziv_police) {
            var id = 0
            axios
            .get('http://localhost:8880/api/police/' + naziv_police)
            .then(response => {
                this.id = response.data.id
            })
            .catch(error => { console.error(error) })

            axios
            .post('http://localhost:8880/api/knjige' + this.$router.currentRoute._value.path.split('/')[2] + '/polica/' + id, { withCredentials: true })
            .then(response => {
                if (response.status != 200) {
                    this.amsg = response.body
                }
            })
            .catch(error => { console.error(error) })
        },
        obrisi(naziv_police) {
            var id = 0
            axios
            .get('http://localhost:8880/api/police/' + naziv_police)
            .then(response => {
                this.id = response.data.id
            })
            .catch(error => { console.error(error) })

            axios
            .delete('http://localhost:8880/api/knjige' + this.$router.currentRoute._value.path.split('/')[2] + '/polica/' + id, { withCredentials: true })
            .then(response => {
                if (response.status != 200) {
                    this.rmsg = response.body
                }
            })
            .catch(error => { console.error(error) })
        }
    }
}
</script>