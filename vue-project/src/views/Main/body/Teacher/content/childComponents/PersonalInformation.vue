<template>
    <div class="Wrapper">
        <div id="container">
            <div class="titleDiv">
                <ul class="titleUl">
                    <li>照片展示：</li>
                    <li>姓名：</li>
                    <li>工号：</li>
                    <li>电话：</li>
                    <li>职称：</li>
                    <li>专业：</li>
                    <li>研究方向：</li>
                    <li>邮箱/QQ:</li>
                    <li>今年可选的毕设题目：</li>
                    <li>个人简介：</li>
                    <li>备注：</li>
                </ul>
            </div>
            <div class="contentDiv">
                <ul class="contentUl">
                    <li><img src="https://tse1-mm.cn.bing.net/th/id/OIP-C.Uwo9I6s6RW11F6vX-YEEjgHaE8?w=303&h=202&c=7&r=0&o=5&dpr=1.5&pid=1.7"
                            class="imgClass">
                    </li>
                    <li><input type="text" v-model="Person.name"></li>
                    <li><input type="text" v-model="Person.teacherId"></li>
                    <li><input type="text" v-model="Person.phoneNumber"></li>
                    <li><input type="text" v-model="Person.title"></li>
                    <li><select class="selectClass1" v-model="Person.major">
                            <option selected="selected" disabled="disabled" style='display: none'></option>
                            <option value="计算机科学与技术学院">计算机科学与技术学院</option>
                            <option value="信息工程学院">信息工程学院</option>
                            <option value="海洋工程学院">海洋工程学院</option>
                            <option value="语言文学学院">语言文学学院</option>
                            <option value="经济管理学院">经济管理学院</option>
                            <option value="理学院">理学院</option>
                            <option value="汽车工程学院">汽车工程学院</option>
                            <option value="材料学院">材料学院</option>
                        </select> 是否愿意接收跨专业学生
                        <select class="selectClass2" v-model="Person.isAcceptCrossMajorStudent">
                            <option selected="selected" disabled="disabled" style='display: none'></option>
                            <option value="yes">是</option>
                            <option value="no">否</option>
                        </select>
                    </li>
                    <li><input type="text" v-model="Person.direction"></li>
                    <li><input type="text" v-model="Person.emailorqq"></li>
                    <li><input type="text" v-model="Person.graduationTitle" class="testInput"></li>
                    <li><input type="text" placeholder="           获得荣誉、性格等介绍" style="color: black;"
                            v-model="Person.selfIntroduction" class="testInput">
                    </li>
                    <li><input type="text" v-model="Person.remark" class="testInput"></li>
                </ul>
            </div>
            <div class="footer"><el-button type="primary" class="saveButton" @click="saveInformation">保存</el-button></div>
        </div>
    </div>
</template>

<script setup>
import axios from 'axios';
import { reactive, ref, onMounted } from 'vue';
import { useRoute } from 'vue-router';
const test = ref('')
const id = ref()
const Person = reactive({
    name: '',
    teacherId: '',
    phoneNumber: '',
    title: '',
    major: '',
    isAcceptCrossMajorStudent: '',
    direction: '',
    emailorqq: '',
    graduationTitle: '',
    selfIntroduction: '',
    remark: '',
})
onMounted(() => {
    axios({
        method: 'get',
        url: 'http://116.62.164.229/hit_teacher/getInfo/' + localStorage.getItem('token'),

    })
        .then(response => {
            console.log(response.data);
            id.value = response.data.data.id
            handleGetInformation(response.data.data)
            // console.log(person.value);
        }).catch(error => {
            console.error(error.data);
        })
})

function handleGetInformation(data) {
        Person.name = data.name,
        Person.phoneNumber = data.telephone,
        Person.title = data.degree,
        Person.major = data.major,
        Person.direction = data.field,
        Person.emailorqq = data.mail,
        Person.graduationTitle = data.titles,
        Person.selfIntroduction = data.info,
        Person.remark = data.memo,
        Person.isAcceptCrossMajorStudent = data.isaccout,
        Person.teacherId = data.teacherId;
}
function saveInformation() {
    // console.log(typeof (Person.major));
    console.log(Person);
    axios({
        method: 'put',
        url: 'http://116.62.164.229/hit_teacher/update_teacher_information',
        data: {
            id: id.value,
            photo: 'https://tse1-mm.cn.bing.net/th/id/OIP-C.Uwo9I6s6RW11F6vX-YEEjgHaE8?w=303&h=202&c=7&r=0&o=5&dpr=1.5&pid=1.7',
            name: Person.name,
            telephone: Person.phoneNumber,
            degree: Person.title,
            major: Person.major,
            isaccout: Person.isAcceptCrossMajorStudent,
            field: Person.direction,
            mail: Person.emailorqq,
            titles: Person.graduationTitle,
            info: Person.selfIntroduction,
            memo: Person.remark,
            teacherId: Person.teacherId,
        },

    })
        .then(response => {
            console.log(response);
            alert('保存成功')
        }).catch(error => {
            console.error(error.data);
        })
}

</script>

<style scoped>
.Wrapper {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
}

#container {
    display: flex;
    justify-content: flex-start;
    flex-wrap: wrap;
}

.titleDiv {
    width: 250px;
    height: 400px;
    text-align: right;
    display: flex;

}

.titleUl {
    display: flex;
    flex-wrap: wrap;
    list-style: none;
    margin-top: 75px;

}

.titleUl li {
    padding-left: 100px;
    padding-top: 10.5px;
    padding-bottom: 10.5px;

}

.contentDiv {
    width: 600px;
    height: 400px;
    display: flex;
    margin-left: 40px;

}

.contentUl {
    display: flex;
    list-style: none;
    flex-wrap: wrap;
}

.contentUl li {
    padding-top: 9px;
    padding-bottom: 9px;
}

input {
    background-color: #ebeef7;
    width: 600px;
    border-radius: 5px;
    border-color: white;
    border-top: 1px solid #ffffff;
    border-left: 1px solid #ffffff;
    height: 26px;
}

.selectClass1 {
    background-color: #ebeef7;
    width: 160px;
    border-color: #ffffff;
    height: 24px;
    border-radius: 5px;
}

.selectClass2 {
    background-color: #ebeef7;
    border-color: #ffffff;
    height: 24px;
    border-radius: 5px;
    width: 67px;
}

.imgClass {
    width: 150px;
    height: 100px;
}

.footer {
    display: flex;
    width: 600px;
}

.saveButton {
    width: 150px;
    text-decoration: underline;
    float: right;
    margin-top: 150px;
    margin-left: 800px;
}

.testInput {
    z-index: 100;
}
</style>