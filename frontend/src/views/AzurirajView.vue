<template>
    <form v-if="prijavljen">
        <label>Ime</label>
        <input v-model="KorisnikDto.ime"/>
        <label>Prezime</label>
        <input v-model="KorisnikDto.prezime"/>
        <label>Korisnicko ime</label>
        <input v-model="KorisnikDto.korisnickoIme"/>
        <label>Email</label>
        <input v-model="KorisnikDto.email"/>
        <label>Lozinka</label>
        <input v-model="KorisnikDto.lozinka"/>
        <label>Datum</label>
        <input type="date" v-model="KorisnikDto.datumRodjenja">
        <label>Opis</label>
        <input v-model="KorisnikDto.opis">
        <button v-on:click="azuriraj()">Azuriraj</button>
    </form>
</template>

<script>
import axios from 'axios'
export default {
    name: 'AzurirajView',
    data: function () {
        return {
            KorisnikDto: {
                ime: '',
                prezime: '',
                korisnickoIme: '',
                email: '',
                lozinka: '',
                datumRodjenja: '',
                opis: ''
            }
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/korisnici/prijavljen', { withCredentials: true })
        .then(response => {
            this.KorisnikDto = response.data
        })
        .catch(error => { console.error(error) })
    },
    methods: {
        azuriraj() {
            axios
            .post('http://localhost:8880/api/korisnici', { withCredentials: true })
            .then(response => {
                this.KorisnikDto = response.data
            })
            .catch(error => { console.error(error) })
        }
    }
}
</script>