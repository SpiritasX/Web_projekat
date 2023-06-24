<template>
    <div v-if="this.$admin">
        <div v-for="zahtev in zahtevi" :key="zahtev.id">
            <zahtev-comp :zahtev="zahtev" />
            <button v-on:click="this.$router.push('/zahtev/' + zahtev.id)">Vidi vise</button>
        </div>
    </div>
</template>

<script>
import axios from 'axios';
import ZahtevComp from '../components/ZahtevComp.vue'

export default {
    name: 'ZahteviView',
    components: {
        ZahtevComp
    },
    data: function () {
        return {
            zahtevi: []
        }
    },
    mounted: function() {
        axios
        .get('http://localhost:8880/api/zahtevi', { withCredentials: true })
        .then(response => {
            this.zahtevi = response.data
        })
        .catch(error => { console.error(error) })
    }
}
</script>