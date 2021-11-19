<template>
  <b-container class="bv-example-row mt-3">
    <b-row>
      <b-col>
        <b-alert show><h3>글보기</h3></b-alert>
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle">목록</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          size="sm"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" size="sm" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <b-row class="mb-1">
      <b-col>
        <b-card
          :header-html="`<h3>${article.subject}</h3><br>
          <div><h5>작성자 : ${article.userid}</h5></div><br>
          <div><h5>${article.regtime}</h5></div>`"
          class="mb-2"
          border-variant="dark"
          no-body
        >
          <b-card-body class="text-align: center">
            <div v-html="message" style="font-size: 30px"></div>
          </b-card-body>
        </b-card>
      </b-col>
    </b-row>
    <!-- <div id="comment_textarea">
      <b-form-textarea v-model="value" debounce="500" rows="3" max-rows="5" placeholder="댓글을 남겨보세요"></b-form-textarea>
    </div> -->
    <div class="comment_box">
      <div class="comment_inbox_user">
        {{ article.userid }}
      </div>
      <div>
        <textarea v-model="comment_content" placeholder="댓글을 남겨보세요" @keyup.exact.enter="submitComment()"></textarea>
      </div>
      <div style="align-items: right">
        <button id="btn-registerComment" squared variant="outline-secondary" @click="submitComment()">등록</button>
      </div>
    </div>
  </b-container>
</template>

<script>
// import moment from "moment";
import { getArticle, deleteArticle, writeComment } from "@/api/board";
import { mapState } from "vuex";
const memberStore = "memberStore";

export default {
  data() {
    return {
      article: {},
      comment_content: '',
    };
  },
  computed: {
    ...mapState(memberStore, ["userInfo"]),

    message() {
      if (this.article.content)
        return this.article.content.split("\n").join("<br>");
      return "";
    },
    // changeDateFormat() {
    //   return moment(new Date(this.article.regtime)).format(
    //     "YYYY.MM.DD hh:mm:ss"
    //   );
    // },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;
      },
      (error) => {
        console.log("삭제 에러발생!!", error);
      }
    );
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "BoardList" });
    },
    moveModifyArticle() {
      this.$router.replace({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      if (confirm("게시글을 삭제하시겠습니까?")) {
        deleteArticle(this.article.articleno, () => {
          this.$router.push({ name: "BoardList2" });
        });
      }
    },
    submitComment() {
      const prev_comment = this.comment_content;
      if(confirm("댓글을 등록하시겠습니까?")) {
        console.log(prev_comment);
        writeComment({articleno: this.article.articleno, content: this.comment_content, userid: this.userInfo.userid}, () => {
          alert('댓글 등록 완료');
          this.comment_content = '';
        })
      }else{
        this.comment_content = prev_comment;
      }
    },
    nothing() {

    }
  },
};
</script>

<style scoped>
  .comment_box {
    display: flex;
    margin-top : 50px;
    border: 1px solid;
    border-radius: 10px 10px 10px 10px;
    flex-direction: column;
    box-sizing: border-box;
  }
  
  .comment_inbox_user {
    padding-left: 20px;
    margin-top: 10px;
    text-align: left;
    font-size:20px;
    color: teal;
    font-weight: 500;
  }

textarea {
  flex: 1 1 auto;
  box-sizing: border-box;
  width: 95%;
  height: 100px;
  margin-top: 10px;
  margin-bottom: 10px;
  border: none;
  outline: none;
  overflow: auto;
}

#btn-registerComment {
  margin-bottom: 20px;
  border: 1px solid grey;
  border-radius: 10px 10px 10px 10px;
  background: white;
  font-size: 15px;
  color: grey;
  padding: 5px 10px 5px 10px;
}

</style>
