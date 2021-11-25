<template>
  <b-container class="bv-example-row mt-3 text-center">
    <side-nav></side-nav>
    <user-nav></user-nav>
    <h3 style="background-color: #170B3B; color: white; height: 40px; font-size: 30px;">동네 정보</h3>
    <town-search-bar type="town"></town-search-bar>
    <town-info></town-info>
    <kakao-map type="town"></kakao-map>
  </b-container>
</template>
<script>
import KakaoMap from '../components/house/KakaoMap.vue';
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";
import TownInfo from '../components/town/TownInfo.vue';
import TownSearchBar from '../components/town/TownSearchBar.vue';

export default {
  name: "Town",
  components: { UserNav, SideNav, TownSearchBar, KakaoMap, TownInfo},
}
</script>
<style scoped>
  h3 {
  background-image: url('../assets/title1.jpg'); 
}
</style>
