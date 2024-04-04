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
    { id: 12, name: 'Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo Hallo hallo hallo '}
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
    flex-grow: 1;
    width: 70%;
    padding-left: 15%;
}

.quiz-list {
    list-style-type: none; /* Remove bulletpoints */
    padding: 0;
    justify-content: center;
}
</style>
