<template>
    <div CLASS="currentStageWrapper">
        <p>目前进程为【{{current}}】，05：26：03之后进行【{{next}}】的匹配！</p>
    </div>
</template>

<script setup>
import {ref} from 'vue';
let current = ref('提前批');
let next = ref('第一志愿');
</script>

<style scoped>
.currentStageWrapper {
    display: flex;
    padding-left: 20px;
    height: 50px;
    font: 18px 'Micosoft YaHei';
    border-bottom: 1px solid rgba(0, 0, 0, 0.3);
    justify-content: flex-start;
    align-items: center;
}

</style>