import { defineStore } from 'pinia';

export const useMultichoiceStore = defineStore({
    id: 'multiChoiceQuestion',
    state: () => ({
        questionType: "MC",
        quizId: null,
        questionId: null,
        questionText: null,
        questionAlternatives: [],
        correctAlternatives: []
    }),
    actions: {
        setQuestionValues(quizId, questionId, questionText, questionAlternatives, correctAlternatives) {
            this.quizId = quizId;
            this.questionId = questionId;
            this.questionText = questionText;
            this.questionAlternatives = questionAlternatives;
            this.correctAlternatives = correctAlternatives;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.questionAlternatives = [];
            this.correctAlternatives = [];
        },
    }
});