<template>
    <div id="navigation-bar">
        <nav>
            <RouterLink @click="closeDropdown" to="/" class="router-button">Home</RouterLink>

            <div id="categoryDropdown">
                <button
                    @click="toggleCategoriesDropdown"
                    class="router-button-loggedIn"
                    :class="{ active: categoriesDropdownOpen }"
                >
                    Search
                    <span class="arrow-icon" :class="{ 'arrow-rotate': categoriesDropdownOpen }"
                        >&#9662;</span
                    >
                </button>
                <div class="dropdown-content-category" :class="{ show: categoriesDropdownOpen }">
                    <input
                        id="categoryInput"
                        v-model="searchInput"
                        type="text"
                        placeholder="Search category tag"
                    />
                    <button id="categorySearch" @click="searchQuizzes">Submit search</button>
                    <RouterLink @click="closeDropdown" to="/" class="router-button"
                        >All Categories</RouterLink
                    >
                    <button
                        v-for="tag in tags"
                        :key="tag.tagId"
                        @click="closeDropdownAndSearch(tag.tagName)"
                        class="router-button"
                    >
                        {{ tag.tagName }}
                    </button>
                </div>
            </div>

            <RouterLink @click="closeDropdown" to="/quizzes" class="router-button"
                >My Quizzes</RouterLink
            >
            <RouterLink @click="closeDropdown" to="/create_quiz" class="router-button"
                >Create Quiz</RouterLink
            >

            <RouterLink v-if="!isLoggedIn" @click="closeDropdown" to="/login" class="router-button"
                >Log in</RouterLink
            >

            <div v-if="isLoggedIn" class="dropdown">
                <button
                    @click="toggleLogoutDropdown"
                    class="router-button-loggedIn"
                    :class="{ active: logoutDropdownOpen }"
                >
                    {{ username }}
                    <span class="arrow-icon" :class="{ 'arrow-rotate': logoutDropdownOpen }"
                        >&#9662;</span
                    >
                </button>
                <div class="dropdown-content" :class="{ show: logoutDropdownOpen }">
                    <a @click="logout" class="router-button">Logout</a>
                    <RouterLink @click="closeDropdown" to="/profile" class="router-button"
                        >My Page</RouterLink
                    >
                </div>
            </div>

            <RouterLink v-if="!isLoggedIn" @click="closeDropdown" to="/signup" class="router-button"
                >Sign Up</RouterLink
            >
        </nav>
    </div>
</template>

<script setup>
import { RouterLink, useRouter } from "vue-router";
import { useTokenStore } from "../stores/token.js";
import { onMounted, ref, watch } from "vue";
import { refresh } from "../utils/updateTokenUtil.js";
import { fetchPublicTags } from "../utils/tagUtils.js";

const router = useRouter();
const tokenStore = useTokenStore();

const isLoggedIn = ref(tokenStore.loggedInUser !== null);
const username = ref(tokenStore.getUsername);
const searchInput = ref("");
const categoriesDropdownOpen = ref(false);
const logoutDropdownOpen = ref(false);
const tags = ref([]);

const searchQuizzes = () => {
    router.push({ path: "/search", query: { search: searchInput.value } });
    closeDropdown();
};

const searchQuizzesButtons = (searchTerm) => {
    searchInput.value = searchTerm;
    router.push({ path: "/search", query: { search: searchTerm } });
    closeDropdown();
};

const logout = () => {
    tokenStore.logout();
    router.push("/login");
};

const toggleCategoriesDropdown = () => {
    categoriesDropdownOpen.value = !categoriesDropdownOpen.value;
};

const toggleLogoutDropdown = () => {
    logoutDropdownOpen.value = !logoutDropdownOpen.value;
};

const closeDropdown = () => {
    categoriesDropdownOpen.value = false;
    logoutDropdownOpen.value = false;
    refresh();
};

const closeDropdownAndSearch = (inputSearch) => {
    categoriesDropdownOpen.value = false;
    logoutDropdownOpen.value = false;
    refresh();
    searchQuizzesButtons(inputSearch);
};

onMounted(async () => {
    tags.value = await fetchPublicTags();
});

watch(
    () => tokenStore.loggedInUser,
    () => {
        isLoggedIn.value = tokenStore.loggedInUser !== null;
        username.value = tokenStore.getUsername;
    }
);
</script>

<style scoped>
#navigation-bar {
    position: sticky;
    top: 0;
    z-index: 999; /* Ensure the navigation bar is always on top */
    background-color: var(--secondary-color);
    font-size: calc(1.2vw + 1.2vh);
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
    overflow-x: visible;
}

nav {
    display: flex;
    justify-content: center;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 100%;
    align-content: center;
    align-items: center;
    overflow-x: visible;
}

.router-button {
    color: var(--fourth-color);
    text-decoration: none;
    text-align: center;
    padding: 10px;
    margin: 0 10px;
    border: none;
    background-color: transparent;
    cursor: pointer;
    transition: border-color 0.3s;
    height: 70%;
    position: relative;
}

