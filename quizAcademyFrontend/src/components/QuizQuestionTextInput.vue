<script setup>
import { ref } from 'vue';
import { useQuizStore } from '../stores/QuizState.js';
import { useTextInputStore } from "../stores/textInputQuestionStore.js";
import { useRouter } from 'vue-router';

let edit = ref(false);
let questionText = ref('');
let answerText = ref('');

const router = useRouter();
const quizStore = useQuizStore();
const textInputStore = useTextInputStore();

if(textInputStore.questionId !== null) {
    console.log("question id was found in store: ", textInputStore.questionId);
    edit.value = true;
    questionText.value = textInputStore.questionText;
    answerText.value = textInputStore.correctAnswers.join('*');
}

const createQuestion = () => {

    console.log("answerText: ", answerText.value);

    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        questionId: quizStore.quizQuestions.length,
        type: "TEXT_INPUT",
        answers: answerText.value.split('*')
    };

    quizStore.addQuestion(questionData);
    textInputStore.resetQuestionValues();
    try{
        console.log("pushing to create_quiz");
        router.push('/create_quiz');
    } catch(error) {
        console.error("pushing to create_quiz failed with error: ", error);
    }
}

const updateQuestion = ()=> {
    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        questionId: textInputStore.questionId,
        type: "TEXT_INPUT",
        answers: answerText.value.split('*')
    };

    const indexOfQuestion = quizStore.getIndexById(textInputStore.questionId);
    console.log("index of question: ", indexOfQuestion);
    quizStore.swapQuestions(indexOfQuestion, questionData);

    textInputStore.resetQuestionValues();
    router.push('/create_quiz');
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
            <button id="cancel" @click="router.push('/create_quiz')">Cancel</button>
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
