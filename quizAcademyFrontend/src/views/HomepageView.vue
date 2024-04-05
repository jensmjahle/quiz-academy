<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {useTokenStore} from "../stores/token.js";
import { handleErrors } from "../utils/badRequests.js";
import { getUserInfo } from "../utils/httputils.js";

let userDetails = ref(null);

const getUserName = async () => {
  try {
    console.log("hallo");
    await useTokenStore().refreshToken();
    console.log("hallo");
    console.log(useTokenStore().getUsername);
    console.log(useTokenStore().getJwtToken);
    const response = await getUserInfo(useTokenStore().getUsername, useTokenStore().getJwtToken);
    console.log("hei");
    userDetails.value = response.data;
    console.log(response);
  } catch (error) {
    await handleErrors(error);
  }
};
</script>

<template>
    <div id="body">
        <h2>Home</h2>
        <p>
            Welcome to Quiz Academy! Here you can take quizzes, create your own quizzes and much
            more!
        </p>
        <button type="button" @click="count++">count is {{ count }}</button>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h1>Quizzes</h1>
        <h2>skerraaa</h2>
      <button type="button" @click="getUserName">Get User Details</button>
      <div v-if="userDetails">
        <h2>User Details</h2>
        <p>Username: {{ userDetails.username }}</p>
      </div>
    </div>
</template>

<style scoped></style>
