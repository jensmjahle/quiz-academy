<template>
    <div class="quiz-list-container">
        <ul class="quiz-list">
            <QuizItem
                v-for="quiz in quizzes"
                :key="quiz.id"
                :quiz="quiz"
                @click="openDialog(quiz)"
            />
        </ul>
        <ListDialog :selectedQuiz="selectedQuiz" @close="closeDialog" />
    </div>
</template>

<script setup>
import { ref } from "vue";
import QuizItem from "./QuizListItem.vue";
import ListDialog from "./ListDialog.vue";
import axios from "axios";

const selectedQuiz = ref(null);

const openDialog = (quiz) => {
    selectedQuiz.value = quiz;
};

const closeDialog = () => {
    selectedQuiz.value = null;
};

const props = defineProps({
    quizzes: Array
});
</script>

<style scoped>
.quiz-list-container {
    justify-content: center;
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
    column-gap: 10px;



    /* Default to single column on narrow screens */
    grid-template-columns: repeat(1, minmax(0, 1fr));
    width: 100%;

    /* Adjust grid for wider screens */
    @media screen and (min-width: 768px) {
        grid-template-columns: repeat(2, minmax(0, 1fr)); /* 2 columns on screens 768px and wider */
        column-gap: 20px;
    }

    @media screen and (min-width: 1024px) {
        grid-template-columns: repeat(
            3,
            minmax(0, 1fr)
        ); /* 3 columns on screens 1024px and wider */
        column-gap: 30px;
    }
}
</style>
