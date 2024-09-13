<template>
    <div class="stuInfWrapper">
        <table class="infTable" >
            <tr>
                <th>姓名</th>
                <th><input type="text" v-model="personalInf.name" :disabled="operate"></th>
            </tr>
            <tr>
                <th>学号</th>
                <th><input type="text" v-model="personalInf.studentId" :disabled="operate"></th>
            </tr>
            <tr>
                <th>生涯规划</th>
                <th>
                    <ul class="lifeChoice" >
                        <li><input type="radio" name="choice" value="保研在本校做毕设" v-model="personalInf.plan" :disabled="operate"><p>保研在本校做毕设</p></li>
                        <li><input type="radio" name="choice" value="保研跟外校老师做毕设" v-model="personalInf.plan" :disabled="operate"><p>保研跟外校老师做毕设</p></li>
                        <li><input type="radio" name="choice" value="考研" v-model="personalInf.plan" :disabled="operate"><p>考研</p></li>
                        <li><input type="radio" name="choice" value="找工作" v-model="personalInf.plan" :disabled="operate"><p>找工作</p></li>
                    </ul>
                </th>
            </tr>
            <tr>
                <th>专业</th>
                <th><input type="text" v-model="personalInf.major" :disabled="operate"></th>
            </tr>
            <tr>
                <th>联系方式</th>
                <th><input type="text" v-model="personalInf.telephone" :disabled="operate"></th>
            </tr>
            <tr>
                <th>个人简介</th>
                <th class="personIntroduction"><textarea cols="30" rows="10" class="textStyle" v-model="personalInf.text" :disabled="operate"></textarea></th>
            </tr>
        </table>
        <el-button type="primary" plain class="btnStyle" @click="toEdit" v-if="vis" >编辑</el-button>
        <el-button type="primary" class="btnStyle" @click="saveInf" v-if="!vis">保存</el-button>
</div>
</template>

<script setup>
import axios from 'axios'
import {reactive,ref,onMounted} from 'vue';
const personalInf = ref({});
const operate = ref(true);
const vis = ref(true);
onMounted(() => {
    axios({
        url: 'http://116.62.164.229/hit_student/getInfo/' + localStorage.getItem('token'),
        method: 'get',
    }).then(response => {
        if(response.data.flag) {
            personalInf.value = response.data.data;
        }
        console.log(response.data);
    }).catch(error => {
        console.log(error.message);
    })
})
function saveInf(){
    axios({
        url:"http://116.62.164.229/hit_student/update_student_information",
        method:"put",
        data:{
            id: personalInf.value.id,
            name: personalInf.value.name,
            studentId: personalInf.value.studentId,
            plan: personalInf.value.plan,
            major: personalInf.value.major,
            telephone: personalInf.value.telephone,
            text: personalInf.value.text,
        },
    })
    .then(response =>{
        if (response.data.flag){
            alert('保存成功');
            vis.value = !vis.value;
            operate.value = !operate.value;
        }
    })
    .catch(error =>{
        console.log(error.data);
    })
}
function toEdit(){
    vis.value = !vis.value;
    operate.value = !operate.value;
}


</script>

<style scoped>
.stuInfWrapper {
    display: flex;
    flex-direction: column;
    font: 20px 'Microsoft YaHei';
}
.infTable {
    width: 800px;
    height: 450px;
    border-spacing: 0;
    border-collapse: collapse;
}
.infTable th {
    border: 1.5px solid rgba(111, 188, 242, 1);
}
.infTable input[type="text"] {
    width: 100%;
    height: 100%;
    font: 20px 'Microsoft YaHei';
    text-align: center;
    border: none;
    outline: none;
}
/* 生涯规划 */
.lifeChoice {
    display: flex;
    justify-content: space-around;
}
.lifeChoice li {
    display: flex;
    list-style: none;

}
/* 个人介绍 */
.personIntroduction {
    width: 700px;
    height: 150px;
}
.textStyle {
    width: 700px;
    height: 150px;
    font: 20px 'Microsoft YaHei';
    resize:none;
    overflow:scroll;
    border: none;
    outline: none;
}
.btnStyle {
    align-self: flex-end;
    width: 150px;
    height: 40px;
    margin-top: 10px;
    font: 20px 'Microsoft YaHei';
}
</style>
