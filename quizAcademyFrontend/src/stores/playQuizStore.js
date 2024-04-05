import { defineStore } from "pinia";

export const usePlayQuizStore = defineStore("playQuiz", {
    state: () => ({
        quiz: null,
        progress: 0,
        currentQuestion: null,
        score: 0,
    }),

    persist: {
        storage: sessionStorage
    },
    actions: {
        startQuiz(quiz) {
            this.quiz = quiz;
            this.progress = 0;
            this.score = 0;
            this.currentQuestion = this.quiz.questions[this.progress];
        },
        nextQuestion(score) {
            this.progress++;
            this.score += score;
            this.currentQuestion = this.quiz.questions[this.progress];
        },
        finishQuiz() {
            this.quiz = null;
            this.progress = 0;
            this.currentQuestion = null;
            this.score = 0;
        }
    },
    getters: {
        getQuiz: (state) => {
            return state.quiz;
        },
        getProgress: (state) => {
            return state.progress;
        },
        getCurrentQuestion: (state) => {
            return state.currentQuestion;
        },
        getScore: (state) => {
            return state.score;
        }
    }
})
