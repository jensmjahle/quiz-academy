<script setup>

</script>

<template>
    <div id="QuizQuestionMultichoice">
        <div id="question">
            <h5 id = "quiz_question">Q.1</h5>
            <input id = "input" type="text" v-model="quizQuestion" placeholder="Enter your question here" />
        </div>
        <div id="alternatives">
            <input id = "input" type="text" v-model="quizQuestion" placeholder="alternative 1"/>
            <input id = checkbox type="checkbox" />
            <input id = "input" type="text" v-model="quizQuestion" placeholder="alternative 2"/>
            <input id = checkbox type="checkbox" />
            <input id = "input" type="text" v-model="quizQuestion" placeholder="alternative 3"/>
            <input id = checkbox type="checkbox" />
            <input id = "input" type="text" placeholder="alternative 4"/>
            <input id = checkbox type="checkbox" />
        </div>
        <div>
            <button>Submit</button>
        </div>
    </div>

</template>

<style scoped>
@media only screen and (max-width: 600px) {
    #question, #alternatives {
        flex: 1;
        flex-direction: column;
    }

    #quiz_question {
        font-size: 20px;
    }

    #input {
        font-size: 15px;
        width: 90%;
    }

    #checkbox {
        margin-left: 0;
        margin-right: 0;
        margin-top: 10px;
    }
}

#question {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    width: 100%;
}
#alternatives {
    display: flex;
    flex-direction: row;
    align-items: center;
    justify-content: center;
    margin-top: 20px;
    width: 100%;
}

#quiz_question {
    margin-right: 20px;
    font-size: 40px;
    font-weight: bold;
    color: var(--fourth-color);
    margin-top: 10px;
    margin-bottom: 10px;
    border: 1px solid #ccc;
    border-radius: 50%;
    background-color: var(--secondary-color);
    padding: 5px 7px;

}

#input {
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
}

#checkbox {
    margin-left: 15px;
    margin-right: 15px;
    -webkit-appearance: none;
    width: 20px; /* Adjust the value as per your needs */
    height: 20px; /* Adjust the value as per your needs */
    background-color: var(--fifth-color); /* Adjust the color as per your needs */
    border-radius: 50%; /* This will make the checkbox completely round */
    cursor: pointer;
}

#checkbox:checked {
    background-color: var(--tertiary-color);
}

#checkbox:checked::after {
    content: "";
    display: block;
    width: 10px; /* Adjust the value as per your needs */
    height: 10px; /* Adjust the value as per your needs */
    background-color: var(--fourth-color); /* Adjust the color as per your needs */
    border-radius: 50%; /* This will make the circle completely round */
    margin: 5px; /* Adjust the value as per your needs */
}

</style>