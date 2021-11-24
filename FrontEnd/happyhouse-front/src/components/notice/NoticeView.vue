<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <button class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600" @click="listNotice()">목록</button>
      </b-col>
      <b-col class="text-center" style="display:flex; align-items: center; justify-content: space-between;">
        <b-button pill variant="outline-secondary" class="mr-2" @click="movePrev()">이전 글</b-button>
        <b-button pill variant="outline-secondary" class="ml-2" @click="moveNext()">다음 글</b-button>
      </b-col>
      <b-col class="text-right">
        <button v-if="checkAdmin" class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600"
          @click="moveModifyNotice"
          >수정</button
        >
        <button v-if="checkAdmin" class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600" @click="removeNotice"
          >삭제</button
        >
      </b-col>
    </b-row>
    <div id="bcContainer">
      <b-row class="mb-1">
        <b-col>
          <div class="notice_box" style="border-radius: 10px;">
            <div class="b_inbox_title ml-2">
              {{ notice.subject && notice.subject }}
            </div>
            <div class="ml-3 mr-4 mt-1" style="display: flex; align-items: center">
              <div class="b_inbox_user" style="display: inline; float: left;">
                  {{ notice.userid && notice.userid }}<b-icon icon="person-check" v-if="checkWriterAdmin"  font-scale="1" style="margin-left: 5px"></b-icon>
              </div>
              <div class="inbox_regtime" style="display: inline; float: left;">
              <b-icon icon="dot" font-scale="1" style="margin-left: 5px"></b-icon>{{ changeDateFormat }}
              </div>
            </div>
            <div v-if="checkFiles()">
              <div v-for="(file, index) in files" v-bind:key="index">
                <img style="width: 80%; margin-bottom: 10px;"  :src='`${file}`' alt="">
              </div>
              <!-- <img style="width: 90%" :src='`${files}`' alt=""> -->
            </div>
            <div class="pre-formatted" style="margin: 50px 0 50px 0">
              {{ notice.content && notice.content }}
            </div>
          </div>
        </b-col>
      </b-row>
    </div>
  </b-container>
</template>

<script>
import moment from "moment";
import { getNotice, deleteNotice, getPrev, getNext, getFile } from "@/api/notice";
import { mapState } from "vuex";
import swal from 'sweetalert';

const memberStore = "memberStore";

export default {
  name: "NoticeView",

  data() {
    return {
      notice: {},
      prevNoticeNo: 0,
      nextNoticeNo: 0,
      files: [],
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.notice.content)
        return this.notice.content.split("\n").join("<br>");
      return "";
    },

    changeDateFormat() {
      return moment(new Date(this.notice.regtime)).format(
          "MM월DD일\xa0\xa0hh:mm"
      );
    },

    checkAdmin() {
      return this.userInfo.userid === 'admin';
    },

    checkWriterAdmin() {
      return this.notice.userid === 'admin';
    }
  },
  created() {
    getNotice(
      this.$route.params.noticeno,
      (response) => {
        this.notice = response.data;

        getFile(
          this.notice.noticeno,
          (response) => {
            this.files = response.data[0];
            console.log("hhehehe", this.files)
          },
          (error) => {
            console.log("사진 가져오기", error);
          }
        )

        getPrev(
          this.notice.noticeno,
          (response) => {
            this.prevNoticeNo = response.data;
          },
          (error) => {
            console.log("이전 글 더이상 없음", error);
          }
        );

        getNext(
          this.notice.noticeno,
          (response) => {
            this.nextNoticeNo = response.data;
          },
          (error) => {
            console.log("이전 글 더이상 없음", error);
          }
        );
      },
      (error) => {
        console.log(error);
      }
    );
    
  },
  mounted() {
    this.resize();
  },
  methods: {
    listNotice() {
      this.$router.push({ name: "NoticeList" });
    },
    moveModifyNotice() {
      this.$router.push({
        name: "NoticeUpdate",
        params: { noticeno: this.notice.noticeno },
      });
    },
    removeNotice() {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        deleteNotice(this.notice.noticeno, () => {
          this.$router.push({ name: "NoticeList" });
        });
      }
    },
    resize() {
      const { textarea } = this.$refs;
      textarea.style.height = "1px";
      textarea.style.height = (textarea.scrollHeight - 4) + "px";
    },
    movePrev() {
      if(this.prevNoticeNo === 0) {
        swal("가장 최근에 작성된 공지입니다.", "더 이상 등록된 공지가 없습니다", "warning");
      }else {
        this.$router.push({
          name: "NoticeView",
          params: { noticeno: this.prevNoticeNo },
        }).catch(()=>{

        });
      }
    },
    checkFiles() {
      return this.files !== '';
    },
    moveNext() {
      if(this.nextNoticeNo === 0) {
        swal("마지막 공지입니다.", "더 이상 등록된 공지가 없습니다", "warning");
      }else {
        this.$router.push({
          name: "NoticeView",
          params: { noticeno: this.nextNoticeNo },
        }).catch(()=>{
          
        });
      }
    },
  },
};
</script>

<style scoped>
  #bcContainer {
    border-top: 1px solid #E6E6E6;
    padding: 0 20px 20px 20px;
    padding-top: 20px;
  }

  .notice_box {
    display: flex;
    border: none;
    flex-direction: column;
    box-sizing: border-box;
    margin: 0 0 30px 0;
  }

  .pre-formatted {
    white-space: pre;
    font-weight: 400;
    font-size: 20px;
    margin: 20px 0 0 0;
  }
  .b_inbox_title {
    text-align: left;
    font-size:30px;
    font-weight: 700;
    color: black;
    margin-top: 5px;
    margin-bottom: 5px;
  }

  .b_inbox_user {
    text-align: left;
    font-size:15px;
    color: #170B3B;
    font-weight: bold;
  }

  .autosize {
    flex: 1 1 auto;
    box-sizing: border-box;
    width: 100%;
    margin-top: 10px;
    border: none;
    outline: none;
    overflow: hidden;
    min-height: 100px;
    resize: none;
  }

</style>
