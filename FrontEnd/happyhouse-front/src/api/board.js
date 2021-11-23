import { apiInstance } from "./index.js";

const api = apiInstance();

function listArticle(param, success, fail) {
  console.log("param1", param);
  api.get(`/board`, { params: param }).then(success).catch(fail);
}

// function writeArticle(article, success, fail) {
//   api.post(`/board`, JSON.stringify(article)).then(success).catch(fail);
// }

function writeArticle(param, success, fail) {
  api.post(`/board`, param).then(success).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function getNext(articleno, success, fail) {
  api.get(`board/next/${articleno}`).then(success).catch(fail);
}

function getPrev(articleno, success, fail) {
  api.get(`board/prev/${articleno}`).then(success).catch(fail);
}

// function modifyArticle(article, success, fail) {
//   api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
// }

function modifyArticle(param, success, fail) {
  api.put(`/board`, param).then(success).catch(fail);
}

function deleteArticle(articleno, success, fail) {
  api.delete(`/board/${articleno}`).then(success).catch(fail);
}

function writeComment(param, success, fail) {
  api.post(`/board/comment`, param).then(success).catch(fail);
}

function listComment(articleno, success, fail) {
  api.get(`/board/comment/${articleno}`).then(success).catch(fail);
}

function modifyComment(param, success, fail) {
  api.put(`/board/comment`, param).then(success).catch(fail);
}

function deleteComment(commentid, success, fail) {
  api.delete(`/board/comment/${commentid}`).then(success).catch(fail);
}

function writeSubComment(param, success, fail) {
  api.post(`/board/comment/sub`, param).then(success).catch(fail);
}

function listSubComment(commentid, success, fail) {
  api.get(`/board/comment/sub/${commentid}`).then(success).catch(fail);
}

function deleteSubComment(sub_commentid, success, fail) {
  api.delete(`/board/comment/sub/${sub_commentid}`).then(success).catch(fail);
}

function getSubCommentCount(articleno, success, fail) {
  api.get(`/board/comment/count/${articleno}`).then(success).catch(fail);
}

export {
  listArticle, writeArticle, getArticle, modifyArticle, deleteArticle,
  writeComment, listComment, modifyComment, deleteComment, getPrev, getNext,
  writeSubComment, listSubComment, deleteSubComment, getSubCommentCount
};
