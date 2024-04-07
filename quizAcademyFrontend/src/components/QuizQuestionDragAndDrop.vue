<script setup>
import { ref } from 'vue';
import {useRouter} from "vue-router";
import { useQuizStore } from '../stores/QuizStore.js';
import { useDragDropStore} from "../stores/dragAndDropQuestionStore.js";

const router = useRouter();
let edit = ref(false);
const imageUploaded = ref(false);

const quizStore = useQuizStore();
const dragDropStore = useDragDropStore();

const questionText = ref('');
const categories = ref([{ name: '', items: '' }]);
const questionPhoto = ref(null);

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
    const updatedCategories = [];
    const categoriesFromStore = dragDropStore.questionCategories;
    for (const key in categoriesFromStore) {
        updatedCategories.push({
            name: key,
            items: categoriesFromStore[key].join('*')
        });
    }
    categories.value = updatedCategories;
    if (dragDropStore.questionImage !== null) {
        questionPhoto.value = dragDropStore.questionImage;
        imageUploaded.value = true;
    }
}

const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = () => {
        // `reader.result` contains the base64 string representation of the image
        questionPhoto.value = reader.result;
    };
    // Read the file as a data URL (base64)
    reader.readAsDataURL(file);
    imageUploaded.value = true;
};

const postDragDropQuestion = () => {

    const formattedCategories = {};
    categories.value.forEach(category => {
        const items = category.items.split('*');
        formattedCategories[category.name] = items;
    });

    const dragDropQuestion = {
        questionText: questionText.value,
        quizId: quizStore.quizId,
        type: "DRAG_AND_DROP",
        categories: formattedCategories,
        imageBase64: questionPhoto.value
    };
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
        categories: formattedCategories,
        imageBase64: questionPhoto.value
    }

    const indexOfQuestion = quizStore.getIndexById(dragDropStore.questionId);
    quizStore.swapQuestions(indexOfQuestion, dragDropQuestion);

    dragDropStore.resetQuestionValues();
    router.push('/create_quiz');
}

const cancelPressed = () => {
    if(confirm('Are you sure you want to cancel?')) {
        dragDropStore.resetQuestionValues();
        router.push('/create_quiz');
    }
}

</script>

<template>
    <div id="drag-and-drop-question">
        <h4>Drag and Drop Question</h4>
        <input id="question_text" class="box_label" placeholder="Question Name" v-model="questionText" />
        <div id="add_picture">
            <h5>Add or change picture for your question:</h5>
            <div v-if="imageUploaded">
                <h5 >Current image</h5>
                <img :src="questionPhoto" alt="Question image"/>
            </div>
            <input type="file" @change="handleFileUpload" accept="image/*" />
        </div>
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
            <button id="cancel" @click="cancelPressed">Cancel</button>
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
