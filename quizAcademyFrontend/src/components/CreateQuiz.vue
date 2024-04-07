<script setup>
import { ref } from 'vue';
import { useQuizStore } from '../stores/QuizState.js';
import axios from 'axios';
import { useRouter } from 'vue-router'
import {useTokenStore} from "../stores/token.js";


const router = useRouter();
const quizStore = useQuizStore();
const tokenStore = useTokenStore();

let quizCreated = ref(false);
let quizId = ref(quizStore.quizId);
let quizPublicStatus = ref(false);
let questions = ref([]);
let quizName = ref('');
let showSavedMessage = ref(false);
let quizDescription = ref('');

const user = tokenStore.loggedInUser.data.username;

if (quizStore.quizName !== null) {
    quizName.value = quizStore.quizName;
    quizDescription.value = quizStore.quizDescription;
    quizCreated.value = true;
    quizPublicStatus.value = quizStore.quizPublicStatus;
    if (Array.isArray(quizStore.quizQuestions)) {
        questions.value = quizStore.quizQuestions;
    } else {
        console.log("no questions in store or not an array. value: ", quizStore.quizQuestions);
        questions.value = [];
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
        isPublic: quizPublicStatus.value,
        quizCreationDate: new Date()
    };

    try {
        console.log(quizData);
        const response = await axios.post('http://localhost:8080/quiz/create', quizData);
        console.log(response.data);
        quizId.value = response.data.quizId;
        quizDescription.value = response.data.quizDescription;
        quizCreated.value = true;
        quizStore.initializeQuiz(quizId.value, quizName.value, questions.value, quizDescription.value, quizPublicStatus.value);
        console.log(quizCreated.value);
    } catch (error) {
        console.error(error);
    }
}

const updateQuiz = async () => {

    if (Array.isArray(quizStore.quizQuestions)) {
        questions.value = quizStore.quizQuestions;
    } else {
        console.log("no questions in store or not an array. value of questions: ", questions.value);
        questions.value = [];
    }

    const date = new Date();

    const quizData = {
        quizId: quizId.value,
        quizName: quizName.value,
        quizDescription: quizDescription.value,
        user: user,
        isPublic: quizPublicStatus.value,
        quizCreationDate: date,
        questions: quizStore.quizQuestions,
    };

    try {
        const response = await axios.post('http://localhost:8080/quiz/update', quizData);
        console.log(response.data);
        quizId.value = response.data.quizId;
        showSavedMessage.value = true;
        setTimeout(() => {
            showSavedMessage.value = false;
        }, 5000);
    } catch (error) {
        console.log("error in updateQuiz with values: ", quizData);
        console.error(error);
    }
}

const editQuestion = async (index) => {
    const routing = quizStore.fromQuestionToQuestionState(index);
    await router.push(routing);
}
const exitAndSave = () => {
    updateQuiz();
    quizStore.resetQuiz();
    router.push('/quizzes');
}

const resetWithConfirm = () => {
    if (confirm('Are you sure you want to reset the quiz?')) {
        quizStore.resetQuiz();
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
        <div id="quiz_description_and_public">
            <input type = "text" id="quiz_description" v-model="quizDescription" placeholder="give a short description for your quiz">
            <div id="is_quiz_public">
                <h5>Tick if quiz should be public:</h5>
                <input type="checkbox" id="is_public" name="is_public" v-model="quizPublicStatus" value="is_public">
            </div>
        </div>

        <div id="question_creation" v-if="quizCreated">
            <hr>
            <h5>Add questions to your quiz:</h5>
            <!-- Only render the router-link if quizId is not null -->
            <router-link v-if="quizId" class="button" :to="{ name: 'multichoice', params: { quizId: quizId.value }}">Add multiple choice question</router-link>
            <router-link v-if="quizId" class="button" :to="{ name: 'text_input', params: { quizId: quizId.value }}">Add text input question</router-link>
            <router-link v-if="quizId" class="button" :to="{ name: 'drag_and_drop', params: { quizId: quizId.value }}">Add drag and drop question</router-link>
        </div>
    </div>
    <div id="question_list" v-if="quizCreated">
        <h5>Questions (click to edit):</h5>
        <ul>
            <li v-for="(question, index) in questions" :key="question.id" @click="editQuestion(index)" id="question_in_list" >
                {{ question.type }}: {{ index + 1 }}. {{ question.questionText }}
            </li>
        </ul>
    </div>
    <div v-if="quizCreated">
        <h5 v-if="showSavedMessage">Quiz saved!</h5>
        <button class="button" @click="updateQuiz">Save quiz</button>
        <button class="button" @click="exitAndSave">Exit and save</button>
        <button class="button" @click="resetWithConfirm">Exit without saving</button>
    </div>
</template>

<style scoped>
#title {
    display: flex;
    flex-flow: column;
}

#title_and_id {
    display: flex;
    flex-flow: row;
}

#question_creation {
    display: flex;
    flex-flow: column;
}

#quiz_name {
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

#is_quiz_public {
    display: flex;
    flex-flow: row;
    align-items: center;
}

#is_public {
    margin-left: 15px;
    margin-right: 15px;
    -webkit-appearance: none;
    width: 20px; /* Adjust the value as per your needs */
    height: 20px; /* Adjust the value as per your needs */
    background-color: var(--fifth-color); /* Adjust the color as per your needs */
    border-radius: 50%; /* This will make the checkbox completely round */
    cursor: pointer;
}

#is_public:checked {
    background-color: var(--tertiary-color);
}

#is_public:checked::after {
    content: "";
    display: block;
    width: 10px; /* Adjust the value as per your needs */
    height: 10px; /* Adjust the value as per your needs */
    background-color: var(--fourth-color); /* Adjust the color as per your needs */
    border-radius: 50%; /* This will make the circle completely round */
    margin: 5px; /* Adjust the value as per your needs */
}

#question_in_list:hover {
    cursor: pointer;
    text-underline-color: var(--fourth-color);
}
</style>
