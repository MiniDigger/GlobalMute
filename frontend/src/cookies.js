import Vue from 'vue';
import VueCookies from 'vue-cookies';

Vue.use(VueCookies);

export default {
  setToken: token => VueCookies.set('TOKEN', token, '7d'),
  getToken: () => VueCookies.get('TOKEN'),
  clearToken: () => VueCookies.remove('TOKEN'),
};
