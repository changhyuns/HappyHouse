<template>
  <b-container class="bv-example-row mt-3 text-center">
    <side-nav></side-nav>
    <user-nav></user-nav>
    <h3>코로나 진료소</h3>
    <house-search-bar></house-search-bar>
    <kakao-map></kakao-map>
  </b-container>
</template>

<script>
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";
import HouseSearchBar from "../components/house/HouseSearchBar.vue";
import KakaoMap from '../components/house/KakaoMap.vue';

export default {
    name:"Corona",
    components: { UserNav, SideNav, HouseSearchBar, KakaoMap},
}
</script>

<style>

</style>