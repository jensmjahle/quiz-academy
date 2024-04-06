<script setup>
import { ref } from 'vue';
import { useQuizStore } from '../stores/QuizState.js';
import { useTextInputStore } from "../stores/textInputQuestionStore.js";
import router from "../router/index.js";

let questionText = ref('');
let answerText = ref('');

const quizStore = useQuizStore();
const textInputStore = useTextInputStore();

const statifyQuestionAndStore = () => {
    const questionStateId = quizStore.quizQuestionStates.length;
    textInputStore.setQuestionValues(quizStore.quizId,questionStateId, questionText.value, answerText.value.split('*'));
    quizStore.addTextInputQuestionState(textInputStore);
}

const createQuestion = async () => {
    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        type: "TEXT_INPUT",
        answers: answerText.value.split('*')
    };

    quizStore.addQuestion(questionData);
    statifyQuestionAndStore();
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