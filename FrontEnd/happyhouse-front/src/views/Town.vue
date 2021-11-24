<template>
  <b-container class="bv-example-row mt-3 text-center">
    <side-nav></side-nav>
    <user-nav></user-nav>
    <h3>동네 정보</h3>
    <town-search-bar type="town"></town-search-bar>
    <kakao-map type="town"></kakao-map>
  </b-container>
</template>
<script>
import KakaoMap from '../components/house/KakaoMap.vue';
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";
import TownSearchBar from '../components/town/TownSearchBar.vue';

export default {
  name: "Town",
  components: { UserNav, SideNav, TownSearchBar, KakaoMap },
};
</script>
<style scoped>

</style>
