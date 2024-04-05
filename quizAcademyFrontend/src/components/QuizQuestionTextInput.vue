<script setup>
import { ref } from 'vue';
import { useStore } from '../stores/createQuizState.js';
import axios from 'axios';
import router from "../router/index.js";

let questionText = ref('');
let answerText = ref('');

const store = useStore();

const createQuestion = async () => {
    const questionData = {
        questionText: questionText.value,
        quizId: store.quizId,
        type: "TEXT_INPUT",
        answers: answerText.value.split('*')
    };

    try {
        console.log(questionData.answers);
        console.log(questionData);
        const response = await axios.post('http://localhost:8080/question/create', questionData);
        console.log(response.data);
    } catch (error) {
        console.error(error);
    }
    store.addQuestion(questionData);
    await router.push('/create_quiz');
}
</script>

<template>
    <div id = "full_question">
        <div id = "text_response_question">
            <input id="input" type="text" v-model="questionText" placeholder="Question" />
            <input id="input" type="text" v-model="answerText" placeholder="Answer" />
        </div>
        <div>
            <h5>Separate correct answers with: *</h5>
        </div>
        <div>
            <button @click="createQuestion">Submit</button>
        </div>
    </div>

</template>

<style scoped>

#full_question {
    margin-top: 3vw;
    display: flex;
    flex-flow: column;
    align-items: center;
}

#text_response_question {
    display: flex;
    flex-flow: column;
}

#input  {
    margin-top: 15px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 60vw;
}

#input::placeholder {
    text-align: center;
}

</style>