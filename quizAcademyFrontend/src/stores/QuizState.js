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
                console.log("quizQuestions was reset to: ", this.quizQuestions);
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
                console.log("quizQuestionStates was called when not an array. Value of quizQuestions: ", this.quizQuestionStates);
                this.quizQuestionStates = [store];
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
                console.log("added textinput question to state successfully");
            } else {
                console.log("quizQuestions was called when not an array. Value of quizQuestions: ", this.quizQuestionStates);
                store.quizId = questionState.quizId;
                store.questionId = questionState.questionId;
                store.questionText = questionState.questionText;
                store.correctAnswers = questionState.correctAnswers;
                this.quizQuestionStates = [store];
                console.log("attempted to reset quizQuestionStates to an array: ", this.quizQuestionStates);
            }
        },
        resetQuestionStates() {
            this.quizQuestionStates = [];
        },
        fromQuestionToQuestionState(questionIndex) {
            const question = this.quizQuestions[questionIndex];
            if(question.type === "DRAG_AND_DROP") {
                console.log("id DRAG_AND_DROP found");
                const questionState = useDragDropStore();
                questionState.setQuestionValues(question.quizId, question.questionId, question.questionText, question.categories);
                return("/create_quiz/drag_and_drop");
            } else if(question.type === "MULTIPLE_CHOICE") {
                console.log("id MULTIPLE_CHOICE found");
                const questionState = useMultichoiceStore();
                questionState.setQuestionValues(question.quizId, question.questionId, question.questionText, question.alternatives, question.correctAlternatives);
                return("/create_quiz/multichoice");
            } else if(question.type === "TEXT_INPUT") {
                console.log("id TEXT_INPUT found");
                const questionState = useTextInputStore();
                questionState.setQuestionValues(question.quizId, question.questionId, question.questionText, question.answers);
                return ("/create_quiz/text_input");
            } else {
                console.log("fromQuestionToQuestionState was called with an invalid question type. Value of question type: ", question.questionType);
            }
        },
        swapQuestions(index1, question) {
            this.quizQuestions.splice(index1, 1);
            this.quizQuestions[index1] = question;
        },
        getIndexById(questionId){
            for(let i = 0; i < this.quizQuestions.length; i++) {
                if(this.quizQuestions[i].questionId === questionId) {
                    return i;
                }
            }
            return -1;
        }
    }
});