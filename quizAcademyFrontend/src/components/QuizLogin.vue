<template>
    <div class="login-form">
        <h2>Login</h2>
        <form @submit.prevent="login" @keypress.enter="login">
            <BaseInput id="username" v-model="username" label="Username" class="field" />
            <BaseInput id="password" v-model="password" label="Password" class="field" type="password" />
            <p v-if="loginError" class="error-message">Username and password is incorrect</p>
            <button type="submit" id="submit-login">Login</button>
        </form>
    </div>
</template>

<script>
import { useTokenStore } from "../stores/token.js";
import router from "../router/index.js";
import BaseInput from "@/components/BaseInput.vue";

export default {
    components: { BaseInput },
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
    methods: {
        async login() {
            await this.tokenStore.getTokenAndSaveInStore(this.username, this.password);
            if (this.tokenStore.loggedInUser) {
                this.loginStatus = "Login successful!";
                await router.push("/");
            } else {
                this.loginStatus = "Login failed!";
                this.loginError = true;
            }
        }
    },
};

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
  color: var(--wrong-answer-border-color);
  margin-bottom: 10px;
}

#submit-login {
    width: 100%;
}

</style>
