<script setup>
import { ref, onMounted } from 'vue';
import { useRoute, useRouter } from 'vue-router';

let route = useRoute();
let router = useRouter();

let quizId = ref(0);

onMounted(() => {
    if (route.params.quizId) {
        quizId.value = route.params.quizId;
    }
});

const categories = ref([{ name: '', items: '' }]);
const storedData = ref({});

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

const submitForm = () => {
    const quizData = {
        'D&D': {}
    };

    categories.value.forEach(category => {
        const items = category.items.split('*');
        quizData['D&D'][category.name] = items;
    });

    console.log(quizData);
    router.push('/create_quiz');
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