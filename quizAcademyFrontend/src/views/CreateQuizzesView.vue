<script lang="ts">
import { useTokenStore } from "../stores/token.js";
import {getUserInfo} from "../utils/httputils.js"
import CreateQuiz from "../components/CreateQuiz.vue";
import sadImage from "../assets/sad.svg";

export default {
    components: { CreateQuiz },
    setup() {
        const tokenStore = useTokenStore();
        return { tokenStore };
    },
    async mounted() {
        if (!this.tokenStore.jwtToken) {
        } else {
            let response = this.tokenStore.loggedInUser;
            this.user = response.data;
        }
    },
    data() {
        return {
            user: null,
            sadImage: sadImage
        }
    }
};
</script>

<template>
  <div>
    <div v-if="user" id="mainContainer">
      <h1>Create a new quiz</h1>
      <CreateQuiz />
    </div>
    <div v-if="!user">
      <img :src="sadImage" alt="Hallo" id="sad-face">
      <p id="not-logged-in-message">Please log in to continue<br>
      as you have be a user<br>
      to create quizzes</p>
      <button @click="$router.push('/login')" id="okay-button">Okay</button>
    </div>
  </div>
</template>


<style scoped>

#mainContainer {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
}

#not-logged-in-message {
    text-align: center;
    margin-top: 20px;
    font-size: calc(1.5vw + 1.6vh);
}

#sad-face {
    display: block;
    margin: 20px auto 0;
    width: 30%;
}

#okay-button {
    display: block;
    margin: 20px auto 0;
    padding: 10px 20px;
    background-color: var(--primary-color);
    color: white;
    border: none;
    border-radius: 5px;
    cursor: pointer;
}

</style>