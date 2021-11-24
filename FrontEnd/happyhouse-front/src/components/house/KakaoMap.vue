/* eslint-disable no-unused-vars */
<template>
  <div id="map_content">
    <div id="map" v-if="this.type != 'town'"></div>
    <div v-if="this.type == 'town'">
      차트영역 {{ martCount }}
    </div>
  </div>
</template>

<script>
import { mapActions, mapState } from "vuex";
const houseStore = "houseStore";
const coronaStore = "coronaStore";

export default {
  name: "KakaoMap",
  data() {
    return {
      map: null,
      markers: [],
      infowindow: null,
      geocoder: new kakao.maps.services.Geocoder(),
      ps: new kakao.maps.services.Places(),
      callbackMart: null,
      callbackSchool: null,
      callbackHospital: null,
      martCount: [],
      schoolCount: [],
      hospitalCount: [],
    };
  },

  computed: {
    ...mapState(houseStore, ["address", "houses", "house"]),
    ...mapState(coronaStore, ["coronaClinic", "address"]),
  },

  props: {
    type: { type: String },
  },

  mounted() {
    if (this.type != "town") {
      if (window.kakao && window.kakao.maps) {
        this.initMap();
      } else {
        const script = document.createElement("script");

        script.onload = () => kakao.maps.load(this.initMap);
        script.src =
          "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=efd45f08160884edeebfcd70783250b3";
        document.head.appendChild(script);
      }
    }

    if (this.type == "corona") {
      this.makeCoronaMarker();
    }
  },

  watch: {
    houses: "makeHouseMarker",
    house: "moveToHouse",
    address: "moveToDong",
  },

  created() {
    this.callbackMart = function (result, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        this.martCount.push(result.length);
      } else {
        this.martCount.push(0);
      }
    };

    this.callbackSchool = function (result, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        this.schoolCount.push(result.length);
      } else {
        this.schoolCount.push(0);
      }
    };

    this.callbackHospital = function (result, status, pagination) {
      if (status === kakao.maps.services.Status.OK) {
        this.hospitalCount.push(result.length);
      } else {
        this.hospitalCount.push(0);
      }
    };

    if (this.type == "corona") {
      this.getClinicList();
    }
  },

  methods: {
    ...mapActions(coronaStore, ["getClinicList"]),

    initMap() {
      const container = document.getElementById("map");
      const options = {
        center: new kakao.maps.LatLng(37.566669392386494, 126.97843525651021),
        level: 5,
      };
      this.map = new kakao.maps.Map(container, options);
    },

    displayMarker(markerPositions) {
      if (this.markers.length > 0) {
        this.markers.forEach((marker) => marker.setMap(null));
      }

      const positions = markerPositions.map(
        (position) => new kakao.maps.LatLng(...position)
      );

      if (positions.length > 0) {
        this.markers = positions.map(
          (position) =>
            new kakao.maps.Marker({
              map: this.map,
              position,
            })
        );

        const bounds = positions.reduce(
          (bounds, latlng) => bounds.extend(latlng),
          new kakao.maps.LatLngBounds()
        );

        this.map.setBounds(bounds);
      }
    },
    displayInfoWindow() {
      if (this.infowindow && this.infowindow.getMap()) {
        //이미 생성한 인포윈도우가 있기 때문에 지도 중심좌표를 인포윈도우 좌표로 이동시킨다.
        this.map.setCenter(this.infowindow.getPosition());
        return;
      }

      var iwContent = '<div style="padding:5px;">Hello World!</div>', // 인포윈도우에 표출될 내용으로 HTML 문자열이나 document element가 가능합니다
        iwPosition = new kakao.maps.LatLng(33.450701, 126.570667), //인포윈도우 표시 위치입니다
        iwRemoveable = true; // removeable 속성을 ture 로 설정하면 인포윈도우를 닫을 수 있는 x버튼이 표시됩니다

      this.infowindow = new kakao.maps.InfoWindow({
        map: this.map, // 인포윈도우가 표시될 지도
        position: iwPosition,
        content: iwContent,
        removable: iwRemoveable,
      });

      this.map.setCenter(iwPosition);
    },

    // ** addressSearch 의 콜백함수가 function() 으로 정의되어 있으면 안에서 this 가 undefined 됨
    // 그렇기 때문에, arrow function 으로 선언해주어야 함
    moveToHouse() {
      // selectbox 에서 선택된 시도 + 구군 기준으로 지도검색
      const address =
        this.address.sido +
        " " +
        this.address.gugun +
        " " +
        this.house.법정동 +
        " " +
        this.house.지번;

      this.geocoder.addressSearch(address, (result, status) => {
        // 정상적으로 검색이 완료됐으면
        if (status === kakao.maps.services.Status.OK) {
          var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
          // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
          this.map.setLevel(2);
          this.map.setCenter(coords);
        }
      });
    },

    makeHouseMarker() {
      this.removeMarker();
      let positions = [];
      this.houses.forEach((house) => {
        const address =
          this.address.sido +
          " " +
          this.address.gugun +
          " " +
          house.법정동 +
          " " +
          house.지번;
        this.geocoder.addressSearch(address, (result, status) => {
          // 정상적으로 검색이 완료됐으면
          let marker;
          if (status === kakao.maps.services.Status.OK) {
            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
            });

            positions.push(coords);
            this.markers.push(marker);
            this.infowindow = new kakao.maps.InfoWindow({
              content: `<div>${house.아파트}</div>`,
            });
          }

          // 주어진 영역이 모두 보이게 하기 위한 boundary 셋팅
          const bounds = positions.reduce(
            (bounds, latlng) => bounds.extend(latlng),
            new kakao.maps.LatLngBounds()
          );

          this.map.setBounds(bounds);

          kakao.maps.event.addListener(
            marker,
            "mouseover",
            this.makeOverListener(this.map, marker, this.infowindow)
          );
          kakao.maps.event.addListener(
            marker,
            "mouseout",
            this.makeOutListener(this.infowindow)
          );
        });
      });
    },

    // 인포윈도우를 표시하는 클로저를 만드는 함수입니다
    makeOverListener(map, marker, infowindow) {
      return function () {
        infowindow.open(map, marker);
      };
    },

    // 인포윈도우를 닫는 클로저를 만드는 함수입니다
    makeOutListener(infowindow) {
      return function () {
        infowindow.close();
      };
    },

    makeCoronaMarker() {
      this.removeMarker();
      this.coronaClinic.forEach((corona) => {
        const address = corona.주소;
        this.geocoder.addressSearch(address, (result, status) => {
          // 정상적으로 검색이 완료됐으면
          let marker;
          if (status === kakao.maps.services.Status.OK) {
            let coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            marker = new kakao.maps.Marker({
              map: this.map,
              position: coords,
            });

            this.markers.push(marker);
            this.infowindow = new kakao.maps.InfoWindow({
              content: `<div>${corona.시설명}</div>`,
            });

            kakao.maps.event.addListener(
              marker,
              "mouseover",
              this.makeOverListener(this.map, marker, this.infowindow)
            );
            kakao.maps.event.addListener(
              marker,
              "mouseout",
              this.makeOutListener(this.infowindow)
            );
          }
        });
      });
    },

    

    removeMarker() {
      this.markers.forEach((marker) => {
        marker.setMap(null);
      });
      this.markers = [];
    },

    moveToDong() {
      const moveAddress =
        this.address.sido + " " + this.address.gugun + " " + this.address.dong;
      if (this.type != "town") {
        this.geocoder.addressSearch(moveAddress, (result, status) => {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            // 지도의 중심을 결과값으로 받은 위치로 이동시킵니다
            this.map.setLevel(5);
            this.map.setCenter(coords);
          }
        });
      } else {
        this.geocoder.addressSearch(moveAddress, (result, status) => {
          // 정상적으로 검색이 완료됐으면
          if (status === kakao.maps.services.Status.OK) {
            var coords = new kakao.maps.LatLng(result[0].y, result[0].x);
            this.commercialArea(coords);
          }
        });
      }
    },

    // 비동기처리해서, house list 로 넘겨줘야함 => vuex 에 등록
    commercialArea(coords) {
      // 마트
      this.ps.categorySearch("MT1", this.callbackMart, {
        location: coords,
        radius: 500, // 반경 1km 이내 (m단위)
      });

      // 학교
      this.ps.categorySearch("SC4", this.callbackSchool, {
        location: coords,
        radius: 200, // 반경 1km 이내 (m단위)
      });

      // 병원
      this.ps.categorySearch("HP8", this.callbackHospital, {
        location: coords,
        radius: 200, // 반경 1km 이내 (m단위)
      });
    },
  },
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
#map_content {
  text-align: center;
  /* justify-content: center; */
}

#map {
  width: 1000px;
  height: 700px;
  display: inline-block;
}

.button-group {
  margin: 10px 0px;
}

button {
  margin: 0 3px;
}
</style>
