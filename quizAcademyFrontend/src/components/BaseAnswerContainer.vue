<template>
    <div>
        <img :src="base64Image" alt="" class="image" v-if="base64Image && !hasAnswered" />
        <h1 v-if="hasAnswered && isCorrect" class="correct">Correct answer!</h1>
        <h1 v-if="hasAnswered && !isCorrect" class="wrong">Wrong answer!</h1>
        <component
            :is="answerComponent"
            :question="question"
            :hasAnswered="hasAnswered"
            @displayResults="displayResults"
            class="component"
        />
        <button v-if="hasAnswered" @click="handleNext;" class="nextQuestion">Next</button>
    </div>
</template>

<script>
import MultipleChoiceAnswers from "./questionPlaythroughComponets/MultipleChoiceQuestionPlaythrough.vue";
import logo from "../assets/logo.png";
import TextInputAnswers from "./questionPlaythroughComponets/TextInputQuestionPlaythrough.vue";
import TrueFalseAnswers from "./questionPlaythroughComponets/TrueFalseQuestionPlaythrough.vue";

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
            base64Image: ""
        };
    },
    mounted() {
        const base64Image = this.question.imageBase64;
        this.displayImage(base64Image);
        console.log(base64Image);
    },
    computed: {
        answerComponent() {
            switch (this.question.type) {
                case "MULTIPLE_CHOICE":
                    return MultipleChoiceAnswers;
                case "TEXT_INPUT":
                    return TextInputAnswers;
                case "DRAG_AND_DROP":
                    return "BaseInputNumber";
                case "TRUE_FALSE":
                    return TrueFalseAnswers;
                default:
                    return "BaseInputText";
            }
        }
    },
    methods: {
        finishQuiz() {},
        displayResults(isCorrect) {
            this.hasAnswered = true;
            this.isCorrect = isCorrect;
            if (isCorrect) {
                this.score = 1;
            } else {
                this.score = 0;
            }
        },
        handleNext() {
            this.hasAnswered = false;
            this.$emit("nextQuestion", this.score);
        },
        displayImage(base64String) {
            const validBase64Regex = /^[A-Za-z0-9+/=]+$/;
            if (base64String && validBase64Regex.test(base64String)) {
                const binaryString = window.atob(base64String);
                const len = binaryString.length;
                const bytes = new Uint8Array(len);
                for (let i = 0; i < len; i++) {
                    bytes[i] = binaryString.charCodeAt(i);
                }
                const blob = new Blob([bytes], { type: "image/jpeg" });
                this.base64Image = URL.createObjectURL(blob);
            }
        }
    }
};
</script>

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
    border-radius: 10px;
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
