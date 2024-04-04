<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {useTokenStore} from "../stores/token.js";
import {count} from "rxjs";

let userDetails = ref(null);

const getUserName = async () => {
  try {
    const tokenStore = useTokenStore();
    const response = await axios.get('http://localhost:8080/users/' + tokenStore.getUsername, {
      headers: {
        'Authorization': `Bearer ${tokenStore.getJwtToken}`
      }
    });
    userDetails.value = response.data;
    console.log(response);
  } catch (error) {

    console.error(error);
  }
};
</script>

<template>
    <div id="body">
        <h2>This is the homepage!!!</h2>
        <QuizList />
        <QuizList />

    </div>
</template>

<style scoped>
#body {
    display: flex;
    justify-content: center;
    align-items: center;
    flex-grow: 1;
}
</style>
