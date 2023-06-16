<template>
    Police
</template>

<script>
export default {
  name: 'LoggedIn',
  data: function () {
    return {
      Police: []
    }
  },
  mounted: function (test) {// Kako proslediti id prijavljenog korisnika da dobijemo njegove police >///<
    fetch('http://localhost:8880/api/korisnici/' + test.id + '/police')
      .then(res => res.json())
      .then(data => { this.Police = data })
      .catch(error => console.error(error))
  }
}
</script>
