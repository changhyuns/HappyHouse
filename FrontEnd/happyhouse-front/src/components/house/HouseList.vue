<template>
  <b-container
    class="bv-example-row"
    id="SideAptList"
  >
    <h3>아파트 목록</h3>
    <h5 v-if="!houses || houses.length == 0">목록이 없습니다.</h5>
    <house-list-row
      v-for="(house, index) in houses"
      :key="index"
      :house="house"
    />
  </b-container>
  <!-- <b-container v-else class="bv-example-row mt-3">
    <b-row>
      <b-col><b-alert show>주택 목록이 없습니다.</b-alert></b-col>
    </b-row>
  </b-container> -->
</template>

<script>
import HouseListRow from "@/components/house/HouseListRow.vue";
import { mapState } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseList",
  components: {
    HouseListRow,
  },
  data() {
    return {};
  },
  computed: {
    ...mapState(houseStore, ["houses"]),
    // houses() {
    //   return this.$store.state.houses;
    // },
  },
};
</script>

<style>
#SideAptList {
  position: fixed;
  width: 300px;
  left: 130px;
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
