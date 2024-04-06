<script setup>
import { ref } from 'vue';
import { useQuizStore } from '../stores/QuizState.js';
import { useTextInputStore } from "../stores/textInputQuestionStore.js";
import router from "../router/index.js";
import axios from "axios";

let edit = ref(false);
let questionText = ref('');
let answerText = ref('');

const quizStore = useQuizStore();
const textInputStore = useTextInputStore();

if(textInputStore.questionId !== null) {
    console.log("question id was found in store: ", textInputStore.questionId);
    edit.value = true;
    questionText.value = textInputStore.questionText;
    answerText.value = textInputStore.correctAnswers.join('*');
}

const statifyQuestionAndStore = () => {
    const questionStateId = quizStore.quizQuestions.length + 1;
    if (answerText.value.includes('*')) {
        answerText.value.split('*');
        textInputStore.setQuestionValues(quizStore.quizId, questionStateId, questionText.value, answerText.value);
        console.log("answer text after includes(\"*\") check:", answerText.value);
    } else {
        textInputStore.setQuestionValues(quizStore.quizId, questionStateId, questionText.value, [answerText.value]);
        console.log("answer text didn't include asterisk. Attempting to input: ", [answerText.value]);
    }
    quizStore.addTextInputQuestionState(textInputStore);
    console.log("edit: ", edit.value);
    edit.value = true;
}

const createQuestion = async () => {

    console.log("answerText: ", answerText.value);

    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        questionId: quizStore.quizQuestions.length,
        type: "TEXT_INPUT",
        questionAnswers: answerText.value.split('*')
    };

    console.log("answer text after input in data; ", questionData.questionAnswers);

    quizStore.addQuestion(questionData);
    textInputStore.resetQuestionValues();
    try{
        await router.push('/create_quiz');
    } catch(error) {
        console.error("pushing to create_quiz failed with error: ", error);
    }
}

const updateQuestion = async ()=> {
    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        questionId: textInputStore.questionId,
        type: "TEXT_INPUT",
        answers: answerText.value.split('*')
    };

    console.log(questionData)

    const response = await axios.post('http://localhost:8080/question/update', questionData);
    console.log(response.data);
    //statifyQuestionAndStore(); //todo: make update method instead of add

    textInputStore.resetQuestionValues();
    await router.push('/create_quiz');
}
</script>

<template>
    <div id="full_question">
        <div id="text_response_question">
            <input id="input" type="text" v-model="questionText" placeholder="Question" />
            <input id="input" type="text" v-model="answerText" placeholder="Answer" />
        </div>
        <div>
            <h5>Separate correct answers with: *</h5>
        </div>
        <div>
            <button @click="createQuestion" v-if="!edit">Submit</button>
            <button @click="updateQuestion" v-if="edit">Update</button>
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

#input {
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
