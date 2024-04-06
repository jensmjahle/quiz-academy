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
            this.shuffleQuestions(this.quiz.questions);
            this.currentQuestion = this.quiz.questions[this.progress];
        },
        nextQuestion(score) {
            this.progress++;
            console.log("score", score)
            this.score += score;
            console.log("this.score", this.score)
            this.currentQuestion = this.quiz.questions[this.progress];
        },
        lastQuestion(score) {
            this.progress++;
            this.score += score;
            this.currentQuestion = null;
        },
        finishQuiz() {
            this.quiz = null;
            this.progress = 0;
            this.currentQuestion = null;
            this.score = 0;
        },
        resetQuiz() {
            this.progress = 0;
            this.shuffleQuestions(this.quiz.questions);
            this.currentQuestion = this.quiz.questions[this.progress];
            this.score = 0;
        },
        shuffleQuestions(questions) {
            let currentIndex = questions.length;
            let temporaryValue, randomIndex;

            while (currentIndex !== 0) {
                randomIndex = Math.floor(Math.random() * currentIndex);
                currentIndex -= 1;

                temporaryValue = questions[currentIndex];
                questions[currentIndex] = questions[randomIndex];
                questions[randomIndex] = temporaryValue;
            }

            return questions;
        },
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
