<template>
  <div class="korisnik">
    <div class="slika">
      <img src="../assets/user.png">
    </div>
    <div class="podaci">
      <h1>{{ korisnik.ime }} {{ korisnik.prezime }}</h1>
      <hr />
      <div class="rodjendan_polje">
        Rodjendan
      </div>
      <div class="rodjendan_datum">
        {{ korisnik.datumRodjenja }}
      </div>
      <div class="opis_polje">
        Opis
      </div>
      <div class="opis_tekst">
        <p v-if="korisnik.opis != null">{{ korisnik.opis }}</p>
        <p v-else>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus et mauris et justo mattis semper. Aliquam semper purus turpis, sed varius nibh porta eu. Morbi ac eros non neque rutrum tincidunt porttitor ac lacus. Nulla aliquet diam ipsum, non dictum libero tincidunt vitae.</p>
      </div>
    </div>
    <br />
    <div class="police">
      Police
      <hr />
      <table class="tabela">
        <tr>
          <td v-for="polica in police" :key="polica.id" :polica="polica">
            <router-link :to="{ name: 'polica', params: { id: polica.id }}">{{ polica.naziv }}</router-link>
          </td>
        </tr>
      </table>
    </div>
  </div>
</template>

<script>
export default {
  data: function () {
    return {
      korisnik: {},
      police: []
    }
  },
  mounted: function () {
    fetch('http://localhost:8880/api/korisnici/' + this.$router.currentRoute._value.path.split('/')[2])
      .then(response => response.json())
      .then(body => { this.korisnik = body })
      .catch(error => { console.error(error) })

    fetch('http://localhost:8880/api/korisnici/' + this.$router.currentRoute._value.path.split('/')[2] + '/police')
      .then(response => response.json())
      .then(body => { this.police = body })
      .catch(error => { console.error(error) })
  }
}
</script>

<style>
.korisnik {
  width: 700px;
  margin: 10px auto;
  color: #533d32;
  background: linear-gradient(to bottom right, #F5DEB3, #8cd2cf94);
}

.slika {
  float: left;
  width: 190px;
  margin-right: 10px;
  overflow: hidden;
}

.podaci {
  float: right;
  width: 500px;
  position: relative;
}

.podaci>div {
  margin: 5px 0;
  color: #533d32;
}

h1 {
  margin: 10px 0px 10px 0px;
}

p {
  margin: 0px;
}

.rodjendan_polje {
  float: left;
  width: 25%;
}

.rodjendan_datum {
  float: right;
  width: 75%;
}

.opis_polje {
  float: left;
  width: 25%;
}

.opis_tekst {
  float: right;
  width: 75%;
}

.police {
  width: 100%;
  float: left;
}

.tabela {
  width: 100%;
}

td {
  text-align: center;
  width: 25%;
  color: #000000c7;
  background-color: #005cfc32;
}
</style>
