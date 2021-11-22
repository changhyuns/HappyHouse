<template>
  <div class="SideNav">
    <div class="logo">
      <router-link :to="{ name: 'Home' }" tag="h2"
        ><b-icon icon="house-fill" font-scale="1"></b-icon
      ></router-link>
    </div>
    <div class="list">
        <router-link :to="{ name: 'House' }" tag="h2" class="list-btn" active-class="active">
          <div class="link-container">부동산</div>
        </router-link>
        <router-link :to="{ name: 'Board' }" tag="h2" class="list-btn" active-class="active">
          <div class="link-container">게시판</div>
        </router-link>
        <h2 class="list-btn" active-class="active">
          <div class="link-container">편의시설</div></h2>
        <h2 class="list-btn" active-class="active">
          <div class="link-container">학교정보</div></h2>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.SideNav {
  position: fixed;
  width: 160px;
  left: 0;
  top: 0;
  bottom: 0;
  background-color: #D0A9F5;
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
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  text-align: center;
}

.logo h2 {
  text-align: center;
  font-size: 30pt;
  margin-top: 10px;
}

.list-btn:focus {
  outline: none;
}

.list-btn.active {
  position: relative;
  background-color: white;
  color: #D0A9F5;
  font-weight: 500;
}

.list-btn.active::before {
  position: absolute;
  content: "";
  top: -30px;
  right: 0;
  height: 30px;
  width: 30px;
  background-color: white;
}

.list-btn.active::after {
  position: absolute;
  content: "";
  bottom: -30px;
  right: 0;
  height: 30px;
  width: 30px;
  background-color: white;
}

.list-btn {
  border: none;
  padding: 16px 0px;
  cursor: pointer;
  font-size: 16px;
  color: white;
  background-color: transparent;
  margin-top: 40px;
}

.list-btn.active .link-container::before {
  position: absolute;
  top: -60px;
  right: 0;
  content: "";
  height: 60px;
  width: 60px;
  border-radius: 15%;
  background-color: #D0A9F5;
}

.list-btn.active .link-container::after {
  position: absolute;
  bottom: -60px;
  right: 0;
  content: "";
  height: 60px;
  width: 60px;
  border-radius: 15%;
  background-color: #D0A9F5;
  z-index: 99;
}
</style>
