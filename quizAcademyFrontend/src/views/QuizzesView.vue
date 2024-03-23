<template>
    <div>
        <h1>Quiz List</h1>
        <QuizList :quizzes="mockQuizzes" />
        <datalist>
            <option v-for="quiz in mockQuizzes" :key="quiz.id">{{ quiz.name }}</option>
        </datalist>
    </div>
</template>

<script setup>
import QuizList from "../components/QuizList.vue"; // Adjust the path accordingly
</script>
