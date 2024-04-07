import { createRouter, createWebHistory } from "vue-router";
import HomepageView from "../views/HomepageView.vue";
import QuizzesView from "../views/QuizzesView.vue";
import LoginView from "../views/QuizLoginView.vue";
import CreateQuizzesView from "../views/CreateQuizzesView.vue";
import MultichoiceView from "../views/MultichoiceView.vue";
import DragAndDropView from "../views/DragAndDropView.vue";
import TextInputView from "../views/TextInputView.vue";
import SigninWiew from "../views/QuizSigninView.vue";
import * as path from "path";
import PlayQuizView from "../views/PlayQuizView.vue";
import { usePlayQuizStore } from "../stores/playQuizStore.js";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: HomepageView
        },
        {
            path: "/quizzes",
            name: "quizzes",
            component: QuizzesView
        },
        {
            path: "/login",
            name: "login",
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
            name: "signup",
            component: SigninWiew
        },
        {
            path: "/play_quiz/",
            name: "play_quiz",
            component: PlayQuizView,
            beforeEnter: (to, from, next) => {
                const playQuizStore = usePlayQuizStore(); // Access the playQuiz store
                if (!playQuizStore.getQuiz) {
                    next({ path: "/" }); // Redirect to homepage if playQuiz is null
                } else {
                    next(); // Proceed to the route
                }
            }
        }
    ]
});
