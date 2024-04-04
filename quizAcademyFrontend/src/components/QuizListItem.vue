<template>
    <li
        class="quiz-item"
        @click="openDialog"
        @mouseenter="hovering = true"
        @mouseleave="hovering = false"
        :style="{ backgroundImage: 'url(' + imageUrl + ')' }"
    >
        <div class="quiz-content">
            <h3 class="quiz-name">{{ quiz.quizName }}</h3>
            <h5 class="quiz-description" v-if="hovering">{{ quiz.quizDescription }}</h5>

            <h5 v-if="quiz.questions" class="number-of-questions">
                {{ quiz.questions.length }} questions
            </h5>
            <h5 v-else class="number-of-questions">Number of questions not available</h5>
        </div>
    </li>
</template>

<script>
export default {
    props: {
        quiz: Object,
        openDialog: Function
    },
    data() {
        return {
            hovering: false,
            showDescription: false,
            imageUrl: "src/assets/logo.png"
        };
    }
};
</script>

<style scoped>
.quiz-item {
    display: block;
    border: 1px solid #ccc;
    padding: 10px;
    margin: 10px; /* Add margin between items */
    cursor: pointer; /* Change cursor to pointer */
    border-radius: 10px;
    font-size: calc(1vw + 1vh); /* Adjust font size based on viewport size */
    text-align: center;
    background-size: cover;
    background-color: var(--secondary-color);
    min-width: 20vw;
    max-width: 100vw;
    aspect-ratio: 1;
    overflow-wrap: break-word; /* Break words that exceed the container width */
    word-break: break-word;
    transition: 0.3s;

    h3 {
        color: var(--fourth-color);
    }


}
.quiz-content {
    background-color: rgba(var(--base-contrast-color-rgb), 0.7);
    border-radius: 10px;
    transition: 0.3s;


}

.quiz-item:hover {
    background-color: var(--tertiary-color); /* Change background color on hover */
    transition: 0.3s;
    h3 {
        font-size: calc(1vw + 1.5vh);
        transition: 0.3s;
    }
    .quiz-description {
        transition: 0.3s;
    }
    .quiz-content {
        background-color: rgba(var(--base-contrast-color-rgb), 1);
        transition: 0.3s;
    }
}
</style>
