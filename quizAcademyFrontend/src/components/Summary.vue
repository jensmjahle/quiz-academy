<script>
export default {
  props: {
      score: {
          type: Number,
          required: true
      },
      totalScore: {
          type: Number,
          required: true
      }

  },
    methods: {
        finishQuiz() {
            this.$emit('finishQuiz');
        },
        resetQuiz() {
            this.$emit('resetQuiz');
        }
}
}
</script>

<template>
<div class="container">
    <h2>Quiz Finished</h2>
    <h3>You got {{score}} correct out of {{totalScore}} total</h3>
    <div class="buttons">
        <button @click="finishQuiz">Finish</button>
        <button @click="resetQuiz">Try Again</button>
    </div>
</div>
</template>

<style scoped>
.container {
    display: flex;
    flex-direction: column;
    justify-content: center;
    gap: 20px;
    text-align: center;
    width: 100%;
}
.buttons {
    display: flex;
}
button {
   flex:1;
}

</style>