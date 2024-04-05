import { defineStore } from 'pinia';

export const useStore = defineStore({
    // unique id of the store across your application
    id: 'inputTextQuestion',
    state: () => ({
        quizId: null,
        questionId: null,
        questionText: null,
        correctAnswers: [],
    }),
    actions: {
        setQuestionValues(quizId, questionId, questionText, correctAnswers) {
            this.quizId = quizId;
            this.questionId = questionId;
            this.questionText = questionText;
            this.correctAnswers = correctAnswers;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.correctAnswers = [];
        },
    }
});