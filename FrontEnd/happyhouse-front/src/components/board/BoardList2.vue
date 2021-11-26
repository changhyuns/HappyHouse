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
        { key: "articleno", label: "글번호", tdClass: "b-tdNum" },
        { key: "subject", label: "제목", tdClass: "b-tdSubject"},
        { key: "userid", label: "작성자", tdClass: "b-tdUser" },
        { key: "regtime", label: "작성일", tdClass: "b-tdClass"},
        { key: "hit", label: "조회수", tdClass: "b-tdClass"},
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

    moveMain() {
      this.$router.push({ name: "Main" });
    },

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
.b-tdClass {
  width: 10%;
  text-align: center;
  cursor: pointer;
  background-color: white;
}
.b-tdSubject {
  width: 60%;
  text-align: center;
  font-weight: bold;
  cursor: pointer;
  background-color: white;
}
.b-tdNum {
  width: 10%;
  text-align: center;
  font-size: 15px;
  cursor: pointer;
  background-color: white;
}
.b-tdUser {
  width: 10%;
  text-align: center;
  color: #170B3B;
  font-weight: 500;
  background-color: white;
}

.pagination {
  justify-content: center;
}

.btn-write{
  background-color: transparent;
}

th {
  background-color: transparent;
  color: #170B3B;
  font-size: 20px;
  font-weight: bold;
}


</style>
