<template>
    <p class="accountIn">注册</p>
    <div class="chooseIdentity">
        <p>身份选择：</p>
        <el-select v-model="identity" class="m-2" placeholder="请选择身份" size="large">
            <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value"
            />
        </el-select>
    </div>
    <div class="inputBox">
        <el-input v-model="userId"  class="inputStyle" placeholder="请输入用户名" />
    </div>
    <div class="inputBox">
        <el-input type="password" v-model="password" class="inputStyle" placeholder="请输入密码" />
    </div>
    <div class="inputBox">
        <el-input type="password" v-model="confirmPass" class="inputStyle" placeholder="请再次确认密码" />
    </div>
    <div class="btnWrapper">
        <el-button type="primary" class="loginBtn" @click="registerFun">注册</el-button>
    </div>
    <div class="bottom">
        <router-link to="/">返回</router-link>
    </div>
</template>

<script setup>
import { ElMessage } from 'element-plus';
import {ref} from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';
const router = useRouter();
let userId = ref('');
let password = ref('');
let identity = ref();
const options = [
    {
        value: 1,
        label: '学生',
    },
    {
        value: 2,
        label: '教师',
    },
    {
        value: 1024,
        label: '管理员',
    }
]
let confirmPass = ref('');
function registerFun(){
    if(userId.value === '' || password.value === ''|| !identity.value){
        ElMessage.error('用户名,密码以及身份不可为空！');        
    }
    else if (password.value !== confirmPass.value) {
        ElMessage.error('两次输入密码不同，请重新输入！');
    }else {
        axios({
            url: 'http://116.62.164.229/start/register',
            method: 'post',
            data: {
                username: userId.value,
                password: password.value,
                identity: identity.value,
            }
        }).then(response => {
            if(response.data.flag) {
                ElMessage({
                    message: response.data.message,
                    type: 'success',
                })
                router.push('/');         
            } else {
                ElMessage.error(response.data.message);
            }

        }).catch(error => {
            console.log(error.message);
        })
    }
}
</script>

<style scoped>
/* 标题*/
.accountIn {
    height: 60px;
    font: 26px 'Microsoft YaHei';
}
/* 选择身份 */
.chooseIdentity {
    display: flex;
    width: 450px;
    justify-content: flex-start;
    align-items: center;
    margin-bottom: 5px;
}
.chooseIdentity p {
    font: 18px  'Microsoft YaHei';
}
/* 输入框 */
.inputBox {
    width: 450px;
    height: 80px;
}

.inputStyle {
    height: 60px;
    font: 20px 'Microsoft YaHei';
}
/* 注册按钮 */
.btnWrapper {
    height: 50px;
}

.loginBtn {
    width: 200px;
    height: 50px;
    font: 22px 'Microsoft YaHei';
    color: rgba(0, 0, 0, 0.8);
    background-color: rgba(42, 130, 228, 0.7);
}
/* 注册与忘记密码 */
.bottom {
    display: flex;
    width: 450px;
    justify-content: space-between;
}
.bottom a{
    color: black;
    font: 18px 'Microsoft YaHei';
    text-decoration: none;
}
</style>