<template>
    <div class="wholeWrapper">
        <div class="boxWrapper">
            <router-view></router-view>
        </div>
    </div>
</template>

<script setup>
import { RouterView } from 'vue-router';
</script>

<style scoped>
.wholeWrapper {
    display: flex;
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    background: rgba(255, 255, 255, 0.5) url('../../assets/img/loginBackground.jpg') no-repeat center;
    background-size: cover;
    justify-content: center;
    align-items: center;
}

.boxWrapper {
    display: flex;
    width: 550px;
    height: 450px;
    background-color: rgba(255, 255, 255, 0.8);
    border-radius: 30px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
</style>