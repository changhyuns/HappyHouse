<template>
  <b-row class="mt-4 mb-4 text-center">
    <b-col class="sm-4">
      <b-form-select
        size="sm"
        v-model="sidoCode"
        :options="sidos"
        @change="gugunList"
      ></b-form-select>
    </b-col>
    <b-col class="sm-4">
      <b-form-select
        size="sm"
        v-model="gugunCode"
        :options="guguns"
        @change="dongList"
      ></b-form-select>
    </b-col>
    <!-- <b-col class="sm-4">
      <b-form-select
        size="sm"
        v-model="dongCode"
        :options="dongs"
      ></b-form-select>
    </b-col> -->
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const houseStore = "houseStore";

export default {
  name: "TownSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["sidos", "guguns"]),
    
  },
  created() {
    this.CLEAR_HOUSE_LIST();
    this.CLEAR_SIDO_LIST();
    this.getSido();
  },
  methods: {
    ...mapActions(houseStore, ["getSido", "getGugun", "getHouseList"]),
    ...mapMutations(houseStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_HOUSE_LIST",
    ]),
   
    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    dongList() {
      if (this.gugunCode){
          console.log("change");
      }
    },
  },
};
</script>

<style></style>
