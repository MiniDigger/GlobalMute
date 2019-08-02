<template>
  <div id="app">
    <Navigation/>
    <b-container class="container-main">
      <router-view/>
    </b-container>
  </div>
</template>

<style lang="scss">
  .container-main {
    margin-top: 20px;
  }
</style>

<script>
  import Navigation from './components/Navigation.vue';
  import cookies from './cookies';
  import api from './api';
  import { SAVE_USER } from './store';

  export default {
    components: { Navigation },
    mounted() {
      const token = cookies.getToken();
      if (token !== null) {
        api.loginWithToken(token, (user) => {
          this.$store.commit(SAVE_USER, user);
          this.$router.push('/');
          cookies.setToken(user.token);
        }, (error) => {
          console.log(error);
        });
      }
    },
  };
</script>
