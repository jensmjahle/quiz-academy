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
            if(Array.isArray(questionCategories)) {
                this.questionCategories = questionCategories;
            } else {
                console.log("questionCategories was called when not an array. Value of questionCategories: ", questionCategories);
                this.questionCategories = [questionCategories];
                console.log("attempted to reset questionCategories to an array: ", this.questionCategories);
            }

        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.questionCategories = [];
        },
    }
});