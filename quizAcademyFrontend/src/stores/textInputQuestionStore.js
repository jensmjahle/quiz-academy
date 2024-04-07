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
            console.log("correctAnswers when entering setQuestionValues: ", correctAnswers)
            this.quizId = quizId;
            this.questionId = questionStateId;
            this.questionText = questionText;
            if(Array.isArray(correctAnswers)){
                this.correctAnswers = correctAnswers;
                console.log("setQuestionValues was called. Value of correctAnswers: ", correctAnswers);
            } else {
                this.correctAnswers = [correctAnswers];
                console.log("setQuestionValues was called when correctAnswers was not an array. Attempted fix. Value of correctAnswers: ", correctAnswers);
            }
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.correctAnswers = [];
        },
    }
});