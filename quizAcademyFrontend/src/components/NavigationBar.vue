<script setup>
import { RouterLink } from "vue-router";
import { useRouter } from "vue-router";
import { useTokenStore } from "../stores/token.js";
import { ref, watch } from "vue";

const router = useRouter();
const tokenStore = useTokenStore();

const isLoggedIn = ref(tokenStore.loggedInUser !== null);
const username = ref(tokenStore.getUsername);

const logout = () => {
    tokenStore.logout();
    router.push("/login");
};

const dropdownOpen = ref(false);

const toggleDropdown = () => {
    dropdownOpen.value = !dropdownOpen.value;
};

const closeDropdown = () => {
    dropdownOpen.value = false;
};

watch(
    () => tokenStore.loggedInUser,
    () => {
        isLoggedIn.value = tokenStore.loggedInUser !== null;
        username.value = tokenStore.getUsername;
    }
);
</script>

<template>
    <div id="navigation-bar">
        <nav>
            <RouterLink @click="closeDropdown" to="/" class="router-button">Home</RouterLink>
            <RouterLink @click="closeDropdown" to="/quizzes" class="router-button">My Quizzes</RouterLink>
            <RouterLink @click="closeDropdown" to="/create_quiz" class="router-button">Create Quiz</RouterLink>
            <RouterLink v-if="!isLoggedIn" @click="closeDropdown" to="/login" class="router-button">Log in</RouterLink>
            <div v-else class="dropdown">
                <button @click="toggleDropdown" class="router-button-loggedIn" :class="{ active: dropdownOpen }">
                    {{ username }}
                    <span class="arrow-icon" :class="{ 'arrow-rotate': dropdownOpen }">&#9662;</span>
                </button>
                <div class="dropdown-content" :class="{ show: dropdownOpen }">
                    <a @click="logout">Logout</a>
                </div>
            </div>
            <RouterLink v-if="!isLoggedIn" @click="closeDropdown" to="/signup" class="router-button">Sign Up</RouterLink>
        </nav>
    </div>
</template>

<style scoped>
#navigation-bar {
    position: sticky;
    top: 0;
    z-index: 999; /* Ensure the navigation bar is always on top */
    background-color: var(--secondary-color);
    font-size: calc(1.2vw + 1.2vh);
    width: 100%;
    display: flex;
    justify-content: center;
    box-sizing: border-box; /* Add this line */
}

nav {
    display: inline-flex;
    justify-content: center;
    margin-top: 5px;
    margin-bottom: 5px;
    width: 100%;
    align-content: center;
    align-items: center;
    align-self: center;
}

.dropdown-content a {
    color: var(--fourth-color); /* Set the color of the anchor tags within the dropdown to black */
    background-color: var(--tertiary-color);
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
    z-index: 999; /* Increase this value as needed */
    position: absolute;
    top: 100%; /* Position it right below the button */
    display: none;
    background: linear-gradient(to bottom, var(--secondary-color) 0%, var(--secondary-color) 70%, transparent 150%);
    padding: 10px 12px;
    font-size: calc(1.2vw + 1.2vh);
    height: calc(2.2vw + 2.2vh);
    border-bottom-right-radius: 20px;
    border-bottom-left-radius: 20px;
    border-bottom-color: var(--tertiary-color);
    box-shadow: 0 5px 4px rgba(0, 0, 0, 0.4);
}

.dropdown-content a {
    color: var(--fourth-color); /* Set the color of the anchor tags within the dropdown to black */
    background-color: var(--secondary-color);
}

.arrow-icon {
    font-size: calc(1.2vw + 1.2vh);
    margin-left: 0px;
}

.dropdownButton {
    color: var(--fourth-color);
    text-decoration: none;
    text-align: center;
    padding: 10px;
    margin: 0 10px;
    border: none;
    background-color: transparent;
    cursor: pointer;
    transition: border-color 0.3s;
    height: 100%;
}

.arrow-rotate {
    transform: rotate(180deg); /* Rotate arrow when dropdown is open */
}

.dropdown-content.show {
    display: block;
    overflow: visible;
    z-index: 999;
    margin-left: 7px;
}

.dropdown-content a:hover {
    cursor: pointer;
}

</style>