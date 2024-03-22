<template>
    <div class="quiz-list-container">
        <ul class="quiz-list">
            <li v-for="quiz in quizzes" :key="quiz.id" class="quiz-item" @click="openDialog(quiz)">
                {{ quiz.name }}
            </li>
        </ul>
        <div v-if="selectedQuiz" class="dialog">
            <div class="dialog-content">
                <button @click="editQuiz(selectedQuiz)">Edit</button>
                <button @click="playQuiz(selectedQuiz)">Play</button>
                <button @click="exportCSV(selectedQuiz)">Export as CSV</button>
                <button class="close-button" @click="closeDialog">Close</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const quizzes = ref([
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
    { id: 11, name: 'Mock Quiz 11aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa' },
]);

const selectedQuiz = ref(null);

const openDialog = (quiz) => {
    selectedQuiz.value = quiz;
};

const closeDialog = () => {
    selectedQuiz.value = null;
};

const editQuiz = (quiz) => {
    console.log('Edit quiz:', quiz);
};

const playQuiz = (quiz) => {
    console.log('Play quiz:', quiz);
};

const exportCSV = (quiz) => {
    console.log('Export as CSV:', quiz);
};
</script>

<style scoped>
.quiz-list-container {
    max-height: 380px; /* Set the max height for the container */
    overflow-y: auto; /* Enable vertical scrolling for the container */
}

.quiz-list {
    list-style-type: none; /* Remove bulletpoints */
    padding: 0;
}

.quiz-item {
    border: 1px solid #ccc; /* Add border */
    padding: 10px;
    margin-bottom: 10px; /* Add margin between items */
    cursor: pointer; /* Change cursor to pointer */
}

.quiz-item:hover {
    background-color: #f0f0f0; /* Change background color on hover */
}

.dialog {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: white;
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dialog-content {
    display: flex;
    flex-direction: column; /* Align items vertically */
    gap: 10px; /* Add gap between buttons */
}

.dialog button {
    padding: 10px 20px;
    cursor: pointer;
    width: 100%; /* Make buttons full width */
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
}
</style>
