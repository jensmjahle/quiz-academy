<script lang="ts">
import { useTokenStore } from "../stores/token";
import { getUserInfo } from "../utils/httputils.js";
import CreateQuiz from "../components/CreateQuiz.vue";

export default {
    components: { CreateQuiz },
    setup() {
        const tokenStore = useTokenStore();
        return { tokenStore };
    },
    async mounted() {
        if (!this.tokenStore.jwtToken) {
            console.log("Unauthenticated context");
        } else {
            console.log("Authenticated context");
            let response = this.tokenStore.loggedInUser;
            this.user = response.data;
        }
    },
    data() {
        return {
            user: null
        };
    }
};
</script>

<template>
    <div>
        <p>Quiz Academy</p>
        <div v-if="user" id="mainContainer">
            <h1>Create a new quiz</h1>
            <CreateQuiz />
        </div>
        <div v-if="!user">
            <h1>Please log in to continue</h1>
        </div>
    </div>
</template>

<style scoped></style>
