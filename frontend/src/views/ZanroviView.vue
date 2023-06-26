<template>
    <div v-for="zanr in zanrovi" :key="zanr.id" :zanr="zanr">
        <router-link :to="'/zanrovi/' + zanr.id">{{ zanr.naziv }}</router-link>
    </div>
</template>

<script>
import axios from 'axios';

export default {
    name: 'ZanroviView',
    data: function () {
        return {
            zanrovi: []
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/zanrovi')
        .then(response => {
            this.zanrovi = response.data;
        })
        .catch(error => { console.error(error) })
    }
}
</script>