<template>
    <div>
        <div class="btnsWrapper">
            <div>
                <button class="btnStyle" @click="toEarlyDecision">提前批</button>
            </div>
            <div>
                <button class="btnStyle" @click="toFillDecision">填写意向</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import {useRouter} from 'vue-router';
const router = useRouter();
function toEarlyDecision(){
    router.push('/student/earlyDecision');
}
function toFillDecision(){
    router.push('/student/fillDecision');
}
</script>

<style scoped>
.btnsWrapper {
    display: flex;
    width: 700px;
    height: 250px;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}
/* 按钮样式 */
.btnStyle {
    width: 500px;
    height: 80px;
    font: 30px 'Microsoft YaHei';
    background-color: #fff;
    border-radius: 20px;
    box-shadow: 2px 2px 2px 2px rgba(0, 0, 0, 0.3);
}
</style>