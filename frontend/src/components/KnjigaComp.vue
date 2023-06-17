<template>
    <div>
        <h3>{{ knjiga.naslov }}</h3>
    </div>
</template>

<script>
export default {
  name: 'KnjigaComp',
  props: ['knjiga']
}
</script>
