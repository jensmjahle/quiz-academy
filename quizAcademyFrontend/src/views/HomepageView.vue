<script setup>
import QuizList from "../components/QuizList.vue";
</script>

<template>
    <div id="body">
        <h2>This is the homepage!!!</h2>
        <QuizList />
        <QuizList />

    </div>
</template>

<style scoped>
#body {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1;
}
</style>
