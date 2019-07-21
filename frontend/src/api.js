import axios from 'axios';

const SERVER_URL = 'http://localhost:8081/api/';

const instance = axios.create({
  baseURL: SERVER_URL,
  timeout: 1000,
});

export default {
  login: (username, password, completed, errorHandler) => instance.post('login', {
    username,
    password,
  }).then(response => completed(response.data)).catch(error => errorHandler(error)),
};
