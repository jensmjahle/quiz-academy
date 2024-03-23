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
    padding-right: 10px; /* Add padding to prevent content from overlapping the scrollbar */
}

.quiz-item:hover {
    background-color: #213547; /* Change background color on hover */
}
</style>
