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
    <b-col class="sm-4">
      <b-form-select
        size="sm"
        v-model="dongCode"
        :options="dongs"
        @change="setAddress"
      ></b-form-select>
    </b-col>
  </b-row>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";

const coronaStore = "coronaStore";

export default {
  name: "TownSearchBar",
  data() {
    return {
      sidoCode: null,
      gugunCode: null,
      dongCode: null,
    };
  },

  props: {
    type: { type: String },
  },

  computed: {
    ...mapState(coronaStore, ["sidos", "guguns", "dongs"]),
  },

  created() {
    this.CLEAR_SIDO_LIST();
    this.CLEAR_GUGUN_LIST();
    this.CLEAR_DONG_LIST();
    this.getSido();
    // this.getClinicList();
  },
  methods: {
    ...mapActions(coronaStore, ["getSido", "getGugun", "getDong"]),
    ...mapMutations(coronaStore, [
      "CLEAR_SIDO_LIST",
      "CLEAR_GUGUN_LIST",
      "CLEAR_DONG_LIST",
      "SET_ADDRESS",
    ]),

    gugunList() {
      this.CLEAR_GUGUN_LIST();
      this.gugunCode = null;
      if (this.sidoCode) this.getGugun(this.sidoCode);
    },

    dongList() {
      this.CLEAR_DONG_LIST();
      this.dongCode = null;
      if (this.gugunCode) {
        this.getDong(this.gugunCode);
      }
    },

    setAddress(){
      // 동까지 셋팅이 되면 카카오 맵 지도 이동시킬 주소 셋팅
      if(this.dongCode){
        this.SET_ADDRESS({sc:this.sidoCode, gc:this.gugunCode, dc:this.dongCode});
      }
    }
  },
};
</script>

<style></style>
