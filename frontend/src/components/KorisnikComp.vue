<template>
    <div>
        <h3>KORISNIK-> {{ korisnik.korisnickoIme }}</h3>
        <h3>{{ korisnik.ime }}</h3>
    </div>
</template>

<script>
export default {
  name: 'KorisnikComp',
  props: ['korisnik']
}
</script>
