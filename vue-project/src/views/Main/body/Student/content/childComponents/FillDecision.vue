<template>
    <!-- 确认提交对话框 -->
    <el-dialog
        v-model="dialogVisible"
        width="30%"
    >
        <span class="dialogContent">提交后不可更改！是否确认提交填写结果？</span>
        <template #footer>
        <span class="dialog-footer">
            <el-button type="primary" @click="confirmSubmit">
            是
            </el-button>
            <el-button @click="dialogVisible = false">否</el-button>
        </span>
        </template>
    </el-dialog>
    <div class="DistributeWrapper">
        <!-- 可填报教师信息 -->
        <div class="headerWrapper">
            <p>可填报教师信息</p>
        </div>
        <div class="listWrapper">
            <table>
                <tr class="titleRow">
                    <th></th>
                    <th>教师</th>
                    <th>学院</th>
                    <th>剩余名额</th>
                    <th>专业</th>
                    <th>联系方式</th>
                    <th></th>
                </tr>
                <tr v-for="(item,index) in tableData" :key="index" class="listRow" :class="index%2==1 ? 'oddRow': ''">
                    <td>{{ index + 1 + (currentPage - 1) * pageSize }}</td>
                    <td>{{ item.name }}老师</td>
                    <td>{{ item.major }}</td>
                    <td>{{ item.selNum }}</td>
                    <td>{{ item.field }}</td>
                    <td>{{ item.telephone }}</td>
                    <td><a @click="toDetailInfo(item.username)">查看详情</a></td>
                </tr>
            </table>
            <!-- 翻页器 -->
            <div class="pageTurnoverWrapper">
                <el-pagination
                    v-model:current-page="currentPage"
                    :page-size="pageSize"
                    :pager-count="11"
                    layout="prev, pager, next"
                    :total="total"
                    @current-change="changePageFillDecision"
                />
            </div>
        </div>
        <div class="fillDecisionWrapper">
            <div class="headerWrapper">
                <p>我的意向填写</p>
            </div>
            <div class="intentionWrapper oddRow">
                <div class="intention">
                    <p>第一意向</p><el-input v-model="input[0]"  />
                </div>
                <div class="intention">
                    <p>第二意向</p><el-input v-model="input[1]"  />
                </div>
            </div>
            <div class="intentionWrapper">
                <div class="intention">
                    <p>第三意向</p><el-input v-model="input[2]"  />
                </div>
                <div class="intention">
                    <p>第四意向</p><el-input v-model="input[3]"  />
                </div>
            </div>
            <div class="intentionWrapper oddRow">
                <div class="intention">
                    <p>第五意向</p><el-input v-model="input[4]"  />
                </div>
                <div class="intention">
                    <p>第六意向</p><el-input v-model="input[5]"  />
                </div>
            </div>
            <p class="submitResult" @click="toSubmit">提交结果</p>
        </div>
    </div>
</template>

<script setup>
import {ref,reactive,onMounted} from 'vue';
import { useRouter } from 'vue-router';
import { ElMessage } from 'element-plus';
import axios from 'axios';
const router = useRouter();
const currentPage = ref(1)
const pageSize = ref(4)
const total = ref(100)
const input = ref([]);
let dialogVisible = ref(false);
let tableData = ref([
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '1/10',
        major: '软件工程',
    },
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
        major: '软工',
    },
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
        major: '软工',
    },
    {
        name: '???',
        system: '计算机学院',
        contact: 'qq:12345678 电话：12345678910',
        number: '0/10',
    },
])
// 获取教师信息
onMounted(() => {
    axios({
        url: `http://116.62.164.229/hit_teacher/page_select_all_teacher/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    }).then(response => {
        console.log(response.data);
        if(response.data.flag) {
            tableData.value = response.data.data.records;
            total.value = response.data.data.total;
        }
    }).catch(error => {
        console.log(error.message);
    })
})
// 改变页数
function changePageFillDecision(){
    axios({
        url: `http://116.62.164.229/hit_teacher/page_select_all_teacher/${currentPage.value}/${pageSize.value}`,
        method: 'get',
    }).then(response => {
        console.log(response.data);
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
function toSubmit(){
    dialogVisible.value = true;
}
function confirmSubmit(){
    for (let i = 0; i < input.value.length; ++i){
        if(input.value[i] !== ''){
            let teacher = 'select_teacher' + (i + 1);
            axios({
                url: `http://116.62.164.229/hit_student_select_teacher/select_teacher${i + 1}`,
                method: 'put',
                data: {
                    student_id: localStorage.getItem('id'),
                    [teacher]: input.value[i],
                }
            }).then(response => {
                console.log(response.data);
                if(response.data.flag){
                    dialogVisible.value = false;
                    alert('提交成功！请耐心等待初步结果！');
                    router.push('/student/intentionFillMain');
                }else {
                    dialogVisible.value = false;
                    ElMessage.error(response.data.message);
                }
            }).catch(error => {
                console.log(error.message);
            })
        }
    }
}
</script>

<style scoped>
.dialogContent {
    font: 20px 'Microsoft YaHei';
}
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
    height: 50px;
    flex-direction: row;
    align-items: center;
}
.headerWrapper>p {
    font: 550 18px 'Microsoft YaHei';
}
/* 表单部分 */
.listWrapper {
    position: relative;
    height: 50%;
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
    font: 18px 'Microsoft YaHei';
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
/* 填写志愿 */
.fillDecisionWrapper {
    display: flex;
    width: 96%;
    margin: 0 auto;
    height: 40%;
    flex-direction: column;
}
.intentionWrapper {
    height: 50px;
    display: flex;
    flex-direction: row;
    justify-content: space-around;
}
.intentionWrapper>p {
    width: 50px;
}
.intention {
    display: flex;
    align-items: center;
}
.intention>p {
    width: 150px;
    font: 20px 'Microsoft YaHei';
}
.submitResult {
    align-self: flex-end;
    margin-top: 10px;
    color: rgba(212, 48, 48, 1);
    font: 20px 'Microsoft YaHei';
    text-decoration: underline;
    cursor: pointer;
}
</style>