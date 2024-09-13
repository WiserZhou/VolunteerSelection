<template>
    <div class="Wrapper">
        <div id="container">
            <div class="containerHeader">
                <p>最终匹配结果</p>
            </div>
            <div class="containerMain">
                <table>
                    <thead>
                        <tr>
                            <th class="nav idClass">序号</th>
                            <th class="nav majorClass">专业</th>
                            <th class="nav nameClass">姓名</th>
                            <th class="nav studyNumber">学号</th>
                            <th class="nav personInformation">个人信息</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(person, index) in persons">
                            <th>{{ index + 1 }}</th>
                            <th class="majorClass">{{ person.studentMajor }}</th>
                            <th>{{ person.studentName }}</th>
                            <th class="studyNumber">{{ person.studentId }}</th>
                            <th class="personInformation">查看</th>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="containerFooter">
                <el-button type="primary" class="buttonClass2">导出结果</el-button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref,onMounted } from 'vue';
import axios from 'axios';
let persons = ref([
    {
        id: 1, major: '计算机', name: '小王', studyNumber: '123', careerPlan: '学习', 
    },
    {
        id: 2, major: '计算机', name: '小李', studyNumber: '321', careerPlan: '吃饭', 
    },
    {
        id: 3, major: '计算机', name: '小周', studyNumber: '145', careerPlan: '玩耍',  
    },
    {
        id: 4, major: '计算机', name: '小赵', studyNumber: '789', careerPlan: '睡觉',  
    },
    {
        id: 5, major: '计算机', name: '小钱', studyNumber: '345', careerPlan: '打豆豆',  
    },

    {
        id: 6, major: '计算机', name: '小孙', studyNumber: '1234', careerPlan: '学习', 
    },
    {
        id: 7, major: '计算机', name: '小吴', studyNumber: '1345', careerPlan: '吃饭',  
    },
    {
        id: 8, major: '计算机', name: '小刘', studyNumber: '456', careerPlan: '睡觉',  
    },
    {
        id: 9, major: '计算机', name: '小红', studyNumber: '678', careerPlan: '打豆豆',  
    },
    {
        id: 10, major: '计算机', name: '小蓝', studyNumber: '334', careerPlan: '学习',  
    },
    {
        id: 11, major: '计算机', name: '小黑', studyNumber: '234', careerPlan: '哈哈哈',
    },
    {
        id: 12, major: '计算机', name: '小白', studyNumber: '574', careerPlan: 'qaq', 
    },
])
onMounted(() => {
    axios({
        // url: 'http://116.62.164.229/hit_match/tea_get/' + localStorage.getItem('id'),
        url: 'http://116.62.164.229/hit_match/tea_get/1',
        method: 'get',
    }).then(response => {
        console.log(response.data);
        if (response.data.flag) {
            persons.value = response.data.data;
        }
    }).catch(error => {
        console.log(error.message);
    })
})
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
.containerHeader p {
    font: 40px 'Microsoft YaHei';
}

.containerMain {
    display: flex;
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


.containerFooter {
    align-self: flex-end;
}

.buttonClass2 {
    width: 100px;
    height: 35px;
    text-decoration: underline;
    float: right;
    margin-right: 80px;
    margin-top: 20px;
}

</style>