import MainView from "../views/MainView.vue";
import {createRouter, createWebHistory} from "vue-router";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: MainView,
        }

    ],
});
