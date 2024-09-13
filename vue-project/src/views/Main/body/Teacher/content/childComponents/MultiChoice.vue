<script setup>
import {reactive} from "vue";
import {useRouter} from 'vue-router';
const router = useRouter();
const item=reactive([{message:'第一意向匹配'},{message:'第二意向匹配'},{message: '第三意向匹配'},{message: '第四意向匹配'},{message: '第五意向匹配'},{message: '第六意向匹配'}])

function toStudentList(x){
  router.push(`/teacher/studentList/${x}`);
}
</script>

<template>
    <ul class="whole">
      <li v-for="(temp,index) in item">
        <el-button type="info" round class="item" @click="toStudentList(index + 1)">
          <strong><u>{{temp.message}}</u></strong>
        </el-button>
      </li>
    </ul>
    <!-- <p id="one"><b><strong><u>(第X匹配时间已过)</u></strong></b></p>
    <p id="two"><b><strong><u>(第X匹配时未到)</u></strong></b></p>
    <p id="three"><b><strong>...  ...</strong></b></p>
    <p id="four"><b><strong><u>(无申请人轮次)</u></strong></b></p> -->

</template>


<style scoped>
  .whole{
    display: flex;
    width: 600px;
    margin-top: 0px;
    margin-left: 100px;
    list-style-type:none ;
    flex-wrap: wrap;
    justify-content: space-between;
  }
  .item{
  font-weight: bolder;
    color: black;
    background-color:#CCCCCC;
    height: 60px;
    width: 280px;
    position: relative;
    margin-top: 30px;
    left: 50px;
  }
</style>
