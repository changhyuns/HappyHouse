<template>
  <b-container fluid="sm" class="mt-3">
    <b-row>
      <b-col>
        <h3 style="text-align:center; margin-top: 100px; color: white; height: 40px; font-size: 30px;">회원가입</h3>
        <b-col style="text-align: left">
          <b-form @submit="onSubmit" @reset="onReset">
            <b-form-group
              id="username-group"
              label="이름"
              label-for="username"
            >
              <b-form-input
                id="username"
                v-model="user.username"
                type="text"
                required
              >
              </b-form-input>
            </b-form-group>

            <b-form-group
              id="userid-group"
              label="아이디"
              label-for="userid"
            >
              <b-form-input
                id="userid"
                v-model="user.userid"
                type="text"
                required
              >
              </b-form-input>
              <div id="idresult" class="mt-1"></div>
            </b-form-group>

            <b-form-group
              id="userpwd-group"
              label="비밀번호"
              label-for="userpwd"
            >
              <b-form-input
                id="userpwd"
                v-model="user.userpwd"
                type="password"
                required
              >
              </b-form-input>
            </b-form-group>

            <b-form-group
              id="pwdcheck-group"
              label="비밀번호 재입력"
              label-for="userpwd"
            >
              <b-form-input
                id="pwdcheck"
                v-model="user.pwdcheck"
                type="password"
                required
              >
              </b-form-input>
            </b-form-group>

            <div class="form-group">
              <label for="emailid">이메일</label><br />
              <div id="email" class="custom-control-inline">
                <input
                  type="text"
                  class="form-control"
                  id="emailid"
                  v-model="user.emailid"
                  placeholder=""
                  size="25"
                />
                @
                <select
                  class="form-control"
                  id="emaildomain"
                  v-model="user.emaildomain"
                >
                  <option value="ssafy.com">ssafy.com</option>
                  <option value="naver.com">naver.com</option>
                  <option value="kakao.com">kakao.com</option>
                  <option value="gmail.com">gmail.com</option>
                </select>
              </div>
            </div>
            <div class="form-group text-center">
              <button class="mr-2" @click="onSubmit">회원가입</button>
              <button class="ml-2" @click="onReset">초기화</button>
            </div>
          </b-form>
        </b-col>
      </b-col>
    </b-row>
  </b-container>
</template>

<script>
import { mapActions} from 'vuex';
import swal from 'sweetalert';

const memberStore = "memberStore";

export default {
  name: "MemberJoin",

  data() {
    return {
      user: {
        username: "",
        userid: "",
        userpwd: "",
        email: "",
      },
      userpwdcheck: "",
      emaildomain: "",
    };
  },

  methods: {
    ...mapActions(memberStore, ["registMember"]),

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";

      !this.user.username &&
        ((msg = "이름을 입력해주세요"),
        (err = false),
        this.$refs.username.focus());
      err &&
        !this.user.userid &&
        ((msg = "아이디를 입력해주세요"),
        (err = false),
        this.$refs.userid.focus());
      err &&
        !this.user.userpwd &&
        ((msg = "비밀번호를 입력해주세요"),
        (err = false),
        this.$refs.userpwd.focus());

      if (!err) alert(msg);
      else{
        this.user.email = this.user.email + '@' + this.emaildomain;
        this.insertMember();
        swal("가입이 완료되었습니다.", 'success');
        this.$router.push({name:"Main"});
      }
    },

    onReset(event) {
      event.preventDefault();
      this.user.username = "";
      this.user.userid = "";
      this.user.userpwd = "";
      this.user.emailid = "";
      this.emaildomain = "";
    },

    insertMember() {
      this.registMember(this.user);
    }
  },
};
</script>
<style scoped>
  .mt-3 {
    width: 500px;
  }

  button {
    margin-top: 20px;
    border: none;
    background-image: url('../../assets/title1.jpg');
    color: white;
    font-weight: 300;
    font-size: 20px;
  }

  h3 {
    background-image: url('../../assets/title1.jpg');
  }
</style>
