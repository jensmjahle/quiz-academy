<template>
    <li
        class="quiz-item"
        @click="openDialog"
        @mouseenter="hovering = true"
        @mouseleave="hovering = false"
        :style="{ backgroundImage: 'url(' + imageUrl + ')' }"
    >
        <div class="quiz-content">
            <div class="always-visible">


            <h3 class="quiz-name">{{ quiz.quizName }}</h3>
            <h5 v-if="quiz.questions" class="number-of-questions">
                {{ quiz.questions.length }} question(s)
            </h5>
            <h5 v-else class="number-of-questions">Number of questions not available</h5>
            </div>

            <h5 class="quiz-description" >{{ quiz.quizDescription }}</h5>
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
    border: 1px solid var(--secondary-color);
    padding: 10px;
    margin: 5px; /* Add margin between items */
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
    text-overflow: ellipsis;

    h3 {
        color: var(--fourth-color);
    }
}
.quiz-content {
    border-radius: 10px;
    transition: 0.3s;

}
.always-visible {
    background-color: rgba(var(--base-contrast-color-rgb), 0.8);
    transition: 0.3s;
    border-radius: 10px;
}

.quiz-item:hover {
    background-color: var(--tertiary-color); /* Change background color on hover */
    transition: 0.3s;
    h3 {
        font-size: calc(1vw + 1.5vh);
        transition: 0.3s;
    }
    .quiz-description {
        overflow: hidden; /* Hide overflow */
        text-overflow: ellipsis; /* Add ellipsis (...) if text overflows */
        transition: 0.3s;
        opacity: 100;
    }
    .quiz-content {
        background-color: rgba(var(--base-contrast-color-rgb), 1);
        transition: 0.3s;
        opacity: 100;
    }
}


.quiz-description {
    overflow: hidden; /* Hide overflow */
    text-overflow: ellipsis; /* Add ellipsis (...) if text overflows */
    opacity: 0;

}
.quiz-name {
    line-height: 1;
}

</style>
