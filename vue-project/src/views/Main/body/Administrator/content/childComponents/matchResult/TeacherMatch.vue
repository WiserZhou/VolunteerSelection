<template>
    <div class="teacherWrapper">
        <table>
            <tr>
                <th>序号</th>
                <th>教师姓名</th>
                <th>专业</th>
                <th>名额</th>
                <th>本专业</th>
                <th>外专业</th>
                <th>名单</th>
            </tr>
            <tr v-for="(item, index) in dataArr" class="detailContent">
                <td>{{ index + 1 + (currentPage - 1) * 8}}</td>
                <td>{{ item.name }}</td>
                <td>{{ item.major }}</td>
                <td>{{ item.number }}</td>
                <td>{{ item.thisMajor }}</td>
                <td>{{ item.otherMajor }}</td>
                <td><a href="#">导出</a></td>
            </tr>
        </table>
        <div class="turnPage">
            <el-pagination
                v-model:current-page="currentPage"
                :page-size="pageSize"
                :pager-count="11"
                layout="prev, pager, next"
                :total="1000"
                @current-change="changePage"
            />
        </div>
    </div>
</template>

<script setup>
import {ref,onMounted} from 'vue';
import axios from 'axios';
const currentPage = ref(1);
const pageSize = ref(8);
const dataArr = ref([
    {
        name: 'fff',
        major: 'xxxx',
        number: '10/10',
        thisMajor: 6,
        otherMajor: 4,
    },
    {
        name: 'fff',
        major: 'xxxx',
        number: '10/10',
        thisMajor: 6,
        otherMajor: 4,
    },
    {
        name: 'fff',
        major: 'xxxx',
        number: '10/10',
        thisMajor: 6,
        otherMajor: 4,
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
</script>

<style scoped>
.teacherWrapper {
    position: relative;
    width: 90%;
    height: 90%;
}
.teacherWrapper table {
    width: 100%;
    background-color: #EDEFF7;
    border-collapse: collapse;
}
.teacherWrapper tr {
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