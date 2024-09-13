<template>
    <div class="headerWrapper">
        <!-- 标题 -->
        <h1 class="headerName">哈尔滨工业大学（威海）毕业设计师生互选系统</h1>
        <!-- 刷新和退出登录 -->
        <div class="headerLink">
            <a href="#">刷新</a>
            <router-link to="/" @click="loginOut">退出登录</router-link>
        </div>
    </div>
</template>

<script setup>
import { RouterLink } from 'vue-router';
function loginOut(){
    localStorage.removeItem('token');
    localStorage.removeItem('identity');
    localStorage.removeItem('id');
}
</script>

<style scoped>
.headerWrapper {
    display: flex;
    justify-content: space-between;
    width: 100%;
    height: 120px;
    background-color: #4191EE;
}
/* 标题 */
.headerName {
    align-self: center;
    padding-left: 60px;
    color: #fff;
}
/* 刷新和退出登录 */
.headerLink {
    align-self: flex-end;
}

.headerLink a {
    padding:0px 5px;
    text-decoration: none;
    color: #fff;
    font: 18px 'Microsoft YaHei';
}
</style>