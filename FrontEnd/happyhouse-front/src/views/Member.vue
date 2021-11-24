<template>
  <div>
    <user-nav></user-nav>
    <side-nav></side-nav>
    <b-container class="bv-example-row mt-3 text-center">
      <h3><b-icon icon="person-lines-fill"></b-icon> 회원 정보</h3>
      <router-view></router-view>
    </b-container>
  </div>
</template>

<script>
import SideNav from '../components/layout/SideNav.vue';
import UserNav from '../components/layout/UserNav.vue';
export default {
  components: { UserNav, SideNav },
  name: "Member",
  components:{
    SideNav,
    UserNav,
  }
};
</script>

<style scoped>
.underline-steelblue {
  display: inline-block;
  background: linear-gradient(180deg, rgba(255, 255, 255, 0) 70%, rgba(72, 190, 233, 0.3) 30%);
}
</style>
