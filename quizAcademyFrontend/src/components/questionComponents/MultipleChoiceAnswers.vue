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
            correctAnswers: [],
            isCorrect: false,
            alternatives: [],
            selectedPosition: null,
        }
    },
    methods: {
        checkAnswer(index) {
            this.alternatives = this.question.alternatives;
            this.correctAnswers = this.question.correctAlternatives;

            this.selectedPosition = index;
            this.isCorrect = this.correctAnswers[index] === 'true';
            this.$emit('displayResults', this.isCorrect);
        }
    }
}
</script>

<template>

    <div class="alternative-buttons">
        <button v-for="(alternative, index) in question.alternatives"
                :key="alternative"
                @click="checkAnswer(index)"
                :disabled="hasAnswered"
                :class="{ 'correct': hasAnswered && (correctAnswers[index] === 'true'),
                           'incorrect': hasAnswered && (correctAnswers[index] === 'false'),
                            'selectedAnswer': hasAnswered && (index === selectedPosition),
                            'notSelected': hasAnswered&&(index !== selectedPosition)}">
            {{ alternative }}
        </button>
    </div>
</template>

<style scoped>
.alternative-buttons {
    display: grid;
    gap: 1em;
    justify-content: center;
    transition: 0.3s;
}

@media screen and (max-width: 600px) {
    .alternative-buttons {
        grid-template-columns: 1fr;
        button {
            font-size: calc(2em + 1vw);
        }
    }
}

@media screen and (min-width: 601px) {
    .alternative-buttons {
        grid-template-columns: repeat(2, 1fr);
        button {
            font-size: calc(1em + 1vw);
        }
    }
}

button {
    width: 100%;
    background-color: var(--secondary-color);
    border-color: var(--primary-color);
    font-weight: bold;
    border-width: 5px;
    transition: 0.3s;
}

button:hover {
    background-color: var(--primary-color);
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

button.notSelected {
    opacity: 0.3;
}
</style>
