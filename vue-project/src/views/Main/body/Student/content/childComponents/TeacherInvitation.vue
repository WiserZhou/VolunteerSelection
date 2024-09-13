<template>
    <!-- 接受对话框 -->
    <el-dialog
        v-model="acceptDialog"
        width="30%"
        @close="accept"
    >
        <span class="dialogContent">恭喜！您与XXX老师毕业设计匹配成功。</span>
        <template #footer>
        <span class="dialog-footer">
            <el-button type="primary" @click="accept">
            确定
            </el-button>
        </span>
        </template>
    </el-dialog>
    <!-- 拒绝对话框 -->
    <el-dialog
        v-model="refuseDialog"
        width="30%"
        @close="refuse"
    >
        <span class="dialogContent">请填写意向，进入双选匹配环节。</span>
        <template #footer>
        <span class="dialog-footer">
            <el-button type="primary" @click="refuse">
            确定
            </el-button>
        </span>
        </template>
    </el-dialog>
    <div class="invitationWrapper">
        <div class="teacherWrapper">
            <div class="picWrapper">
                <img src="" alt="">
            </div>
            <div class="infWrapper">
                <p>教师：{{ teacherInf.name }}老师&nbsp;&nbsp;{{ teacherInf.system }}</p>
                <p>研究方向:{{ teacherInf.field }}</p>
            </div>
        </div>
        <div class="dialogWrapper">
            <div class="questionWrapper">
                <p>是否接受该教师邀请？</p>
            </div>
            <div class="chooseWrapper">
                <p @click="toAccept">是</p>
                <p @click="toRefuse">否</p>
            </div>
        </div>
        <el-button type="primary" class="btnStyle" @click="goBack">返回</el-button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router';
import {ref} from 'vue';
const router = useRouter();
let teacherInf = ref({
    name: 'A',
    system: '软件学院',
    field: '大数据',
})
let acceptDialog = ref(false);
let refuseDialog = ref(false);
function toAccept(){
    acceptDialog.value = true;
}
function toRefuse(){
    refuseDialog.value = true;
}
function accept(){
    acceptDialog = false;
    router.push('/student/intentionFillMain');
}
function refuse(){
    refuseDialog.value= false;
    router.push('/student/intentionFillMain');
}
function goBack(){
    router.push('/student/earlyDecision');
}
</script>

<style scoped>
/* 消息框 */
.dialogContent {
    font: 20px 'Microsoft YaHei';
}
.invitationWrapper {
    display: flex;
    width: 80%;
    height: 94%;
    flex-direction: column;
    justify-content: flex-start;
    align-items: center;
}
/* 上方老师信息 */
.teacherWrapper {
    display: flex;
    width: 100%;
    flex: 2;
    background-color: #CCC;
    margin-bottom: 10px;
    border-radius: 20px;
    justify-content: center;
    align-items: center;
}
.picWrapper {
    width: 20%;
    height: 80%;
    background-color: #fff;
}
.infWrapper {
    display: flex;
    width: 50%;
    height: 80%;
    margin-left: 20px;
    background-color: #fff;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.infWrapper>p {
    font: 26px 'Microsoft YaHei';
}
/* 下方对话框确认是否 */
.dialogWrapper {
    display: flex;
    width: 100%;
    flex: 1;
    background-color: #CCC;
    border-radius: 20px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.questionWrapper {
    display: flex;
    width: 80%;
    height: 50%;
    background-color: #fff;
    justify-content: center;
    align-items: center;
}
.questionWrapper>p {
    font: 26px 'Microsoft YaHei';
}
.chooseWrapper {
    display: flex;
    width: 80%;
    justify-content: flex-end;

}
.chooseWrapper>p {
    margin-right: 10px;
    text-decoration: underline;
    font: 20px 'Microsoft YaHei';
    cursor: pointer;
}
.btnStyle {
    align-self: flex-end;
    margin-top: 10px;
    width: 100px;
    height: 50px;
}
</style>