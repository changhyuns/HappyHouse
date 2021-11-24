<template>
  <b-container class="bv-example-row mt-5">
    <b-row>
    </b-row>
    <b-row class="mb-1">
      <b-col class="text-left">
        <button class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600" @click="listArticle()">목록</button>
      </b-col>
      <b-col class="text-center" style="display:flex; align-items: center; justify-content: space-between;">
        <b-button pill variant="outline-secondary" class="mr-2" @click="movePrev()">이전 글</b-button>
        <b-button pill variant="outline-secondary" class="ml-2" @click="moveNext()">다음 글</b-button>
      </b-col>
      <b-col class="text-right">
        <button v-if="checkWriter" class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600"
          @click="moveModifyArticle"
          >수정</button
        >
        <button v-if="checkWriter" class="btn-write mr-2" style="border: none; color: #170B3B; font-weight:600" @click="removeArticle"
          >삭제</button
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
            <div class="ml-3 mr-4 mt-1" style="; display: flex; align-items: center">
              <div class="b_inbox_user" style="display: inline; float: left;">
                {{ article.userid && article.userid }} <b-icon icon="person-check" v-if="checkWriterAdmin"  font-scale="1"></b-icon>
              </div>
              <div class="inbox_regtime" style="display: inline; float: left;">
              <b-icon icon="dot" font-scale="1" style="margin-left: 5px"></b-icon>{{ changeDateFormat }}
              </div>
            </div>
            <div class="pre-formatted" style="margin: 50px 0 50px 0">
              {{ article.content && article.content }}
            </div>
          </div>
        </b-col>
      </b-row>
      <div style="border-top: 1px solid #E6E6E6; padding-top: 5px;" class="comment_title">댓글 ({{ this.comments_length }})</div> 
      <div v-for="comment in comments" v-bind:key="comment.commentid">
        <comment-list-row v-bind:comment="comment"/>
      </div>
      <div class="comment_box_write">
        <div class="inbox_user">
          {{ userInfo.userid }}<b-icon style="margin-left: 50x" icon="person-check" v-if="checkCommentWriterAdmin" font-scale="1"></b-icon>
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
import { getArticle, deleteArticle, writeComment, listComment, getPrev, getNext, getSubCommentCount} from "@/api/board";
import { mapState } from "vuex";
import CommentListRow from './child/CommentListRow.vue';
import swal from 'sweetalert';

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
      prevNo: 0,
      nextNo: 0
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
          "MM월DD일\xa0\xa0hh:mm"
      );
    },

    checkWriter() {
      return (this.userInfo.userid === this.article.userid || this.userInfo.userid === 'admin')
    },

    checkCommentWriterAdmin() {
      return this.userInfo.userid === 'admin';
    },

    checkAdmin() {
      return this.comment.userid === 'admin';
    },

    checkWriterAdmin() {
      return this.article.userid === 'admin';
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
            console.log("comments", this.comments);
            this.comments_length = response.data.length;
          },
          (error) => {
            console.log("댓글 불러오기 실패!", error);
          }
        );

        getPrev(
          this.article.articleno,
          (response) => {
            this.prevNo = response.data;
          },
          (error) => {
            console.log("이전 글 더이상 없음", error);
          }
        );

        getNext(
          this.article.articleno,
          (response) => {
            this.nextNo = response.data;
          },
          (error) => {
            console.log("이전 글 더이상 없음", error);
          }
        );

        getSubCommentCount(
          this.article.articleno,
          (response) => {
            console.log(response);
            this.comments_length += response.data;
          },
          (error) => {
            console.log("하위 댓글 개수 가져오기 실패", error);
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
      this.$router.push({
        name: "BoardUpdate",
        params: { articleno: this.article.articleno },
      });
      //   this.$router.push({ path: `/board/modify/${this.article.articleno}` });
    },
    removeArticle() {
      swal({
          text: "삭제하면 되돌릴 수 없습니다.",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {
            deleteArticle(this.article.articleno, () => {
              swal("게시글 삭제 완료!", {
                icon: "success",
              });
              this.$router.push({ name: "BoardList2" });
            });
          }
        });
    },
    submitComment() {
      swal("댓글을 등록하시겠습니까?", {
        buttons: ["취소", "등록"],
      })
      .then((willSubmit) => {
        if(willSubmit) {
          writeComment({articleno: this.article.articleno, content: this.comment_content, userid: this.userInfo.userid}, () => {
            swal("댓글 등록 완료!", {
              icon: "success",
            });
            this.$router.go();
          })
        }
      });
    },
    resize() {
      const { textarea } = this.$refs;
      textarea.style.height = "1px";
      textarea.style.height = (textarea.scrollHeight - 4) + "px";
    },
    movePrev() {
      if(this.prevNo === 0) {
        swal("가장 최근에 작성된 게시글입니다.", "더 이상 등록된 글이 없습니다", "warning");
      }else {
        this.$router.push({
          name: "BoardView",
          params: { articleno: this.prevNo },
        }).catch(()=>{

        });
      }
    },
    moveNext() {
      if(this.nextNo === 0) {
        swal("마지막 글입니다.", "더 이상 등록된 글이 없습니다", "warning");
      }else {
        this.$router.push({
          name: "BoardView",
          params: { articleno: this.nextNo },
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

  .board_box {
    display: flex;
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
    border: 1px solid #E6E0F8;
    border-radius: 10px 10px 10px 10px;
    flex-direction: column;
    box-sizing: border-box;
    overflow: hidden;
    height: auto;
  }
  
  .b_inbox_title {
    text-align: left;
    font-size: 30px;
    font-weight: 700;
    color: black;
    margin-top: 10px;
  }

  .b_inbox_user {
    text-align: left;
    font-size:20px;
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
    font-size: 15px;
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
