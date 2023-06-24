<template>

</template>

<script>
import axios from 'axios';
import KnjigaComp from '../components/KnjigaComp.vue'
export default {
  name: 'HomePage',
  components: {
    KnjigaComp
  },
  data: function () {
    return {
      ima_za_prikaz: false,
      pretraga: '',
      knjige: []
    }
  },
  methods: {
    pretrazi() {
      axios
      .get('http://localhost:8880/api/pretrazi?pretraga=' + this.$route.query.pretraga)
      .then(response => {
        if (response.data != null) {
          this.ima_za_prikaz = true
          knjige = response.data
        }
      })
      .catch(error => { console.error(error) })
    }
  }
}
</script>