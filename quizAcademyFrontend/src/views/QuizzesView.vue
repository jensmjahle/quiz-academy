<template>
    <div id="body">
        <h1>Quiz List</h1>
        <QuizList :quizzes="quizzes" />

    </div>
</template>

<script setup>
import { onMounted, ref } from "vue";
import { fetchQuizzes } from "../utils/quizUtils.js";
import QuizList from "../components/QuizList.vue";

const quizzes = ref([]);
onMounted(async () => {
    quizzes.value = await fetchQuizzes();
});

</script>

<style scoped>
#body {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1;
}
</style>
