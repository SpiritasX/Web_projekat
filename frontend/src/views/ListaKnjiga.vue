<template>
  <div class="lista-knjiga">
    <div class="zaglavlje">
      <h1>DOSTUPNE KNJIGE</h1>
      <p>UŽIVAJTE U ČITANJU!</p>
    </div>
    <div class="knjige">
      <knjiga-comp v-for="knjiga in knjige" :key="knjiga.id" :knjiga="knjiga" />
    </div>
  </div>
</template>

<script>
import KnjigaComp from '../components/KnjigaComp.vue'
export default {
  name: 'ListaKnjiga',
  components: { KnjigaComp },
  data: function () {
    return {
      knjige: []
    }
  },
  mounted: function () {
    fetch('http://localhost:8880/api/knjige/')
      .then(res => res.json())
      .then(data => { this.knjige = data })
      .catch(error => console.error(error))
  }
}
</script>

<style>
.lista-knjiga {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-top: 50px;
}

.zaglavlje {
  background-color: #b3d6cc;
  padding: 20px;
  border-radius: 10px;
  text-align: center;
  margin-bottom: 20px;
}

.zaglavlje h1 {
  font-size: 28px;
  margin-bottom: 10px;
  color: #533d32;
}

.zaglavlje p {
  font-size: 16px;
  color: #533d32;
}

.knjige {
  display: flex;
  flex-direction: column;
  align-items: center;
  text-align: center;
}

.knjige .knjiga-comp {
  margin-bottom: 20px;
}

@media (max-width: 768px) {
  .lista-knjiga {
    flex-direction: column;
    align-items: center;
  }

  .tekst, .knjige {
    width: 100%;
  }
}

.knjige .knjiga-comp img {
  width: 200px;
  height: 300px;
  object-fit: cover;
  border-radius: 10px;
}

.knjige .knjiga-comp .naslov {
  font-size: 18px;
  margin-top: 10px;
}

</style>
