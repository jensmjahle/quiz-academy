<template>
    <div id="title">
        <h5>Name your quiz:</h5>
        <div id="title_and_id">
            <input type="text" id="quiz_name" v-model="quizName" placeholder="Quiz name" />
            <button class="button" @click="createQuiz" v-if="!quizCreated">Create Quiz</button>
        </div>
        <div id="quiz_description_and_public">
            <input
                type="text"
                id="quiz_description"
                v-model="quizDescription"
                placeholder="give a short description for your quiz"
            />
            <div id="is_quiz_public">
                <h5>Tick if quiz should be public:</h5>
                <input
                    type="checkbox"
                    id="is_public"
                    name="is_public"
                    v-model="quizPublicStatus"
                    value="is_public"
                />
            </div>
        </div>
        <div id="add_picture" v-if="quizCreated">
            <h5>Add or change picture for your question:</h5>
            <div v-if="imageUploaded">
                <h5>Current image</h5>
                <img :src="quizPhoto" alt="Question image" />
            </div>
            <input type="file" @change="handleFileUpload" accept="image/*" />
        </div>
        <div id="question_creation" v-if="quizCreated">
            <hr />
            <h5>Add questions to your quiz:</h5>
            <!-- Only render the router-link if quizId is not null -->
            <router-link
                v-if="quizId"
                class="button"
                :to="{ name: 'multichoice', params: { quizId: quizId.value } }"
                >Add multiple choice question</router-link
            >
            <router-link
                v-if="quizId"
                class="button"
                :to="{ name: 'text_input', params: { quizId: quizId.value } }"
                >Add text input question</router-link
            >
            <router-link
                v-if="quizId"
                class="button"
                :to="{ name: 'drag_and_drop', params: { quizId: quizId.value } }"
                >Add drag and drop question</router-link
            >
            <router-link
                v-if="quizId"
                class="button"
                :to="{ name: 'true_false', params: { quizId: quizId.value } }"
                >Add true or false question</router-link
            >
        </div>
    </div>
    <div id="question_list" v-if="quizCreated">
        <h5>Questions (click to edit):</h5>
        <ul>
            <li
                v-for="(question, index) in questions"
                :key="question.id"
                @click="editQuestion(index)"
                id="question_in_list"
            >
                <span style="cursor: pointer; margin-left: 5px" @click.stop="deleteQuestion(index)"
                    >[X]</span
                >
                {{ question.type }}: {{ index + 1 }}. {{ question.questionText }}
            </li>
        </ul>
    </div>
    <div v-if="quizCreated">
        <h5>Select Tags:</h5>
        <select v-model="selectedTag" @change="addTagToQuiz">
            <option v-for="tag in allTags" :key="tag" :value="tag">{{ tag }}</option>
        </select>
    </div>
    <div v-if="quizCreated">
        <h5>Current tags:</h5>
        <ul>
            <li v-for="tag in quizTags" :key="tag.tagId" @click="removeTag(tag)">
                <span style="cursor: pointer; margin-left: 5px" @click.stop="removeTag(tag)"
                    >[X]</span
                >
                {{ tag.tagName }}
            </li>
        </ul>
    </div>
    <div v-if="quizCreated">
        <h5 v-if="showSavedMessage">Quiz saved!</h5>
        <button class="button" @click="updateQuiz">Save quiz</button>
        <button class="button" @click="exitAndSave">Exit and save</button>
        <button class="button" @click="resetWithConfirm">Exit without saving</button>
    </div>
</template>

<script setup>
import { ref, onMounted } from "vue";
import { useQuizStore } from "../stores/QuizStore.js";
import axios from "axios";
import { useRouter } from "vue-router";
import { useTokenStore } from "../stores/token.js";
import { fetchPublicTags } from "@/utils/tagUtils.js";

const quizStore = useQuizStore();
const tokenStore = useTokenStore();
const router = useRouter();

const quizCreated = ref(false);
const quizId = ref(quizStore.quizId);
const quizPublicStatus = ref(false);
const questions = ref([]);
const quizName = ref("");
const showSavedMessage = ref(false);
const quizDescription = ref("");
const quizTags = ref([]);
const allTags = ref();
const selectedTag = ref(null);
let quizPhoto = ref(null);
let imageUploaded = ref(false);

const user = tokenStore.loggedInUser.data.username;

const initializeData = async () => {
    try {
        const tags = await fetchPublicTags(); // Fetch tags asynchronously
        allTags.value = tags.map((tag) => ({ tagId: tag.tagId, tagName: tag.tagName }));

        if (quizStore.quizName !== null) {
            quizCreated.value = true;
            quizName.value = quizStore.quizName;
            quizDescription.value = quizStore.quizDescription;
            quizPublicStatus.value = quizStore.quizPublicStatus;
            quizTags.value = quizStore.quizTags;
            quizPhoto.value = quizStore.quizImage;
            if (quizStore.quizImage !== null) {
                imageUploaded.value = true;
            }
            if (Array.isArray(quizStore.quizQuestions)) {
                questions.value = quizStore.quizQuestions;
            } else {
                questions.value = [];
            }
        }
    } catch (error) {
        console.error("Error fetching tags:", error);
    }
};

