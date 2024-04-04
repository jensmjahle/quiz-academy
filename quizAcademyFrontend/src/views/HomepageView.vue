<script setup>
import { ref } from "vue";
import axios from "axios";
import { useTokenStore } from "../stores/token.js";
import QuizList from "../components/QuizList.vue";

let userDetails = ref(null);

const getQuizzes = async () => {
    try {
        const tokenStore = useTokenStore();
        const response = await axios.get("http://localhost:8080/quiz/all", {
            headers: {
                Authorization: `Bearer ${tokenStore.getJwtToken}`
            }
        });
        userDetails.value = response.data;
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

const quizzes = ref([]);
const quizzesBeforeChecks = ref([
    { id: 1, name: "Mock Quiz 1", quizDescription: "This is a mock quiz..." },
    { id: 2, name: "Mock Quiz 2" }
    // Other mock quizzes...
]);

const fetchQuizzes = async () => {
    try {
        const response = await axios.get("http://localhost:8080/quiz/all");
        quizzes.value = response.data;
        console.log(response.data);
    } catch (error) {
        console.error("Error fetching quizzes:", error);
    }
};
</script>

<template>
    <div id="body">
        <h2>This is the homepage!!!</h2>
        <QuizList :quizzes="quizzes" />
        <QuizList :quizzes="quizzesBeforeChecks" />
        <button @click="fetchQuizzes">Get Quizzes</button>
    </div>
</template>

<style scoped>
#body {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1;
}
</style>
