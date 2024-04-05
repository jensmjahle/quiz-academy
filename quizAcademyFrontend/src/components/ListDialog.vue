<template>
    <div class="dialog-overlay" v-if="selectedQuiz" @click="closeDialogOutside">
        <div class="dialog">
            <div class="dialog-header">
                <h2 class="title">{{ selectedQuiz.quizName }}</h2>
                <h5 class="description">{{ selectedQuiz.quizDescription }}</h5>

                <div class="info">
                    <h5>Questions:</h5>
                    <h4>{{ selectedQuiz.questions.length }}</h4>
                    <h5>Created by:</h5>
                    <h4>{{ selectedQuiz.user }}</h4>
                </div>

                <div class="dialog-content">
                    <button @click="editQuiz">Edit</button>
                    <button @click="playQuiz">Play</button>
                    <button @click="exportCSV">Export as CSV</button>
                    <button class="cancel-button" @click="closeDialog">Cancel</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
import { usePlayQuizStore } from "../stores/playQuizStore.js";
import router from "../router/index.js";

export default {
    props: {
        selectedQuiz: Object
    },
    methods: {
        // placeholders for all methods on click
        editQuiz() {
            console.log("Edit quiz:", this.selectedQuiz);
        },
        playQuiz() {
            usePlayQuizStore().startQuiz(this.selectedQuiz);
            router.push("/play_quiz");
            console.log("Play quiz:", this.selectedQuiz);
        },
        exportCSV() {
            console.log("Export as CSV:", this.selectedQuiz);
        },
        closeDialog() {
            this.$emit("close");
        },
        closeDialogOutside(event) {
            // Check if the clicked element is outside of the dialog
            if (!event.target.closest(".dialog")) {
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
    background-color: var(--secondary-color);
    padding: 20px;
    border: 1px solid #ccc;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    border-radius: 10px;
    max-width: 700px;
    /* rounds edges of box*/
}
.title {
    background-color: rgba(var(--primary-color-rgb), 0.5);
    border-radius: 10px;
    margin-bottom: 10px;
}
.description {
    background-color: rgba(var(--primary-color-rgb), 0.5);
    border-radius: 10px;
    margin-bottom: 10px;
}

.dialog-content {
    display: flex;
    flex-direction: row; /* Align items horizontally */
    gap: 10px; /* Add gap between buttons */
    justify-content: center; /* Center buttons */
}
.dialog-header {
    display: flex;
    flex-direction: column;
    text-align: center;
}
.info {
    display: flex;
    flex-direction: row;
    text-align: center;
    justify-content: center;
    align-items: center;
    gap: 10px;

    h5 {
        color: var(--fourth-color);
    }
}
/*
.dialog button {
    padding: 10px 20px;
    cursor: pointer;
    width: 100%; /* Make buttons full width *
    height: 65px;

}*/

.cancel-button {
    align-self: flex-end; /* Align to the right */
    padding: 10px 20px;
    height: 65px;
}
</style>
