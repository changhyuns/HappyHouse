<template>
  <div>
    <div class="main">
      <div class="title">
        <h1>HAPPY HOUSE</h1>
        <h4>
          당신의 행복한 집, <br />
          지금 찾아보세요!
        </h4>
      </div>
      <div class="login">
        <h2>LOGIN</h2>
        <member-login></member-login>
      </div>
    </div>
    <footer-bar></footer-bar>
  </div>
</template>

<script>
import FooterBar from "../components/layout/FooterBar.vue";
import MemberLogin from "../components/user/MemberLogin.vue";

export default {
  name: 'Main',
  components: { FooterBar, MemberLogin },
};
</script>
<style scoped>
.main {
  background-image: url(../assets/happyhouse.png);
  background-repeat: no-repeat;
  background-size: 100% 100%;
  min-height: 913px;
  min-width: 1600px;
}

.title {
  text-align: center;
  position: relative;
  top: 100px;
  left: -20%;
}

.title > h1 {
  font-family: 'Black Han Sans', sans-serif;
  font-size: 60px;
}

.title > h4 {
  font-size: 30px;
}

.login {
  position: relative;
  top: 130px;
  left: 72%;
  width: 500px;
}

.login > h2 {
  font-family: 'Black Han Sans', sans-serif;
  position: relative;
  text-align: center;
  left: -17%;
}
</style>