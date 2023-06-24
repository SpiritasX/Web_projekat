<template>
    <div>
        {{ ZahtevDto.email }}
        <br />
        {{ ZahtevDto.telefon }}
        <br />
        {{ ZahtevDto.poruka }}
        <br />
        {{ ZahtevDto.datum }}
        <br />
        {{ ZahtevDto.status }}
        <br />
    </div>
</template>

<script>
export default {
    name: 'ZahtevComp',
    props: ['ZahtevDto'],
}
</script>