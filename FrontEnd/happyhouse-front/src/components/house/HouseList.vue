<template>
  <b-container class="bv-example-row" id="SideAptList" href="#list">
    <h3 v-if="!houses || houses.length == 0">목록이 없습니다.</h3>
    <house-list-row
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
    <b-pagination
      v-if="houses && houses.length != 0"
      v-model="currentPage"
      :total-rows="totalHouse"
      :per-page="perPage"
      aria-controls="my-table"
      @page-click="pageClick"
    >
    </b-pagination>
  </b-container>
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapActions, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {
      perPage: 10,
      currentPage: 1,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "totalHouse", "gugunCode", "curPage"]),
  },

  methods: {
    ...mapActions(houseStore, ["getHouseList"]),

    pageClick: function (button, page) {
      this.currentPage = page;
      this.getHouseList({
        gugunCode: this.gugunCode,
        curPage: this.currentPage,
      });

      // 페이지 클릭했을 때, 스크롤 최상단으로 이동
      document.getElementById("SideAptList").scrollTop = 0;
    },
  },
};
</script>

<style>
#SideAptList {
  position: fixed;
  width: 450px;
  height: 700px;
  left: 180px;
  top: 160px;
  bottom: 0;
  overflow: auto;
  z-index: 10;
  text-align: left;
}

#SideAptList::-webkit-scrollbar {
  width: 10px;
}
#SideAptList::-webkit-scrollbar-thumb {
  background-color: #2f3542;
  border-radius: 10px;
  background-clip: padding-box;
  border: 2px solid transparent;
}
#SideAptList::-webkit-scrollbar-track {
  background-color: grey;
  border-radius: 10px;
  box-shadow: inset 0px 0px 5px white;
}

#SideAptList h3 {
  text-align: center;
  margin: 30px;
}

#SideAptList h5 {
  text-align: center;
  margin: 30px;
  font-weight: 400;
}
</style>
