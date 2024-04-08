<template>
    <div class="login-form">
        <h2>Login</h2>
        <form @submit.prevent="login">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" v-model="username" required />
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" v-model="password" required />
            </div>
            <p v-if="loginError" class="error-message">Username and password is incorrect</p>
          <button type="submit" id="submit-login">Login</button>
        </form>
    </div>
</template>

<script>
import axios from "axios";
import { useTokenStore } from "../stores/token.js";
import router from "../router/index.js";

export default {
    setup() {
        const tokenStore = useTokenStore();
        return { tokenStore };
    },
    data() {
        return {
            username: "",
            password: "",
            loginStatus: "",
            loginError: false
        };
    },
    mounted() {
        // Use localStorage to load user input data
        const storedLoginUser = JSON.parse(sessionStorage.getItem("loginUser"));
        if (storedLoginUser) {
            this.username = storedLoginUser.username;
            this.password = storedLoginUser.password;
        }
    },
    watch: {
        // Watch for changes in username and password and update localStorage
        username(value) {
            sessionStorage.setItem(
                "loginUser",
                JSON.stringify({ username: value, password: this.password })
            );
        },
        password(value) {
            sessionStorage.setItem(
                "loginUser",
                JSON.stringify({ username: this.username, password: value })
            );
        }
    },
    methods: {
        async login() {
            await this.tokenStore.getTokenAndSaveInStore(this.username, this.password);
            if (this.tokenStore.loggedInUser) {
                this.loginStatus = "Login successful!";
                await router.push("/");
                console.log("Login successful!");
                sessionStorage.removeItem("loginUser");
            } else {
                this.loginStatus = "Login failed!";
                this.loginError = true;
            }
        }
    },
    beforeDestroy() {
        // Clear local storage when the component is destroyed
        sessionStorage.removeItem("loginUser");
    }
};

window.addEventListener("beforeunload", function (event) {
    // Remove item whenever page is refreshed
    sessionStorage.removeItem("loginUser");
});
</script>

<style scoped>
.login-form {
    width: 100%; /* Ensure the form takes up the available width */
    padding: 50px;
    border: 1px solid #ccc;
    border-radius: 5px;
    margin-top: 100px;
    margin-bottom: 100px;
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
    width: calc(100% - 5px); /* Adjust button width to account for border */
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

.error-message {
  color: red;
  margin-bottom: 10px;
}

#submit-login {
    width: 100%;
}

</style>
