<template>
  <div>
    <b-card>
      <b-form-group
              label="Username"
              label-for="username"
              :state="state($v.username)"
      >
        <b-form-input id="username" v-model="username" :state="state($v.username)"
                      trim @blur="$v.username.$touch()"/>
      </b-form-group>

      <b-form-group
              label="Password"
              label-for="password"
              :state="state($v.password)"
      >
        <b-form-input id="password" v-model="password" :state="state($v.password)"
                      trim @blur="$v.password.$touch()" type="password"/>
      </b-form-group>

      <b-button type="submit" variant="primary" @click="submit">Submit</b-button>
    </b-card>
  </div>
</template>

<script>
  import { required } from 'vuelidate/lib/validators';
  import api from '../api';
  import cookies from '../cookies';
  import { SAVE_USER } from '../store';

  export default {
    name: 'Login',
    data() {
      return {
        username: '',
        password: '',
      };
    },
    methods: {
      state(validation) {
        if (validation.$dirty) {
          return validation.$error ? 'invalid' : 'valid';
        }
        return null;
      },
      submit() {
        api.login(this.username, this.password, (user) => {
          this.$store.commit(SAVE_USER, user);
          this.$router.push('/');
          cookies.setToken(user.token);
        }, (error) => {
          console.log(error);
          cookies.clearToken();
        });
      },
    },
    validations: {
      username: {
        required,
      },
      password: {
        required,
      },
    },
  };
</script>

<style scoped>

</style>
