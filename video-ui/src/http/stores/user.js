import { defineStore } from "pinia";

const state = {
  user: {}, //用户信息
  permissions: [], //用户权限
  roles: [], //用户角色
  token: void 0, //令牌
}
export default defineStore('user', {
  persist: true,
  state () {
    return state;
  },
})
