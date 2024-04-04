<script setup>
import { RouterLink } from 'vue-router';
import { useRouter } from 'vue-router';
import { useTokenStore} from "../stores/token.js";
import { watch, ref } from 'vue';

const router = useRouter();

const tokenStore = useTokenStore();
const isLoggedIn = ref(tokenStore.loggedInUser !== null);
const username = ref(tokenStore.getUsername);

const logout = () => {
  tokenStore.logout();
  router.push("/login");
}

const dropdownOpen = ref(false);

const toggleDropdown = () => {
  dropdownOpen.value = !dropdownOpen.value;
};

watch(() => tokenStore.loggedInUser, () => {
  isLoggedIn.value = tokenStore.loggedInUser !== null;
  username.value = tokenStore.getUsername;
});
</script>

<template>
  <div id="navigation-bar">
    <nav>
      <RouterLink @click="$emit('link-clicked','Home')" to="/" class="router-button">Home</RouterLink>
      <RouterLink @click="$emit('link-clicked','My Quizzes')" to="/quizzes" class="router-button">My Quizzes</RouterLink>
      <RouterLink @click="$emit('link-clicked','Create Quiz')" to="/create_quiz" class="router-button">Create Quiz</RouterLink>
      <RouterLink v-if="!isLoggedIn" @click="$emit('link-clicked','Log In')" to="/login" class="router-button">Log in</RouterLink>
      <div v-else class="dropdown">
        <button @click="toggleDropdown" class="router-button-loggedIn" :class="{ 'active': dropdownOpen }">{{ username }}</button>
        <div class="dropdown-content" :class="{ 'show': dropdownOpen }">
          <a @click="logout">Logout</a>
        </div>
      </div>
      <RouterLink @click="$emit('link-clicked','Sign Up')" to="/signup" class="router-button">Sign Up</RouterLink>
    </nav>
  </div>
</template>

<style scoped>
#navigation-bar {
  position: sticky;
  top: 0;
  z-index: 1000;
  background-color: var(--secondary-color);
  font-size: calc(1.2vw + 1.2vh);
  width: 100%;
  display: flex;
  justify-content: center;
}

nav {
  display: flex;
  justify-content: center;
  margin-top: 5px;
  margin-bottom: 5px;
  width: 75%;
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
  height: 100%;
}

.router-button-loggedIn {
  color: var(--fourth-color);
  text-decoration: none;
  text-align: center;
  padding: 10px;
  margin: 0 10px;
  font-size: calc(1.2vw + 1.2vh);
  border: none;
  background-color: transparent;
  cursor: pointer;
  transition: border-color 0.3s;
  height: 100%;
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
}

.dropdown-content {
  display: none;
  position: absolute;
  background-color: #fff;
  box-shadow: 0 8px 16px 0 rgba(0,0,0,0.2);
  padding: 10px 16px;
  z-index: 1;
  top: 100%; /* Position the dropdown below the button */
  left: 0; /* Align the dropdown with the button */
}

.dropdown-content.show {
  display: block;
}
</style>