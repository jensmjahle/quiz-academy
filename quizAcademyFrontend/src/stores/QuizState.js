import { defineStore } from 'pinia';
import { useDragDropStore } from './dragAndDropQuestionState';
import { useMultichoiceStore } from './multichoideQuestionState.js';
import { useTextInputStore } from './textInputQuestionState.js';

export const useQuizStore = defineStore({
    // unique id of the store across your application
    id: 'main',
    state: () => ({
        quizId: null,
        quizName: null,
        quizDescription: null,
    }),
    actions: {
        quizQuestions: [],
        quizQuestionStates: [],
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
            this.resetQuestionStates();
        },
        addDragDropQuestionState(questionState) {
            const store = useDragDropStore();
            if(Array.isArray(this.quizQuestionStates)) {
                store.quizId = questionState.quizId;
                store.questionId = questionState.questionId;
                store.questionText = questionState.questionText;
                store.questionCategories = questionState.questionCategories;
                this.quizQuestionStates.push(store);
            } else {
                console.log("quizQuestions was called when not an array. Value of quizQuestions: ", this.quizQuestionStates);
            }
        },
        addMultichoiceQuestionState(questionState) {
            const store = useMultichoiceStore();
            if(Array.isArray(this.quizQuestionStates)) {
                store.quizId = questionState.quizId;
                store.questionId = questionState.questionId;
                store.questionText = questionState.questionText;
                store.questionAlternatives = questionState.questionCategories;
                store.correctOptions = questionState.correctOptions;
                this.quizQuestionStates.push(store);
            } else {
                console.log("quizQuestions was called when not an array. Value of quizQuestions: ", this.quizQuestionStates);
            }
        },
        addTextInputQuestionState(questionState) {
            const store = useTextInputStore();
            if(Array.isArray(this.quizQuestionStates)) {
                store.quizId = questionState.quizId;
                store.questionId = questionState.questionId;
                store.questionText = questionState.questionText;
                store.correctAnswers = questionState.correctAnswers;
                this.quizQuestionStates.push(store);
            } else {
                console.log("quizQuestions was called when not an array. Value of quizQuestions: ", this.quizQuestionStates);
            }
        },
        resetQuestionStates() {
            this.quizQuestionStates = [];
        }
    }
});