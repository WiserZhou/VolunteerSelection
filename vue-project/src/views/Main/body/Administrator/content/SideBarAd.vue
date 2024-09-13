<template>
    <div class="sideBarWrapper">
        <div class="greeting">您好！xxx管理员！<br>xxxxxxxx!</div>
        <div class="sideBar">
            <router-link to="/administrator/changePassword">修改密码</router-link>
            <router-link to="/administrator/distributeNum">教师名额分配</router-link>
            <router-link to="/administrator/intentionProcess">意向填写进程</router-link>
            <router-link to="/administrator/matchResult">匹配结果查看</router-link>
            <router-link to="/administrator/sysMessage">系统消息</router-link>
        </div>


    </div>
</template>

<script setup>
import {RouterLink} from 'vue-router';
import {ref} from 'vue';
</script>

<style scoped>
.sideBarWrapper {
    display: flex;
    width: 300px;
    height: 100%;
    /* border-right: 1px solid rgba(0, 0, 0, 0.2); */
    box-shadow: 3px 1px 5px rgba(0, 0, 0, 0.1);
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.greeting {
    height: 15%;
    font: 300 30px 'Microsoft YaHei';
    text-align: center;
}
.sideBar {
    display: flex;
    height: 60%;
    flex-direction: column;
    justify-content: center;
    align-items: center;

}

a {
    flex: 1;
    color: #333;
    font: 20px 'Microsoft YaHei';
}
</style>