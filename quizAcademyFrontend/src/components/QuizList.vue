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
import axios from "axios";

const quizzesBeforeChecks = ref([
    { id: 1, name: 'Mock Quiz 1', quizDescription: 'This is a mock quiz. And this is a test description. Educational quiz this is. Created by me' },
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
    { id: 12, name: 'Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo '}
]);

const fetchQuizzes = async () => {
    try {
        const response = await axios.get('/api/quizzes'); // Endre URL-en til din backend-endepunkt
        quizzes.value = response.data;
    } catch (error) {
        console.error('Error fetching quizzes:', error);
    }
};

quizzesBeforeChecks.value.forEach(quiz => {
    if (quiz.name.length > 20) {
        quiz.shortenedName = quiz.name.slice(0, 20) + '...';
    } else {
        quiz.shortenedName = quiz.name;
    }
});

const selectedQuiz = ref(null);
const quizzes = ref([]);

const openDialog = (quiz) => {
    selectedQuiz.value = quiz;
};

const closeDialog = () => {
    selectedQuiz.value = null;
};
</script>

<style scoped>
.quiz-list-container {
    display: flex;
    flex-grow: 1;
    gap: 10px; /* Add gap between items */
    width: 80%;
    min-width: 400px;

}

.quiz-list {
    display: grid;
    list-style-type: none; /* Remove bullet points */
    padding: 15px;
    gap: 10px;

    /* Default to single column on narrow screens */
    grid-template-columns: repeat(1, minmax(0, 1fr));

    /* Adjust grid for wider screens */
    @media screen and (min-width: 768px) {
        grid-template-columns: repeat(2, minmax(0, 1fr)); /* 2 columns on screens 768px and wider */
    }

    @media screen and (min-width: 1024px) {
        grid-template-columns: repeat(3, minmax(0, 1fr)); /* 3 columns on screens 1024px and wider */
    }


}
</style>
