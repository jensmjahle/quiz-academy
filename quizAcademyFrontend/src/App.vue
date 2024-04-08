<template>
  <div id="app">
    <div v-if="!isPlayQuizPage" id="header">
      <h1 :style="{ fontSize: headerSize }">Quiz Academy</h1>
      <h5>{{ welcomeMessage }}</h5>
    </div>

    <navigation-bar @link-clicked="(args) => handleLinkClicked(args)" />

    <div id="body" :class="bodyClass">
      <router-view />
    </div>
    <div id="footer">
      <p>Quiz Academy 2024</p>
      <div class="contact">
        <p>Contact Us:</p>
        <a href="mailto:quiz.academy@gmail.com" class="email-button">Send mail</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { RouterView, useRoute } from "vue-router";
import { ref, onMounted, computed, watch } from "vue";
import NavigationBar from "./components/NavigationBar.vue";

const welcomeMessage = ref(
    "Welcome to Quiz Academy! Here you can take quizzes, create your own quizzes and much more!"
);
const headerSize = ref("calc(3vw + 3vh)");

// Method to handle emitted event from child component
const handleLinkClicked = (args) => {
    switch (args) {
        case "Home":
            welcomeMessage.value = "Welcome to Quiz Academy! You are on the Home page.";
            headerSize.value = "calc(3vw + 3vh)";
            break;
        case "My Quizzes":
            welcomeMessage.value = "Welcome to Quiz Academy! You are viewing your quizzes.";
            headerSize.value = "calc(2vw + 2vh)";
            break;
        case "Create Quiz":
            welcomeMessage.value = "Welcome to Quiz Academy! You are creating a new quiz.";
            headerSize.value = "calc(2.5vw + 2.5vh)";
            break;
        case "Log In":
            welcomeMessage.value = "Welcome to Quiz Academy! Please log in to continue.";
            headerSize.value = "calc(3.5vw + 3.5vh)";
            break;
        case "Sign Up":
            welcomeMessage.value = "Welcome to Quiz Academy! Please sign up to create an account.";
            headerSize.value = "calc(3.5vw + 3.5vh)";
            break;
        default:
            welcomeMessage.value =
                "Welcome to Quiz Academy! Here you can take quizzes, create your own quizzes and much more!";
            headerSize.value = "calc(3vw + 3vh)";
    }
};
const headerHeight = ref(0);
const footerHeight = ref(0);
const isPlayQuizPage = ref(false);



onMounted(() => {
    headerHeight.value = document.getElementById("header").offsetHeight;
    footerHeight.value = document.getElementById("footer").offsetHeight;
    watch(
        () => route.name,
        () => {
            if (route.name === "play_quiz") {
                isPlayQuizPage.value = true;
            } else {
                isPlayQuizPage.value = false;
            }
        }
    );
});

const route = useRoute();
const bodyClass = computed(() => {
    if (route.name === "login" || route.name === "signup") {
        return "special-body";
    }
    return "";
});
</script>

<style scoped>
.contact {
    display: flex;
    flex-direction: row;
    justify-content: center;
    align-items: center;
    gap: 10px;
}
</style>
