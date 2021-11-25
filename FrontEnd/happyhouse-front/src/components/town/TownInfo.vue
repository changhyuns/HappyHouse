<script>
import { Bar } from "vue-chartjs";
import { mapMutations, mapState } from "vuex";

const mapStore = "mapStore";

export default {
  extends: Bar,
  data() {
    return {
      datacollection: {
        //Data to be represented on x-axis
        labels: ["학교", "대형마트", "지하철", "공공기관", "주유소,충전소"],
        datasets: [
          {
            label: "동네 정보",
            backgroundColor: ["#6886C5", "#8ADBDF", "#FECCBE", "#DDFFBC", "#FFD581" ],
            pointBackgroundColor: "white",
            borderWidth: 1,
            pointBorderColor: "#249EBF",
            //Data to be represented on y-axis
            data: [0, 0, 0, 0, 0],
          },
        ],
      },
      //Chart.js options that controls the appearance of the chart
      options: {
        scales: {
          yAxes: [
            {
              ticks: {
                beginAtZero: true,
              },
              gridLines: {
                display: true,
              },
            },
          ],
          xAxes: [
            {
              gridLines: {
                display: false,
              },
            },
          ],
        },
        legend: {
          display: true,
        },
        responsive: true,
        maintainAspectRatio: false,
      },
    };
  },

  created() {
    this.CLEAR_ALL();
  },

  computed: {
    ...mapState(mapStore, ["schoolCount", "martCount", "oilCount", "metroCount", "publicCount", "flag"]),
  },

  watch: {
    flag: "reRenderChart",
  },

  mounted() {
    //renderChart function renders the chart with the datacollection and options object.
    this.renderChart(this.datacollection, this.options);
  },

  methods: {
    ...mapMutations(mapStore, ["CLEAR_ALL"]),

    reRenderChart() {
      setTimeout(() => {
        this.datacollection.datasets[0].data = [this.schoolCount, this.martCount, this.metroCount, this.publicCount, this.oilCount];
        this.renderChart(this.datacollection, this.options);
      }, 100);
    },
  },
};
</script>
