import MainView from "../views/HomepageView.vue";
import {createRouter, createWebHistory} from "vue-router";
import HomepageView from "../views/HomepageView.vue";
import App from "../App.vue";

export default createRouter({
    history: createWebHistory(),
    routes: [
        {
            path: "/",
            component: HomepageView,
        }

    ],
});
