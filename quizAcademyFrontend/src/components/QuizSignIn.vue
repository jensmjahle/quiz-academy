<template>
    <div class="signUp-form">
        <h2>Sign up</h2>
        <form @submit.prevent="signup">
            <BaseInput
                id="first_name"
                v-model="firstName"
                label="First Name"
                class="field"
            />
            <BaseInput
                id="last_name"
                v-model="lastName"
                label="Last Name"
                class="field"
            />
            <BaseInput
                id="username"
                v-model="username"
                label="Username"
                class="field"
            />
            <BaseInput
                id="email"
                v-model="email"
                label="Email"
                class="field"
            />
            <BaseInput
                id="password"
                v-model="password"
                label="Password"
                class="field"
                type="password"
            />
            <BaseInput
                id="repeatPassword"
                v-model="repeatPassword"
                label="Confirm Password"
                class="field"
                type="password"
            />

            <div v-if="!isPasswordValid" class="password-mismatch">Passwords do not match</div>
            <div v-if="userNameExists" class="password-mismatch">Username already exists</div>
            <button
                type="submit"
                :disabled="isFormIncomplete || !isPasswordValid"
                :class="{ disabled: isFormIncomplete || !isPasswordValid }"
            >
                Sign up
            </button>

        </form>
        <div>
            <p>Already have an account? <router-link to="/login">Log in</router-link></p>
        </div>
    </div>
</template>

<script>
import {signUpUser} from "../utils/httputils.js";
import router from "../router/index.js";
import { useTokenStore } from "../stores/token.js";
import BaseInput from "@/components/BaseInput.vue";

export default {
    components: { BaseInput },

  setup() {
    const tokenStore = useTokenStore();
    return { tokenStore };
  },
    data() {
        return {
            firstName: "",
            lastName: "",
            email: "",
            username: "",
            password: "",
            repeatPassword: "",
            userNameExists: false
        };
    },
    computed: {
        isFormIncomplete() {
            return !(
                this.firstName &&
                this.lastName &&
                this.email &&
                this.username &&
                this.password &&
                this.repeatPassword
            );
        },
        isPasswordValid() {
            return this.password === this.repeatPassword;
        }
    },
    mounted() {
        // Use localStorage to load user input data
        const storedSignUpUser = JSON.parse(sessionStorage.getItem("signUpUser"));
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
            sessionStorage.setItem(
                "signUpUser",
                JSON.stringify({
                    firstName: value,
                    lastName: this.lastName,
                    email: this.email,
                    username: this.username,
                    password: this.password,
                    repeatPassword: this.repeatPassword
                })
            );
        },
        lastName(value) {
            sessionStorage.setItem(
                "signUpUser",
                JSON.stringify({
                    firstName: this.firstName,
                    lastName: value,
                    email: this.email,
                    username: this.username,
                    password: this.password,
                    repeatPassword: this.repeatPassword
                })
            );
        },
        email(value) {
            sessionStorage.setItem(
                "signUpUser",
                JSON.stringify({
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: value,
                    username: this.username,
                    password: this.password,
                    repeatPassword: this.repeatPassword
                })
            );
        },
        username(value) {
            sessionStorage.setItem(
                "signUpUser",
                JSON.stringify({
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    username: value,
                    password: this.password,
                    repeatPassword: this.repeatPassword
                })
            );
        },
        password(value) {
            sessionStorage.setItem(
                "signUpUser",
                JSON.stringify({
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    username: this.username,
                    password: value,
                    repeatPassword: this.repeatPassword
                })
            );
        },
        repeatPassword(value) {
            sessionStorage.setItem(
                "signUpUser",
                JSON.stringify({
                    firstName: this.firstName,
                    lastName: this.lastName,
                    email: this.email,
                    username: this.username,
                    password: this.password,
                    repeatPassword: value
                })
            );
        }
    },
    methods: {
      async signup() {
        try {
          const response = await signUpUser(
              this.firstName,
              this.lastName,
              this.username,
              this.password,
              this.email
          );
          if (response.status === 201) {
            await this.tokenStore.getTokenAndSaveInStore(this.username, this.password);
            if (this.tokenStore.jwtToken) {
              this.loginStatus = "Login successful!";
              await router.push("/");
              sessionStorage.removeItem("signUpUser");
            } else {
              this.loginStatus = "Login failed!";
            }
          }
        } catch (error) {
          console.error(error)
          if (error.response && error.response.status === 409) {
            this.userNameExists = true;
          }
        }
      }
    },
    beforeDestroy() {
        // Clear local storage when the component is destroyed
        localStorage.removeItem("signUpUser");
    }
};

window.addEventListener("beforeunload", function (event) {
    // Remove item whenever page is refreshed
    localStorage.removeItem("signUpUser");
});
</script>

<style scoped>
.signUp-form {
    min-width: 50vw;
    max-width: 100vw;
    padding: 50px;
    border: 1px solid #ccc;
    border-radius: 5px;

}

.form-group {
    margin-bottom: 20px;
    text-align: left;
    overflow-x: unset;
}

.half-width {
    margin-right: 50px;
    display: table-cell;
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

first-name-lastname {
    display: table;
}

button.disabled {
    cursor: not-allowed;
    background-color: #ccc;
}
button.disabled:hover {
    background-color: #ccc;
}

.password-mismatch {
    color: var(--wrong-answer-border-color);
}

button:hover {
    background-color: #0056b3;
}
</style>
