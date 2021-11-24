<template>
  <b-container class="mt-4" v-if="userInfo">
    <b-row>
      <b-col></b-col>
      <b-col cols="8">
        <b-jumbotron>
          <b-container class="mt-4 text-left ">
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">
                <h6>아이디</h6></b-col
              ><b-col cols="4" align-self="start"><h6>{{ userInfo.userid }}</h6></b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">
                <h6>이름</h6></b-col
              ><b-col cols="4" align-self="start">
                <h6>{{userInfo.username}}</h6>
                </b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">
                <h6>E-mail</h6></b-col
              ><b-col cols="4" align-self="start"><h6>{{ userInfo.email }}</h6></b-col>
              <b-col cols="2"></b-col>
            </b-row>
            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="2" align-self="end">
                <h6>가입일</h6></b-col
              ><b-col cols="4" align-self="start">
                <h6>{{userInfo.joindate}}</h6></b-col>
              <b-col cols="2"></b-col>
            </b-row>
          </b-container>
          <hr class="my-4" />

          <b-button variant="primary" href="#" @click="modifyMember" class="mr-1">정보수정</b-button>
          <b-button variant="danger" href="#" @click="resignMember" class="mr-1">회원탈퇴</b-button>
        </b-jumbotron>
      </b-col>
      <b-col></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  name: "MemberMyPage",
  components: {},
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(memberStore, ["deleteMember"]),

    resignMember() {
      this.deleteMember(this.userInfo.userid);
      alert("탈퇴되었습니다.");
      if (this.$route.path != "/") this.$router.push({ name: "Main" });
    },

    modifyMember(){
      this.$router.push({name: "MemberModify"});
    }
  },
};
</script>

<style></style>
