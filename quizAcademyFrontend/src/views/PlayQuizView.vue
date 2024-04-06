<script>
import { usePlayQuizStore } from "../stores/playQuizStore.js";
import router from "../router/index.js";
import BaseAnswerContainer from "../components/BaseAnswerContainer.vue";
export default {
    components: { BaseAnswerContainer },
    data() {
        return {
            store: usePlayQuizStore(),
            questionNr: 0,
            question: null,
            quizName: null,
        };
    },
    setup() {
        if (usePlayQuizStore().quiz == null) {
            console.log("No quiz found");
            router.push("/");
        }
    },
    methods: {
        nextQuestion(score) {
            if (this.questionNr < this.store.quiz.questions.length-1) {
                this.store.nextQuestion(score);
                this.questionNr++;
            } else {
                console.log("Quiz finished");
                router.push("/quiz_results");
            }
        },
    },
}


</script>

<template>
    <div class="header">
        <div class="underline">
            <h5 >{{store.quiz.quizName}}: </h5>
            <h5 class="qNr">Question {{questionNr+1}} of {{store.quiz.questions.length}}</h5>
            <h5 class="user">Created By: {{store.quiz.user}}</h5>
        </div>
        <div class="question">
            <h2 v-if="store.quiz.questions && store.quiz.questions.length > 0">
                {{ store.quiz.questions[questionNr].questionText }}
            </h2>
        </div>

    </div>
    <div class="answers">
        <BaseAnswerContainer :question="store.getCurrentQuestion" @nextQuestion='nextQuestion' class="component"></BaseAnswerContainer>
    </div>

</template>

<style scoped>
    .header {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        margin: 20px;
        width: 90vw;


        .qNr, .user {
            color: var(--secondary-color);
        }
    }
    .underline {
        display: flex;
        flex-direction: row;
        justify-content: space-between;
        width: 100%;
        border-bottom: 1px solid var(--secondary-color);

    }

 .component {
        display: flex;
        flex-direction: column;
        align-items: center;
        justify-content: space-between;
        width: 100%
    }

    @media screen and (min-width: 601px) {
        .header {
            display: flex;
            flex-direction: column;
            align-items: center;
            justify-content: space-between;
            width: 75vw;


            .qNr, .user {
                color: var(--secondary-color);
            }
        }
        .underline {
            display: flex;
            flex-direction: row;
            justify-content: space-between;
            width: 100%;
            border-bottom: 1px solid var(--secondary-color);

        }

    }

</style>