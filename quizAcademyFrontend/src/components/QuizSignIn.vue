<template>
  <div class="login-form">
    <h2>Sign up</h2>
    <form @submit.prevent="login">
      <div class="form-group">
        <div class="first-name-lastname">
        <div class="half-width">
          <label for="first_name">First Name:</label>
          <input type="text" id="first_name" v-model="firstName" required>
        </div>
        <div class="half-width">
          <label for="last_name">Last Name:</label>
          <input type="text" id="last_name" v-model="lastName" required>
        </div>
        </div>
      </div>
      <div class="form-group">
        <label for="username">Username:</label>
        <input type="username" id="username" v-model="username" required>
      </div>
      <div class="form-group">
        <label for="email">Email:</label>
        <input type="email" id="email" v-model="email" required>
      </div>
      <div class="form-group">
        <label for="password">Password:</label>
        <input type="password" id="password" v-model="password" required>
      </div>
      <div class="form-group">
        <label for="confirm_password">Confirm Password:</label>
        <input type="password" id="repeatPassword" v-model="repeatPassword" required>
        <div v-if="!isPasswordValid" class="password-mismatch">Passwords do not match</div>
      <button type="submit" :disabled="isFormIncomplete || !isPasswordValid" :class="{ 'disabled': isFormIncomplete || !isPasswordValid }">Sign up</button>
      </div>
    </form>
    <div>
      <p>Already have an account? <router-link to="/login">Log in</router-link></p>
    </div>
  </div>
</template>

<script>

export default {
  data() {
    return {
      firstName: '',
      lastName: '',
      email: '',
      username: '',
      password: '',
      repeatPassword: ''
    };
  },
  computed: {
    isFormIncomplete() {
      return !(this.firstName && this.lastName && this.email && this.username && this.password && this.repeatPassword);
    },
    isPasswordValid() {
      return (this.password === this.repeatPassword);
    }
  },
  mounted() {
    // Use localStorage to load user input data
    const storedSignUpUser = JSON.parse(sessionStorage.getItem('signUpUser'));
    if (storedSignUpUser) {
      this.firstName = storedSignUpUser.firstName;
      this.lastName = storedSignUpUser.lastName;
      this.email = storedSignUpUser.email;
      this.username = storedSignUpUser.username;
      this.password = storedSignUpUser.password;
      this.repeatPassword = storedSignUpUser.repeatPassword;
    }
  },
  watch: {
    // Watch for changes in username and password and update localStorage
    firstName(value) {
      sessionStorage.setItem('signUpUser', JSON.stringify({ firstName: value, lastName: this.lastName, email: this.email, username: this.username, password: this.password, repeatPassword: this.repeatPassword }));
    },
    lastName(value) {
      sessionStorage.setItem('signUpUser', JSON.stringify({ firstName: this.firstName, lastName: value, email: this.email, username: this.username, password: this.password, repeatPassword: this.repeatPassword }));
    },
    email(value) {
      sessionStorage.setItem('signUpUser', JSON.stringify({ firstName: this.firstName, lastName: this.lastName, email: value, username: this.username, password: this.password, repeatPassword: this.repeatPassword }));
    },
    username(value) {
      sessionStorage.setItem('signUpUser', JSON.stringify({ firstName: this.firstName, lastName: this.lastName, email: this.email, username: value, password: this.password, repeatPassword: this.repeatPassword }));
    },
    password(value) {
      sessionStorage.setItem('signUpUser', JSON.stringify({ firstName: this.firstName, lastName: this.lastName, email: this.email, username: this.username, password: value, repeatPassword: this.repeatPassword }));
    },
    repeatPassword(value) {
      sessionStorage.setItem('signUpUser', JSON.stringify({ firstName: this.firstName, lastName: this.lastName, email: this.email, username: this.username, password: this.password, repeatPassword: value }));
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
  },
  beforeDestroy() {
    // Clear local storage when the component is destroyed
    localStorage.removeItem('signUpUser');
  },
};

window.addEventListener('beforeunload', function(event) {
  // Remove item whenever page is refreshed
  localStorage.removeItem('signUpUser');
});

</script>

<style scoped>
.login-form {
  width: 50%;
  padding: 50px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.form-group {
  margin-bottom: 20px;
  text-align: left;
}

.half-width {
  margin-right: 50px;
  display: table-cell
}

input[type="text"] {
  width: 85%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

input[type="username"],
input[type="password"],
input[type="email"] {
  width: 100%;
  padding: 8px;
  border: 1px solid #ccc;
  border-radius: 3px;
}

button {
  width: 100%;
  padding: 10px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 3px;
  cursor: pointer;
}

first-name-lastname{
  display: table;
}

button.disabled {
  cursor: not-allowed;
  background-color: #ccc;
}

.password-mismatch {
  color: red;
}



button:hover {
  background-color: #0056b3;
}
</style>