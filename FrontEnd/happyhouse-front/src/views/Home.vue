<template>
  <div>
    <body class="Home">
      <user-nav></user-nav>
      <div class="top">
        <h1>Happy House</h1>
      </div>
      <div class="center">
        <button>
          <b-icon icon="search" aria-hidden="true"></b-icon>
          <router-link :to="{ name: 'House' }"> 부동산정보</router-link>
        </button>
        <button>
          <b-icon icon="list" aria-hidden="true"></b-icon>
          <router-link :to="{ name: 'Board' }"> 게시판</router-link>
        </button>
        <button>
          <b-icon icon="exclamation-circle" aria-hidden="true"></b-icon>
          <router-link :to="{ name: 'Notice' }"> 공지사항</router-link>
        </button>
      </div>
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

.top h1{
  color: white;
}

.center {
  position: relative;
  text-align: center;
}

button {
  margin-block-start: 1em;
  margin-block-end: 1em;
  margin-inline-start: 10px;
  margin-inline-end: 10px;
}

.Home {
  background-image: url(../assets/main2.jpg);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  min-height: 913px;
  min-width: 1600px;
  background-position: top left;
}
</style>
