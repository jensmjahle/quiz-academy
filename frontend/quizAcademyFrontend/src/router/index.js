import MainView from "../views/MainView.vue";
import {createRouter, createWebHistory} from "vue-router";
import QuizzesView from "../views/QuizzesView.vue";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: MainView,
        },
        {
            path: "/quizzes",
            component: QuizzesView,
        }

    ],
});
