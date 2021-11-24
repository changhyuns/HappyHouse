import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

function registUser(param, success, fail) {
  api.post(`/user/regist`, param).then(success).catch(fail);
}

function updateUser(param, success, fail) {
  api.put(`/user`, param).then(success).catch(fail);
}

function deleteUser(userid, success, fail) {
  api.delete(`/user/${userid}`).then(success).catch(fail);
}

function getUserProfile(userid, success, fail) {
  api.get(`/user/profile/${userid}`).then(success).catch(fail);
}

export { login, findById, registUser, updateUser, deleteUser, getUserProfile };
