<template>
    <div class="DistributeWrapper">
        <div class="headerWrapper">
            <div class="changeableFill">
                <p>所属院系：</p>
                <el-input v-model="queryInf.system" class="inputSize"/>
            </div>
            <div class="changeableFill">
                <p>所属专业：</p>
                <el-input v-model="queryInf.major" class="inputSize"/>
            </div>
            <div class="changeableFill">
                <p>职位：</p>
                <el-input v-model="queryInf.title" class="inputSize"/>
            </div>
            <el-button type="primary" round>查询</el-button>
        </div>
        <div class="listWrapper">
            <table>
                <tr class="titleRow">
                    <th>序号</th>
                    <th>教师姓名</th>
                    <th>所属院系</th>
                    <th>所属专业</th>
                    <th>职称</th>
                    <th>个人信息</th>
                    <th>联系方式</th>
                    <th>备注</th>
                </tr>
                <tr v-for="(item,index) in tableData" :key="index" class="listRow" :class="index%2==1 ? 'oddRow': ''">
                    <td>{{ index + 1 + (currentPage - 1) * pageSize }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.major }}</td>
                    <td>{{ item.field }}</td>
                    <td>{{ item.degree }}</td>
                    <td>方向、所得荣誉、性格等等...<a @click="toDetailInfo(item.username)">详情</a></td>
                    <td>{{ item.telephone }}</td>
                    <td>{{ item.extra }}</td>
                </tr>
            </table>
            <div class="pageTurnoverWrapper">
                <el-pagination
                    v-model:current-page="currentPage"
                    :page-size="pageSize"
                    :pager-count="11"
                    layout="prev, pager, next"
                    :total="total"
                    @current-change="changePageTeacherList"
                />
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import {ref,reactive,onMounted} from 'vue';
import { useRouter } from 'vue-router';
const currentPage = ref(1)
const pageSize = ref(9)
const total = ref(100)
const router = useRouter();
let queryInf = ref({
    system: '',
    major: '',
    title: '',
})
let tableData = ref([])
onMounted(() => {
    axios({
        url: `http://116.62.164.229/hit_teacher/page_select_all_teacher/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    }).then(response => {
        if(response.data.flag) {
            tableData.value = response.data.data.records;
            total.value = response.data.data.total;
        }
    }).catch(error => {
        console.log(error.message);
    })
})
function changePageTeacherList(){
    axios({
        url: `http://116.62.164.229/hit_teacher/page_select_all_teacher/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    }).then(response => {
        if(response.data.flag) {
            tableData.value = response.data.data.records;
            total.value = response.data.data.total;
        }
    }).catch(error => {
        console.log(error.message);
    })
}
function toDetailInfo(value){
    router.push('/student/teacherDetail/' + value);
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
    height: 50px;
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