<template>
  <div class="container">
    <div class="correctAnswers" v-if="hasAnswered">

      <h4>Accepted answers:</h4>
      <ul>
        <li
            v-for="answer in question.answers"
            :key="answer.id">
          <h5>{{answer}}</h5>
        </li>
      </ul>
    </div>
    <input
        v-model="answer"
        :placeholder="'Answer here...'"
        class="field"
        :class="{ 'correct': hasAnswered && isCorrect,
                   'incorrect': hasAnswered && !isCorrect}"
        data-cy="answer-input"
    />
    <button class="submitButton" @click="checkAnswer(answer)" :disabled="hasAnswered">Submit</button>
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
            const correctAnswers = this.question.answers;
            answer.toLowerCase();
            this.isCorrect = correctAnswers.includes(answer);

            this.$emit('displayResults', this.isCorrect);
        }
    }
}
</script>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    height: 100%;
}
.field {
    height: 100%;
    padding: 1em;
    border-radius: 10px;
    border: 5px solid var(--primary-color);
    font-weight: bold;
}
.submitButton {
    width: 100%;
        background-color: var(--secondary-color);
        border-color: var(--primary-color);
        color: var(--base-color);
        font-weight: bold;
        border-width: 5px;
        transition: 0.3s;
}
.submitButton:hover:enabled{
    background-color: var(--primary-color);
    color: var(--base-color);
}
.submitButton:disabled {
    opacity: 50%;
    cursor: default;
}

.correctAnswers {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-bottom: 1em;
    padding: 3px;
    border-radius: 10px;
    background-color: rgba(var(--base-color-rgb), 0.1);
}
.correct {
    border-color: var(--correct-answer-border-color);
    color: var(--correct-answer-border-color) ;
}
.incorrect {
    border-color: var(--wrong-answer-border-color);
    color: var(--wrong-answer-border-color);
}
</style>