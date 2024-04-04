<script setup>
import { onMounted, ref } from "vue";
import axios from "axios";
import { useTokenStore } from "../stores/token.js";
import QuizList from "../components/QuizList.vue";

let userDetails = ref(null);

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
const PostQuiz = async () => {
    try {
        const tokenStore = useTokenStore();
        const response = await axios.get("http://localhost:8080/quiz/create", {
            data: {
                quiz: newQuiz
            }
        });
        userDetails.value = response.data;
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
};

const quizzes = ref([]);
const quizzesBeforeChecks = ref([
    { id: 1, name: "Mock Quiz 1", quizDescription: "This is a mock quiz..." },
    { id: 2, name: "Mock Quiz 2" }
    // Other mock quizzes...
]);

const fetchQuizzes = async () => {
    try {
        const response = await axios.get("http://localhost:8080/quiz/all");
        quizzes.value = response.data;
        console.log(response.data);
    } catch (error) {
        console.error("Error fetching quizzes:", error);
    }
};

onMounted(fetchQuizzes)
</script>

<template>
    <div id="body">
        <h2>This is the homepage!!!</h2>
        <QuizList :quizzes="quizzes" />
        <QuizList :quizzes="quizzesBeforeChecks" />
        <button @click="PostQuiz">Get Quizzes</button>
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
