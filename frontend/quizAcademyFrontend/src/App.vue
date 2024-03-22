<script setup>
import {  RouterView } from "vue-router";
import NavigationBar from "./components/NavigationBar.vue";

</script>

<template>
  <div id = "app">
    <div id = "header">
      <h1>Quiz Academy</h1>
      <h5>Welcome to Quiz Academy! Here you can take quizzes, create your own quizzes and much more!</h5>
    </div>

    <navigation-bar />

    <div id = "body">
      <router-view />
    </div>
    <div id = "footer">
      <p>Quiz Academy 2024</p>
    </div>
  </div>

</template>

<style scoped>

</style>
