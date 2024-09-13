<template>
    <div class="Wrapper">
        <div id="container">
            <table>
                <thead>
                    <tr>
                        <th class="th1Class">我的意向</th>
                        <th class="th2Class">选报导师</th>
                        <th class="th3Class">初步匹配结果</th>
                    </tr>
                </thead>
                <tbody>
                    <tr v-for="result in results">
                        <th class="th1Class">{{ result.title }}</th>
                        <th class="th2Class">{{ result.teacher }}</th>
                        <th class="th3Class">{{ result.primaryMatchResults }}</th>
                    </tr>
                </tbody>
            </table>
        </div>
        <el-button type="info" class="feedbackBtn">我要反馈</el-button>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { onMounted } from "vue";
import axios from 'axios';
onMounted(() => {
    axios({
        // url: 'http://116.62.164.229/hit_match/stu_get/' + localStorage.getItem('id'),
        url: 'http://116.62.164.229//hit_match/stu_get/2',
        method: 'get',
    })
        .then(response => {
            console.log(response.data);
        }).catch(error => {
            console.error(error.message);
        })
})
const titles = []
const results = ref([
    // { title: '第一意向', teacher: 'A', primaryMatchResults: '未被录取,顺至第二意向', },
    // { title: '第二意向', teacher: 'B', primaryMatchResults: '未被录取,顺至第三意向', },
    // { title: '第三意向', teacher: 'C', primaryMatchResults: '未被录取,顺至第四意向', },
    // { title: '第四意向', teacher: 'D', primaryMatchResults: '未被录取,顺至第五意向', },
    // { title: '第五意向', teacher: 'E', primaryMatchResults: '未被录取,顺至第六意向', },
    // { title: '第六意向', teacher: 'F', primaryMatchResults: '未被录取,进行随机分配', },
    // { title: '初步随机分配', teacher: '无', primaryMatchResults: '随机到导师G', },
    // { title: '最终随机分配', teacher: '无', primaryMatchResults: '随机到导师F', },
])

</script>

<style scoped>
.Wrapper {
    display: flex;
    flex-direction: column;
    justify-content: flex-start;
    flex-wrap: wrap;
}

#container {
    display: flex;
    flex-direction: column;
    justify-content: start;
    flex-wrap: wrap;
}

table {
    border-collapse: collapse;
    border: 1px solid black;
    width: 660px;
    height: 594px;
    display: flex;
    flex-wrap: wrap;
    float: left;
}

table,
th,
td {
    border: 1px solid black;
}

.th1Class {
    width: 200px;
    height: 66px;
}

.th2Class {
    width: 230px;
    height: 66px;
}

.th3Class {
    width: 230px;
    height: 66px;
}

/* 我要反馈按钮 */
.feedbackBtn {
    width: 20%;
    margin-top: 10px;
    align-self: flex-end;
}
</style>