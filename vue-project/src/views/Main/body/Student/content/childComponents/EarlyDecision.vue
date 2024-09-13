<template>
    <div class="DistributeWrapper">
        <div class="headerWrapper">
            <p>向你发送邀请的老师</p>
        </div>
        <div class="listWrapper">
            <table>
                <tr class="titleRow">
                    <th>序号</th>
                    <th>姓名</th>
                    <th>院系</th>
                    <th>联系方式</th>
                    <th>名额</th>
                    <th></th>
                </tr>
                <tr v-for="(item,index) in tableData" :key="index" class="listRow" :class="index%2==1 ? 'oddRow': ''">
                    <td>{{ index + 1 }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.system }}</td>
                    <td>{{ item.contact }}</td>
                    <td>{{ item.number }}</td>
                    <td><a @click="toDetailInfo">查看</a></td>
                </tr>
            </table>
            <div class="pageTurnoverWrapper">
                <el-pagination
                    :page-size="20"
                    :pager-count="11"
                    layout="prev, pager, next"
                    :total="1000"
                />
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import {ref,reactive,onMounted} from 'vue';
import { useRouter } from 'vue-router';
const router = useRouter();
let tableData = ref([
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
    },
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
    },
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
    },
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
    },
])
onMounted(() => {
    axios({
        // url: 'http://116.62.164.229/hit_teacher_select_student/stu_getTeachersel/' + localStorage.getItem('id'),
        url: 'http://116.62.164.229/hit_teacher_select_student/stu_getTeachersel/2',
        method: 'get',
    }).then(response => {
        console.log(response.data);
        if(response.data.flag) {
            tableData.value = response.data.data;
        }
    }).catch(error => {
        console.log(error.message);
    })
})
function toDetailInfo(){
    router.push('/student/teacherInvitation');
}
</script>

<style scoped>
.DistributeWrapper {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
}
/* 头部的几个设置数目的部分 */
.headerWrapper {
    display: flex;
    margin-left: 2%;
    height: 100px;
    flex-direction: row;
    align-items: center;
}
.changeableFill {
    display: flex;
    align-items: center;
}
.changeableFill p {
    font: 18px 'Microsoft YaHei';
}
.inputSize {
    width: 120px;
    height: 40px;
    padding-right: 20px;
}
/* 表单部分 */
.listWrapper {
    position: relative;
    height: 100%;
    width: 96%;
    margin: 0 auto;
    border-bottom: 1px solid rgba(166, 166, 166, 1);
}
.listWrapper>table {
    width: 100%;
    border-collapse: collapse;
}
.titleRow {
    background-color: #CCC;
}

.listWrapper>table tr {
    height: 60px;
    font: 18px 'Microsoft YaHei';
}
.listRow {
    text-align: center;
}
.listRow:hover {
    background-color: #F5F7FA;
}
.oddRow {
    background-color: #CCC;
}
.oddRow:hover {
    background-color: rgba(166, 166, 166, 0.7);
}
/* 翻页器 */
.pageTurnoverWrapper {
    position: absolute;
    display: flex;
    height: 30px;
    right: 0;
    bottom: 5px;
}
</style>