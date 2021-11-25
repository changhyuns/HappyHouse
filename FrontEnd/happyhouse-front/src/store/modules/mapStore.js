const mapStore = {
    namespaced: true,
    state: {
        schoolCount: 0,
        martCount: 0,
        oilCount: 0,
        metroCount: 0,
        publicCount: 0,
        flag : 1,
    },

    mutations: {
        SET_SCHOOL_COUNT: (state, count) => {
            state.schoolCount = count;
        },

        SET_MART_COUNT: (state, count) => {
            state.martCount = count;
        },

        SET_OIL_COUNT: (state, count) => {
            state.oilCount = count;
        },

        SET_METRO_COUNT: (state, count) => {
            state.metroCount = count;
        },

        SET_PUBLIC_COUNT: (state, count) => {
            state.publicCount = count;
        },

        CLEAR_ALL: (state) => {
            state.schoolCount = 0;
            state.martCount = 0;
            state.oilCount = 0;
            state.metroCount = 0;
            state.publicCount = 0;
        },

        SET_FLAG: (state, data) => {
            state.flag = data;
        },
    }
};

export default mapStore;