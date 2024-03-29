<template>
  <div class="login-form">
    <h2>Sign up</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="text" id="username" v-model="username" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <button type="submit">Sign up</button>
    </form>
  </div>
</template>

<script>

export default {
  data() {
    return {
      username: '',
      password: ''
    };
  },
  mounted() {
    // Use localStorage to load user input data
    const storedSignUpUser = JSON.parse(localStorage.getItem('signUpUser'));
    if (storedSignUpUser) {
      this.username = storedSignUpUser.username;
      this.password = storedSignUpUser.password;
    }
  },
  watch: {
    // Watch for changes in username and password and update localStorage
    username(value) {
      localStorage.setItem('signUpUser', JSON.stringify({ username: value, password: this.password }));
    },
    password(value) {
      localStorage.setItem('signUpUser', JSON.stringify({ username: this.username, password: value }));
    }
  },
  methods: {
    login() {
      // Here you can implement your login logic
      // For simplicity, let's just log the username and password for now
      console.log('Username:', this.username);
      console.log('Password:', this.password);

      // Delete local storage after sign up
      localStorage.removeItem('signUpUser');

      // After successful login, you can redirect the user to another page
      // For example, using Vue Router: this.$router.push('/dashboard');
    }
  }
};
</script>

<style scoped>
.login-form {
  width: 100%; /* Ensure the form takes up the available width */
  padding: 50px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 20px;
  text-align: left; /* Align child elements to the left */
}



input[type="text"],
input[type="password"] {
  width: calc(100%); /* Adjust input width to account for margin-right */

  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}



button {
  width: calc(100% - 5px ); /* Adjust button width to account for border */
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

button:hover {
  background-color: #0056b3;
}
</style>
