<template>
    <div class="sideBarWrapper">
        <div class="greeting">您好！{{teacherName}}老师！<br>{{teacherID}}!</div>
        <div class="sideBar">
            <router-link to="/teacher/changePassword">修改密码</router-link>
            <router-link to="/teacher/personalInformation">我的信息</router-link>
            <router-link to="/teacher/primaryQue">提前批</router-link>
            <router-link to="/teacher/multiChoice">双选匹配</router-link>
            <router-link to="/teacher/completeResult">结果导出</router-link>
            <router-link to="/teacher/sysMessage">系统消息</router-link>
        </div>


    </div>
</template>

<script setup>
import {RouterLink} from 'vue-router';
import {ref,onMounted} from 'vue';
import axios from 'axios';
let teacherName = ref('');
let teacherID = ref('');
onMounted(() => {
    axios({
        url: 'http://116.62.164.229/hit_teacher/getInfo/' + localStorage.getItem('token'),
        method: 'get',
    }).then(response => {
        if(response.data.flag) {
            localStorage.setItem('id',response.data.data.id);
            teacherName.value = response.data.data.name;
            teacherID.value = response.data.data.teacherId;
        }
    }).catch(error => {
        console.log(error.message);
    })
})
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