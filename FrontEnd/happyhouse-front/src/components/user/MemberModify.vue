<template>
  <b-container class="mt-4">
    <b-jumbotron>
      <b-row>
        <b-col>
          <h3>회원 정보 수정</h3>
        </b-col>
      </b-row>
      <b-container class="mt-4">
        <b-avatar src="https://placekitten.com/300/300" size="6rem"></b-avatar>
        <b-row>
          <b-col>
            <b-col style="text-align: left">
              <b-form @submit="onSubmit" @reset="onReset">
                <b-form-group id="username-group" label="이름" label-for="username">
                  <b-form-input readonly id="username" v-model="user.username" type="text" required> </b-form-input>
                </b-form-group>

                <b-form-group id="userid-group" label="아이디" label-for="userid">
                  <b-form-input readonly id="userid" v-model="user.userid" type="text" required> </b-form-input>
                  <div id="idresult" class="mt-1"></div>
                </b-form-group>

                <b-form-group id="userpwd-group" label="비밀번호" label-for="userpwd">
                  <b-form-input id="userpwd" v-model="user.userpwd" type="password" required> </b-form-input>
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
                </div>
                <label style="margin-top: 10px ;" for="profile">프로필 사진 등록</label>
                <div class="filebox">
                  <input type="file" id="profile" ref="files" v-on:change="handleFileUpload()" multiple />
                </div>
                <div class="form-group text-center">
                  <b-button type="submit" variant="primary" class="m-1">완료</b-button>
                  <b-button type="reset" variant="danger" class="m-1">초기화</b-button>
                </div>
              </b-form>
            </b-col>
          </b-col>
        </b-row>
      </b-container>
    </b-jumbotron>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import axios from "axios";

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

    submitFile() {
      for (let i = 0; i < this.files.length; i++) {
        let formData = new FormData();
        formData.append('title', this.title);
        formData.append('files', this.files[i]);
        formData.append('userid', this.userInfo.userid);
        axios.post('http://localhost:9999/vue/gallery/',
          formData, {
            headers: {
              'Content-Type': 'multipart/form-data'
            }
          }
        ).then(function() {
          console.log('SUCCESS!!');
        })
        .catch(function() {
          console.log('FAILURE!!');
        });
      }
    },

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
      this.user.emailid = "";
      this.user.emaildomain = "";
    },
  },
};
</script>

<style></style>
