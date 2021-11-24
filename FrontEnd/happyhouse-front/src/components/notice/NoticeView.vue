<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <button class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600" @click="listNotice()">목록</button>
      </b-col>
      <b-col class="text-center">
        <b-button pill variant="outline-secondary" class="mr-2" @click="movePrev()">이전 글</b-button>
        <b-button pill variant="outline-secondary" class="ml-2" @click="moveNext()">다음 글</b-button>
      </b-col>
      <b-col class="text-right">
        <button v-if="checkWriter" class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600"
          @click="moveModifyNotice"
          >수정</button
        >
        <button v-if="checkWriter" class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600" @click="removeNotice"
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
            <div class="ml-3 mr-4 mt-1">
              <div class="b_inbox_user" style="display: inline; float: left;">
                {{ notice.userid && notice.userid }}
              </div>
              <div class="inbox_regtime" style="display: inline; float: right;">
              {{ changeDateFormat }}
              </div>
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
import { getNotice, deleteNotice, getPrev, getNext} from "@/api/notice";
import { mapState } from "vuex";
import swal from 'sweetalert';

const memberStore = "memberStore";

export default {
  name: "NoticeView",

  data() {
    return {
      Notice: {},
      prevNoticeNo: 0,
      nextNoticeNo: 0
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
          "MM월DD일 \xa0 hh:mm"
      );
    },

    checkAdmin() {
      return this.comment.userid === 'admin';
    },
  },
  created() {
    getNotice(
      this.$route.params.noticeno,
      (response) => {
        this.notice = response.data;

        getPrev(
          this.notice.noticeno,
          (response) => {
            this.prevNo = response.data;
          },
          (error) => {
            console.log("이전 글 더이상 없음", error);
          }
        );

        getNext(
          this.notice.noticeno,
          (response) => {
            this.nextNo = response.data;
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
      if(this.prevNo === 0) {
        swal("가장 최근에 작성된 공지입니다.", "더 이상 등록된 공지가 없습니다", "warning");
      }else {
        this.$router.push({
          name: "NoticeView",
          params: { noticeno: this.prevNoticeNo },
        }).catch(()=>{

        });
      }
    },
    moveNext() {
      if(this.nextNo === 0) {
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
    border: 1px solid #170B3B;
    padding: 0 20px 20px 20px;
    margin-top: 10px;
    border-radius: 10px;
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

  .comment_title {
    margin: 20px 0 20px 20px;
    font-weight: 700;
    font-size: 20px;
    text-align: left;
  }

  .comment_box_write {
    display: flex;
    margin-top : 10px;
    padding: 10px 20px 0 20px;
    border: 1px solid #170B3B;
    border-radius: 10px 10px 10px 10px;
    flex-direction: column;
    box-sizing: border-box;
    overflow: hidden;
    height: auto;
  }
  
  .b_inbox_title {
    text-align: left;
    font-size:40px;
    font-weight: 700;
    color: black;
    margin-top: 10px;
  }

  .b_inbox_user {
    text-align: left;
    font-size:25px;
    color: #170B3B;
    font-weight: bold;
  }

  .inbox_user {
    text-align: left;
    font-size:20px;
    color: #170B3B;
    font-weight: 500;
  }

  .inbox_regtime {
    text-align: right;
    font-size:20px;
    font-weight: 300;
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

  #btn-registerComment {
    margin-bottom: 20px;
    border: 1px solid lightgrey;
    border-radius: 15px 15px 15px 15px;
    background: white;
    font-size: 20px;
    color: grey;
    padding: 5px 10px 5px 10px;
  }

</style>
