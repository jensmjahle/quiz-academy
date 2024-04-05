import { defineStore } from 'pinia';

export const useStore = defineStore({
    // unique id of the store across your application
    id: 'main',
    state: () => ({
        quizId: null,
        quizName: null,
        quizQuestions: [],
        quizDescription: null,
    }),
    actions: {
        setQuizId(id) {
            this.quizId = id;
        },
        resetQuizId() {
            this.quizId = null;
        },
        setQuizName(name) {
            this.quizName = name;
        },
        resetQuizName() {
            this.quizName = null;
        },
        setQuizQuestions(questions) {
            this.quizQuestions = questions;
        },
        resetQuizQuestions() {
            this.quizQuestions = [];
        },
        setQuizDescription(description) {
            this.quizDescription = description;
        },
        resetQuizDescription() {
            this.quizDescription = null;
        },
        initializeQuiz(quizId, quizName, questions, quizDescription) {
            this.setQuizId(quizId);
            this.setQuizName(quizName);
            this.setQuizQuestions(questions);
            this.setQuizDescription(quizDescription);
        },
        resetQuiz() {
            this.resetQuizId();
            this.resetQuizName();
            this.resetQuizQuestions();
            this.resetQuizDescription();
        }
    }
});