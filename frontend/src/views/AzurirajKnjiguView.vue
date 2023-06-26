<template>
    <form>
        <label>Naslov</label>
        <input v-model="BookDto.naslov"/>
        <label>ISBN</label>
        <input v-model="BookDto.isbn"/>
        <label>Datum objavljivanja</label>
        <input type="date" v-model="BookDto.datumObjavljivanja"/>
        <label>Broj strana</label>
        <input type="number" v-model="BookDto.brojStrana"/>
        <label>Opis</label>
        <textarea v-model="BookDto.opis"></textarea>
        <button v-on:click="saveBook()">Sačuvaj knjigu</button>
    </form>
</template>


<script>
import axios from 'axios';

export default {
    name: 'DodajKnjiguView',
    data: function () {
        return {
            BookDto: {
                naslov: '',
                isbn: '',
                datumObjavljivanja: '',
                opis: '',
                brojStrana: 0
            }
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/knjige/' + this.$route.query.id)
        .then(response => {
            this.BookDto = response.data
        })
        .catch(error => { console.error(error) })
    },
    methods: {
        saveBook() {
            axios
            .put('http://localhost:8880/api/knjige/' + this.$route.query.id, this.BookDto, {withCredentials:true})
            .catch(error => { console.error(error) })
        }
    }
}
</script>