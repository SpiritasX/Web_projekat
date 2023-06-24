<template>
    <div v-if="this.$admin">
        <zahtev-comp :ZahtevDto="ZahtevDto" />
        <button v-on:click="obradi(true)">Prihvati</button>
        <button v-on:click="obradi(false)">Odbij</button>
    </div>
</template>

<script>
import axios from 'axios'
import ZahtevComp from '../components/ZahtevComp.vue'
export default {
    name: 'ZahtevView',
    components: {
        ZahtevComp
    },
    data: function () {
        return {
            ZahtevDto: {
                email: null,
                telefon: null,
                poruka: null,
                datum: null,
                status: null
            }
        }
    },
    methods: {
        obradi(bool) {
            axios
            .put('http://localhost:8880/api/zahtevi/' + ZahtevDto.id + '?prihvati=' + bool, { withCredentials: true })
            .then(response => {
                this.$router.push('/');
            })
            .catch(error => { console.error(error) })
        }
    }
}
</script>