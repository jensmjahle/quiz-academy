<!-- Modal.vue -->
<template>
    <div class="modal" v-if="isOpen">
        <div class="modal-content">
            <span class="close" @click="close">&times;</span>
            <div class="modal-options">
                <div @click="handleEdit">Edit</div>
                <div @click="handlePlay">Play</div>
                <div @click="handleExport">Export as CSV</div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';

const isOpen = ref(false);

const open = () => {
    isOpen.value = true;
};

const close = () => {
    isOpen.value = false;
};

const handleEdit = () => {
    console.log('Edit action');
    close();
};

const handlePlay = () => {
    console.log('Play action');
    close();
};

const handleExport = () => {
    console.log('Export as CSV action');
    close();
};
</script>

<style scoped>
/* Modal styles */
</style>
