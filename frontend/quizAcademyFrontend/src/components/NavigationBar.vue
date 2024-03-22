<script setup>

</script>

<template>
  <div id = "navigation-bar">
    <nav>
      <RouterLink to="/" class="router-button">Home</RouterLink>
      <RouterLink to="/quizzes" class="router-button">Quizzes</RouterLink>
      <RouterLink to="/create" class="router-button">Create Quiz</RouterLink>
      <RouterLink to="/login" class="router-button">Log in</RouterLink>
      <RouterLink to="/signup" class="router-button">Register New User</RouterLink>
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
}

nav {
  display: flex;
  justify-content: space-around;
  color: #242424;
  margin-top: 5px;
  margin-bottom: 5px;
}

.router-button {
  color: var(--fourth-color);
  text-decoration: none;
  width: 100%;
  text-align: center;
  border-bottom-width: 4px;
  border-top-width: 4px;
  border-bottom-style: solid;
  border-top-style: solid;
  border-color: var(--secondary-color);
  margin-left: 20px;
  margin-right: 20px;
}

.router-button:hover {
  color: var(--base-color);
  border-bottom-color: var(--tertiary-color);
}

</style>