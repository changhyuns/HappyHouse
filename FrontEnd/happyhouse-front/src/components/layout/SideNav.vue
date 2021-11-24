<template>
  <div class="SideNav">
    <div class="logo">
      <router-link :to="{ name: 'Home' }" tag="h2"
        ><b-icon icon="house-fill" font-scale="1"></b-icon
        ><span style="font-size: 25px; margin-left: 5px"
          >HappyHouse</span
        ></router-link
      >
    </div>
    <div class="list">
      <div class="list-btn ml-3" active-class="active" @click="viewHouseList">
        <span class="link-container"
          ><b-icon icon="search" style="margin-right: 5px" />부동산</span
        >
        <span
          ><b-icon
            icon="caret-down-fill"
            v-if="!this.foldHouseList"
            style="margin-left: 50px"
          ></b-icon>
          <b-icon icon="caret-up-fill" v-else style="margin-left: 50px"
        /></span>
      </div>
      <transition name="fade">
        <div>
          <router-link
            :to="{ name: 'House' }"
            tag="h2"
            class="list-btn"
            active-class="active"
            v-if="this.foldHouseList"
          >
            <div class="link-container-sub">
              <b-icon icon="map" style="margin-right: 5px" />
              매물 검색
            </div>
          </router-link>
          <router-link
            :to="{ name: 'Town' }"
            tag="h2"
            class="list-btn"
            active-class="active"
            v-if="this.foldHouseList"
          >
            <div class="link-container-sub">
              <b-icon icon="shop" style="margin-right: 5px" />
              동네 정보조회
            </div>
          </router-link>
          <router-link
            :to="{ name: 'Corona' }"
            tag="h2"
            class="list-btn"
            active-class="active"
            v-if="this.foldHouseList"
          >
            <div class="link-container-sub">
              <b-icon icon="exclamation-triangle" style="margin-right: 5px" />
              코로나 진료소
            </div>
          </router-link>
        </div>
      </transition>
      <router-link
        :to="{ name: 'Board' }"
        tag="h2"
        class="list-btn"
        active-class="active"
      >
        <div class="link-container">
          <b-icon icon="journals" style="margin-right: 5px" />커뮤니티
        </div>
      </router-link>
      <router-link
        :to="{ name: 'Notice' }"
        tag="h2"
        class="list-btn"
        active-class="active"
      >
        <div class="link-container">
          <b-icon icon="bell" style="margin-right: 5px" />공지사항
        </div>
      </router-link>
      <router-link :to="{name:'MyPage'}" tag="h2" class="list-btn" active-class="active">
        <div class="link-container">
          <b-icon icon="person" style="margin-right: 5px"/>내 정보
        </div>
      </router-link>
    </div>
  </div>
</template>

<script>
import { mapMutations, mapState } from "vuex";
const navStore = "navStore";

export default {
  data() {
    return {
      houseFlag: true,
    };
  },

  computed: {
    ...mapState(navStore, ["foldHouseList"]),
  },

  methods: {
    ...mapMutations(navStore, ["SET_FOLD_HOUSELIST"]),

    viewHouseList() {
      if (this.foldHouseList) {
        this.houseFlag = false;
        this.SET_FOLD_HOUSELIST(false);
      } else {
        this.houseFlag = true;
        this.SET_FOLD_HOUSELIST(true);
      }
    },
  },
};
</script>

<style scoped>
.SideNav {
  position: fixed;
  width: 250px;
  left: 0;
  top: 0;
  bottom: 0;
  /* background-color: #d0a9f5; */
  /* background-color: #2a1a72; */
  background-image: url('../../assets/back6.jpg');
  z-index: 10;
  text-align: left;
}

h2 {
  color: white;
  font-size: 15pt;
  cursor: pointer;
}

ul li {
  list-style-type: none;
}

.fade-enter-active,
.fade-leave-active {
  transition: opacity 0.3s;
}
.fade-enter,
.fade-leave-to {
  opacity: 0;
}

.list {
  /*
  margin-top: 40px;
  display: flex;
  flex-direction: column;
  text-align: center;
  */
  flex-direction: column;
}

.logo h2 {
  font-size: 20pt;
  margin-top: 30px;
  margin-bottom: 10px;
  margin-left: 5px;
  margin-right: 5px;
}


.link-container {
  margin-left: 30px;
  margin-top: 10px;
  margin-bottom: 10px;
  /* display: inline; */
  text-align: left;
}

div > .link-container {
  margin-left: 15px;
  /* display: inline; */
  text-align: left;
}

.link-container-sub {
  margin-left: 60px;
  font-size: 16px;
}

.list-btn:focus {
  outline: none;
}

.list-btn.active {
  position: relative;
  background-color: white;
  /* color: #d0a9f5; */
  /* color: #2a1a72; */
  color: black;
  font-weight: 500;
}

/*
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
*/

.list-btn {
  border: none;
  padding: 14px 0px;
  cursor: pointer;
  font-size: 20px;
  color: white;
  background-color: transparent;
  /* margin-top: 40px; */
}

.list-btn span {
  margin-left: 10px;
}

/*
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
*/
</style>
