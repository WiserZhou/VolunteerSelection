<template>
    <div class="processWrapper">
        <table>
            <tr>
                <td>总人数</td>
                <td>{{ num.sum }}</td>
            </tr>
            <tr>
                <td>已提交人数</td>
                <td>{{ num.sumbmitted }}</td>
            </tr>
            <tr>
                <td>未提交人数</td>
                <td>{{ num.noSubmit }}</td>
            </tr>
        </table>
        <el-button type="info" class="btnStyle">导出名单</el-button>
    </div>
</template>

<script setup>
import {ref} from 'vue';
let num = ref({
    sum: 20,
    sumbmitted: 10,
    noSubmit: 10,
})
</script>

<style scoped>
.processWrapper {
    width: 50%;
    display: flex;
    flex-direction: column;
    align-items: center;
}
table {
    width: 600px;
    height: 400px;
    border-collapse: collapse;
}

table td {
    width: 300px;
    border:  1px solid #2A82E4;
    text-align: center;
    font: 20px 'Microsoft YaHei';
}
/* 导出按钮 */
.btnStyle {
    width: 100px;
    height: 50px;
    margin-top: 10px;
    align-self: flex-end;
    font: 18px 'Microsoft YaHei';
}
</style>