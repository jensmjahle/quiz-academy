import { defineStore } from 'pinia';

export const useTextInputStore = defineStore({
    id: 'textInputQuestion',
    state: () => ({
        questionType: "TI",
        quizId: null,
        questionId: null,
        questionText: null,
        correctAnswers: [],
        questionImage: null,
    }),
    actions: {
        setQuestionValues(quizId, questionStateId, questionText, correctAnswers, questionImage) {
            this.quizId = quizId;
            this.questionId = questionStateId;
            this.questionText = questionText;
            this.questionImage = questionImage;
            if(Array.isArray(correctAnswers)){
                this.correctAnswers = correctAnswers;
            } else {
                this.correctAnswers = [correctAnswers];
            }
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.correctAnswers = [];
            this.questionImage = null;
        },
    }
});