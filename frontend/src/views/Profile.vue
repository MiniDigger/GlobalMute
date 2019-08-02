<template>
  <div>
    <b-card>
      Username: {{user.username}}<br>
      E-Mail: {{user.email}}<br>
      ID: {{user.id}}<br>
      Token: {{user.token}}
    </b-card>
  </div>
</template>

<script>
  import { mapState } from 'vuex';

  export default {
    name: 'Profile',
    computed: mapState(['user']),
  };
</script>

<style scoped>

</style>
