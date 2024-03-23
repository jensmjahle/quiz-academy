<template>
    <div class="dialog-overlay" v-if="selectedQuiz" @click="closeDialogOutside">
        <div class="dialog">
            <div class="dialog-content">
                <button @click="editQuiz">Edit</button>
                <button @click="playQuiz">Play</button>
                <button @click="exportCSV">Export as CSV</button>
                <button class="cancel-button" @click="closeDialog">Cancel</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    props: {
        selectedQuiz: Object
    },
    methods: {
        // placeholders for all methods on click
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
        },
        closeDialogOutside(event) {
            // Check if the clicked element is outside of the dialog
            if (!event.target.closest('.dialog')) {
                this.closeDialog();
            }
        }
    }
};
</script>

<style scoped>
.dialog-overlay {
    position: fixed;
    top: 0;
    left: 0;
    width: 100%;
    height: 100%;
    background-color: rgba(0, 0, 0, 0.5); /* Semi-transparent overlay */
    display: flex;
    justify-content: center; /* Center horizontally */
    align-items: center; /* Center vertically */
}

.dialog {
    background-color: #213547;
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.dialog-content {
    display: flex;
    flex-direction: row; /* Align items horizontally */
    gap: 10px; /* Add gap between buttons */
}

.dialog button {
    padding: 10px 20px;
    cursor: pointer;
    width: 100%; /* Make buttons full width */
    height: 65px;

}

.cancel-button {
    align-self: flex-end; /* Align to the right */
    padding: 10px 20px;
    height: 65px;
}
</style>
