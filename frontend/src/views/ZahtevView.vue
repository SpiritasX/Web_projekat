<template>
    <div v-if="this.$cookies.get('ULOGA') === 'ADMINISTRATOR'">
        <zahtev-comp :ZahtevDto="ZahtevDto" />
        <button v-on:click="obradi(true)">Prihvati</button>
        <button v-on:click="obradi(false)">Odbij</button>
        <div>
            {{ this.msg }}
        </div>
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
            },
            msg: null
        }
    },
    mounted: function () {
        axios
        .get('http://localhost:8880/api/zahtevi/' + this.$router.currentRoute._value.path.split('/')[2], {withCredentials:true})
        .then(response => {
            this.ZahtevDto = response.data
        })
        .catch(error => { console.error(error) })
    },
    methods: {
        obradi(bool) {
            axios
            .put('http://localhost:8880/api/zahtevi/' + this.ZahtevDto.id, 'prihvati=' + bool, {withCredentials:true})
            .then(response => {
                this.msg = response.data
            })
            .catch(error => { console.error(error) })
        }
    }
}
</script>