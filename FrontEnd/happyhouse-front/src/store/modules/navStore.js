const navStore = {
    namespaced: true,
    state: {
        foldHouseList: false,
    },

    mutations: {
        SET_FOLD_HOUSELIST(state, data) {
            state.foldHouseList = data;            
        }
    }
}

export default navStore;