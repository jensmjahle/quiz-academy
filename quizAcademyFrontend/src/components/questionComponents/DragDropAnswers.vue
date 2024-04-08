<script>

import { ref } from "vue";

export default {
    props: {
        question: {
            type: Object,
            required: true
        },
        hasAnswered: {
            type: Boolean,
            required: true
        }
    },
    etup() {
        const items = ref([
            { id: 0, title: "item a", list: 1 },
            { id: 1, title: "item b", list: 1 },
            { id: 2, title: "item c", list: 2 },
            { id: 3, title: "item d", list: 2 },
            { id: 4, title: "item e", list: 3 },
            { id: 5, title: "item f", list: 4 }
        ]);

        const getList = (list) => {
            return items.value.filter((item) => item.list === list);
        };

        const dragStart = (event, item) => {
            event.dataTransfer.dropEffect = "move";
            event.dataTransfer.effectAllowed = "move";
            event.dataTransfer.setData("itemID", item.id);
        };

        const onDrop = (event, list) => {
            const itemID = parseInt(event.dataTransfer.getData("itemID"));
            const item = items.value.find((item) => item.id === itemID);
            if (item) {
                item.list = list;
            } else {
                console.error(`Item with ID ${itemID} not found.`);
            }
        };

        return {
            getList,
            dragStart,
            onDrop
        };
    },

    data() {
        return {
            categories: [],
            isCorrect: false,
            allItems: [],
            draggedItem: null
        }
    },
    computed: {
        shuffledItems() {
            const categories = this.question.categories;
            const allItems = ref();
            console.log(categories)

            // Shuffle the flat list of items
            return this.shuffleArray(allItems);
        },
    },
    methods: {
        checkAnswer() {
         console.log(this.question.categories)


            this.isCorrect = this.correctAnswers[index] === 'true';
            this.$emit('displayResults', this.isCorrect);
        },
        dragStart(event, item) {
            this.draggedItem = item;
        },
        dropItem(categoryIndex) {
            if (this.draggedItem) {
                this.question.categories[categoryIndex].items.push(this.draggedItem);
                this.draggedItem = null;
            }
        },
        onDrop(event, list) {
            const itemID = parseInt(event.dataTransfer.getData("itemID"));
            const item = items.value.find((item) => item.id === itemID);
            if (item) {
                item.list = list;
            } else {
                console.error(`Item with ID ${itemID} not found.`);
            }
        },
        shuffleArray(array) {
            // Shuffle the array using Fisher-Yates algorithm
            for (let i = array.length - 1; i > 0; i--) {
                const j = Math.floor(Math.random() * (i + 1));
                [array[i], array[j]] = [array[j], array[i]];
            }
            return array;
        },
    }
}
</script>
<template>
    <div class="drop-zone" @drop="onDrop($event, 1)" @dragover.prevent @dragenter.prevent>
        <div
            v-for="item in getList(1)"
            :key="item.id"
            class="drag-el"
            draggable="true"
            @dragstart="dragStart($event, item)"
        >
            {{ item.title }}
        </div>
    </div>
    <div class="drop-zone" @drop="onDrop($event, 2)" @dragover.prevent @dragenter.prevent>
        <div
            v-for="item in getList(2)"
            :key="item.id"
            class="drag-el"
            draggable="true"
            @dragstart="dragStart($event, item)"
        >
            {{ item.title }}
        </div>
    </div>
</template>

<style scoped>
.dropBox {
    height: 20vh;
    border: 1px solid #ccc;
    margin-bottom: 10px;
}
.highlight {
    background-color: #f0f0f0;
}


ul {
    list-style-type: none;
    padding: 0;
}
.dragDropItem {
    padding: 8px 16px;
    margin-bottom: 4px;
    background-color: var(--secondary-color);
    cursor: pointer;
    border-radius: 10px;
    transition: 0.3s;
    text-align: center;
}
.dragDropItem:hover {
    background-color: var(--tertiary-color);
}

.categoriesContainers {
    display: grid;
    grid-template-columns: 1fr;
    margin-bottom: 20px;
    text-align: center;
    justify-content: center;


    @media screen and (min-width: 768px) {
        grid-template-columns: repeat(2, minmax(0, 1fr)); /* 2 columns on screens 768px and wider */
        column-gap: 20px;
    }

    @media screen and (min-width: 1024px) {
        grid-template-columns: repeat(
            3,
            minmax(0, 1fr)
        ); /* 3 columns on screens 1024px and wider */
        column-gap: 30px;
    }
}
</style>