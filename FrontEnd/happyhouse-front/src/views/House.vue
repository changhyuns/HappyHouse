<template>
<div>
<div>
   <nav>
      <user-nav></user-nav>
    </nav>
</div>
  <div>
    <aside>
      <side-nav></side-nav>
    </aside>
    <section>
      <div class="map-area">
        <h3><b-icon icon="house-fill"></b-icon> 부동산 검색</h3>
        <div>
          <house-search-bar></house-search-bar>
        </div>
        <div>
          <house-list />
        </div>
        <div>
          <kakao-map></kakao-map>
        </div>
        <div>
          <house-detail />
        </div>
      </div>
    </section>
  </div>
</div>
</template>
<script>
import HouseSearchBar from "@/components/house/HouseSearchBar.vue";
import HouseList from "@/components/house/HouseList.vue";
import HouseDetail from "@/components/house/HouseDetail.vue";
import SideNav from "../components/layout/SideNav.vue";
import UserNav from "../components/layout/UserNav.vue";
import KakaoMap from "../components/house/KakaoMap.vue";

export default {
  name: "House",
  components: {
    HouseSearchBar,
    HouseList,
    HouseDetail,
    SideNav,
    UserNav,
    KakaoMap,
  },
};
</script>
<style scoped>
.underline-orange {
  display: inline-block;
  background: linear-gradient(
    180deg,
    rgba(255, 255, 255, 0) 70%,
    rgba(231, 149, 27, 0.3) 30%
  );
}

h3 {
  text-align: center;
}

section {
  display: inline-block;
  text-align: center;
  position: relative;
  left: 560px;
  width: 900px;
  margin-left: 20px;
}

section .map-area {
  display: inline-block;
}
</style>