onMounted(() => {
    initializeData();
});

const handleFileUpload = (event) => {
    const file = event.target.files[0];
    if (!file) return;

    const reader = new FileReader();
    reader.onload = () => {
        quizPhoto.value = reader.result;
        quizStore.setQuizPhoto(quizPhoto.value);
    };
    reader.readAsDataURL(file);
    imageUploaded.value = true;
};

const addTagToQuiz = () => {
    const selectedTagObj = selectedTag.value;
    if (selectedTagObj && !quizTags.value.some((tag) => tag.tagId === selectedTagObj.tagId)) {
        quizTags.value.push({ tagId: selectedTagObj.tagId, tagName: selectedTagObj.tagName });
    }
};

const createQuiz = async () => {
    const quizData = {
        quizName: quizName.value,
        quizDescription: quizDescription.value,
        user: user,
        isPublic: quizPublicStatus.value,
        quizCreationDate: new Date()
    };

    try {
        const response = await axios.post("http://localhost:8080/quiz/create", quizData);

        quizId.value = response.data.quizId;
        quizDescription.value = response.data.quizDescription;
        quizCreated.value = true;
        quizStore.initializeQuiz(
            quizId.value,
            quizName.value,
            questions.value,
            quizDescription.value,
            quizPublicStatus.value,
            quizTags.value,
            quizPhoto.value
        );
        console.log(quizCreated.value);
    } catch (error) {
        console.error(error);
    }
};

const updateQuiz = async () => {
    if (Array.isArray(quizStore.quizQuestions)) {
        questions.value = quizStore.quizQuestions;
    } else {
        questions.value = [];
    }
    if (!Array.isArray(quizTags.value)) {
        quizTags.value = [quizTags.value];
    }

    quizStore.setQuizPhoto(quizPhoto.value);
    const date = new Date();

    const quizData = {
        quizId: quizId.value,
        quizName: quizName.value,
        quizDescription: quizDescription.value,
        user: user,
        tags: quizTags.value,
        isPublic: quizPublicStatus.value,
        quizImage: quizPhoto.value,
        quizCreationDate: date,
        questions: quizStore.quizQuestions
    };

    try {
        const response = await axios.post("http://localhost:8080/quiz/update", quizData);
        quizId.value = response.data.quizId;
        showSavedMessage.value = true;
        setTimeout(() => {
            showSavedMessage.value = false;
        }, 5000);
    } catch (error) {
        console.error(error);
    }
};

const removeTag = (tagToRemove) => {
    const index = quizTags.value.indexOf(tagToRemove);
    if (index !== -1) {
        quizTags.value.splice(index, 1);
    }
};

const editQuestion = async (index) => {
    const routing = quizStore.fromQuestionToQuestionState(index);
    await router.push(routing);
};

const deleteQuestion = (index) => {
    if (confirm("Are you sure you want to delete the question?")) {
        quizStore.deleteQuestion(index);
    }
};
const exitAndSave = () => {
    updateQuiz();
    quizStore.resetQuiz();
    router.push("/quizzes");
};

const resetWithConfirm = () => {
    if (confirm("Are you sure you want to reset the quiz?")) {
        quizStore.resetQuiz();
        router.push("/quizzes");
    }
};
</script>

<style scoped>
#title {
    display: flex;
    flex-flow: column;
}

#title_and_id {
    display: flex;
    flex-flow: row;
}

#question_creation {
    display: flex;
    flex-flow: column;
}

#quiz_name {
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 25vw;
}

#quiz_description {
    margin-top: 15px;
    margin-bottom: 10px;
    font-size: 30px;
    padding-top: 5px;
    padding-bottom: 5px;
    padding-left: 10px;
    background-color: var(--fifth-color);
    border-radius: 5px;
    width: 25vw;
}

#quiz_name::placeholder {
    text-align: center;
}

.button {
    background-color: var(--fourth-color);
    padding: 10px 20px;
    margin: 10px;
    border-radius: 10px;
    border-width: 4px;
    cursor: pointer;
    font-family: var(--font-family);
    font-size: 20px;
}

.button:active {
    color: var(--base-color);
}

#is_quiz_public {
    display: flex;
    flex-flow: row;
    align-items: center;
}

#is_public {
    margin-left: 15px;
    margin-right: 15px;
    -webkit-appearance: none;
    width: 20px;
    height: 20px;
    background-color: var(--fifth-color);
    border-radius: 50%;
    cursor: pointer;
}

#is_public:checked {
    background-color: var(--tertiary-color);
}

#is_public:checked::after {
    content: "";
    display: block;
    width: 10px;
    height: 10px;
    background-color: var(--fourth-color);
    border-radius: 50%;
    margin: 5px;
}

#question_in_list:hover {
    cursor: pointer;
    text-underline-color: var(--fourth-color);
}
</style>
