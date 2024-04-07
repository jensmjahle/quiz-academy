<script setup>
import { ref } from 'vue';
import {useRouter} from "vue-router";
import { useQuizStore } from '../stores/QuizState.js';
import { useDragDropStore} from "../stores/dragAndDropQuestionStore.js";

const router = useRouter();
let edit = ref(false);

const quizStore = useQuizStore();
const dragDropStore = useDragDropStore();

const questionText = ref('');
const categories = ref([{ name: '', items: '' }]);

const addCategory = () => {
    if(categories.value.length >= 3) {
        alert("You can't have more than 3 categories.");
        return;
    }
    categories.value.push({ name: "", items: "" });
};

const removeCategory = (index) => {
    const category = categories.value[index];
    if (category.name || category.items) {
        if (window.confirm("Are you sure you want to remove this category?")) {
            categories.value.splice(index, 1);
        }
    } else {
        categories.value.splice(index, 1);
    }
};

if (dragDropStore.questionId !== null) {
    edit.value = true;
    questionText.value = dragDropStore.questionText;
    console.log("question id found");
    const updatedCategories = [];
    const categoriesFromStore = dragDropStore.questionCategories;
    console.log("categories from store: ", categoriesFromStore);
    for (const key in categoriesFromStore) {
        updatedCategories.push({
            name: key,
            items: categoriesFromStore[key].join('*')
        });
    }
    categories.value = updatedCategories;
}

const postDragDropQuestion = () => {

    const formattedCategories = {};
    categories.value.forEach(category => {
        const items = category.items.split('*');
        formattedCategories[category.name] = items;
    });

    console.log("quizId: ", quizStore.quizId);

    const dragDropQuestion = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        type: "DRAG_AND_DROP",
        categories: formattedCategories
    };

    console.log(dragDropQuestion);
    quizStore.addQuestion(dragDropQuestion);

    dragDropStore.resetQuestionValues();
    router.push('/create_quiz');
}

const editQuestion = () => {
    const formattedCategories = {};

    categories.value.forEach(category => {
        const items = category.items.split('*');
        formattedCategories[category.name] = items;
    });

    const dragDropQuestion = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        type: "DRAG_AND_DROP",
        categories: formattedCategories
    }

    const indexOfQuestion = quizStore.getIndexById(dragDropStore.questionId);
    console.log("index of question: ", indexOfQuestion);
    quizStore.swapQuestions(indexOfQuestion, dragDropQuestion);

    dragDropStore.resetQuestionValues();
    router.push('/create_quiz');
}

</script>

<template>
    <div id="drag-and-drop-question">
        <h4>Drag and Drop Question</h4>
        <input id="question_text" class="box_label" placeholder="Question Name" v-model="questionText" />
        <h5>Write all correct responses in each box. Separated by: *</h5>
        <h5>They will be displayed in a single box to be sorted.</h5>

        <div v-for="(category, index) in categories" :key="index" id="category_with_answers">
            <input
                class="box_label"
                id="box_label_category"
                type="text"
                v-model="category.name"
                placeholder="Category"
            />
            <input
                class="box_label"
                id="box_label_answers"
                type="text"
                v-model="category.items"
                placeholder="Enter correct items here, separated by: *"
            />
            <button id="remove_category" @click="removeCategory(index)">Remove category</button>
        </div>
        <div id="buttons">
            <button id="add_category" @click="addCategory">Add a category</button>
            <button id="submit_question" v-if="!edit" @click="postDragDropQuestion">Submit</button>
            <button id="update_question" v-if="edit" @click="editQuestion">Update</button>
            <button id="cancel" @click="router.push('/create_quiz')">Cancel</button>
        </div>
    </div>
</template>

<style scoped>
#drag-and-drop-question {
    margin-top: 3vw;
    display: flex;
    flex-flow: column;
    align-items: center;
}

#category_with_answers {
    display: flex;
    flex-flow: column;
}

.box_label {
    margin-top: 15px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 60vw;
    text-align: center;
}

.box_label::placeholder {
    text-align: center;
}

#buttons {
    display: flex;
    flex-flow: row;
    justify-content: center;
}

#remove_category {

}
</style>
