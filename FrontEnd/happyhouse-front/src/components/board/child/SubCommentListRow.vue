<template>
  <div class="subComment_box" style="display: flex;">
    <div style="display: flex; align-items: center; margin-right:20px">
      <b-icon icon="arrow-return-right" aria-hidden="true" font-scale="1"></b-icon>
    </div>
    <div style="width: 100%">
      <div style="padding-top: 5px; display: flex; align-items: center;">
        <div class="inbox_user" style="display: inline; float: left;">
          {{ subComment && subComment.userid }} <b-icon icon="person-check" v-if="checkAdmin"  font-scale="1"></b-icon>
        </div>
        <div class="inbox_regtime" style="display: inline; float: left;">
          <b-icon icon="dot" font-scale="1" style="margin-left: 5px"></b-icon>{{ subComment && changeDateFormat }}
        </div>
        <div style="display:inline; float: right;">
          <b-icon icon="x-square" v-if="checkWriter" aria-hidden="true" style="margin-left: 10px; cursor: pointer" @click="removeSubComment" ></b-icon>
        </div>
      </div>
      <div>
        <textarea v-model="subComment.content" ref="textarea" @focus="resize" style="resize:none" readonly></textarea>
      </div>
    </div>
  </div>
</template>

<script>
  import { mapState } from "vuex";
  import moment from "moment";
  import { deleteSubComment } from "@/api/board";
  import swal from "sweetalert";

  const memberStore = "memberStore";

  export default {
    name: "SubCommentListRow",
    props: ["subComment"],
    computed: {
      ...mapState(memberStore, ["userInfo"]),

      changeDateFormat() {
        return moment(new Date(this.subComment.regtime)).format(
            "MM월DD일\xa0\xa0hh:mm"
        );
      },

      checkWriter() {
        return this.userInfo.userid === this.subComment.userid;
      },

      checkAdmin() {
        return this.subComment.userid === 'admin';
      },

      checkWriterProfile() {
        return this.writerProfile !== null;
      },
    },

    methods: {
      resize() {
        const { textarea } = this.$refs;
        textarea.style.height = "1px";
        textarea.style.height = (textarea.scrollHeight - 4) + "px";
      },
      removeSubComment() {
        swal({
          text: "해당 댓글을 삭제하시겠습니까?",
          icon: "warning",
          buttons: ["취소", "삭제"],
          dangerMode: true,
        })
        .then((willDelete) => {
          if (willDelete) {
            deleteSubComment(this.subComment.sub_comment_id, () => {
              swal("댓글 삭제 완료!", {
                icon: "success",
              });
              this.$router.go();
            });
          }
        });
      },
    },
    mounted() {
      this.resize();
    }
  };
</script>

<style scoped>
  .subComment_box {
    border-bottom: 1px solid #F2F2F2;
    background-color: #FAFAFA;
    padding: 0 0 0 20px;
    margin-bottom: 15px;
  }

  .inbox_user {
    text-align: left;
    font-size: 15px;
    color: #170B3B;
    font-weight: 500;
  }

  .inbox_regtime {
    text-align: right;
    font-size: 15px;
    font-weight: 300;
  }

  textarea {
    background-color: #FAFAFA;
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
  }

</style>