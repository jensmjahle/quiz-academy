<script setup>
import { ref, onMounted, provide } from "vue";
import { useRoute } from "vue-router";

let route = useRoute();

let questions = ref([]);

provide("questions", questions);

// This method below will be changed to do an API call once the API is ready.
// Then it will pull all questions given the current quiz id, which also needs to be made
onMounted(() => {
    if (route.params.question) {
        questions.value.push(route.params.question);
    } else {
        questions.value = [
            { id: 1, type: "text_input", name: "question 1", answer: "answer 1" },
            { id: 2, type: "multichoice", name: "question 2", answer: "answer 2" }
        ];
    }
});
</script>

<template>
    <div id="title">
        <h5>Name your quiz:</h5>
        <div id="title_and_id">
            <input type="text" id="quiz_name" placeholder="Quiz name" />
        </div>

        <div id="question_creation">
            <h5>Add questions to your quiz:</h5>
            <router-link class="button" :to="{ name: 'multichoice' }"
                >Add multiple choice question</router-link
            >
            <router-link class="button" :to="{ name: 'text_input' }"
                >Add text input question</router-link
            >
            <router-link class="button" :to="{ name: 'drag_and_drop' }"
                >Add drag and drop question</router-link
            >
        </div>
    </div>
    <div id="question_list">
        <h5>Questions:</h5>
        <ul>
            <li v-for="question in questions" :key="question.id">
                {{ question.type }}: {{ question.name }}
            </li>
        </ul>
    </div>
</template>

<style scoped>
#title {
    display: flex;
    flex-flow: column;
}

#title_and_id {
    display: flex;
    flex-flow: row;
}

#question_creation {
    display: flex;
    flex-flow: column;
}

#quiz_name {
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 25vw;
}

#quiz_name::placeholder {
    text-align: center;
}

.button {
    background-color: var(--fourth-color);
    padding: 10px 20px;
    margin: 10px;
    border-radius: 10px;
    border-width: 4px;
    cursor: pointer;
    font-family: var(--font-family);
    font-size: 20px;
}

.button:active {
    color: var(--base-color);
}
</style>
