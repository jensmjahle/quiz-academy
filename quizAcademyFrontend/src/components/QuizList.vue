<template>
    <div class="quiz-list-container">
        <ul class="quiz-list">
            <QuizItem v-for="quiz in quizzesBeforeChecks" :key="quiz.id" :quiz="quiz" @click="openDialog(quiz)" />
        </ul>
        <ListDialog :selectedQuiz="selectedQuiz" @close="closeDialog" />
    </div>
</template>

<script setup>
import { ref } from 'vue';
import QuizItem from './QuizListItem.vue';
import ListDialog from './ListDialog.vue';

const quizzesBeforeChecks = ref([
    { id: 1, name: 'Mock Quiz 1' },
    { id: 2, name: 'Mock Quiz 2' },
    { id: 3, name: 'Mock Quiz 3' },
    { id: 4, name: 'Mock Quiz 4' },
    { id: 5, name: 'Mock Quiz 5' },
    { id: 6, name: 'Mock Quiz 6' },
    { id: 7, name: 'Mock Quiz 7' },
    { id: 8, name: 'Mock Quiz 8' },
    { id: 9, name: 'Mock Quiz 9' },
    { id: 10, name: 'Mock Quiz 10' },
    { id: 11, name: 'Mock Quiz 11aaaaaaa1aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa' },
]);


quizzesBeforeChecks.value.forEach(quiz => {
    if (quiz.name.length > 20) {
        quiz.shortenedName = quiz.name.slice(0, 20) + '...';
    } else {
        quiz.shortenedName = quiz.name;
    }
});

const selectedQuiz = ref(null);

const openDialog = (quiz) => {
    selectedQuiz.value = quiz;
};

const closeDialog = () => {
    selectedQuiz.value = null;
};
</script>

<style scoped>
.quiz-list-container {
    max-height: 400px; /* Set the max height for the container */
    overflow-y: auto; /* Enable vertical scrolling for the container */
    scrollbar-gutter: stable; /* Prevent scrollbar from overlapping content */
    scrollbar-color: var(--secondary-color) var(--primary-color); /* Set scrollbar colors */
    overflow-x: clip;
    justify-content: center;
    display: flex;
}

.quiz-list {
    list-style-type: none; /* Remove bulletpoints */
    padding: 0;
    width: 450px;
}
</style>
