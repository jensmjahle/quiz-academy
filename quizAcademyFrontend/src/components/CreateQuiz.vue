<script setup>
import { ref, onMounted, provide } from 'vue';
import { useRoute } from 'vue-router';
import axios from 'axios';

let route = useRoute();

let questions = ref([]);

let quizCreated = ref(false);

let quizId = ref(null);

provide('questions', questions);

onMounted(() => {

    if (route.params.question) {
        questions.value.push(route.params.question);
    } else {
        questions.value = [
            { id: 1, type: 'text_input', name: 'question 1', answer: 'answer 1' },
            { id: 2, type: 'multichoice', name: 'question 2', answer: 'answer 2' }
        ];
    }
});

const createQuiz = async () => {
    const quizName = document.getElementById('quiz_name').value;
    const quizDescription = 'This is a quiz';
    const user = 'jens'; //TODO: Change this to the actual user
    //const tags = []; //TODO: Change this to the actual tags
    const questions = [];

    const quizData = {
        quizName: quizName,
        quizDescription: quizDescription,
        user: user,
        quizCreationDate: new Date(),
        //tags: tags,
        questions: questions
    };

    try {
        const response = await axios.post('http://localhost:8080/quiz/create', quizData);
        console.log(response.data);
        quizCreated.value = true;
        quizId.value = response.data.quizId; // This line should now work without error
    } catch (error) {
        console.error(error);
    }
}



</script>

<template>
    <div id="title">
        <h5>Name your quiz:</h5>
        <div id="title_and_id">
            <input type="text" id="quiz_name" placeholder="Quiz name" />
            <button class="button" @click="createQuiz">Create Quiz</button>
        </div>


        <div id="question_creation" v-if = "quizCreated">
            <h5>Add questions to your quiz:</h5>
            <router-link class= "button" :to="{ name: 'multichoice', params: { quizId: quizId.value} }">Add multiple choice question</router-link>
            <router-link class= "button" :to="{ name: 'text_input', params: { quizId: quizId.value} }">Add text input question</router-link>
            <router-link class= "button" :to="{ name: 'drag_and_drop', params: { quizId: quizId.value} }">Add drag and drop question</router-link>
        </div>
    </div>
    <div id="question_list" v-if = "quizCreated">
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