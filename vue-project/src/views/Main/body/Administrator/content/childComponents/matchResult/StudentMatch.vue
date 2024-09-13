<template>
    <div class="studentWrapper">
        <table>
            <tr>
                <th>序号</th>
                <th>学生姓名</th>
                <th>学号</th>
                <th>专业</th>
                <th>匹配结果</th>
            </tr>
            <tr v-for="(item, index) in dataArr" class="detailContent">
                <td>{{ index + 1 + (currentPage - 1) * 8 }}</td>
                <td>{{ item.studentName }}</td>
                <td>{{ item.studentId }}</td>
                <td>{{ item.studentMajor }}</td>
                <td>{{ item.teacherName }}老师 {{item.teacherMajor}}</td>
            </tr>
        </table>
        <div class="turnPage">
            <el-pagination
                v-model:current-page="currentPage"
                :page-size="pageSize"
                :pager-count="11"
                layout="prev, pager, next"
                :total="totalItems"
                @current-change="changePage"
            />
        </div>
        <el-button type="primary" plain class="btnStyle">导出名单</el-button>
    </div>
</template>

<script setup>
import axios from 'axios';
import {ref,reactive,onMounted} from 'vue';
const totalItems = ref(1);
const currentPage = ref(1);
const pageSize = ref(8);
const dataArr = ref([
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
    {
        name: 'fff',
        id: '233',
        major: 'xxxx',
        result: 'xx老师 xxx专业',
    },
])
onMounted(() => {
    axios({
        url: `http://116.62.164.229/hit_match/get_match/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    }).then(response => {
        if(response.data.flag)
        {
            dataArr.value = response.data.data.records;
            totalItems.value = response.data.data.total;
        }
    }).catch(error => {
        console.log(error.message);
    })
})
function changePage(){
    axios({
        url: `http://116.62.164.229/hit_match/get_match/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    }).then(response => {
        if(response.data.flag)
        {
            dataArr.value = response.data.data.records;
            totalItems.value = response.data.data.total;
        }
        console.log(response.data);
    }).catch(error => {
        console.log(error.message);
    })
}
</script>

<style scoped>
.studentWrapper {
    position: relative;
    width: 90%;
    height: 90%;
}
.studentWrapper table {
    width: 100%;
    background-color: #EDEFF7;
    border-collapse: collapse;
}
.studentWrapper tr {
    height: 50px;
    font: 18px 'Microsoft YaHei';
}
.detailContent td{
    border-top: 1px solid rgb(166, 166, 166);
    text-align: center;
}
.turnPage {
    display: flex;
    justify-content: flex-end;
    align-items: center;
}
.btnStyle {
    position: absolute;
    right: 10px;
    bottom: 10px;
    width: 100px;
    height: 50px;
    font: 18px 'Microsoft YaHei';
}
</style>