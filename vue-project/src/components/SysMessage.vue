<template>
    <div class="wholeWrapper">
        <div class="message" v-for="item in messageList" :key="item.id">
            <div class="front">
                <span class="title">{{ item.timeName }}</span><span class="time">{{ item.timeFlag }}</span>
            </div>
            <p class="text">{{ item.description }}</p>
        </div>
    </div>
</template>
<script setup>
import {ref,reactive} from 'vue';
import axios from "axios";
let messageList = ref([
    {
        id: '001',
        timeName: '',
        timeFlag: '××年××月××日××：××',
        description: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容。',
    },
    {
        id: '002',
        timeName: '',
        timeFlag: '××年××月××日××：××',
        description: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容。',
    },
    {
        id: '003',
        timeName: '',
        timeFlag: '××年××月××日××：××',
        description: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容。',
    },
    {
        id: '004',
        timeName: '',
        timeFlag: '××年××月××日××：××',
        description: '内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容内容。',
    }
])
axios({
    url: '/api/hit_administer/find_all_time',
    method: 'get',
}).then(response => {
    if(response.data.flag)
    {
        messageList.value = response.data.data;
    }
    console.log(response.data);
}).catch(error => {
    console.log(error.message);
})
</script>
<style scoped>
.wholeWrapper {
    width: 100%;
    height: 100%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.message{
    display: flex;
    margin-top: 25px;
    width: 90%;
    height: 130px;
    background: rgba(204, 204, 204, 1);
    flex-direction: column;
}
.title{
    /** 文本1 */
    padding: 0 10px;
    font-size: 30px;
    color: rgba(0, 0, 0, 1);
}
.time{
    /** 文本2 */
    font-size: 24px;
    color: rgba(0, 0, 0, 1);
    margin-left: 30px;
}
.front{
    width: 761px;
    height: 48px;
}
.text{
    width: 100%;
    margin-top: 8px;
    padding: 0 20px;
    /** 文本1 */
    font-size: 24px;
    color: rgba(0, 0, 0, 1);
    text-align: left;
}
</style>