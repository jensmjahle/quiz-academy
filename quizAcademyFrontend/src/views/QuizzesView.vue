<template>
  <div>
    <div v-if="user" id="mainContainer">
      <h1>My Quizzes</h1>
      <QuizList :quizzes="quizzes" />
    </div>
    <div v-if="!user">
      <img :src="sadImage" alt="Hallo" id="sad-face">
      <p id="not-logged-in-message">Please log in to<br>
        view your quizzes</p>
      <button @click="$router.push('/login')" id="okay-button">Okay</button>
    </div>
  </div>
</template>

<script>
import { onMounted, ref } from "vue";
import { fetchAllQuizzesByUser, fetchPublicQuizzes } from "../utils/quizUtils.js";
import QuizList from "../components/QuizList.vue";
import { useTokenStore } from "../stores/token.js";
import CreateQuiz from "../components/CreateQuiz.vue";
import sadImage from "../assets/sad.svg";
export default {
    components: { QuizList },
    setup() {
        const tokenStore = useTokenStore();
        return { tokenStore };
    },
    async mounted() {
        if (!this.tokenStore.jwtToken) {
        } else {
            let response = this.tokenStore.loggedInUser;
            this.user = response.data;
            this.quizzes = await fetchAllQuizzesByUser(this.user.username);

        }
    },
    data() {
        return {
            user: null,
            sadImage: sadImage,
            quizzes: []
        }
    }
};

</script>

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
