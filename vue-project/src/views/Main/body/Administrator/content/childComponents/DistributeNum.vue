<template>
    <div class="DistributeWrapper">
        <div class="headerWrapper">
            <div class="changeableFill">
                <p>学生最多可填写的志愿数目：</p>
                <el-input v-model="stuNum" placeholder="请输入数字" class="inputSize"/>
                <el-button type="primary" round>保存</el-button>
            </div>
            <div class="changeableFill">
                <p>多少轮后可使用系统自动匹配功能：</p>
                <el-input v-model="roundNum" placeholder="请输入数字" class="inputSize"/>
                <el-button type="primary" round>保存</el-button>
            </div>
            <div class="changeableFill">
                <p>教师在提前批最多邀请学生数：</p>
                <el-input v-model="mostStuNum" placeholder="请输入数字" class="inputSize"/>
                <el-button type="primary" round>保存</el-button>
            </div>
        </div>
        <div class="listWrapper">
            <table>
                <tr class="titleRow">
                    <th>序号</th>
                    <th>教师姓名</th>
                    <th>所属院系</th>
                    <th>所属专业</th>
                    <th>可分配名额数</th>
                    <th>保存</th>
                </tr>
                <tr v-for="(item,index) in tableData" :key="index" class="listRow">
                    <td>{{ index + 1 }}</td>
                    <td>{{ item.name }}</td>
                    <td>{{ item.system }}</td>
                    <td>{{ item.major }}</td>
                    <td class="numWrapper">
                        <div>
                            <p>本专业：</p><el-input v-model="item.stuNumber" placeholder="请输入数字" class="inputStyle"/>
                        </div>
                        <div>
                            <p>其他专业：</p><el-input v-model="item.otherMajor" class="inputStyle"/>
                        </div>
                        <div>
                            <el-input v-model="item.otherMajorNum" placeholder="请输入数字" class="inputStyle"/>
                        </div>
                    </td>
                    <td><el-button type="primary">保存</el-button></td>
                </tr>
            </table>
            <div class="pageTurnoverWrapper">
                <el-pagination
                    :page-size="20"
                    :pager-count="11"
                    layout="prev, pager, next"
                    :total="1000"
                />
            </div>
        </div>
        <div class="footerWrapper">
            <el-button type="primary">全部清空</el-button>
            <el-button type="primary">全部保存</el-button>
        </div>
    </div>
</template>

<script setup>
import {ref,reactive} from 'vue';
let stuNum = ref('');
let roundNum = ref('');
let mostStuNum = ref('');
let tableData = reactive([
    {
        name: '???',
        system: '计算机学院',
        major: '软件工程',
        stuNumber: 5,
        otherMajor: '',
        otherMajorNum:5,
    },
    {
        name: '???',
        system: '计算机学院',
        major: '软件工程',
        stuNumber: 5,
        otherMajor: '',
        otherMajorNum:5,
    },
])
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
    height: 100px;
    flex-direction: row;
    flex-wrap: wrap;
}
.changeableFill {
    width: 50%;
    display: flex;
    flex-direction: row;
    justify-content: center;
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
    height: 460px;
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
.numWrapper {
    height: 50px;
    display: flex;
    align-items: center;
    justify-content: center;
}
.numWrapper>div {
    margin-top: 2px;
    display: flex;
    align-items: center;
}
.numWrapper>div>p{
    width: 80px;
}
.inputStyle {
    width: 100px;
    margin: 0 10px;
}
/* 翻页器 */
.pageTurnoverWrapper {
    position: absolute;
    display: flex;
    height: 30px;
    right: 0;
    bottom: 5px;
}
/* 底部按钮栏 */
.footerWrapper {
    flex: 1;
    width: 96%;
    height: 100%;
    margin: 0 auto;
    display: flex;
    justify-content: flex-end;
    align-items: center;
}
</style>