<template>
  <div class="lista-knjiga">
    <div class="zaglavlje">
      <img src="../assets/knjige6.jpg" alt="Slika knjige 6" class="slika-leva" />
      <h1>DOSTUPNE KNJIGE</h1>
      <p>UŽIVAJTE U ČITANJU!</p>
      <img src="../assets/knjige8.jpg" alt="Slika knjige 7" class="slika-centar" />
      <img src="../assets/knjige7.jpg" alt="Slika knjige 7" class="slika-desna" />
    </div>
    <table>
      <thead>
        <tr>
          <th>Naslov</th>
          <th>Ocena</th>
          <th>Opis</th>
          <th>Zanrovi</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="knjiga in knjige" :key="knjiga.id">
          <td>{{ knjiga.naslov }}</td>
          <td>{{ knjiga.ocena }}</td>
          <td>{{ knjiga.opis }}</td>
          <td>
            <ul>
              <li v-for="zanr in knjiga.zanrovi" :key="zanr.id">{{ zanr.naziv }}</li>
            </ul>
          </td>
        </tr>
      </tbody>
    </table>
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
  height: 250px;
  width: 500px;
  /* padding: 200px; */
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
table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

table th,
table td {
  padding: 8px;
  text-align: left;
  border: 1px solid #000000;
}

table th {
  background-color: #b3d6cc;
}

table tbody tr:nth-child(even) {
  background-color: #84d0dca5;
}

table tbody tr:hover {
  background-color: #eed0fc5a;
}

table ul {
  margin: 0;
  padding: 0;
}

table ul li {
  list-style: none;
  margin-bottom: 3px;
}
.slika-leva {
  width: 100px;
  height: auto;
  float: left;
  margin-right: 5px;
  margin-top: 40px;
}

.slika-desna {
  width: 100px;
  height: auto;
  float: right;
  margin-top: -100px;
}

.slika-centar {
  width: 100px;
  height: auto;
  float: center;
  margin-top: 20px;
}

</style>
