import { defineStore } from "pinia";

export const useTrueFalseStore = defineStore({
    id: "trueFalseQuestion",
    state: () => ({
        questionType: "TF",
        quizId: null,
        questionId: null,
        questionText: null,
        questionAnswer: null,
        questionImage: null
    }),
    actions: {
        setQuestionValues(quizId, questionStateId, questionText, correctAnswer, questionImage) {
            this.quizId = quizId;
            this.questionId = questionStateId;
            this.questionText = questionText;
            this.questionAnswer = correctAnswer;
            this.questionImage = questionImage;
        },
        resetQuestionValues() {
            this.quizId = null;
            this.questionId = null;
            this.questionText = null;
            this.questionAnswer = null;
            this.questionImage = null;
        }
    }
});
