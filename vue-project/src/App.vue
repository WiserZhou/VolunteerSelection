<script setup>
import {RouterView} from 'vue-router';
</script>

<template>
  <div class="wrapper">
    <router-view></router-view>
  </div>
</template>

<style scoped>
.wrapper {
  display: flex;
  flex-direction: column;
  /* justify-content: center; */
  width: 100%;
  height: 100%;
  background-color: antiquewhite;
}
</style>
