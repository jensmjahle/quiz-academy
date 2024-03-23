import { createRouter, createWebHistory } from "vue-router";
import HomepageView from "../views/HomepageView.vue";
import MyQuizzesView from "../views/MyQuizzesView.vue";
import LoginView from "../views/QuizLoginView.vue";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: HomepageView
        },
        {
            path: "/quizzes",
            component: MyQuizzesView
        },
        {
            path: "/login",
            component: LoginView
        }
    ]
});
