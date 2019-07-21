import Vue from 'vue';
import Vuelidate from 'vuelidate';
import BootstrapVue from 'bootstrap-vue';

import './scss/build.scss';
import App from './App.vue';
import store from './store';
import router from './router';

Vue.config.productionTip = false;

Vue.use(Vuelidate);
Vue.use(BootstrapVue);

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app');
