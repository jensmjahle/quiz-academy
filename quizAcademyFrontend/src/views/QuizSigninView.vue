<template>
  <div class="quiz-view">
    <QuizSignin />
  </div>
</template>

<script>
import QuizSignin from '../components/QuizSignIn.vue';

export default {
  components: {
    QuizSignin
  }
};
</script>

<style>
.quiz-view {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}
</style>
