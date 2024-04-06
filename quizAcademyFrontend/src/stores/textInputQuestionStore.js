import { defineStore } from 'pinia';

export const useTextInputStore = defineStore({
    id: 'textInputQuestion',
    state: () => ({
        questionType: "TI",
        quizId: null,
        questionId: null,
        questionText: null,
        correctAnswers: [],
    }),
    actions: {
        setQuestionValues(quizId, questionStateId, questionText, correctAnswers) {
            this.quizId = quizId;
            this.questionId = questionStateId;
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