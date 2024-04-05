import { defineStore } from 'pinia';

export const useStore = defineStore({
    // unique id of the store across your application
    id: 'multiChoiceQuestion',
    state: () => ({
        quizId: null,
        questionId: null,
        questionText: null,
        questionAlternatives: [],
        correctOptions: null,
    }),
    actions: {
        setQuestionValues(quizId, questionId, questionText, questionAlternatives, correctOptions) {
            this.quizId = quizId;
            this.questionId = questionId;
            this.questionText = questionText;
            this.questionAlternatives = questionAlternatives;
            this.correctOptions = correctOptions;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.questionAlternatives = [];
            this.correctOptions = null;
        },
    }
});