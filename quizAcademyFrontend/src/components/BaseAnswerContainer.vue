<script>
import MultipleChoiceAnswers from '../components/questionComponents/MultipleChoiceAnswers.vue';
import logo from "../assets/logo.png";
import TextInputAnswers from "./questionComponents/TextInputAnswers.vue";

export default {
    props: {
        question: {
            type: Object,
            required: true
        }
    },
    data() {
        return {
            hasAnswered: false,
            isCorrect: false,
            logo: logo,
            score: 0,
      }
    },
    computed: {
        answerComponent() {
            switch (this.question.type) {
                case 'MULTIPLE_CHOICE':
                    return MultipleChoiceAnswers
                case 'TEXT_INPUT':
                    return TextInputAnswers
                case 'DRAG_AND_DROP':
                    return 'BaseInputNumber'
                case 'TRUE_FALSE':
                    return 'BaseInputDate'
                default:
                    return 'BaseInputText'
            }
        }
    },
    methods: {
        finishQuiz() {
        },
        displayResults(isCorrect) {
            this.hasAnswered = true
            this.isCorrect = isCorrect
            if(isCorrect){
                this.score += 1
            }
        },
        handleNext(){
            this.hasAnswered = false
            this.$emit('nextQuestion',this.score)

        }

    }
}

</script>

<template>
<div>
    <img :src="logo" alt="logo" class="image" v-if="!hasAnswered"/>
    <h1 v-if="hasAnswered && isCorrect" class="correct">Correct answer!</h1>
    <h1 v-if="hasAnswered && !isCorrect"class="wrong">Wrong answer!</h1>
    <component :is="answerComponent" :question="question"  :hasAnswered="hasAnswered" @displayResults="displayResults" class="component"/>
    <button v-if="hasAnswered" @click=(handleNext) class="nextQuestion">Next</button>
</div>
</template>

<style scoped>
component {
    width: 100%;
}
.nextQuestion {
    margin-top: 20px;
    width: 80%;
font-size: 1.5em;
    transition: 0.3s;

}
.image {
    max-height: 40vh;
}
.correct {
    color: var(--correct-answer-border-color);
    margin-bottom: 20px;
}
.wrong {
    color: var(--wrong-answer-border-color);
    margin-bottom: 20px;
}

</style>