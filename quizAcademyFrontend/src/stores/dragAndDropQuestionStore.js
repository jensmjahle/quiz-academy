import { defineStore } from 'pinia';

export const useDragDropStore = defineStore({
    id: 'dragAndDropQuestion',
    state: () => ({
        questionType: "DD",
        quizId: null,
        questionId: null,
        questionText: null,
        questionCategories: [],
        questionImage: null,
    }),
    actions: {
        setQuestionValues(quizId, questionId, questionText, questionCategories, questionImage) {
            this.quizId = quizId;
            this.questionId = questionId;
            this.questionText = questionText;
            this.questionCategories = questionCategories;
            this.questionImage = questionImage;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.questionCategories = [];
        },
    }
});