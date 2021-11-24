import { sidoList, gugunList, dongList } from "@/api/house.js";
import { getCoronaList } from "../../api/corona";

const coronaStore = {
    namespaced: true,
    state: {
        sidos: [{ value: null, text: "선택하세요" }],
        guguns: [{ value: null, text: "선택하세요" }],
        dongs: [{ value: null, text: "선택하세요" }],
        coronaAddress: { sido: null, gugun: null, dong: null },
        total: 0,
        gugunCode: null,
        curPage: 1,
        coronaClinic: [],
    },

    getters: {},

    mutations: {
        SET_SIDO_LIST: (state, sidos) => {
            sidos.forEach((sido) => {
                state.sidos.push({ value: sido.sidoCode, text: sido.sidoName });
            });
        },
        SET_GUGUN_LIST: (state, guguns) => {
            guguns.forEach((gugun) => {
                state.guguns.push({ value: gugun.gugunCode, text: gugun.gugunName });
            });
        },
        SET_DONG_LIST: (state, dongs) => {
            dongs.forEach((dong) => {
                state.dongs.push({ value: dong.dongCode, text: dong.dongName })
            });
        },
        CLEAR_SIDO_LIST: (state) => {
            state.sidos = [{ value: null, text: "선택하세요" }];
        },
        CLEAR_GUGUN_LIST: (state) => {
            state.guguns = [{ value: null, text: "선택하세요" }];
        },
        CLEAR_DONG_LIST: (state) => {
            state.dongs = [{ value: null, text: "선택하세요" }];
        },

        SET_ADDRESS: (state, data) => {
            state.coronaAddress = {}
            
            state.sidos.forEach(s => {
                if (s.value == data.sc) {
                    state.coronaAddress.sido = s.text;
                }
            });

            state.guguns.forEach(g => {
                if (g.value == data.gc) {
                    state.coronaAddress.gugun = g.text;
                }
            });

            state.dongs.forEach(d => {
                if (d.value == data.dc) {
                    state.coronaAddress.dong = d.text;
                }
            })
        },

        SET_TOTAL_COUNT: (state, count) => {
            state.total = count;
        },

        SET_GUGUN_CODE: (state, code) => {
            state.gugunCode = code;
        },

        SET_CUR_PAGE: (state, page) => {
            state.curPage = page;
        },

        SET_CORONA_LIST: (state, data) => {
            state.coronaClinic = data;
        },
    },

    actions: {
        getSido: ({ commit }) => {
            sidoList(
                ({ data }) => {
                    // console.log(data);
                    commit("SET_SIDO_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getGugun: ({ commit }, sidoCode) => {
            const params = {
                sido: sidoCode,
            };
            gugunList(
                params,
                ({ data }) => {
                    // console.log(commit, response);
                    commit("SET_GUGUN_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },
        getDong: ({ commit }, gugunCode) => {
            const params = {
                gugun: gugunCode,
            };
            dongList(
                params,
                ({ data }) => {
                    commit("SET_DONG_LIST", data);
                },
                (error) => {
                    console.log(error);
                }
            );
        },

        getClinicList: ({ commit }) => {
            getCoronaList(
                ({ data }) => {
                    commit("SET_CORONA_LIST", data.data);
                }, (error) => {
                    console.log(error);
                });
        },
    },
};

export default coronaStore;
