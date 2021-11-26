import { apiInstance } from "./index.js";

const api = apiInstance();

function listNotice(param, success, fail) {
  api.get(`/notice`, { params: param }).then(success).catch(fail);
}

function writeNotice(param, success, fail) {
  api.post(`/notice`, param).then(success).catch(fail);
}

function getNotice(noticeno, success, fail) {
  api.get(`/notice/${noticeno}`).then(success).catch(fail);
}

function getNext(noticeno, success, fail) {
  api.get(`notice/next/${noticeno}`).then(success).catch(fail);
}

function getPrev(noticeno, success, fail) {
  api.get(`notice/prev/${noticeno}`).then(success).catch(fail);
}

function modifyNotice(param, success, fail) {
  api.put(`/notice`, param).then(success).catch(fail);
}

function deleteNotice(noticeno, success, fail) {
  api.delete(`/notice/${noticeno}`).then(success).catch(fail);
}

function getFile(noticeno, success, fail) {
  api.get(`notice/gallery/${noticeno}`).then(success).catch(fail);
}

export {
  listNotice, writeNotice, getNotice, modifyNotice, deleteNotice, getPrev, getNext, getFile
};
