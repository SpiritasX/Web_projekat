<template>
    <form v-if="this.$cookies.get('ULOGA') === 'ADMINISTRATOR'">
        <label>Naslov</label>
        <input v-model="BookDto.naslov"/>
        <label>isbn</label>
        <input v-model="BookDto.isbn"/>
        <label>Datum objavljivanja</label>
        <input type="date" v-model="BookDto.datumObjavljivanja"/>
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
                opis: ''
            }
        }
    },
    methods: {
        saveBook() {
            axios
            .post('http://localhost:8880/api/knjige/', this.BookDto, {withCredentials:true})
            .catch(error => { console.error(error) })
        }
    }
}
</script>