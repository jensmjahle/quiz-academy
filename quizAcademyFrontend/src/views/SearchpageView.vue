<template>
    <div id="body">
        <h1>Search Results for: {{ route.query.search }}</h1>
        <!-- Display the search term -->
        <h3 v-if="quizzes.length === 0" class="noResults">No quizzes found :(</h3>
        <QuizList :quizzes="quizzes"></QuizList>
    </div>
</template>

<script setup>
import { onMounted, ref, watch } from "vue";
import QuizList from "../components/QuizList.vue";
import { fetchPublicQuizzes } from "../utils/quizUtils.js";
import { usePlayQuizStore } from "../stores/playQuizStore.js";
import { useRoute } from "vue-router"; // Import useRoute
import { fetchAllQuizzesBySearch } from "../utils/quizUtils.js";

const quizzes = ref([]);
const route = useRoute(); // Get the current route
const searchInput = ref(route.query.search); // Get the search query parameter

watch(searchInput, async (newSearchInput) => {
    quizzes.value = await fetchAllQuizzesBySearch(newSearchInput);
});

watch(
    () => route.query.search,
    (newSearchInput) => {
        searchInput.value = newSearchInput;
    }
);
onMounted(async () => {
    const searchInput = route.query.search; // Get the search query parameter
    quizzes.value = await fetchAllQuizzesBySearch(searchInput);
});
</script>

<style scoped>
#body {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1;
    text-align: center;
    align-content: center;
    flex-direction: column;
}

.noResults {
    padding-top: 20px;
    color: var(--primary-color);
    border-bottom: var(--primary-color) 3px solid;
}
</style>
