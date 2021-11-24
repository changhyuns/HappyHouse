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
            v-model="article.subject"
            type="text"
            required
            placeholder="제목 입력..."
          ></b-form-input>
        </b-form-group>

        <b-form-group id="content-group" label="내용" label-for="content" style="font-size: 20px; font-weight: bold; width: 100%; color: #170B3B">
          <b-form-textarea
            style="border: 1px solid #170B3B; font-size: 15px;" 
            id="content"
            v-model="article.content"
            placeholder="내용 입력..."
            rows="10"
            max-rows="15"
          ></b-form-textarea>

        </b-form-group>
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
import { getArticle } from "@/api/board";
import { mapActions, mapState } from "vuex";
import swal from "sweetalert";

const boardStore = "boardStore";
const memberStore = "memberStore";

export default {
  name: "BoardWriteForm",
  data() {
    return {
      article: {
        articleno: 0,
        subject: "",
        content: "",
      },
      isUserid: false,
      title: "",
      files: [],
    };
  },
  props: {
    type: { type: String },
  },
  created() {
    if (this.type === "modify") {
      getArticle(
        this.$route.params.articleno,
        ({ data }) => {
          this.article = data;
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
  // mounted() {
  //   axios.get('http://localhost:9999/vue/gallery/getImageList')
  //     .then(res => {
  //       console.log(res.data);
  //       this.galleryDatas = res.data
  //     }
  //   )
  //   .catch(error => console.log(error));
  // },
  methods: {
    ...mapActions(boardStore, ["registBoard", "modifyBoard"]),

    insertBoard() {
      // this.registBoard({userid: this.userInfo.userid, subject: this.article.subject, content: this.article.content});
      this.registGallery();
    },

    updateBoard() {
      this.modifyBoard({articleno: this.article.articleno, userid: this.userInfo.userid, subject: this.article.subject, content: this.article.content});
      swal("성공", "게시글을 수정 완료!", "success");
      this.$router.push({
        name: "BoardView",
        params: { articleno: this.article.articleno }
      });
    },

    onSubmit(event) {
      event.preventDefault();

      let err = true;
      let msg = "";
      // !this.article.userid &&
      //   ((msg = "작성자 입력해주세요"),
      //   (err = false),
      //   this.$refs.userid.focus());
      // err &&
        !this.article.subject &&
        ((msg = "제목 입력해주세요"),
        (err = false),
        this.$refs.subject.focus());
      err &&
        !this.article.content &&
        ((msg = "내용 입력해주세요"),
        (err = false),
        this.$refs.content.focus());

      if (!err) alert(msg);
      else
        // this.type === "register" ? this.registArticle() : this.updateArticle();
        this.type === "register" ? this.insertBoard() 
                                : this.updateBoard();
      
    },
    onReset(event) {
      event.preventDefault();
      this.article.articleno = 0;
      this.article.subject = "";
      this.article.content = "";
      this.$router.push({ name: "BoarUpdate", params: { articleno: this.article.articleno }},
      );
    },
    
    moveList() {
      this.$router.push({ name: "BoardList2" });
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
</style>
