<template>
    <div class="sideBarWrapper">
        <div class="greeting">您好！{{ name }}同学！<br>{{stuId}}!</div>
        <div class="sideBar">
            <router-link to="/student/personCenter">个人中心</router-link>
            <router-link to="/student/teacherList">教师信息</router-link>
            <router-link to="/student/intentionFillMain">意向填报</router-link>
            <router-link to="/student/checkResult">匹配结果查看</router-link>
            <router-link to="/student/sysMessage">系统消息</router-link>
        </div>


    </div>
</template>

<script setup>
import {RouterLink} from 'vue-router';
import {ref,onMounted} from 'vue';
import axios from 'axios';
const name = ref('');
const stuId = ref('');
onMounted(() => {
    axios({
        url: 'http://116.62.164.229/hit_student/getInfo/' + localStorage.getItem('token'),
        method: 'get',
    }).then(response => {
        if(response.data.flag) {
            localStorage.setItem('id',response.data.data.id);
            name.value = response.data.data.name;
            stuId.value = response.data.data.studentId;

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