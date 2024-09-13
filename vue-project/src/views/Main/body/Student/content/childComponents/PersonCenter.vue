<template>
    <div class="btnsWrapper">
        <div>
            <el-button type="primary" class="btnStyle" @click="toStuPersonInf">个人信息</el-button>
        </div>
        <div>
            <el-button type="primary" class="btnStyle" @click="toChangePassword">修改密码</el-button>
        </div>
    </div>
</template>

<script setup>
import {useRouter} from 'vue-router';
const router = useRouter();
function toStuPersonInf(){
    router.push('/student/stuPersonInf');
}
function toChangePassword(){
    router.push('/student/changePassword');
}
</script>

<style scoped>
.btnsWrapper {
    display: flex;
    width: 700px;
    height: 200px;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}
/* 按钮样式 */
.btnStyle {
    width: 500px;
    height: 80px;
    font: 30px 'Microsoft YaHei';
}
</style>