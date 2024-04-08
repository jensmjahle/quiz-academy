<template>
  <div id="QuizQuestionMultichoice">
    <h5 id="currently_editing" v-if="edit">Editing question</h5>
    <div id="question">
      <h5 id="quiz_question">Q.1</h5>
      <input
          id="input"
          v-model="question"
          type="text"
          placeholder="Enter your question here"
      />
    </div>
    <div id="add_picture">
      <h5>Add or change picture for your question:</h5>
      <div v-if="imageUploaded">
        <h5 >Current image</h5>
        <img :src="questionPhoto" alt="Question image"/>
      </div>
      <input type="file" @change="handleFileUpload" accept="image/*" />
    </div>
    <div id="alternatives">
      <div id="alternativesMulti">
        <input
            id="input"
            v-model="alternatives[0]"
            type="text"
            placeholder="alternative one"
        />
        <label for="checkbox">Check for correct alternative</label>
        <input id="checkbox" v-model="correctAlternatives[0]" type="checkbox" />
        <input
            id="input"
            v-model="alternatives[1]"
            type="text"
            placeholder="alternative two"
        />
        <label for="checkbox">Check for correct alternative</label>
        <input id="checkbox" v-model="correctAlternatives[1]" type="checkbox" />
        <input
            id="input"
            v-model="alternatives[2]"
            type="text"
            placeholder="alternative three"
        />
        <label for="checkbox">Check for correct alternative</label>
        <input id="checkbox" v-model="correctAlternatives[2]" type="checkbox" />
        <input
            id="input"
            v-model="alternatives[3]"
            type="text"
            placeholder="alternative four"
        />
        <label for="checkbox">Check for correct alternative</label>
        <input id="checkbox" v-model="correctAlternatives[3]" type="checkbox" />
      </div>
    </div>
    <div>
      <button @click="submitForm" v-if="!edit">Submit</button>
      <button @click="updateQuestion" v-if="edit">Update</button>
      <button id="cancel" @click="cancelPressed">Cancel</button>

    </div>
  </div>

</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useQuizStore } from '../stores/QuizStore.js';
import { useMultichoiceStore} from "../stores/multichoideQuestionStore.js";

const router = useRouter();
const quizStore = useQuizStore();
const multichoiceStore = useMultichoiceStore();

let edit = ref(false);
let question = ref('');
let alternatives = ref([]);
let correctAlternatives = ref([]);
let questionPhoto = ref(null);
let imageUploaded = ref(false);

if (multichoiceStore.questionId !== null) {
    question.value = multichoiceStore.questionText;
    alternatives.value = multichoiceStore.questionAlternatives;
    edit.value = true;
    for (let i = 0; i < 4; i++) {
        if (multichoiceStore.correctAlternatives[i]  === "true") {
            correctAlternatives.value[i] = true;
        } else {
            correctAlternatives.value[i] = false;
        }
    }
    if(multichoiceStore.questionImage !== null) {
        questionPhoto.value = multichoiceStore.questionImage;
        imageUploaded.value = true;
    }
}

const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = () => {
        questionPhoto.value = reader.result;
    };
    reader.readAsDataURL(file);
    imageUploaded.value = true;
};

function oneCorrectCheck() {
    // Use Array.prototype.some() to check if at least one correct alternative is selected
    return correctAlternatives.value.some((correct) => {
        return !!correct;
    });
}

const submitForm = async () => {
    const hasCorrect = oneCorrectCheck();
    if (!hasCorrect) {
        alert('You need to select one correct alternative');
        return;
    }
    const correctAsStrings = [];
    for (let i = 0; i < 4; i++) {
        if (correctAlternatives.value[i] === true) {
            correctAsStrings[i] = "true";
        } else {
            correctAsStrings[i] = "false";
        }
    }

    const questionData = {
        questionText: question.value,
        quizId: quizStore.quizId,
        questionId: quizStore.quizQuestions.length,
        type: 'MULTIPLE_CHOICE',
        alternatives: alternatives.value,
        correctAlternatives: correctAsStrings,
        imageBase64: questionPhoto.value
    };

    quizStore.addQuestion(questionData);

    multichoiceStore.resetQuestionValues();

    await router.push('/create_quiz');
}

const updateQuestion = () => {

    const correctAsStrings = [];

    for (let i = 0; i < 4; i++) {
        if (correctAlternatives.value[i] === true) {
            correctAsStrings[i] = "true";
        } else {
            correctAsStrings[i] = "false";
        }
    }

    const questionData = {
        questionText: question.value,
        quizId: quizStore.quizId,
        questionId: multichoiceStore.questionId,
        type: 'MULTIPLE_CHOICE',
        alternatives: alternatives.value,
        correctAlternatives: correctAsStrings,
        imageBase64: questionPhoto.value
    };

    const indexOfQuestion = quizStore.getIndexById(multichoiceStore.questionId);
    quizStore.swapQuestions(indexOfQuestion, questionData);

    multichoiceStore.resetQuestionValues();

    router.push('/create_quiz');
}

const cancelPressed = () => {
    if(confirm('Are you sure you want to cancel?')) {
        multichoiceStore.resetQuestionValues();
        router.push('/create_quiz');
    }
}
</script>

<style scoped>
@media only screen and (max-width: 600px) {
    #question,
    #alternatives {
        flex: 1;
        flex-direction: column;
    }

    #quiz_question {
        font-size: 20px;
    }

    #input {
        font-size: 15px;
        width: 90%;
    }

    #checkbox {
        margin-left: 0;
        margin-right: 0;
        margin-top: 10px;
    }
}

#question {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    width: 100%;
}
#alternatives {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    width: 100%;
}

#quiz_question {
    margin-right: 20px;
    font-size: 40px;
    font-weight: bold;
    color: var(--fourth-color);
    margin-top: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 50%;
    background-color: var(--secondary-color);
    padding: 5px 7px;
}

#input {
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
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

#QuizQuestionMultichoice {
    display: flex;
    flex-direction: column;
    align-items: center;
    margin-top: 3vw;
    justify-content: center;
    #checkbox {
        margin-left: 0;
        margin-right: 0;
        margin-top: 10px;
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
        text-align: center;
    }
  #alternativesMulti {
    display: flex;
    flex-direction: column;
    align-items: center;
    #input {
        margin-top: 5px;
        font-size: 30px;
        padding-top: 5px;
        padding-bottom: 5px;
        padding-left: 10px;
        background-color: var(--fifth-color);
        border-radius: 5px;
        width: 60vw;
    }
  }
}

#checkbox {
  vertical-align: middle; /* Align checkbox with label vertically */
}

</style>
