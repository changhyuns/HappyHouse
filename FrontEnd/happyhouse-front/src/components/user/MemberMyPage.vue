<template>
  <b-container class="text-center" style="margin-top: 80px;" v-if="userInfo">
    <b-row class="center">
      <b-col></b-col>
      <b-col cols="10">
        <div class="wrapper text-center">
          <b-container class="mt-5 text-center">

            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="3" align-self="end"><h6>아이디</h6></b-col>
              <b-col style="margin-left: 50px; margin-top: 30px" cols="3" align-self="start"><h5>{{ userInfo.userid }}</h5></b-col>
              <b-col cols="2"></b-col>
            </b-row>

            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="3" align-self="end"><h6>이름</h6></b-col>
              <b-col style="margin-left: 50px;" cols="3" align-self="start"><h5>{{userInfo.username}}</h5></b-col>
              <b-col cols="2"></b-col>
            </b-row>

            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="3" align-self="end"><h6>E-mail</h6></b-col>
              <b-col style="margin-left: 50px;" cols="3" align-self="start"><h5>{{ userInfo.email }}</h5></b-col>
              <b-col cols="2"></b-col>
            </b-row>

            <b-row>
              <b-col cols="2"></b-col>
              <b-col cols="3" align-self="end"><h6>가입일</h6></b-col>
              <b-col style="margin-left: 50px;" cols="3" align-self="start"><h5>{{userInfo.joindate}}</h5></b-col>
              <b-col cols="2"></b-col>
            </b-row>

          </b-container>
          <div style="border-top: 1px solid lightgrey; margin-top: 100px" />
          <div class="text-center" style="display: flex; align-items: center; justify-content: center; margin-top: 30px">
            <button id="btn-modify" @click="modifyMember">수 정</button>
            <button id="btn-resign" @click="resignMember">계정  탈퇴</button>
          </div>
        </div>
      </b-col>
      <b-col style=""></b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import swal from 'sweetalert';

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
      swal("탈퇴되었습니다.", 'success');
      if (this.$route.path != "/") this.$router.push({ name: "Main" });
    },

    modifyMember(){
      this.$router.push({name: "MemberModify"});
    }
  },
};
</script>

<style scoped>
  .wrapper {
    border: 1px solid black;
    width: 100%;
    height: 500px;
    background-image: url('../../assets/title1.jpg');
  }

  #btn-modify {
    border: 1px solid white;
    border-radius: 20px;
    margin-right: 20px;
    width: 20%;
    height: 40px;
    background-color: transparent;
    font-weight: 600;
    font-size: 20px;
    color: white;
  }
  
  #btn-resign {
    border: 1px solid white;
    border-radius: 20px;
    margin-left: 20px;
    width: 20%;
    height: 40px;
    color: white;
    background-color: transparent;
    font-weight: 600;
    font-size: 20px;
  }

  .row {
    margin: 20px;
  }

  h5, h6 {
    color: white;
  }
</style>
