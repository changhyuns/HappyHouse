<template>
  <div class="comment_box">
    <div style="padding-top: 5px">
      <div class="inbox_user" style="display: inline; float: left;">
        {{ comment.userid && comment.userid }} <b-icon icon="star-fill" animation="fade" v-if="checkAdmin"  font-scale="1"></b-icon>
      </div>
      <div class="inbox_regtime" style="display: inline; float: right;">
        {{ comment.regtime && changeDateFormat }}
        <b-icon icon="x-square" v-if="checkWriter" aria-hidden="true" style="float: right; margin: 5px 0 0 10px; cursor: pointer" @click="removeComment()" ></b-icon>
      </div>
    </div>
    <div>
      <textarea v-model="comment.content" ref="textarea" @focus="resize" style="resize:none" readonly></textarea>
    </div>
    <div>
      <div v-if="checkSubComment">
        <div v-for="subComment in subComments" v-bind:key="subComment.sub_comment_id">
          <sub-comment-list-row v-bind:subComment="subComment"/>
        </div>
      </div>
      <div>
        <div>
          <button class="btn-writeSubComment" :style="writeButtonDisplay" squared variant="outline-secondary" @click="showWriteForm()">댓글 달기</button>
        </div>
          <div class="subComment_box_write" :style="writeBoxDisplay">
            <div class="inbox_user">
              {{ userInfo.userid }}
            </div>
            <div>
              <textarea class="autosize" ref="textarea" v-model="subComment_content" placeholder="댓글을 남겨보세요"></textarea>
            </div>
            <div style="align-items: right">
              <button class="btn-writeSubComment" squared variant="outline-secondary" @click="submitSubComment()">등록</button>
            </div>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapState } from "vuex";
  import moment from "moment";
  import { writeSubComment, listSubComment, deleteComment } from "@/api/board";
  import swal from "sweetalert";
  import SubCommentListRow from './SubCommentListRow.vue';

  const memberStore = "memberStore";

  export default {
    name: "CommentListRow",
    components: { SubCommentListRow },
    // props: {
    //   commentid: Number,
    //   userid: String,
    //   articleno: Number,
    //   hit: Number,
    //   regtime: String,
    //   content: String,
    // },
    props: ["comment"],
    data() {
      return {
        subComments: [],
        subComment_content: '',
        writeBoxDisplay: {
          display: 'none',
        },
        writeButtonDisplay: {
          display: 'inline',
        }
      }
    },
    created() {
      listSubComment(
          this.comment.commentid,
          (response) => {
            console.log("subComment", response.data);
            this.subComments = response.data;
          },
          (error) => {
            console.log("하위 댓글 불러오기 실패!", error);
          }
        );
    },

    computed: {
      ...mapState(memberStore, ["userInfo"]),

      changeDateFormat() {
        return moment(new Date(this.comment.regtime)).format(
            "MM월DD일 \xa0 hh:mm"
        );
      },

      checkSubComment() {
        return this.subComments.length !== 0
      },

      checkWriter() {
        return this.userInfo.userid === this.comment.userid;
      },

      checkAdmin() {
        return this.comment.userid === 'admin';
      }
    },

    methods: {
      submitSubComment() {
        swal("하위 댓글을 등록하시겠습니까?", {
          buttons: ["취소", "등록"],
        })
        .then((willSubmit) => {
          if(willSubmit) {
            writeSubComment({articleno: this.comment.articleno, commentid: this.comment.commentid, content: this.subComment_content, userid: this.userInfo.userid}, () => {
              swal("하위 댓글 등록 성공!", {
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
      removeComment() {
        swal({
          text: "삭제하면 되돌릴 수 없습니다.",
          icon: "warning",
          buttons: true,
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {
            deleteComment(this.comment.commentid, () => {
              swal("댓글 삭제 완료!", {
                icon: "success",
              });
              this.$router.go();
            });
          }
        });
      },

      showWriteForm() {
        this.writeBoxDisplay.display = 'flex';
        this.writeButtonDisplay.display = 'none';
      }
    },

    mounted() {
      this.resize();
    }
  };
</script>

<style scoped>
  .comment_box {
    border: none;
    border-radius: 10px;
    background-color: #F2EFFB;
    padding: 0 20px 0 20px;
    margin-bottom: 15px;
  }

  .inbox_user {
    text-align: left;
    font-size:20px;
    color: #170B3B;
    font-weight: 500;
  }

  .inbox_regtime {
    text-align: right;
    font-size: 20px;
    font-weight: 300;
  }

  textarea {
    flex: 1 1 auto;
    box-sizing: border-box;
    width: 100%;
    height: auto;
    border: none;
    outline: none;
    overflow: hidden;
    cursor: default;
    white-space: pre;
    font-weight: 300;
    font-size: 15px;
    margin: 10px 0 10px 0;
    min-height: 30px;
    background-color: #F2EFFB;
  }

  .btn-writeSubComment {
    margin-bottom: 10px;
    border: 1px solid #9F81F7;
    background-color: #F2EFFB;
    border-radius: 15px 15px 15px 15px;
    font-size: 15px;
    color: #9F81F7;
    padding: 5px 10px 5px 10px;
  }

  .subComment_box_write {
    margin-top : 10px;
    padding: 10px 20px 0 20px;
    border: 1px solid #170B3B;
    border-radius: 10px 10px 10px 10px;
    flex-direction: column;
    box-sizing: border-box;
    overflow: hidden;
    height: auto;
  }

  .autosize {
    flex: 1 1 auto;
    box-sizing: border-box;
    width: 100%;
    margin-top: 10px;
    border: none;
    outline: none;
    overflow: hidden;
    min-height: 50px;
    resize: none;
  }
</style>