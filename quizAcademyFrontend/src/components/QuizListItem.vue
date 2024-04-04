<template>
    <li class="quiz-item" @click="openDialog" @mouseenter="hovering = true" @mouseleave ="hovering = false" :style="{ backgroundImage: 'url(' + imageUrl + ')' }">
       <div class="quiz-content" :class="{ 'show-description': showDescription }">
           <h3 class="quiz-name">{{ quiz.name }}</h3>
            <h5 class="quiz-description" v-if="hovering" >{{ quiz.quizDescription }}</h5>

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
            imageUrl: 'src/assets/logo.png'
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
    overflow: hidden;
    background-color: var(--secondary-color);
    color: var(--fourth-color);
    min-width: 20vw;
    max-width: 100vw;
    aspect-ratio: 1;
}

.quiz-item:hover {
    background-color: var(--tertiary-color); /* Change background color on hover */
    h3 {
        font-size: calc(1vw + 1.5vh);

    }
    .quiz-description {
        background-color: var(--base-contrast-color);
        color: var(--base-color);
        border-radius: 10px;
         padding: 2px;

    }

}

</style>
