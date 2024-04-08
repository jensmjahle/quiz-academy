<template>
  <div id="full_question">
    <div id="text_response_question">
      <input id="input" type="text" v-model="questionText" placeholder="Question" />
      <input id="input" type="text" v-model="answerText" placeholder="Answer" />
    </div>
    <div>
      <h5>Separate correct answers with: *</h5>
    </div>
    <div id="add_picture">
      <h5>Add or change picture for your question:</h5>
      <div v-if="imageUploaded">
        <h5 >Current image</h5>
        <img :src="questionPhoto" alt="Question image"/>
      </div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
    </div>
    <div>
      <button @click="createQuestion" v-if="!edit">Submit</button>
      <button @click="updateQuestion" v-if="edit">Update</button>
      <button id="cancel" @click="cancelPressed">Cancel</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import { useQuizStore } from '../stores/QuizStore.js';
import { useTextInputStore } from "../stores/textInputQuestionStore.js";
import { useRouter } from 'vue-router';

let edit = ref(false);
let questionText = ref('');
let answerText = ref('');
let questionPhoto = ref(null);
let imageUploaded = ref(false);

const router = useRouter();
const quizStore = useQuizStore();
const textInputStore = useTextInputStore();

if(textInputStore.questionId !== null) {
    edit.value = true;
    questionText.value = textInputStore.questionText;
    answerText.value = textInputStore.correctAnswers.join('*');
    questionPhoto.value = textInputStore.questionImage;
    if (questionPhoto.value !== null) {
        imageUploaded.value = true;
    }
}

const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = () => {
        // `reader.result` contains the base64 string representation of the image
        questionPhoto.value = reader.result;
    };
    // Read the file as a data URL (base64)
    reader.readAsDataURL(file);
    imageUploaded.value = true;
};

const createQuestion = () => {

    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        questionId: quizStore.quizQuestions.length,
        type: "TEXT_INPUT",
        answers: answerText.value.split('*'),
        imageBase64: questionPhoto.value
    };

    quizStore.addQuestion(questionData);
    textInputStore.resetQuestionValues();
    try{
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
        answers: answerText.value.split('*'),
        imageBase64: questionPhoto.value
    };

    const indexOfQuestion = quizStore.getIndexById(textInputStore.questionId);
    quizStore.swapQuestions(indexOfQuestion, questionData);

    textInputStore.resetQuestionValues();
    router.push('/create_quiz');
}

const cancelPressed = () => {
    if (confirm('Are you sure you want to cancel?')) {
        textInputStore.resetQuestionValues();
        router.push('/create_quiz');
    }
}
</script>

<style scoped>
#full_question {
    margin-top: 3vw;
    display: flex;
    flex-flow: column;
    align-items: center;
    padding: 15px;
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

#add_picture {
    margin-top: 15px;
    display: flex;
    flex-flow: column;
    justify-content: center;
    align-items: center;
}
</style>
