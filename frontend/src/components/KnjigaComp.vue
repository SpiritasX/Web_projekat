<template>
  <div>
    <h3>KNJIGA-> {{ knjiga.naslov }}</h3>
    <h4>ocena: {{ knjiga.ocena }}</h4>
    <h4>zanr: </h4>
    <p v-for="zanr in knjiga.zanrovi" :key="zanr.id" :zanr="zanr">{{ zanr.naziv }}</p>
    <h4>broj strana: {{ knjiga.brojStrana }}</h4>
    <button @click="dodajNaPolicu">Dodaj na policu</button>
  </div>
</template>

<script>
export default {
  name: 'KnjigaComp',
  props: ['knjiga'],
  methods: {
    dodajNaPolicu: function () {
      const idKnjige = this.knjiga.id
      const idPolice = 1
      fetch(`http://localhost:8880/knjige/${idKnjige}/police/${idPolice}`, {
        method: 'POST'
      })
        .then(res => {
          if (res.ok) {
            console.log('nikad se nece desiti')
          } else {
            console.error('greskka')
          }
        })
        .catch(error => console.error(error))
    }
  }
}
</script>
