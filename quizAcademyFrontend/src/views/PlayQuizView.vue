<template>
  <div class="header">
    <div class="underline">
      <h5 >{{store.quiz.quizName}}: </h5>
      <h5 class="qNr">Question {{questionNr+1}} of {{store.quiz.questions.length}}</h5>
      <h5 class="user">Created By: {{store.quiz.user}}</h5>
    </div>
    <div class="question" v-if="!isCompleted">
      <h2 v-if="store.quiz.questions && store.quiz.questions.length > 0">
        {{ store.quiz.questions[questionNr].questionText }}
      </h2>
    </div>
  </div>
  <div class="answers" v-if="!isCompleted">
    <BaseAnswerContainer :question="store.getCurrentQuestion" @nextQuestion='nextQuestion' class="component"></BaseAnswerContainer>
  </div>
  <div v-else>
    <Summary :score="store.getScore" :total-score="questionNr+1" @finishQuiz='finishQuiz' @resetQuiz='resetQuiz' ></Summary>


  </div>

</template>

<script>
import { usePlayQuizStore } from "../stores/playQuizStore.js";
import router from "../router/index.js";
import BaseAnswerContainer from "../components/BaseAnswerContainer.vue";
import Summary from "../components/Summary.vue";
export default {
    components: { Summary, BaseAnswerContainer },
    data() {
        return {
            store: usePlayQuizStore(),
            questionNr: 0,
            question: null,
            quizName: null,
            isCompleted: false,
        };
    },
    setup() {
        if (usePlayQuizStore().quiz == null) {
            router.push("/");
        }
    },
    methods: {
        nextQuestion(score) {
            if (this.questionNr < this.store.quiz.questions.length-1) {
                this.store.nextQuestion(score);
                this.questionNr++;
            } else {
                this.store.lastQuestion(score);
                this.isCompleted = true;
            }
        },
        finishQuiz() {
            this.store.finishQuiz();
            router.push("/");
        },
        resetQuiz() {
            this.store.resetQuiz();
            this.questionNr = 0;
            this.isCompleted = false;
        },
    },
}


</script>

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