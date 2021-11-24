<template>
  <b-container class="bv-example-row mt-3 text-center">
    <side-nav></side-nav>
    <user-nav></user-nav>
    <h3>동네 정보</h3>
    <town-search-bar></town-search-bar>
  </b-container>
</template>
<script>
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";
import TownSearchBar from '../components/town/TownSearchBar.vue';

export default {
  name: "Town",
  components: { UserNav, SideNav, TownSearchBar },
};
</script>
<style scoped>

</style>
