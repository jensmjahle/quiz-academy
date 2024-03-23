import { createRouter, createWebHistory } from "vue-router";
import HomepageView from "../views/HomepageView.vue";
import QuizzesView from "../views/QuizzesView.vue";

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
        }
    ]
});
