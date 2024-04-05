<script setup>
import { ref } from 'vue';
import { useStore } from '../stores/createQuizState.js';
import axios from 'axios';
import { useRouter } from 'vue-router'


const router = useRouter();
const store = useStore();

let quizCreated = ref(false);
let quizId = ref(store.quizId);

let questions = ref([]);
let quizName = ref('');
let showSavedMessage = ref(false);
let quizDescription = ref('');

const user = 'jens'; //TODO: Change this to the actual user

if (store.quizName !== null) {
    quizName.value = store.quizName;
    quizDescription.value = store.quizDescription;
    quizCreated.value = true;
    if (Array.isArray(store.quizQuestions)) {
        questions.value = store.quizQuestions;
    } else {
        console.log("no questions in store or not an array");
    }
} else {
    console.log("no quiz name")
}

const createQuiz = async () => {
    //const tags = []; //TODO: Change this to the actual tags

    const quizData = {
        quizName: quizName.value,
        quizDescription: quizDescription.value,
        user: user,
        quizCreationDate: new Date()
    };

    try {
        console.log(quizData);
        const response = await axios.post('http://localhost:8080/quiz/create', quizData);
        console.log(response.data);
        quizId.value = response.data.quizId;
        quizDescription.value = response.data.quizDescription;
        quizCreated.value = true;
        store.initializeQuiz(quizId.value, quizName.value, questions.value, quizDescription.value);
        console.log(quizCreated.value);
    } catch (error) {
        console.error(error);
    }
}

const updateQuiz = async () => {

    const quizData = {
        quizId: quizId.value,
        quizName: quizName.value,
        quizDescription: quizDescription.value,
        user: user,
        quizCreationDate: new Date(),
        questions: store.quizQuestions,
    };

    try {
        const response = await axios.post('http://localhost:8080/quiz/update', quizData);
        quizId.value = response.data.quizId;

        showSavedMessage.value = true;
        setTimeout(() => {
            showSavedMessage.value = false;
        }, 5000);
    } catch (error) {
        console.error(error);
    }
}

const resetWithConfirm = () => {
    if (confirm('Are you sure you want to reset the quiz?')) {
        store.resetQuiz();
        router.push('/quizzes');
    }
}

</script>

<template>
    <div id="title">
        <h5>Name your quiz:</h5>
        <div id="title_and_id">
            <input type="text" id="quiz_name" v-model="quizName" placeholder="Quiz name" />
            <button class="button" @click="createQuiz" v-if="!quizCreated">Create Quiz</button>
        </div>
        <div id="quiz_description">
            <input type = "text" id="quiz_description" v-model="quizDescription" placeholder="give a short description for your quiz">
        </div>

        <div id="question_creation" v-if="quizCreated">
            <h5>Add questions to your quiz:</h5>
            <!-- Only render the router-link if quizId is not null -->
            <router-link v-if="quizId" class="button" :to="{ name: 'multichoice', params: { quizId: quizId.value }}">Add multiple choice question</router-link>
            <router-link v-if="quizId" class="button" :to="{ name: 'text_input', params: { quizId: quizId.value }}">Add text input question</router-link>
            <router-link v-if="quizId" class="button" :to="{ name: 'drag_and_drop', params: { quizId: quizId.value }}">Add drag and drop question</router-link>
        </div>
    </div>
    <div id="question_list" v-if="quizCreated">
        <h5>Questions:</h5>
        <ul>
            <li v-for="question in questions" :key="question.id">
                {{ question.type }}: {{ question.name }}
            </li>
        </ul>
    </div>
    <div v-if="quizCreated">
        <h5 v-if="showSavedMessage">Quiz saved!</h5>
        <button class="button" @click="updateQuiz">Save quiz</button>
        <button class="button" @click="resetWithConfirm">Exit without saving</button>
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

#quiz_description {
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