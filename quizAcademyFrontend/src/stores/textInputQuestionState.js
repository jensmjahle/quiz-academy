import { defineStore } from 'pinia';

export const useTextInputStore = defineStore({
    id: 'textInputQuestion',
    state: () => ({
        questionType: "IT",
        quizId: null,
        questionStateId: null,
        questionText: null,
        correctAnswers: [],
    }),
    actions: {
        setQuestionValues(quizId, questionStateId, questionText, correctAnswers) {
            this.quizId = quizId;
            this.questionStateId = questionStateId;
            this.questionText = questionText;
            this.correctAnswers = correctAnswers;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionStateId = null;
            this.questionText = null;
            this.correctAnswers = [];
        },
    }
});