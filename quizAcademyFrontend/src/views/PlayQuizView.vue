<script>
import { usePlayQuizStore } from "../stores/playQuizStore.js";
import { onMounted } from "vue";
export default {
    data() {
        return {
            store: usePlayQuizStore(),
            questionNr: 0,
            question: null,
            quizName: null,
        };
    },
    setup() {
        onMounted(() => {
            if (!this.store.quiz) {
                this.$router.push("/");
                alert("No quiz found. Please try again.");
            }
            this.quizName = this.store.quiz.name;
            this.question = this.store.getProgress();
        });
    },
}


</script>

<template>
    <div class="header">
        <h1 class="qNr">Q.{{questionNr}}</h1>
        <h1 >{{store.quiz.quizName}}</h1>
    </div>
    <div class="question">
        <h2>{{question.question}}</h2>
    </div>
    <div class="answers">
        // to do - add answers
    </div>

</template>

<style scoped>
    .header {
        display: flex;
        flex-direction: row;
        align-items: center;
        margin: 50px;
        justify-content: space-between;
        width: 100%;

        .qNr {
            color: var(--secondary-color);
        }
    }

</style>