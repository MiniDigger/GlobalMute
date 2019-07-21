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

  export default {
    components: { Navigation },
  };
</script>
