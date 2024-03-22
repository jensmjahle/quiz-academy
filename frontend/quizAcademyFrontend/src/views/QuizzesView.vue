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
import QuizList from '../components/QuizList.vue'; // Adjust the path accordingly

// Define mock data
const mockQuizzes = [
    { id: 1, name: 'Mock Quiz 1' },
    { id: 2, name: 'Mock Quiz 2' },
    { id: 3, name: 'Mock Quiz 3' }
];
</script>
