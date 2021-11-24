import { listArticle } from "@/api/board.js";
import { writeArticle } from "@/api/board.js";
import { getArticle } from "@/api/board.js";
import { modifyArticle } from "@/api/board.js";
import { deleteArticle } from "@/api/board.js";
import { listComment } from "@/api/board.js";

import router from "../../router";
import moment from "moment";

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
    commentList: [],
    commentCount: 0,
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

    SET_COMMENT_LIST(state, response) {
      state.commentList = response;
    },

    SET_COMMENT_COUNT(state, response) {
      state.commetCount = response;
    }
  },
  actions: {
    async getBoardList({ commit }, param) {
      await listArticle(
        param,
        (response) => {
          console.log("listArticle GET response : ", response.data);
          for (let i = 0; i < response.data.list.length; i++) {
            response.data.list[i].regtime = moment(new Date(response.data.list[i].regtime)).format("MM.DD");
          }
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
    },

    async getCommentList({ commit }, param) {
      await listComment(
        param,
        (response) => {
          console.log("listComment GET response : ", response.data);
          commit("SET_COMMENT_COUNT", response.data.total);
          commit("SET_COMMENT_LIST", response.data.list);
          // console.log(this.state.boardList);
        },
        (error) => {
          console.log("error : ", error);
        }
      );
    },

  },
};

export default boardStore;
