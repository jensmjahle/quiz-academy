<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import { useStore } from '../stores/QuizState.js';


let router = useRouter();

const store = useStore();

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

const submitForm = async () => {
    postDragDropQuestion();
    await router.push('/create_quiz');
}

function postDragDropQuestion() {
    console.log(store.quizId);
    const questionText = "Drag the correct answer to the correct box.";

    // Convert the categories from the input fields to the required format
    const formattedCategories = {};
    categories.value.forEach(category => {
        const items = category.items.split('*');
        formattedCategories[category.name] = items;
    });

    const dragDropQuestion = {
        questionText: questionText,
        quizId: store.quizId,
        type: "DRAG_AND_DROP",
        categories: formattedCategories
    };

    console.log(dragDropQuestion);
    store.addQuestion(dragDropQuestion);
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
            <button id="submit_question" @click="submitForm">Submit</button>
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