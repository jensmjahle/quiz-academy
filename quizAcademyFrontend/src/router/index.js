import { createRouter, createWebHistory } from "vue-router";
import HomepageView from "../views/HomepageView.vue";
import QuizzesView from "../views/QuizzesView.vue";
import LoginView from "../views/QuizLoginView.vue";
import CreateQuizzesView from "../views/CreateQuizzesView.vue";
import MultichoiceView from "../views/MultichoiceView.vue";
import DragAndDropView from "../views/DragAndDropView.vue";
import TextInputView from "../views/TextInputView.vue";
import SigninWiew from "../views/QuizSigninView.vue";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: HomepageView
        },
        {
            path: "/quizzes",
            component: QuizzesView
        },
        {
            path: "/login",
            component: LoginView
        },
        {
            path: "/create_quiz",
            name: "create_quiz",
            component: CreateQuizzesView
        },
        {
            path: "/create_quiz/multichoice",
            name: "multichoice",
            component: MultichoiceView
        },
        {
            path: "/create_quiz/text_input",
            name: "text_input",
            component: TextInputView
        },
        {
            path: "/create_quiz/drag_and_drop",
            name: "drag_and_drop",
            component: DragAndDropView
        },
        {
            path: "/signup",
            component: SigninWiew
        }
    ]
});