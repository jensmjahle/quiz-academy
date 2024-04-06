import { defineStore } from 'pinia';
import { useDragDropStore } from './dragAndDropQuestionStore.js';
import { useMultichoiceStore } from './multichoideQuestionStore.js';
import { useTextInputStore } from './textInputQuestionStore.js';

export const useQuizStore = defineStore({
    // unique id of the store across your application
    id: 'main',
    state: () => ({
        quizId: null,
        quizName: null,
        quizDescription: null,
        quizPublicStatus: false,
    }),

    persist: {
        storage: sessionStorage
    },
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
        setQuizPublicStatus(status) {
            this.quizPublicStatus = status;
        },
        resetQuizPublicStatus() {
            this.quizPublicStatus = false;
        },
        initializeQuiz(quizId, quizName, questions, quizDescription, status) {
            this.setQuizId(quizId);
            this.setQuizName(quizName);
            this.setQuizDescription(quizDescription);
            this.setQuizQuestions(questions);
            this.setQuizPublicStatus(status)
        },
        resetQuiz() {
            this.resetQuizId();
            this.resetQuizName();
            this.resetQuizQuestions();
            this.resetQuizDescription();
            this.resetQuestionStates();
            this.resetQuizPublicStatus();
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
        },
        fromQuestionToQuestionState(questionIndex) {
            const question = this.quizQuestions[questionIndex];
            if(question.questionType === "DD") {
                const questionState = useDragDropStore();
                questionState.setQuestionValues(question.quizId, question.questionId, question.questionText, question.questionCategories);
                return("/question/drag_and_drop");
            } else if(question.questionType === "MC") {
                const questionState = useMultichoiceStore();
                questionState.setQuestionValues(question.quizId, question.questionId, question.questionText, question.questionAlternatives);
                return("/question/multichoice");
            } else if(question.questionType === "TI") {
                const questionState = useTextInputStore();
                questionState.setQuestionValues(question.quizId, question.questionId, question.questionText, question.correctAnswers);
                return ("/question/text_input");
            } else {
                console.log("fromQuestionToQuestionState was called with an invalid question type. Value of question type: ", question);
            }
        }
    }
});