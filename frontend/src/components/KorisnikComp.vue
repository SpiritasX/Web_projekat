<template>
    <div>
        <router-link :to="'/korisnik/' + korisnik.id">KORISNIK-> {{ korisnik.korisnickoIme }}</router-link>
        <h3>{{ korisnik.ime }}</h3>
    </div>
</template>

<script>
export default {
  name: 'KorisnikComp',
  props: ['korisnik']
}
</script>
