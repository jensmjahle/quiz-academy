import { defineStore } from 'pinia';

export const useStore = defineStore({
    // unique id of the store across your application
    id: 'main',
    state: () => ({
        quizId: null,
        quizName: null
    }),
    actions: {
        setQuizId(id) {
            this.quizId = id;
        },
        resetQuizId() {
            this.quizId = null;
        },
        setQuizName(name) {
            this.quizName = name;
        },
        initializeQuiz(quizId, quizName) {
            this.setQuizId(quizId);
            this.setQuizName(quizName);
        }
    }
});