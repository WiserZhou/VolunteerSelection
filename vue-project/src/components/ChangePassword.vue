<template>
    <el-dialog
        v-model="centerDialogVisible"
        width="30%"
        heignt="50%"
        align-center
        class="dialogSty"
        >
        <p class="dialogContent">{{ alertInf }}</p>
        <template #footer>
        <span class="dialog-footer">
            <el-button type="primary" @click="centerDialogVisible = false">
            确定
            </el-button>
        </span>
        </template>
    </el-dialog>
    <div class="entireWrapper">
        <div class="changePasswordWrapper">
            <div>
                <p class="labelTag">原密码：</p>
                <el-input type="password" v-model="oldPassword" placeholder="请输入原密码" class="inputSty" />
            </div>
            <div>
                <p class="labelTag">新密码：</p>
                <el-input type="password" v-model="newPassword" placeholder="请输入新密码" class="inputSty" />
            </div>
            <div>
                <p class="labelTag">验证码：</p>
                <el-input v-model="checkWord" placeholder="请输入验证码" class="inputSty" />
            </div>
        </div>
        <el-button type="primary" class="confirmBtn" @click="toSubmit">确认</el-button>
    </div>
</template>

<script setup>
import {ref} from 'vue';
import axios from 'axios';
let oldPassword = ref('');
let newPassword = ref('');
let checkWord = ref('');
let alertInf = ref('');
let centerDialogVisible = ref(false);
function toSubmit(){
    axios({
        url: '/api/hit_administer/update_password',
        method: 'put',
        data: {
            username: localStorage.getItem('token'),
            password: oldPassword.value,
            update_password: newPassword.value,
            identity: localStorage.getItem('identity'),
        }
    }).then(response => {
        alertInf.value = response.data.message;
        centerDialogVisible.value = true;
        if(response.data.flag) {
            oldPassword.value = '';
            newPassword.value = '';
        }
    }).catch(error => {
        console.log(error.message);
    })
}
</script>

<style>
.dialogSty {
    display: flex;
    width: 700px;
    height: 400px;
    background-color: #EBEDF7;
    flex-direction: column;
    justify-content: space-between;
}
</style>
<style scoped>
.dialogContent {
    font: 30px 'Microsoft YaHei';
    text-align: center;
}
.entireWrapper {
    display: flex;
    flex-direction: column;
    align-items: center;
}
.changePasswordWrapper {
    display: flex;
    width: 700px;
    height: 300px;
    background-color: rgba(237, 239, 247, 1);
    border-radius: 50px;
    box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.2);
    flex-direction: column;
    align-items: center;
    justify-content: center;
}
.changePasswordWrapper>div {
    display: flex;
    width: 70%;
    margin-top: 20px;
    justify-content: center;
    align-items: center;
}
.labelTag {
    width: 100px;
    font: 20px 'Microsoft YaHei';
}
.inputSty {
    height: 50px;
}
.confirmBtn {
    width: 150px;
    height: 50px;
    margin-top: 20px;
    align-self: flex-end;
    font: 20px 'Microsoft YaHei';
    box-shadow: 1px 1px 1px 1px rgba(0, 0, 0, 0.2);
}
</style>