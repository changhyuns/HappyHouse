<template>
  <div class="comment_box">
    <div>
      <div class="inbox_user" style="display: inline; float: left;">
        {{ comment.userid && comment.userid }}
      </div>
      <div class="inbox_regtime" style="display: inline; float: right;">
        {{ comment.regtime && changeDateFormat }}
      </div>
    </div>
    <div>
      <textarea v-model="comment.content" ref="textarea" @focus="resize" style="resize:none" readonly></textarea>
    </div>
  </div>
</template>

<script>
  import moment from "moment";
  export default {
    name: "CommentListRow",
    // props: {
    //   commentid: Number,
    //   userid: String,
    //   articleno: Number,
    //   hit: Number,
    //   regtime: String,
    //   content: String,
    // },
    props: ["comment"],

    computed: {
      changeDateFormat() {
        return moment(new Date(this.comment.regtime)).format(
            "MM월DD일 \xa0 hh:mm"
        );
      },
    },

    methods: {
      resize() {
        const { textarea } = this.$refs;
        textarea.style.height = "1px";
        textarea.style.height = (textarea.scrollHeight - 4) + "px";
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
    background-color: #FAFAFA;
    padding: 20px 20px 0 20px;
    margin-bottom: 15px;
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
    font-size: 20px;
    margin: 10px 0 10px 0;
    min-height: 30px;
    background-color: #FAFAFA;

  }

</style>