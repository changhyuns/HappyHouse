<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
        <b-col>
          <b-alert show><h3>글목록</h3></b-alert>
        </b-col>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <b-button variant="outline-primary" @click="moveWrite()"
            >글쓰기</b-button
          >
        </b-col>
      </b-row>
      <b-row>
        <b-col>
          <b-table
            striped
            hover
            :items="boardList"
            :fields="fields"
            :current-page="currentPage"
            @row-clicked="viewArticle"
          >
          </b-table>
        </b-col>
      </b-row>
    </b-container>
    <b-pagination
        v-model="currentPage"
        :total-rows="boardCount"
        :per-page="perPage"
        aria-controls="my-table"
        @page-click="pageClick"
    ></b-pagination>
    </div>
</template>

<script>
// import BoardListRow from "@/components/board/child/BoardListRow.vue";
import { mapActions, mapState } from "vuex";

const boardStore = "boardStore";

export default {
  name: "BoardList",

  // components: {
  //   BoardListRow
  // },

  data() {
    return {
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject" },
        { key: "userid", label: "작성자", tdClass: "tdClass" },
        { key: "regtime", label: "작성일", tdClass: "tdClass" },
        { key: "hit", label: "조회수", tdClass: "tdClass" },
        { key: "content", label: "내용", tdClass: "tdClass"},
      ],
      perPage: 10,
      currentPage: 1,
      start: 0,
    };
  },

  computed: {
    ...mapState(boardStore, ["boardList", "boardCount"])
  },

  created() {
    this.getList();
  },

  methods: {
    ...mapActions(boardStore, ["getBoardList"]),

    moveWrite() {
      this.$router.push({ name: "BoardWrite" });
    },

    getList() {
      this.getBoardList({key: 'subject', pg: this.currentPage, spp: this.perPage, start: this.start, word: ''});
    },

    viewArticle(article) {
      this.$router.push({
        name: "BoardView",
        params: { articleno: article.articleno },
      });
    },

    pageClick: function (button, page){
			this.currentPage = page;
      this.getList();
			},
  },
};
</script>

<style scope>
.tdClass {
  width: 50px;
  text-align: center;
}
.tdSubject {
  width: 300px;
  text-align: left;
}
</style>
