<template>
    {{ recenzija.ocena }}
    <br />
    {{ recenzija.tekst }}
    <br />
    {{ recenzija.datumRecenzije }}
    <br />
</template>

<script>
export default {
    name: 'RecenzijaComp',
    props: ['recenzija']
}
</script>