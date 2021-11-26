<template>
  <b-container class="mt-4">
    <div class="wrapper">
      <b-row>
        <b-col>
          <h3 style="color: white; font-weight: 400; font-size: 30px;">회원 정보 수정</h3>
        </b-col>
      </b-row>
      <b-container class="mt-4">
        <b-row>
          <b-col>
            <b-col style="text-align: left">
              <b-form @submit="onSubmit" @reset="onReset">
                <b-form-group id="username-group" label="이름" label-for="username">
                  <b-form-input readonly id="username" v-model="user.username" type="text" required> </b-form-input>
                </b-form-group>

                <b-form-group id="userid-group" label="아이디" label-for="userid">
                  <b-form-input readonly id="userid" v-model="user.userid" type="text" required > </b-form-input>
                  <div id="idresult" class="mt-1"></div>
                </b-form-group>

                <b-form-group id="userpwd-group" label="비밀번호" label-for="userpwd">
                  <b-form-input id="userpwd" placeholder="변경할 비밀번호" v-model="user.userpwd" type="password" required > </b-form-input>
                </b-form-group>

                <b-form-group id="pwdcheck-group" label="비밀번호 재입력" label-for="pwdcheck">
                  <b-form-input id="pwdcheck" type="password" v-model="pwdcheck" required> </b-form-input>
                </b-form-group>

                <div class="form-group">
                  <label for="emailid">이메일</label><br />
                  <div id="email" class="custom-control-inline">
                    <input type="text" class="form-control" id="emailid" v-model="user.email" placeholder="" size="25" />
                    @
                    <select class="form-control" id="emaildomain" v-model="emaildomain">
                      <option value="ssafy.com">ssafy.com</option>
                      <option value="naver.com">naver.com</option>
                      <option value="kakao.com">kakao.com</option>
                      <option value="gmail.com">gmail.com</option>
                    </select>
                  </div>
                  <!-- <div class="form-group text-center" style="margin-top: 30px">
                    <b-button type="submit" variant="primary" class="m-1">완료</b-button>
                    <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
                  </div> -->
                  <div class="text-center" style="display: flex; align-items: center; justify-content: center; margin-top: 30px">
                    <button id="btn-modify" @click="modifyMember">수 정</button>
                    <button id="btn-resign" @click="resignMember">계정  탈퇴</button>
                  </div>
                </div>
              </b-form>
            </b-col>
          </b-col>
        </b-row>
      </b-container>
    </div>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";

const memberStore = "memberStore";

export default {
  data() {
    return {
      user: {
        username: "",
        userid: "",
        userpwd: "",
        email: "",
        joindate: "",
      },
      title: '',
      files: [],
      pwdcheck: "",
      emaildomain: "",
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },

  created() {
    this.user.username = this.userInfo.username;
    this.user.userid = this.userInfo.userid;
    this.user.joindate = this.userInfo.joindate;
  },

  methods: {
    ...mapActions(memberStore, ["updateMember"]),

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      !this.user.username && ((msg = "이름을 입력해주세요"), (err = false), this.$refs.username.focus());
      err && !this.user.userid && ((msg = "아이디를 입력해주세요"), (err = false), this.$refs.userid.focus());
      err && !this.user.userpwd && ((msg = "비밀번호를 입력해주세요"), (err = false), this.$refs.userpwd.focus());
      err && this.user.userpwd != this.pwdcheck && ((msg = "비밀번호가 동일하지 않습니다."), (err = false));

      if (!err) alert(msg);
      else {
        this.user.email = this.user.email + "@" + this.emaildomain;
        this.updateMember(this.user);
        alert("수정이 완료되었습니다.");
        this.$router.push({ name: "MyPage" });
      }
    },

    onReset(event) {
      event.preventDefault();
      this.user.userpwd = "";
      this.user.email = "";
      this.emaildomain = "";
      this.pwdcheck = "";
    },
  },
};
</script>

<style scoped>
  h3 {
    color: white;
    font-weight: 400;
    font-size: 30px;
  }

  .wrapper {
    border: 1px solid black;
    width: 100%;
    height: 700px;
    background-image: url('../../assets/title1.jpg');
    margin-top: 80px;
    padding-top: 30px;
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
  
  label {
    color: white;
  }

  .d-block {
    color: white;
  }

</style>
