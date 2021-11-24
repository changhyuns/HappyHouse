<template>
  <b-container class="bv-example-row mt-3 text-center" style="max-width: 1000px;">
    <side-nav></side-nav>
    <user-nav></user-nav>
    <h3 style="color: white; height: 40px; font-size: 30px; ">
      공지사항
    </h3>
    <router-view :key="$route.fullPath"></router-view>
  </b-container>
</template>
<script>
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";

export default {
  name: "Notice",
  components: { UserNav, SideNav },
};
</script>
<style scoped>
.underline-hotpink {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 27, 139, 0.3) 30%
  );
}

h3 {
  background-image: url('../assets/title1.jpg'); 
}
</style>
