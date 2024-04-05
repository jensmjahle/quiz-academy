<script setup>
import { onMounted, ref } from "vue";
import QuizList from "../components/QuizList.vue";
import { fetchQuizzes } from "../utils/quizUtils.js";

const newQuiz = {
    quizId: 4,
    quizName: "Mixed Type Quiz",
    quizDescription: "Test your knowledge with this mixed type quiz!",
    user: "john_doe",
    quizCreationDate: new Date(),
    tags: [
        { tagId: 21, tagName: "Mixed Type Quiz" },
        { tagId: 5, tagName: "General" }
    ],
    questions: [
        {
            questionId: 1,
            questionText: "What is the capital of Italy?",
            quizId: 4,
            type: "MULTIPLE_CHOICE",
            alternatives: ["London", "Paris", "Rome", "Berlin"],
            correctAlternatives: ["Rome"]
        },
        {
            questionId: 2,
            questionText: "How many sides does a square have?",
            quizId: 4,
            type: "MULTIPLE_CHOICE",
            alternatives: ["Three", "Four", "Five", "Six"],
            correctAlternatives: ["Four"]
        },
        {
            questionId: 3,
            questionText: "What is the chemical symbol for water?",
            quizId: 4,
            type: "TEXT_INPUT",
            answers: ["H2O"]
        },
        {
            questionId: 4,
            questionText: "The Earth is flat. True or False?",
            quizId: 4,
            type: "TRUE_FALSE",
            correctAnswer: false
        },
        {
            questionId: 5,
            questionText: "Who wrote the play 'Hamlet'?",
            quizId: 4,
            type: "TEXT_INPUT",
            answers: ["William Shakespeare"]
        },
        {
            questionId: 6,
            questionText: "What is the capital of Japan?",
            quizId: 4,
            type: "MULTIPLE_CHOICE",
            alternatives: ["Beijing", "Seoul", "Tokyo", "Bangkok"],
            correctAlternatives: ["Tokyo"]
        },
        {
            questionId: 7,
            questionText: "How many planets are in the solar system?",
            quizId: 4,
            type: "MULTIPLE_CHOICE",
            alternatives: ["Six", "Seven", "Eight", "Nine"],
            correctAlternatives: ["Eight"]
        },
        {
            questionId: 8,
            questionText: "Who painted the Mona Lisa?",
            quizId: 4,
            type: "TEXT_INPUT",
            answers: ["Leonardo da Vinci"]
        },
        {
            questionId: 9,
            questionText: "The Great Wall of China is visible from space. True or False?",
            quizId: 4,
            type: "TRUE_FALSE",
            correctAnswer: false
        },
        {
            questionId: 10,
            questionText: "What is the largest mammal?",
            quizId: 4,
            type: "MULTIPLE_CHOICE",
            alternatives: ["Elephant", "Whale", "Giraffe", "Hippopotamus"],
            correctAlternatives: ["Whale"]
        }
    ]
};

const quizzes = ref([]);
onMounted(async () => {
    quizzes.value = await fetchQuizzes();
});

</script>

<template>
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
