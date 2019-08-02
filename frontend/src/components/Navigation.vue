<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand to="/">GlobalMute</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item to="/">Link</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-item v-if="user === null || user.id === null" to="login">Login</b-nav-item>
          <b-nav-item-dropdown v-else right>
            <template slot="button-content"><em>{{user.username}}</em></template>
            <b-dropdown-item to="profile">Profile</b-dropdown-item>
            <b-dropdown-item @click="logout">Log Out</b-dropdown-item>
          </b-nav-item-dropdown>
        </b-navbar-nav>
      </b-collapse>
    </b-navbar>
  </div>
</template>

<script>
  import { mapState } from 'vuex';
  import cookies from '../cookies';
  import { SAVE_USER } from '../store';

  export default {
    name: 'Navigation',
    computed: mapState(['user']),
    methods: {
      logout() {
        this.$store.commit(SAVE_USER, null);
        cookies.clearToken();
      },
    },
  };
</script>

<style scoped>

</style>
