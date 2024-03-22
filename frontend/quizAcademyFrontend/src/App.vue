<script setup>
import { RouterLink, RouterView } from "vue-router";

</script>

<template>
  <div id = "app">
    <nav>
      <RouterLink to="/">Home</RouterLink>
    </nav>
    <RouterView />
  </div>

</template>

<style scoped>

</style>
