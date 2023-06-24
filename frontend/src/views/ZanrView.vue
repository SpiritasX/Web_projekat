<template>
    <h1>{{ zanr }}</h1>
    <knjiga-comp v-for="knjiga in knjige" :key="knjiga.id" :knjiga="knjiga" />
</template>

<script>
import KnjigaComp from '../components/KnjigaComp.vue'
import axios from 'axios'
export default {
    name: 'ZanrView',
    components: {
        KnjigaComp
    },
    data: function () {
        return {
            zanr: '',
            knjige: []
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/zanrovi/' + this.$router.currentRoute._value.path.split('/')[2])
        .then(response => {
            this.zanr = response.data
        })
        .catch(error => { console.error(error) })

        axios
        .get('http://localhost:8880/api/zanrovi/' + this.$router.currentRoute._value.path.split('/')[2] + '/knjige')
        .then(response => {
            this.knjige = response.data
        })
        .catch(error => { console.error(error) })
    }
}
</script>