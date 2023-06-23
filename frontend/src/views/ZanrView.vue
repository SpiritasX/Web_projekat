<template>
    <h1>{{ zanr }}</h1>
</template>

<script>
import axios from 'axios'
export default {
    name: 'ZanrView',
    data: function () {
        return {
            zanr: ''
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/zanrovi/' + this.$router.currentRoute._value.path.split('/')[2])
        .then(response => {
            this.zanr = response.data
        })
        .catch(error => { console.error(error) })
    }
}
</script>