<template>
  <div>
    <b-container class="bv-example-row mt-3">
      <b-row>
      </b-row>
      <b-row class="mb-1">
        <b-col class="text-right">
          <button class="btn-write" style="border: none; color: #170B3B; font-weight:600" @click="moveWrite()"
            >글쓰기</button>
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
import { mapActions, mapState } from "vuex";
import moment from "moment";

const boardStore = "boardStore";

export default {
  name: "BoardList",

  data() {
    return {
      fields: [
        { key: "articleno", label: "글번호", tdClass: "tdClass" },
        { key: "subject", label: "제목", tdClass: "tdSubject"},
        { key: "userid", label: "작성자", tdClass: "tdUser" },
        { key: "regtime", label: "작성일", tdClass: "tdClass"},
        { key: "hit", label: "조회수", tdClass: "tdClass"},
      ],
      perPage: 10,
      currentPage: 1,
      start: 0,
    };
  },

  computed: {
    ...mapState(boardStore, ["boardList", "boardCount"]),
    changeDateFormat() {
      return moment(new Date(this.comment.regtime)).format(
        "MM월DD일 \xa0 hh:mm"
      );
    },
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
  width: 12%;
  text-align: center;
  cursor: pointer;
  background-color: white;
}
.tdSubject {
  width: 52%;
  text-align: center;
  cursor: pointer;
  background-color: white;
}

.tdUser {
  width: 12%;
  text-align: center;
  color: #170B3B;
  font-weight: 500;
  background-color: white;
}

.pagination {
  justify-content: center;
}

.btn-write{
  color: mediumpurple;
  cursor: pointer;
  border: none;
  background-color: transparent;
  margin: 20px 10px 20px 10px;
}

th {

  background-color: transparent;
  color: #170B3B;
  font-size: 20px;
  font-weight: bold;
}


</style>
