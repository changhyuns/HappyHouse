<template>
  <div>
    <body class="Home">
      <user-nav></user-nav>
      <b-container>
        <b-row>
          <b-col>
            <div class="top">
              <h1>Happy House</h1>
              <h5>당신이 찾는 행복한 집</h5>
            </div>
          </b-col>
        </b-row>
        <div class="center">
          <b-row>
            <b-col>
              <router-link :to="{ name: 'House' }"><button type="button" class="imgBtn1"></button> </router-link>
            </b-col>
            <b-col>
              <router-link :to="{ name: 'Board' }">
                <button type="button" class="imgBtn2"></button>
              </router-link>
            </b-col>
            <b-col>
              <router-link :to="{ name: 'Notice' }">
                <button type="button" class="imgBtn3"></button>
              </router-link>
            </b-col>
          </b-row>
        </div>
        <div class="center">
          <b-row>
            <b-col>
              <router-link :to="{ name: 'House' }" tag="h5">
                <h5><b-icon icon="search" aria-hidden="true" /> 부동산정보</h5>
              </router-link>
            </b-col>
            <b-col>
              <router-link :to="{ name: 'Board' }" tag="h5">
                <h5><b-icon icon="list" aria-hidden="true" /> 게시판</h5>
              </router-link>
            </b-col>
            <b-col>
              <router-link :to="{ name: 'Notice' }" tag="h5">
                <h5><b-icon icon="exclamation-circle" aria-hidden="true" /> 공지사항</h5>
              </router-link>
            </b-col>
          </b-row>
        </div>
      </b-container>
    </body>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import UserNav from "@/components/layout/UserNav.vue";
import FooterBar from "../components/layout/FooterBar.vue";

export default {
  name: "Main",
  props: {
    msg: String,
  },

  components: {
    UserNav,
    FooterBar,
  },
};
</script>

<style scoped>
.top {
  position: relative;
  text-align: center;
  height: 500px;
}

.top h1 {
  color: black;
  position: relative;
  top: 25%;
  font-size: 60px;
  font-family: "Black Han Sans", sans-serif;
}

.top h5 {
  color: black;
  position: relative;
  top: 25%;
  font-size:25px;
}

.center {
  position: relative;
  text-align: center;
}

button {
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 40px;
  margin-inline-end: 40px;
}

.imgBtn1 {
  background-image: url(../assets/map.png);
  background-size: 100% 100%;
  width: 200px;
  height: 200px;
  outline-style: initial;
  border-radius: 100px;
  border: 0;
  cursor: pointer;
}

.imgBtn2 {
  background-image: url(../assets/notepad.png);
  background-repeat: no-repeat;
  background-position: center;
  background-size: 70% 70%;
  width: 200px;
  height: 200px;
  outline-style: initial;
  border-radius: 100px;
  border: 0;
  cursor: pointer;
}

.imgBtn3 {
  background-image: url(../assets/notification.png);
  background-size: 80% 80%;
  background-repeat: no-repeat;
  background-position: center;
  width: 200px;
  height: 200px;
  outline-style: initial;
  border-radius: 100px;
  border: 0;
  cursor: pointer;
}

.Home {
  background-image: url(../assets/banner.png);
  background-repeat: no-repeat;
  background-size: 100% 50%;
  min-height: 913px;
  min-width: 1600px;
  background-position: top center;
}
</style>
