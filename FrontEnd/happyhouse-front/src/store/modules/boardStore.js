import { listArticle } from "@/api/board.js";
import { writeArticle } from "@/api/board.js";
import { getArticle } from "@/api/board.js";
import { modifyArticle } from "@/api/board.js";
import { deleteArticle } from "@/api/board.js";
import router from "../../router";


const boardStore = {
  namespaced: true,
  state: {
    board: {
      subject: '',
      content: '',
      userid: '',
    },
    boardList: [],
    boardDetail: null,
    searchWord: '',
    searchType: 'subject',
    perPage: 10,
    currentPage: 1,
    start: 0,
    boardCount: 0,
  },
  getters: {
    getBoardListState(state) {
      return state.boardList;
    }
  },
  mutations: {
    SET_BOARD_LIST(state, response) {
      state.boardList = response;
      console.log(state.boardList);
    },
    SET_BOARD_DETAIL(state, response) {
      state.boardDetail = response;
    },

    SET_BOARD_COUNT(state, response) {
      state.boardCount = response;
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
          commit("SET_BOARD_COUNT", response.data.total);
          commit("SET_BOARD_LIST", response.data.list);
          // console.log(this.state.boardList);
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
    registBoard({ commit }, param) {
      console.log("ddddd", param);
      writeArticle(
        param,
        (response) => {
          console.log(commit, response);
          alert('게시글을 작성하였습니다.');
          router.push({ name: "BoardList2" });
        },
        (error) => {
          console.log(error);
          alert('게시글 작성에 실패하였습니다');
        }
      );
    },
    modifyBoard({ commit }, data) {
      console.log(data);
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
