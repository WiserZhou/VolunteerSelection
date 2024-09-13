<template>
    <div>
        <div class="btnsWrapper">
            <div>
                <button class="btnStyle" @click="studentMatch">学生匹配结果</button>
            </div>
            <div>
                <button class="btnStyle" @click="teacherMatch">教师匹配结果</button>
            </div>
            <div>
                <button class="btnStyle" @click="firstFeedback">初步随机结果反馈</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import {useRouter} from 'vue-router';
const router = useRouter();
function studentMatch(){
    router.push('/administrator/studentMatch');
}
function teacherMatch(){
    router.push('/administrator/teacherMatch');
}
function firstFeedback(){
    router.push('/administrator/firstFeedback');
}
</script>

<style scoped>
.btnsWrapper {
    display: flex;
    width: 700px;
    height: 350px;
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