<script setup>
import { onMounted, ref } from "vue";
import QuizList from "../components/QuizList.vue";
import { fetchPublicQuizzes } from "../utils/quizUtils.js";
import { usePlayQuizStore } from "../stores/playQuizStore.js";

const quizzes = ref([]);
onMounted(async () => {
    quizzes.value = await fetchPublicQuizzes();
});

</script>

<template>
    <button @click="quizzes.push(play_quiz)" v-if="usePlayQuizStore().quiz!==null">Continue Last Quiz...</button>
    <div id="body">
        <h2>Our top picks!!!</h2>
        <QuizList :quizzes="quizzes" />
        <h2>Educational</h2>
        <QuizList :quizzes="quizzes" />
        <h2>General Knowledge</h2>
        <QuizList :quizzes="quizzes" />
        <h2>Entertainment</h2>
        <QuizList :quizzes="quizzes" />
        <h2>Technology</h2>
        <QuizList :quizzes="quizzes" />
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
