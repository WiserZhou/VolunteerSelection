<template>
    <p class="accountIn">账号登录</p>
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
        <el-input v-model="userId" :prefix-icon="User" class="inputStyle" placeholder="请输入用户名" />
    </div>
    <div class="inputBox">
        <el-input type="password" v-model="password" :prefix-icon="Key" class="inputStyle" placeholder="请输入密码" />
    </div>
    <div class="weekLogin">
        <input type="checkbox">
        <p>一周内免登录</p>
    </div>
    <div class="btnWrapper">
        <el-button type="primary" class="loginBtn" @click="loginIn">登录</el-button>
    </div>
    <div class="bottom">
        <router-link to="/register">注册</router-link>
        <a href="#">忘记密码？</a>
    </div>
</template>

<script setup>
    import { RouterLink } from 'vue-router';
    import {User,Key} from '@element-plus/icons-vue';
    import {ref} from 'vue';
    import {useRouter} from 'vue-router';
    import { ElMessage } from 'element-plus';
    import axios from 'axios';
    let userId = ref('');
    let password = ref('');
    let identity = ref();
    const router = useRouter();
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
    function loginIn(){
        if(!identity.value) {
            ElMessage.error('身份不可为空！');        
        }
        else {
            axios({
                url:'http://116.62.164.229/start/login', 
                method: 'post',
                data: {
                    username: userId.value,
                    password: password.value,
                    identity: identity.value,
                },
            }).then(response => {
                console.log(response.data);
                if (response.data.flag) {
                    localStorage.setItem('token',userId.value);
                    localStorage.setItem('identity',identity.value);
                    if (identity.value === 1) {
                        router.push('/student');
                    }else if(identity.value === 2) {
                        router.push('/teacher');
                    }else if(identity.value === 1024) {
                        router.push('/administrator');
                    }
                    ElMessage({
                        message: response.data.message,
                        type: 'success',
                    })
                }else {
                    ElMessage.error(response.data.message);
                }
            }).catch(error => {
                console.log(error.message);
            })
        }
    }
</script>

<style scoped>
/* 账号登录标题 */
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
/* 一周内免登录 */
.weekLogin {
    display: flex;
    width: 440px;
    height: 50px;
    justify-content: flex-start;
    align-items: center;
    font: 16px 'Microsoft YaHei';
}

.weekLogin input {
    width: 25px;
    height: 25px;
}

.weekLogin p {
    padding-left: 5px;
}
/* 登入按钮 */
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