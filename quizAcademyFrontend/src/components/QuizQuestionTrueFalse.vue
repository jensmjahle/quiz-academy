<template>
  <div id="full_question">
    <p id="info-text">True or False question <br>
      Check circle if question is true</p>
    <div id="text_response_question">
      <input id="input" type="text" v-model="questionText" placeholder="Question" />
      <input id="checkbox" type="checkbox" v-model="answer"/>
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
import { useTrueFalseStore } from "../stores/trueFalseQuestionStore.js";
import { useRouter } from 'vue-router';

let edit = ref(false);
let questionText = ref('');
let answer = ref(null);
let questionPhoto = ref(null);
let imageUploaded = ref(false);

const router = useRouter();
const quizStore = useQuizStore();
const trueFalseStore = useTrueFalseStore()

if(trueFalseStore.questionId !== null) {
    edit.value = true;
    questionText.value = trueFalseStore.questionText;
    answer.value = trueFalseStore.questionAnswer;
    questionPhoto.value = trueFalseStore.questionImage;
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
        type: "TRUE_FALSE",
        correctAnswer: answer.value,
        imageBase64: questionPhoto.value
    };

    quizStore.addQuestion(questionData);
    trueFalseStore.resetQuestionValues();
    router.push('/create_quiz');
}

const updateQuestion = ()=> {
    const questionData = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        questionId: trueFalseStore.questionId,
        type: "TRUE_FALSE",
        correctAnswer: answer.value,
        imageBase64: questionPhoto.value
    };

    const indexOfQuestion = quizStore.getIndexById(trueFalseStore.questionId);
    quizStore.swapQuestions(indexOfQuestion, questionData);
    trueFalseStore.resetQuestionValues();
    router.push('/create_quiz');
}

const cancelPressed = () => {
    if (confirm('Are you sure you want to cancel?')) {
        trueFalseStore.resetQuestionValues();
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
    flex-flow: row-reverse;
    align-items: center;
    #checkbox {
        margin-top: 15px;
    }
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

#checkbox {
    margin-left: 15px;
    margin-right: 15px;
    -webkit-appearance: none;
    width: 20px; /* Adjust the value as per your needs */
    height: 20px; /* Adjust the value as per your needs */
    background-color: var(--fifth-color); /* Adjust the color as per your needs */
    border-radius: 50%; /* This will make the checkbox completely round */
    cursor: pointer;
}

#checkbox:checked {
    background-color: var(--tertiary-color);
}

#checkbox:checked::after {
    content: "";
    display: block;
    width: 10px; /* Adjust the value as per your needs */
    height: 10px; /* Adjust the value as per your needs */
    background-color: var(--fourth-color); /* Adjust the color as per your needs */
    border-radius: 50%; /* This will make the circle completely round */
    margin: 5px; /* Adjust the value as per your needs */
}

#info-text {
  font-size: 30px;
  margin-bottom: 10px;
  padding: 10px;
  justify-self: center;
  text-align: center; /* Add this line */
}

#add_picture {
  margin-top: 15px;
  display: flex;
  flex-flow: column;
  justify-content: center;
  align-items: center;
}
</style>
