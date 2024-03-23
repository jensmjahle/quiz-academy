<template>
    <div class="dialog" v-if="selectedQuiz">
        <div class="dialog-content">
            <button @click="editQuiz">Edit</button>
            <button @click="playQuiz">Play</button>
            <button @click="exportCSV">Export as CSV</button>
            <button class="close-button" @click="closeDialog">Close</button>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        selectedQuiz: Object
    },
    methods: {
        editQuiz() {
            console.log('Edit quiz:', this.selectedQuiz);
        },
        playQuiz() {
            console.log('Play quiz:', this.selectedQuiz);
        },
        exportCSV() {
            console.log('Export as CSV:', this.selectedQuiz);
        },
        closeDialog() {
            this.$emit('close');
        }
    }
};
</script>

<style scoped>
.dialog {
    position: fixed;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    background-color: #213547;
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dialog-content {
    display: flex;
    flex-direction: column; /* Align items vertically */
    gap: 10px; /* Add gap between buttons */
}

.dialog button {
    padding: 10px 20px;
    cursor: pointer;
    width: 100%; /* Make buttons full width */
}

.close-button {
    position: absolute;
    top: 10px;
    right: 10px;
}
</style>
