<template>
  <div class="container">
    <h2 class="title">My Page</h2>
    <div class="info">
      <p><span class="label">Username:</span> {{ username }}</p>
      <p><span class="label">First Name:</span> {{ firstName }}</p>
      <p><span class="label">Last Name:</span> {{ lastName }}</p>
      <p><span class="label">Email:</span> {{ email }}</p>
    </div>
    <button @click="deleteAccount" class="btn">Delete user</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import { useTokenStore } from '../stores/token.js';

const username = ref(useTokenStore().getUsername);
const firstName = ref(useTokenStore().getFirstName);
const lastName = ref(useTokenStore().getLastName);
const email = ref(useTokenStore().getEmail);

const deleteAccount = () => {
  useTokenStore().deleteUser();
};
</script>

<style scoped>
.container {
  width: 80%;
  margin: 0 auto;
  margin-top: 60px;
  margin-bottom: 60px;
  padding: 20px;
  box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  background-color: var(--secondary-color);
  font-size: calc(1.5vw + 1.5vh);
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.title {
  text-align: center;
  color: var(--fourth-color);
  margin-bottom: 20px;
}

.info {
  display: grid;
  gap: 10px;
  font-size: calc(3vw + 3vh); /* Increase the font size */
}

.label {
  font-weight: lighter;
  color: var(--fourth-color);
  font-size: calc(2vw + 1vh);
}

p {
  font-size: calc(1.5vw + 1.5vh);
  letter-spacing: 1px;
}

.btn {
  background-color: var(--primary-color);
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: calc(1.5vw + 1.5vh);
}

.btn:hover {
  background-color: var(--fourth-color);
}

</style>