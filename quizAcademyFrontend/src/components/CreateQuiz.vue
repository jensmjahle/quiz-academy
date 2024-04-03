<script setup>
import {onMounted, ref} from 'vue';
import { useStore } from '../stores/createQuizState.js';
import axios from 'axios';

let quizCreated = ref(false);
let quizId = ref(null);
let questions = ref([]);
let quizName = ref('');

const store = useStore();


if (store.quizName !== null) {
    quizName.value = store.quizName;
} else {
    console.log("no quiz name")
}

const createQuiz = async () => {
    const quizDescription = 'This is a quiz';
    const user = 'jens'; //TODO: Change this to the actual user
    //const tags = []; //TODO: Change this to the actual tags
    const question1 = {
        questionId: 21,
        questionText: "question1",
        quizId: 21,
        type: "TEXT_INPUT",
        answers: ["answer1", "answer2"]
        //answers: answerText.value.split('*')
    };
    const questions = [question1];

    const quizData = {
        quizName: quizName.value,
        quizDescription: quizDescription,
        user: user,
        quizCreationDate: new Date(),
        //tags: tags,
        questions: questions
    };

    try {
        const response = await axios.post('http://localhost:8080/quiz/create', quizData);
        quizId.value = response.data.quizId;
        console.log(response.data);
        quizCreated.value = true;
        console.log('quizId: ', quizId.value);
        store.initializeQuiz(quizId.value, quizName.value);
    } catch (error) {
        console.error(error);
    }
}

</script>

<template>
    <div id="title">
        <h5>Name your quiz:</h5>
        <div id="title_and_id">
            <input type="text" id="quiz_name" v-model="quizName" placeholder="Quiz name" />
            <button class="button" @click="createQuiz">Create Quiz</button>
        </div>

        <div id="question_creation" v-if="quizCreated">
            <h5>Add questions to your quiz:</h5>
            <!-- Only render the router-link if quizId is not null -->
            <router-link v-if="quizId" class="button" :to="{ name: 'multichoice', params: { quizId: quizId.value }}">Add multiple choice question</router-link>
            <router-link v-if="quizId" class="button" :to="{ name: 'text_input', params: { quizId: quizId.value }}">Add text input question</router-link>
            <router-link v-if="quizId" class="button" :to="{ name: 'drag_and_drop', params: { quizId: quizId.value }}">Add drag and drop question</router-link>
        </div>
    </div>
    <div id="question_list" v-if="quizCreated"> <!-- TODO: add check to list of questions -->
        <h5>Questions:</h5>
        <ul>
            <li v-for="question in questions" :key="question.id">
                {{ question.type }}: {{ question.name }}
            </li>
        </ul>
    </div>
</template>

<style scoped>

#title {
    display:flex;
    flex-flow: column;
}

#title_and_id {
    display: flex;
    flex-flow: row;
}

#question_creation {
    display:flex;
    flex-flow: column;
}

#quiz_name  {
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 25vw;
}

#quiz_name::placeholder {
    text-align: center;
}

.button {
    background-color: var(--fourth-color);
    padding: 10px 20px;
    margin: 10px;
    border-radius: 10px;
    border-width: 4px;
    cursor: pointer;
    font-family: var(--font-family);
    font-size: 20px;
}

.button:active {
    color: var(--base-color);
}
</style>