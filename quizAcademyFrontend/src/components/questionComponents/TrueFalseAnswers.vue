<template>
  <div class="alternative-buttons">
    <button
        :disabled="hasAnswered"
        :class="{
        'trueButton': true,
        'correct': hasAnswered && (question.correctAnswer === true),
        'incorrect': hasAnswered && (question.correctAnswer === false),
        'selectedAnswer':hasAnswered && (true === answer),
        'notSelected': hasAnswered && (false === answer)}"
        @click="checkAnswer(true)">True</button>

    <button
        :disabled="hasAnswered"
        :class="{
        'falseButton': true,
        'correct': hasAnswered && (question.correctAnswer === false),
        'incorrect': hasAnswered && (question.correctAnswer === true),
        'selectedAnswer': hasAnswered && (false === answer),
        'notSelected': hasAnswered && (true === answer)}" @click="checkAnswer(false)">False</button>

  </div>
</template>

<script>

export default {
    props: {
        question: {
            type: Object,
            required: true
        },
        hasAnswered: {
            type: Boolean,
            required: true
        }
    },

    data() {
        return {
            isCorrect: false,
            answer: null
        }
    },
    methods: {
        checkAnswer(answer) {
            const correctAnswers = this.question.correctAnswer;
            this.answer = answer;
            this.isCorrect = correctAnswers === answer;

            this.$emit('displayResults', this.isCorrect);
        }
    }
}
</script>

<style scoped>

button {
    width: 100%;
    font-weight: bold;
    border-width: 5px;
    transition: 0.3s;
}

button.trueButton
 {
    background-color: var(--correct-answer-color);
    border-color: var(--correct-answer-border-color);
}
button.trueButton:hover {
    background-color: var(--correct-answer-border-color);
}

button.falseButton {
    background-color: var(--wrong-answer-color);
    border-color: var(--wrong-answer-border-color);
}
button.falseButton:hover {
    background-color: var(--wrong-answer-border-color);
}
button.correct {
    background-color: var(--correct-answer-color);
    border-color: var(--correct-answer-border-color);
    transition: 0.3s;
}

button.incorrect {
    background-color: var(--wrong-answer-color);
    border-color: var(--wrong-answer-border-color);
    border-style: dotted;
    transition: 0.3s;
}

button.incorrect:hover {
    background-color: var(--wrong-answer-color);
    cursor: default;
}

button.correct:hover {
    background-color: var(--correct-answer-color);
    cursor: default;
}
button.selectedAnswer:disabled {
    color: var(--base-color);

}
button.notSelected {
    color: var(--base-color);
    opacity: 0.3;
}

</style>