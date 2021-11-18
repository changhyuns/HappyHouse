import { listArticle } from "@/api/board.js";
import { writeArticle } from "@/api/board.js";
import { getArticle } from "@/api/board.js";
import { modifyArticle } from "@/api/board.js";
import { deleteArticle } from "@/api/board.js";


const boardStore = {
  namespaced: true,
  state: {
    boardList: [],
    boardDetail: null,
    searchWord: '',
    searchType: 'subject',
    perPage: 10,
    currentPage: 1,
    start: 0,
  },
  getters: {
    getBoardListState(state) {
      return state.boardList;
    }
  },
  mutations: {
    SET_BOARD_LIST(state, response) {
      this.state.boardList = response;
    },
    SET_BOARD_DETAIL(state, response) {
      this.state.boardDetail = response;
    },
  },
  actions: {
    async getBoardList({ commit }, param) {
      // const params = {
      //   key: this.state.key,
      //   pg: this.state.currentPage,
      //   spp: this.state.perPage,
      //   start: this.state.start,
      //   word: this.state.searchWord,
      // };
      await listArticle(
        param,
        (response) => {
          console.log("listArticle GET response : ", response.data);
          commit("SET_BOARD_LIST", response.data);
          console.log(this.state.boardList);
        },
        (error) => {
          console.log("error : ", error);
        }
      );
    },
    getBoardDetail({ commit }, articleno) {
      getArticle(
        articleno,
        (response) => {
          console.log("getArticle GET response : ", response.data);
          commit("SET_BOARD_DETAIL", response.data);
        },
        (error) => {
          console.log("error : ", error);
        }
      );
    },
    registBoard({ commit }, data) {
      const params = {
        userid: data.userid,
        content: data.content,
        subject: data.subject
      };
      writeArticle(
        params,
        (response) => {
          console.log(commit);
          console.log(response);
        },
        (error) => {
          console.log("error : ", error);
        }
      );
    },
    modifyBoard({ commit }, data) {
      const params = {
        userid: data.userid,
        articleno: data.articleno,
        content: data.content,
        subject: data.subject
      };
      modifyArticle(
        params,
        (response) => {
          console.log(commit);
          console.log(response);
        },
        (error) => {
          console.log("error : ", error);
        }
      )
    },
    deleteBoard({ commit }, articleno) {
      deleteArticle(
        articleno,
        (response) => {
          console.log(commit);
          console.log(response);
        },
        (error) => {
          console.log("error : ", error);
        }
      )
    }

    // deleteQna({ commit }, articleno) {
    //   http.delete(`/board/${articleno}`).then(response => {
    //     console.log(response);
    //   }).catch(error => {
    //     console.log(error);
    //   })
    // }
    // modifyQna({ commit }, data) {
    //   http.put(`/board`, {
    //     userid: data.userid, articleno: data.articleno, content: data.content, subject: data.subject
    //   }).then(response => {
    //   }).catch(error => {
    //     console.log(error);
    //   })
    // },

    // registQna({ commit }, data) {
    //   http.post(`/board`, {
    //     userid: data.userid,
    //     content: data.content,
    //     subject: data.subject,
    //   }).then(response => {
    //   }).catch(error => {
    //     console.log(error);
    //   })
    // },

    // getQnaList({ commit }) {
    //   http.get(`/board`, {
    //     params: {
    //       key: this.state.searchType,
    //       word: this.state.searchWord,
    //     },
    //   }).then(response => {
    //     console.log(response);
    //     commit("SET_QNA_LIST", response.data)
    //   }).catch(error => {
    //     console.log(error);
    //   })
    // },

    // getQnaDetail({ commit }, articleno) {
    //   http.get(`/board/${articleno}`).then(response => {
    //     commit("SET_QNA_DETAIL", response.data);
    //   }).catch(error => {
    //     console.log(error);
    //   })
    // },



  },
};

export default boardStore;
