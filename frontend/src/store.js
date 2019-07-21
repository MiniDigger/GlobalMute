import Vuex from 'vuex';
import Vue from 'vue';

export const SAVE_USER = 'saveUser';

Vue.use(Vuex);

const store = new Vuex.Store({
  state: {
    user: {
      id: null,
      username: null,
      email: null,
      token: null,
    },
  },
  mutations: {
    [SAVE_USER](state, user) {
      state.user = user;
    },
  },
});

export default store;
