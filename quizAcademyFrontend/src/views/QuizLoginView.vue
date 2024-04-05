<template>
    <div class="quiz-view">
        <QuizComponent />
    </div>
</template>

<script>
import QuizComponent from "../components/QuizLogin.vue";

export default {
    components: {
        QuizComponent
    }
};
</script>

<style>
.quiz-view {
    display: flex;
    justify-content: center;
    align-items: center;
}
</style>
