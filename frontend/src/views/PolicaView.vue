<template>
    <h1>{{ polica.naziv }}</h1>
    <table>
        <td v-for="stavka in polica.stavke" :key="stavka.knjiga.id" :knjiga="stavka.knjiga">
            <knjiga-comp />
        </td>
    </table>
</template>

<script>
import axios from 'axios'
import KnjigaComp from '../components/KnjigaComp.vue'
export default {
    name: 'PolicaView',
    components: {
        KnjigaComp
    },
    data: function () {
        return {
            polica: {}
        }
    },
    created: function () {
        axios
        .get('http://localhost:8880/api/police/' + this.$router.currentRoute._value.path.split('/')[2])
        .then(response => {
            this.polica = response.data;
            console.log(polica.stavke)
        })
        .catch(error => { console.error(error) })
    }
}
</script>