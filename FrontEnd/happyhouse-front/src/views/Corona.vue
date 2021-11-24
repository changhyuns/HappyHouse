<template>
  <b-container class="bv-example-row mt-3 text-center">
    <side-nav></side-nav>
    <user-nav></user-nav>
    <h3 style="background-color: #170B3B; color: white; height: 40px; font-size: 30px;">코로나 진료소 위치</h3>
    <town-search-bar type="corona"></town-search-bar>
    <kakao-map type="corona"></kakao-map>
  </b-container>
</template>

<script>
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";
import KakaoMap from '../components/house/KakaoMap.vue';
import TownSearchBar from '../components/town/TownSearchBar.vue';

export default {
    name:"Corona",
    components: { UserNav, SideNav, KakaoMap, TownSearchBar},
}
</script>

<style>

</style>