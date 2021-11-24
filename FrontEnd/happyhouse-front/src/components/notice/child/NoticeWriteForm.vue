<template>
  <b-row class="mb-1">
    <b-col style="text-align: left; border: 3px solid #170B3B;
      padding: 0 20px 20px 20px;
      margin-top: 10px;
      border-radius: 10px;
      padding-top: 20px;">
      <b-form @submit="onSubmit" @reset="onReset">
        <b-form-group
          style="font-size: 20px; font-weight: bold; width: 100%; color: #170B3B"
          id="subject-group"
          label="제목"
          label-for="subject"
        >
          <b-form-input
            id="subject"
            style="border: 1px solid #170B3B; font-size: 15px; "
            v-model="notice.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content" style="font-size: 20px; font-weight: bold; width: 100%; color: #170B3B">
          <b-form-textarea
            style="border: 1px solid #170B3B; font-size: 15px;" 
            id="content"
            v-model="notice.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>
        </b-form-group>
        <div class="container" v-if="this.type === 'modify'">
          <div class="filebox">
            <input style="margin-top: 20px" type="file" id="files" ref="files" v-on:change="handleFileUpload()" multiple />
          </div>
        </div>
        <div style="text-align: center">
        <button class="btn-write" style="border: none; color: #170B3B; font-weight:600;" type="submit"
          v-if="this.type === 'register'"
          >작성</button
        >
        <button class="btn-write" style="border: none; color: #170B3B; font-weight:600;" type="submit" v-else
          >수정</button
        >
        </div>
      </b-form>
    </b-col>
  </b-row>
</template>

<script>
import { getNotice } from "@/api/notice";
import { mapActions, mapState } from "vuex";
import swal from "sweetalert";
import axios from "axios";

const noticeStore = "noticeStore";
const memberStore = "memberStore";

export default {
  name: "NoticeWriteForm",
  data() {
    return {
      notice: {
        noticeno: 0,
        subject: "",
        content: "",
      },
      isUserid: false,
      title: '',
      files: [],
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      getNotice(
        this.$route.params.noticeno,
        ({ data }) => {
          this.notice = data;
        },
        (error) => {
          console.log(error);
        }
      );
      this.isUserid = true;
    }
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(noticeStore, ["registNotice", "modifyNotice"]),

    insertNotice() {
      this.registNotice({userid: this.userInfo.userid, subject: this.notice.subject, content: this.notice.content});
    },

    updateNotice() {
      this.submitFile();
      this.modifyNotice({noticeno: this.notice.noticeno, userid: this.userInfo.userid, subject: this.notice.subject, content: this.notice.content});
      swal("성공", "게시글을 수정 완료!", "success");
      // this.$router.push({
      //   name: "NoticeView",
      //   params: { noticeno: this.notice.noticeno }
      // });
      this.$router.go();
    },

    handleFileUpload() {
      this.files = this.$refs.files.files;
      console.log(this.files);
    },

    submitFile() {
      for (let i = 0; i < this.files.length; i++) {
        let formData = new FormData();
        formData.append('title', this.title);
        formData.append('files', this.files[i]);
        formData.append('noticeno', this.notice.noticeno);
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
      // !this.notice.userid &&
      //   ((msg = "작성자 입력해주세요"),
      //   (err = false),
      //   this.$refs.userid.focus());
      // err &&
        !this.notice.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.notice.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        // this.type === "register" ? this.registnotice() : this.updatenotice();
        this.type === "register" ? this.insertNotice() 
                                : this.updateNotice();
      
    },
    onReset(event) {
      event.preventDefault();
      this.notice.noticeno = 0;
      this.notice.subject = "";
      this.notice.content = "";
      this.$router.push({ name: "BoarUpdate", params: { noticeno: this.notice.noticeno }},
      );
    },
    
    moveList() {
      this.$router.push({ name: "NoticeList" });
    },
  },
};
</script>

<style scoped>
  #modifyContainer {
    border: 1px solid #170B3B;
    padding: 0 20px 20px 20px;
    margin-top: 10px;
    border-radius: 10px;
    padding-top: 20px;
  }

  /* .filebox .upload-name {
    display: inline-block;
    height: 40px;
    padding: 0 10px;
    vertical-align: middle;
    border: 1px solid #dddddd;
    width: 78%;
    color: #999999;
  } */
  /* .filebox label {
    display: inline-block;
    padding: 10px 20px;
    color: #fff;
    vertical-align: middle;
    background-color: #999999;
    cursor: pointer;
    height: 40px;
    margin-left: 10px;
  }
  .filebox input[type="file"] {
    position: absolute;
    width: 0;
    height: 0;
    padding: 0;
    overflow: hidden;
    border: 0;
  } */
</style>
