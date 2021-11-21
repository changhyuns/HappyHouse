<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <b-button variant="outline-primary" @click="listArticle()">목록</b-button>
      </b-col>
      <b-col class="text-center">
        <b-button pill variant="outline-secondary" class="mr-2">이전 글</b-button>
        <b-button pill variant="outline-secondary" class="ml-2">다음 글</b-button>
      </b-col>
      <b-col class="text-right">
        <b-button
          variant="outline-info"
          @click="moveModifyArticle"
          class="mr-2"
          >글수정</b-button
        >
        <b-button variant="outline-danger" @click="removeArticle"
          >글삭제</b-button
        >
      </b-col>
    </b-row>
    <div id="bcContainer">
      <b-row class="mb-1">
        <b-col>
          <div class="board_box" style="border-radius: 10px;">
            <div class="b_inbox_title ml-2">
              {{ article.subject && article.subject }}
            </div>
            <div class="ml-3 mr-4 mt-1">
              <div class="b_inbox_user" style="display: inline; float: left;">
                {{ article.userid && article.userid }}
              </div>
              <div class="inbox_regtime" style="display: inline; float: right;">
              {{ changeDateFormat }}
              </div>
            </div>
            <div class="pre-formatted" style="margin: 50px 0 50px 0">
              {{ article.content && article.content }}
            </div>
          </div>
        </b-col>
      </b-row>
      <div class="comment_title">댓글 ({{ comments.length && comments.length }})</div> 
      <div v-for="comment in comments" v-bind:key="comment.commentid">
        <comment-list-row v-bind:comment="comment"/>
      </div>
      <div class="comment_box_write">
        <div class="inbox_user">
          {{ article.userid }}
        </div>
        <div>
          <textarea class="autosize" ref="textarea" v-model="comment_content" placeholder="댓글을 남겨보세요"></textarea>
        </div>
        <div style="align-items: right">
          <button id="btn-registerComment" squared variant="outline-secondary" @click="submitComment()">등록</button>
        </div>
      </div>
    </div>
  </b-container>
</template>

<script>
import moment from "moment";
import { getArticle, deleteArticle, writeComment, listComment } from "@/api/board";
import { mapState } from "vuex";
import CommentListRow from './child/CommentListRow.vue';

const memberStore = "memberStore";

export default {
  name: "BoardView",
	components: { CommentListRow },

  data() {
    return {
      article: {},
      comments: [],
      comments_length: 0,
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

    changeDateFormat() {
      return moment(new Date(this.article.regtime)).format(
          "MM월DD일 \xa0 hh:mm"
      );
    },
  },
  created() {
    getArticle(
      this.$route.params.articleno,
      (response) => {
        this.article = response.data;

        listComment(
          this.article.articleno,
          (response) => {
            this.comments = response.data;
            this.comments_length = response.data.length;
          },
          (error) => {
            console.log("댓글 불러오기 실패!", error);
          }
        );
      },
      (error) => {
        console.log("삭제 에러발생!!", error);
      }
    );
    
  },
  mounted() {
    this.resize();
  },
  methods: {
    listArticle() {
      this.$router.push({ name: "BoardList2" });
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
          this.$router.go();
        })
      }else{
        this.comment_content = prev_comment;
      }
    },
    resize() {
      const { textarea } = this.$refs;
      textarea.style.height = "1px";
      textarea.style.height = (textarea.scrollHeight - 4) + "px";
    },
  },
};
</script>

<style scoped>
  #bcContainer {
    border: 1px solid lightgrey;
    padding: 0 20px 20px 20px;
    margin-top: 30px;
    border-radius: 10px;
    padding-top: 20px;
  }

  .board_box {
    display: flex;
    border: none;
    flex-direction: column;
    box-sizing: border-box;
    margin: 0 0 30px 0;
  }

  .pre-formatted {
    white-space: pre;
    font-weight: 300;
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
    border: 1px solid grey;
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
    color: teal;
    font-weight: 500;
  }

  .inbox_user {
    text-align: left;
    font-size:20px;
    color: teal;
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
