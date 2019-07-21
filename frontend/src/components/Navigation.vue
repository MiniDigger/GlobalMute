<template>
  <div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
      <b-navbar-brand href="#">GlobalMute</b-navbar-brand>

      <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

      <b-collapse id="nav-collapse" is-nav>
        <b-navbar-nav>
          <b-nav-item href="#">Link</b-nav-item>
          <b-nav-item href="#" disabled>Disabled</b-nav-item>
        </b-navbar-nav>

        <!-- Right aligned nav items -->
        <b-navbar-nav class="ml-auto">
          <b-nav-form>
            <b-form-input size="sm" class="mr-sm-2" placeholder="Search"></b-form-input>
            <b-button size="sm" class="my-2 my-sm-0" type="submit">Search</b-button>
          </b-nav-form>

          <b-nav-item-dropdown text="Lang" right>
            <b-dropdown-item href="#">EN</b-dropdown-item>
            <b-dropdown-item href="#">ES</b-dropdown-item>
            <b-dropdown-item href="#">RU</b-dropdown-item>
            <b-dropdown-item href="#">FA</b-dropdown-item>
          </b-nav-item-dropdown>

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
