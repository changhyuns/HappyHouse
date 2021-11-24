<template>
  <b-container class="bv-example-row mt-3">
    <b-row class="mb-1">
      <b-col class="text-right" >
        <button class="btn-write" v-if="checkAdmin()" style="border: none; color: #170B3B; font-weight:600" @click="moveWrite()"
        >글쓰기</button>
      </b-col>
    </b-row>
    <b-row>
        <b-col>
          <b-table
            striped
            hover
            :items="noticeList"
            :fields="fields"
            :current-page="currentPage"
            @row-clicked="viewNotice"
          >
          </b-table>
        </b-col>
      </b-row>
  </b-container>
</template>

<script>
import { mapActions, mapState } from "vuex";
import moment from "moment";

const noticeStore = "noticeStore";
const memberStore = "memberStore";

export default {
  name: "NoticeList",
  data() {
    return {
      fields: [
        { key: "noticeno", label: "글번호", tdClass: "b-tdNum" },
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
    ...mapState(noticeStore, ["noticeList", "noticeCount"]),
    ...mapState(memberStore, ["userInfo"]),
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
    ...mapActions(noticeStore, ["getNoticeList"]),

    moveWrite() {
      this.$router.push({ name: "NoticeWrite" });
    },

    getList() {
      this.getNoticeList({key: 'subject', pg: this.currentPage, spp: this.perPage, start: this.start, word: ''},
      (response) => {console.log("ghgggggggggg", response)});
    },

    viewNotice(notice) {
      this.$router.push({
        name: "NoticeView",
        params: { noticeno: notice.noticeno },
      });
    },

    pageClick: function (button, page){
			this.currentPage = page;
      this.getList();
		},

    checkAdmin() {
      return this.userInfo.userid === 'admin';
    }
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
.btn-write{
  color: mediumpurple;
  cursor: pointer;
  border: none;
  background-color: transparent;
  margin: 20px 10px 20px 10px;
}
</style>
