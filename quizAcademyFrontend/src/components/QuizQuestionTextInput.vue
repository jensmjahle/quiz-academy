<script setup>
import { ref} from 'vue';
import { useRouter } from 'vue-router';

let router = useRouter();

let questionText = ref('');
let questionAnswer = ref('');

function submitQuestion() {
    const questionObject = {
        TI: 'TI',
        question: questionText.value,
        answer: questionAnswer.value,
    };
    console.log(questionObject);
    router.push('/create_quiz');
}
</script>

<template>
    <div id = "full_question">
        <div id = "text_response_question">
            <input id="input" type="text" v-model="questionText" placeholder="Question" />
            <input id="input" type="text" placeholder="Answer" />
        </div>
        <div>
            <h5>Separate correct answers with: *</h5>
        </div>
        <div>
            <button @click="submitQuestion">Submit</button>
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