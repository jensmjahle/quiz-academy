import { defineStore } from 'pinia';

export const useDragDropStore = defineStore({
    id: 'dragAndDropQuestion',
    state: () => ({
        questionType: "DD",
        quizId: null,
        questionId: null,
        questionText: null,
        questionCategories: [],
    }),
    actions: {
        setQuestionValues(quizId, questionId, questionText, questionCategories) {
            this.quizId = quizId;
            this.questionId = questionId;
            this.questionText = questionText;
            this.questionCategories = questionCategories;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.questionCategories = [];
        },
    }
});