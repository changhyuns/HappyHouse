<template>
  <div class="user_nav">
    <b-nav v-if="userInfo" align=right>
      <b-nav-item-dropdown right>
        <template #button-content>
          <span>{{ userInfo.username }}({{ userInfo.userid }})님 환영합니다.</span>
        </template>
        <b-dropdown-item>
          <router-link :to="{ name: 'MyPage' }">
            <b-icon icon="person-circle"></b-icon>내정보보기</router-link
          >
        </b-dropdown-item>
        <b-dropdown-item @click.prevent="onClickLogout">
          <b-icon icon="key"></b-icon>로그아웃</b-dropdown-item
        >
      </b-nav-item-dropdown>
    </b-nav>
    <b-nav v-else align=right>
      <b-nav-item-dropdown right>
        <template #button-content>
          <b-icon icon="people" font-scale="2"></b-icon>
        </template>
        <b-dropdown-item
          ><router-link :to="{ name: 'SignUp' }"
            ><b-icon icon="person-circle"></b-icon> 회원가입</router-link
          ></b-dropdown-item
        >
        <b-dropdown-item
          ><router-link :to="{ name: 'Main' }"
            ><b-icon icon="key"></b-icon> 로그인</router-link
          ></b-dropdown-item
        >
      </b-nav-item-dropdown>
    </b-nav>
  </div>
</template>

<script>
import { mapState, mapMutations } from "vuex";
import swal from "sweetalert";

const memberStore = "memberStore";

export default {
  name: "UserNav",
  computed: {
    ...mapState(memberStore, ["isLogin", "userInfo"]),
    
  },
  methods: {
    ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    onClickLogout() {
      swal('로그아웃 되었습니다.', 'success');
      this.SET_IS_LOGIN(false);
      this.SET_USER_INFO(null);
      sessionStorage.removeItem("access-token");
      this.$router.push({name: 'Main'});
    },
  },
};
</script>

<style scoped>
.user_nav {
  position: relative;
  text-align: right;
  width: 100;
  padding-right: 10px;
}

span {
  color: black;
  margin-right:5px;
}
</style>
