<template>
  <div class="SideNav">
    <div class="logo">
      <router-link :to="{ name: 'Home' }" tag="h2"
        ><b-icon icon="house" font-scale="1"></b-icon
      ></router-link>
    </div>
    <div class="list">
      <router-link :to="{ name: 'House' }" tag="h2">부동산 검색</router-link>
      <router-link :to="{ name: 'Board' }" tag="h2">게시판</router-link>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.SideNav {
  position: fixed;
  width: 150px;
  left: 0;
  top: 0;
  bottom: 0;
  background-color: #4d24ad;
  z-index: 10;
  text-align: left;
}

h2 {
  color: white;
  font-size: 15pt;
  cursor: pointer;
  margin-bottom: 10px;
}

.list {
  margin-left: 10px;
}

.logo h2 {
  text-align: center;
  font-size: 20pt;
}
</style>