.router-button-loggedIn {
    color: var(--fourth-color);
    text-decoration: none;
    text-align: center;
    padding: 0 10px;
    margin: 0 10px;
    font-size: calc(1.2vw + 1.2vh);
    border: none;
    background-color: transparent;
    cursor: pointer;
    transition: border-color 0.3s;
    justify-content: center;
    justify-items: center;
    justify-self: center;
    align-content: center;
    align-items: center;
    align-self: center;
}

.router-button:hover {
    color: var(--base-color);
    border-color: transparent;
}

.router-button-loggedIn:hover {
    color: var(--base-color);
    border-color: transparent;
}

.active {
    border-color: var(--tertiary-color);
}

.dropdown {
    position: relative;
    overflow: visible;
    font-size: calc(1.2vw + 1.2vh);
    height: 100%;
}

.dropdown-content {
    box-sizing: content-box;
    z-index: 999; /* Increase this value as needed */
    position: absolute;
    top: 100%; /* Position it right below the button */
    display: none;
    background: linear-gradient(
        to bottom,
        var(--secondary-color) 0%,
        var(--secondary-color) 90%,
        transparent 150%
    );
    padding: 10px 12px;
    font-size: calc(1.2vw + 1.2vh);
    height: calc(8.2vw + 5.2vh);
    border-bottom-right-radius: 20px;
    border-bottom-left-radius: 20px;
    border-bottom-color: var(--tertiary-color);
    box-shadow: 0 5px 4px rgba(0, 0, 0, 0.4);
    justify-content: center;
    align-items: center;
}

.dropdown-content a {
    color: var(--fourth-color); /* Set the color of the anchor tags within the dropdown to black */
    background-color: var(--secondary-color);
}

.arrow-icon {
    font-size: calc(1.2vw + 1.2vh);
    margin-left: 0;
}

.arrow-rotate {
    transform: rotate(180deg); /* Rotate arrow when dropdown is open */
}

.dropdown-content.show {
    position: absolute;
    margin: 0;
    left: -8px;
    display: inline-grid;
    overflow: visible;
    z-index: 999;
    justify-content: center;
    justify-items: center;
    padding-top: 0;
    .router-button {
        padding: 10px;
        width: 100%;
        justify-self: center;
    }
}

.dropdown-content a:hover {
    cursor: pointer;
    color: var(--base-color);
}

.dropdown-content-category {
    z-index: 999;
    position: absolute;
    top: calc(100%); /* Position it below the navbar */
    left: -11vw;
    display: none;
    background: linear-gradient(
        to bottom,
        var(--secondary-color) 0%,
        var(--secondary-color) 80%,
        transparent 300%
    );
    font-size: calc(1.2vw + 1.2vh);
    border-bottom-right-radius: 20px;
    border-bottom-left-radius: 20px;
    border-bottom-color: var(--tertiary-color);
    box-shadow: 0 5px 4px rgba(0, 0, 0, 0.4);
    justify-content: center;
    align-items: center;
    overflow-y: scroll;
}

#categoryDropdown {
    position: relative;
    top: 0;
    overflow: visible;
}

.dropdown-content-category.show {
    margin: 0;
    padding: 0;
    display: inline-grid;
    z-index: 999;
    max-height: 250px;
    min-width: calc(34vw - 1vw);
    .router-button {
        padding: 10px;
        width: 100%;
        justify-self: center;
        font-size: calc(1.2vw + 1.2vh);
    }
}

@media screen and (min-width: 768px) {
    .dropdown-content-category.show {
        display: inline-grid;
        grid-template-columns: repeat(2, auto); /* Two columns */
        grid-column-gap: 10px; /* Adjust the gap between columns */
    }
}

@media screen and (max-width: 768px) {
    .dropdown-content-category.show {
        display: inline-grid;
    }
}

#categoryInput {
    padding: 5px;
    border-radius: 5px;
    border: 1px solid var(--fourth-color);
    margin: 5px 0;
    width: 90%; /* Adjust as needed */
    justify-self: center;
}

#categorySearch {
    padding: 5px 10px;
    border-radius: 5px;
    border: none;
    background-color: var(--fourth-color);
    color: var(--secondary-color);
    cursor: pointer;
    margin: 5px 0;
    width: 90%; /* Adjust as needed */
    justify-self: center;
}

@media screen and (max-width: 450px) {
    nav {
        display: inline-grid;
        grid-template-columns: repeat(2, auto);
        grid-column-gap: 10px;
        margin-bottom: 5px;
        padding-bottom: 5px;
        font-size: calc(2.2vw + 1.2vh);
    }

    #categorySearch {
        padding: 10px;
        margin: 5px 0;
        width: 90%;
        justify-self: center;
    }

    .dropdown-content a {
        padding: 10px;
        text-align: center;
        width: 100%;
    }

    .router-button {
        padding-bottom: 10px;
        width: 90%;
        justify-self: center;
    }

    .router-button-loggedIn {
        width: 90%;
        justify-self: center;
        margin-top: 15px;
        font-size: calc(2.2vw + 1.2vh);
    }

    .dropdown-content-category.show {
        padding-top: 0;
    }
}
</style>
