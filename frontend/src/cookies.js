import Vue from 'vue';
import VueCookies from 'vue-cookies';

Vue.use(VueCookies);

export default {
  // TODO properly secure the cookies
  setToken: token => VueCookies.set('TOKEN', token, '7d', '/', true),
  getToken: () => VueCookies.get('TOKEN'),
  clearToken: () => VueCookies.remove('TOKEN'),
};
