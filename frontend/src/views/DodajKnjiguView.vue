<template>
    <form v-if="this.$admin">
        <label>Naslov</label>
        <input v-model="BookDto.naslov"/>
        <label>ISBN</label>
        <input v-model="BookDto.ISBN"/>
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
                ISBN: '',
                datumObjavljivanja: '',
                opis: '',
                idAutora: 0
            }
        }
    },
    methods: {
        saveBook() {
            axios
            .post('http://localhost:8880/api/knjige/', this.BookDto)
            .catch(error => { console.error(error) })
        }
    }
}
</script>