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

<script>
import { ref } from "vue";
export default {
    setup() {
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
    }
};
</script>

<style scoped>
.drop-zone {
    display: flex;
    justify-content: space-between;
    padding: 20px;
    border: 1px solid #ccc;
    border-radius: 5px;
    min-height: 25px;
}

.drag-el {
    padding: 10px;
    border-radius: 5px;
    cursor: pointer;
}
</style>
