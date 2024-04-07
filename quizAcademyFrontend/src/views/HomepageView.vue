<script>
import { onMounted, ref } from "vue";
import QuizList from "../components/QuizList.vue";
import { fetchAllQuizzesByTag, fetchPublicQuizzes } from "../utils/quizUtils.js";
import { fetchPublicTags } from "../utils/tagUtils.js";
export default {
    components: { QuizList },
    setup() {
        const quizzesByTags = ref({});
        const tags = ref([]);
        onMounted(async () => {

            const allTags = await fetchPublicTags();


            tags.value = allTags;
            for (let i = 0; i < allTags.length; i++) {
                const tag = allTags[i];
                const quizzes = await fetchAllQuizzesByTag(tag.tagId);

                    quizzesByTags.value[i] = quizzes;

            }

        });
        return { quizzesByTags, tags };
    },


};
</script>

<template>

    <div id="body">
        <div v-for="(tag , index) in tags" :key="tag" class="container">
            <h2>{{ tag.tagName }}</h2>
            <H4 v-if="quizzesByTags[index].length === 0">No quizzes found</H4>
            <QuizList :quizzes="quizzesByTags[index]"></QuizList>

        </div>
    </div>
</template>

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
.container {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin: 20px;
}
</style>
