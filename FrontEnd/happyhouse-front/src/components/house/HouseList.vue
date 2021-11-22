<template>
  <b-container class="bv-example-row" id="SideAptList">
    <h3>아파트 목록</h3>
    <h5 v-if="!houses || houses.length == 0">목록이 없습니다.</h5>
    <house-list-row
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
    <b-pagination v-if="houses && houses.length !=0"
        v-model="curPage"
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
import { mapActions, mapMutations, mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {
      perPage: 10,
      // currentPage: 1,
    };
  },
  computed: {
    ...mapState(houseStore, ["houses", "totalHouse", "gugunCode", "curPage"]),
    
    // houses() {
    //   return this.$store.state.houses;
    // },
  },

  methods: {
    ...mapActions(houseStore, ["getHouseList"]),    

    pageClick: function (button, page){
			this.currentPage = page;
      this.getHouseList({gugunCode : this.gugunCode, curPage: this.currentPage});  
      // 클릭했을 때, 최상단으로 이동하게 하기
			},
  },
};
</script>

<style>
#SideAptList {
  position: fixed;
  width: 400px;
  left: 160px;
  top: 0;
  bottom: 0;
  overflow: auto;
  background-color: #f4f0fd;
  z-index: 10;
  text-align: left;
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
