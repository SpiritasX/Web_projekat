<template>
    <h1>{{ polica.naziv }}</h1>
    <table>
        <td v-for="stavka in polica.stavke" :key="stavka.knjiga.id">
            <knjiga-comp :knjiga="stavka.knjiga"/>
            <recenzija-comp :recenzija="stavka.recenzija"/>
            <div v-if="polica.naziv === 'Read'">
                <form>
                    <label>Ocena</label>
                    <input type="number" v-model="RecenzijaDto.ocena">
                    <label>Tekst</label>
                    <input type="textbox" v-model="RecenzijaDto.tekst">
                    <button v-on:click="dodajRecenziju(stavka.knjiga)">Dodaj recenziju</button>
                </form>
            </div>
        </td>
    </table>
</template>

<script>
import axios from 'axios'
import KnjigaComp from '../components/KnjigaComp.vue'
import RecenzijaComp from '../components/RecenzijaComp.vue'
export default {
    name: 'PolicaView',
    components: {
        KnjigaComp,
        RecenzijaComp
    },
    data: function () {
        return {
            polica: {},
            RecenzijaDto: {
                ocena: '',
                tekst: ''
            }
        }
    },
    created: function () {
        axios
        .get('http://localhost:8880/api/police/' + this.$router.currentRoute._value.path.split('/')[2])
        .then(response => {
            this.polica = response.data;
        })
        .catch(error => { console.error(error) })
    },
    methods: {
        dodajRecenziju(knjiga) {
            console.log(knjiga)
            axios
            .post('http://localhost:8880/api/recenzije/' + knjiga.id, this.RecenzijaDto, {withCredentials:true})
            .catch(error => { console.error(error) })
        }
    }
}
</script>