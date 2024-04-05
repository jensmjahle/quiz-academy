import { defineStore } from 'pinia';

export const useStore = defineStore({
    // unique id of the store across your application
    id: 'main',
    state: () => ({
        quizId: null,
        quizName: null,
        quizDescription: null,
    }),
    actions: {
        quizQuestions: [],
        setQuizId(id) {
            this.quizId = id;
        },
        resetQuizId() {
            this.quizId = null;
        },
        setQuizName(name) {
            this.quizName = name;
        },
        resetQuizName() {
            this.quizName = null;
        },
        setQuizQuestions(questions) {
            if(Array.isArray(questions)){
                this.quizQuestions = questions;
            } else {
                console.log("setQuizQuestions was called when questions was not an array. Value of questions: ", questions);
            }
        },
        addQuestion(question) {
            if(Array.isArray(this.quizQuestions)) {
            this.quizQuestions.push(question);
            } else {
                console.log("quizQuestions was called when not an array. Value of quizQuestions: ", this.quizQuestions);
                this.quizQuestions = [question];
            }
        },
        resetQuizQuestions() {
            this.quizQuestions = [];
        },
        setQuizDescription(description) {
            this.quizDescription = description;
        },
        resetQuizDescription() {
            this.quizDescription = null;
        },
        initializeQuiz(quizId, quizName, questions, quizDescription) {
            this.setQuizId(quizId);
            this.setQuizName(quizName);
            this.setQuizDescription(quizDescription);
            this.setQuizQuestions(questions);
        },
        resetQuiz() {
            this.resetQuizId();
            this.resetQuizName();
            this.resetQuizQuestions();
            this.resetQuizDescription();
        }
    }
});