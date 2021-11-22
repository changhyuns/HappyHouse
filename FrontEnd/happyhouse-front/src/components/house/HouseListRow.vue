<template>
  <div class="house_list">
    <b-row>
      <b-card
        :header="house.아파트"
        header-text-variant="white"
        header-tag="header"
        header-bg-variant="dark"
        style="width: 320px"
        @click="selectHouse()"
      >
        <b-card-text>
          일련번호 : {{ house && house.일련번호 }} <br />
          법정동 : {{ house && house.법정동 }} <br />
          층수 : {{ house && house.층 }} <br />
          거래금액 :
          {{
            house &&
            (parseInt(house.거래금액.replace(",", "")) * 10000) | price
          }}원 <br />
        </b-card-text>
      </b-card>
    </b-row>
    <br>
  </div>
</template>

<script>
import { mapActions } from "vuex";

const houseStore = "houseStore";

export default {
  name: "HouseListRow",
  data() {
    return {
      isColor: false,
    };
  },
  props: {
    house: Object,
  },
  methods: {
    ...mapActions(houseStore, ["detailHouse"]),
    selectHouse() {
      // console.log("listRow : ", this.house);
      // this.$store.dispatch("getHouse", this.house);
      this.detailHouse(this.house);
    },
    colorChange(flag) {
      this.isColor = flag;
    },
  },

  filters: {
    price(value) {
      if (!value) return value;
      return value.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
    },
  },
};
</script>

<style scoped>
.apt {
  width: 50px;
}
.mouse-over-bgcolor {
  background-color: lightblue;
}

.house_list {
  margin-left: 30px;
}
</style>
