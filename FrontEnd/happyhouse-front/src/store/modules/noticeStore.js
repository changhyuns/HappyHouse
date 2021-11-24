import { listNotice } from "@/api/notice.js";
import { writeNotice } from "@/api/notice.js";
import { getNotice } from "@/api/notice.js";
import { modifyNotice } from "@/api/notice.js";
import { deleteNotice } from "@/api/notice.js";

import swal from "sweetalert";
import router from "../../router";

const noticeStore = {
  namespaced: true,
  state: {
    notice: {
      subject: '',
      content: '',
      userid: '',
    },
    noticeList: [],
    noticeDetail: null,
    searchWord: '',
    searchType: 'subject',
    perPage: 10,
    currentPage: 1,
    start: 0,
    noticeCount: 0,
  },
  getters: {
  },
  mutations: {
    SET_NOTICE_LIST(state, response) {
      state.noticeList = response;
    },
    SET_NOTICE_DETAIL(state, response) {
      state.noticeDetail = response;
    },

    SET_NOTICE_COUNT(state, response) {
      state.noticeCount = response;
    },
  },
  actions: {
    async getNoticeList({ commit }, param) {
      await listNotice(
        param,
        (response) => {
          commit("SET_NOTICE_COUNT", response.data.total);
          commit("SET_NOTICE_LIST", response.data.list);
        },
        (error) => {
          console.log("error : ", error);
        }
      );
    },
    getNoticeDetail({ commit }, noticeno) {
      getNotice(
        noticeno,
        (response) => {
          commit("SET_NOTICE_DETAIL", response.data);
        },
        (error) => {
          console.log("error : ", error);
        }
      );
    },
    registNotice({ commit }, param) {
      writeNotice(
        param,
        () => {
          swal({
            title: "SUCCESS",
            text: "공지글 작성 완료!",
            icon: "success",
            button: "확인",
          });
          router.push({ name: "NoticeList" });
        },
        (error) => {
          console.log(error);
        }
      );
    },
    modifyNotice({ commit }, data) {
      const params = {
        userid: data.userid,
        noticeno: data.noticeno,
        content: data.content,
        subject: data.subject
      };
      modifyNotice(
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
    deleteNotice({ commit }, noticeno) {
      deleteNotice(
        noticeno,
        (response) => {
          console.log(commit);
          console.log(response);
        },
        (error) => {
          console.log("error : ", error);
        }
      )
    },
  },
};

export default noticeStore;
