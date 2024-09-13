<template>
    <div class="Wrapper">
        <div id="container">
            <div class="containerHeader">
                <div><input type="text" class="inputClass" v-model="searchContent"></div>
                <div><el-button type="primary" class="buttonClass1" @click="Searchstudent">搜索</el-button></div>
                <div>
                    <p class="pClass">您至多能邀请【{{ availableStudentNumber }}】名学生</p>
                </div>
            </div>
            <div class="containerMain">
                <table>
                    <thead>
                        <tr>
                            <th class="nav idClass">序号</th>
                            <th class="nav majorClass">专业</th>
                            <th class="nav nameClass">姓名</th>
                            <th class="nav studyNumber">学号</th>
                            <th class="nav careerPlan">生涯规划</th>
                            <th class="nav personInformation">个人信息</th>
                            <th class="nav isChecked">是否勾选</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(person, index) in persons">
                            <th>{{ index + 1 + (currentPage - 1) * pageSize }}</th>
                            <th class="majorClass">{{ person.major }}</th>
                            <th>{{ person.name }}</th>
                            <th class="studyNumber">{{ person.studyID }}</th>
                            <th class="careerPlan">{{ person.plan }}</th>
                            <th class="personInformation">{{ person.selfInformation }}</th>
                            <th class="isChecked"><input type="checkbox" v-model="person.status" @change="checkNum(index)">
                            </th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <!-- <div class="check">
                <a href="https://baidu.com" target="_blank">查看</a>
            </div> -->
            <div class="pageTurnoverWrapper">
                <el-pagination
                    v-model:current-page="currentPage"
                    :page-size="pageSize"
                    :pager-count="11"
                    layout="prev, pager, next"
                    :total="1000"
                    @current-change="changePagePQ"
                />
            </div>
            <div class="navigate">
                <p class="navigateTextClass">
                    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    目前已经勾选{{ selectedStudentNumber }}名学生,还可选择{{ availableStudentNumber - selectedStudentNumber }}名学生</p>
            </div>
            <div class="containerFooter">
                <el-button type="primary" class="buttonClass2" @click="inviteSuccess">发送邀请</el-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ref, computed } from 'vue'
import { onMounted } from "vue";
const currentPage = ref(1)
const pageSize = ref(8)
let availableStudentNumber = ref(3);
const searchContent = ref('');
let persons = ref([])
let selectedStudentNumber = computed(() => {
    const personArr = persons.value.filter((x) => {
        return x.status === true;
    })
    return personArr.length;
})

// 获取提前批数据
onMounted(() => {
    axios({
        url: `http://116.62.164.229/hit_student/page_select_all_student/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    })
        .then(response => {
            console.log(response.data.data.records);
            persons.value = response.data.data.records;
        }).catch(error => {
            console.log(error.message);
        })
})
// 更改页数获取当前页数的提前批数据
function changePagePQ() {
    axios({
        url: `http://116.62.164.229/hit_student/page_select_by_name/${currentPage.value}/${pageSize.value}/${searchContent.value}`,
        method: 'get',
    })
    .then(response => {
        console.log(response.data);
        persons.value = response.data.data.records;
    }).catch(error => {
        console.log(error.message);
    })
}

function Searchstudent() {
    axios({
        method: 'get',
        url: `http://116.62.164.229/hit_student/page_select_by_name/1/${pageSize.value}/${searchContent.value}`
    }).then(response => {
        persons.value = response.data.data.records;
    }).catch(error => {
        console.error(error.data);
    })
}

function inviteSuccess() {
    for (let i = 0; i < persons.value.length; ++i) {
        if ((persons.value)[i].status == true) {
            axios({
                method: 'post',
                url: 'http://116.62.164.229/hit_teacher_select_student/tea_select_stu',
                data: {
                    student_id: persons.value[i].id,
                    teacher_id: localStorage.getItem('id'),
                }
            })
            .then(response => {
                console.log(response.data);
            }).catch(error => {
                console.error(error.data);
            })
        }
    }
    alert('邀请成功')
}
function checkNum(index) {
    if (availableStudentNumber.value - selectedStudentNumber.value < 0) {
        (persons.value)[index].status = false;
        alert('您已经达到您能邀请的上限啦！');
    }
}

</script>

<style scoped>
.Wrapper {
    display: flex;
    width: 100%;
    justify-content: center;
}

#container {
    display: flex;
    width: 90%;
    flex-direction: column;
    justify-content: space-around;
    align-items: center;
}

.containerHeader {
    display: flex;
    flex-direction: row;
    justify-content: flex-start;
    margin-bottom: 10px;
}

.containerMain {
    display: flex;
}

.navigate {
    display: flex;
    align-self: flex-start;
}



.inputClass {
    background-color: #ebeef7;
    width: 600px;
    height: 35px;
    font-size: 20px;

}

.containerHeader div:nth-child(1) {
    float: left;
    margin-left: 50px;
}

.containerHeader div:nth-child(2) {
    float: left;
    margin-left: 40px;
}

.containerHeader div:nth-child(3) {
    float: left;
    margin-left: 30px;
}

.buttonClass1 {

    width: 60px;
    height: 35px;
    font-size: 20px;
}

.pClass {
    color: red;
    font-size: 20px;
}

table {
    border-collapse: collapse;
    width: 1100px;
    height: 380px;
}

table,
th,
td {
    border: 1px solid black;
}

.nav {
    background-color: #ebeef7;
}

.idClass {
    width: 70px;
}

.majorClass {
    width: 220px;
}

.nameClass {
    width: 70px;
}

.studyNumber {
    width: 140px;
}

.careerPlan {
    width: 230px;
}

.personInformation {
    width: 70px;
}

.isChecked {
    width: 70px;

}

.navigateTextClass {
    font-size: 20px;
}

.containerFooter {
    align-self: flex-end;
}

.buttonClass2 {
    width: 100px;
    height: 35px;
    text-decoration: underline;
    float: right;
    margin-right: 80px;

}

.check a {
    text-decoration: underline;
    color: black;
    float: right;
    margin-right: 117px;
}

.pageTurnoverWrapper {
    display: flex;
    width: 88%;
    height: 50px;
    justify-content: flex-end;
}
</style>