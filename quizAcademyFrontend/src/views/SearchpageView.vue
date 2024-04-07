<script setup>
import { onMounted, ref } from "vue";
import QuizList from "../components/QuizList.vue";
import { fetchPublicQuizzes } from "../utils/quizUtils.js";
import { usePlayQuizStore } from "../stores/playQuizStore.js";
import { useRoute } from 'vue-router'; // Import useRoute

const quizzes = ref([]);
const route = useRoute(); // Get the current route

onMounted(async () => {
  const searchInput = route.query.search; // Get the search query parameter
  quizzes.value = await fetchPublicQuizzes(searchInput);
});
</script>

<template>
  <h1>Search Results for: {{ route.query.search }}</h1> <!-- Display the search term -->
  <QuizList :quizzes="quizzes" />
</template>