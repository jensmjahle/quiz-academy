<template>
    <li class="quiz-item" @click="openDialog">
        {{ quiz.name }}
    </li>
</template>

<script>
export default {
    props: {
        quiz: Object,
        openDialog: Function
    }
};
</script>

<style scoped>
.quiz-item {
    border: 1px solid #ccc;
    padding-top: 10px;
    padding-bottom: 10px;
    margin-bottom: 10px; /* Add margin between items */
    cursor: pointer; /* Change cursor to pointer */
    border-radius: 10px;
    font-size: calc(25px + 3vmin);
    text-align: center;
    background-color: var(--secondary-color);
    color: var(--fourth-color);
    word-break: break-word;
    word-wrap: break-word;
}

.quiz-item:hover {
    background-color: var(--tertiary-color); /* Change background color on hover */
}
</style>
