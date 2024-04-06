<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useQuizStore } from '../stores/QuizState.js';
import { useDragDropStore} from "../stores/dragAndDropQuestionStore.js";
import axios from 'axios';

let router = useRouter();
let edit = ref(false);

const quizStore = useQuizStore();
const dragDropStore = useDragDropStore();

const questionText = "Drag the correct answer to the correct box.";
const categories = ref([{ name: '', items: '' }]);

const addCategory = () => {
    categories.value.push({ name: '', items: '' });
};

const removeCategory = (index) => {
    const category = categories.value[index];
    if (category.name || category.items) {
        if (window.confirm('Are you sure you want to remove this category?')) {
            categories.value.splice(index, 1);
        }
    } else {
        categories.value.splice(index, 1);
    }
};

//todo: check if this works
if (dragDropStore.questionId !== null) {
    categories.value = dragDropStore.questionCategories;
    edit.value = true;
}

const submitForm = async () => {
    postDragDropQuestion();
    statifyQuestionAndStore()
    dragDropStore.resetQuestionValues();
    await router.push('/create_quiz');
}

const statifyQuestionAndStore = () => {
    const questionStateId = quizStore.quizQuestionStates.length;
    dragDropStore.setQuestionValues(quizStore.quizId, questionStateId, "Drag the correct answer to the correct box.", categories.value);
    quizStore.addDragDropQuestionState(dragDropStore);
}

function postDragDropQuestion() {
    console.log(quizStore.quizId);

    const formattedCategories = {};
    categories.value.forEach(category => {
        const items = category.items.split('*');
        formattedCategories[category.name] = items;
    });

    const dragDropQuestion = {
        questionText: questionText,
        quizId: quizStore.quizId,
        type: "DRAG_AND_DROP",
        categories: formattedCategories
    };

    console.log(dragDropQuestion);
    quizStore.addQuestion(dragDropQuestion);
}

const editQuestion = async () => {

    const dragDropQuestion = {
        questionText: questionText,
        quizId: quizStore.quizId,
        type: "DRAG_AND_DROP",
        categories: categories.value
    }

    const response = await axios.post("http://localhost:8080/question/update", dragDropQuestion);
    console.log(response.data);

    statifyQuestionAndStore(); //todo: make update method instead of add
    dragDropStore.resetQuestionValues();
    await router.push('/create_quiz');
}

</script>

<template>
    <div id="drag-and-drop-question">
        <h4>Drag and Drop Question</h4>
        <h5>Write all correct responses in each box. Separated by: *</h5>
        <h5>They will be displayed in a single box to be sorted.</h5>

            <div v-for="(category, index) in categories" :key="index" id="category_with_answers">
                <input
                    class = "box_label"
                    id="box_label_category"
                    type="text"
                    v-model="category.name"
                    placeholder="Category"
                />
                <input
                    class = "box_label"
                    id="box_label_answers"
                    type="text"
                    v-model="category.items"
                    placeholder="Enter correct items here, separated by: *"
                />
                <button id="remove_category" @click="removeCategory(index)">Remove category</button>
            </div>
        <div id="buttons">
            <button id="add_category" @click="addCategory">Add a category</button>
            <button id="submit_question" v-if="!edit" @click="submitForm">Submit</button>
            <button id="update_question" v-if="edit" @click="editQuestion">Update</button>
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

#category_with_answers{
    display: flex;
    flex-flow: column;
}

.box_label  {
    margin-top: 15px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 60vw;
}

.box_label::placeholder {
    text-align: center;
}

#box_label_category {
    text-align: center;
}

#buttons {
    display: flex;
    flex-flow: row;
    justify-content: center;
}

</style>