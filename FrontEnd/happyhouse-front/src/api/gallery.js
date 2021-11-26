import { apiInstance } from "./index.js";

const api = apiInstance();

function uploadGallery(param, success, fail) {
  api.post(`/gallery`, param).then(success).catch(fail);
}

function getGalleryArticle(articleno, success, fail) {
  api.get(`/gallery/article/${articleno}`).then(success).catch(fail);
}

function getGalleryNotice(noticeno, success, fail) {
  api.get(`/gallery/notice/${noticeno}`).then(success).catch(fail);
}

export { uploadGallery, getGalleryArticle, getGalleryNotice };
