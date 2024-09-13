<script setup>
import axios from "axios";
import {reactive,onMounted} from "vue";
import {useRoute} from 'vue-router';
const route = useRoute();
const intentionIndex = ['一','二','三','四','五','六'];
const head=reactive({
  id:"序号         ",
  name:"姓名             ",
  major:"专业 ",
  plan:"生涯规划          ",
  info:"个人信息          ",
  option:"勾选      ",
})
const content=reactive([{
  id:"1",
  name:"xx",
  major:"新能源科学与工程",
  plan:"考研",
  info:"查看",
},{
  id:"2",
  name:"xx",
  major:"电气工程及自动化",
  plan:"工作",
  info:"查看",
}])
// 获取数据
onMounted(() => {
    axios({
      url: `http://116.62.164.229/hit_student_select_teacher/get_student_has_select${route.params.id}/${localStorage.getItem('id')}`,
      method: 'get',
    }).then(response => {
      console.log(response.data);
    }).catch(error => {
      console.log(error.message);
    })
})
</script>

<template>
  <div>
    <span class="whole">
<!--  <a href="http://www.baidu.com" id="title">向您发送第一意向申请的学生</a>-->
  <p id="title" >向您发送第{{ intentionIndex[route.params.id - 1] }}意向申请的学生</p>
  <p id="none"><u>尚未勾选的学生</u></p>
  <p id="done"><u>查看已勾选的学生</u></p>
  <p id="remain"><u>您的名额剩余数【7】</u></p>
</span>
  <div class="main">
    <table>
      <tr >
        <th id="top">
        {{head.id}}{{head.name}}{{head.major}}{{head.plan}}{{head.info}}{{head.option}}</th>
      </tr>

      <tr v-for = "(item,index) in content" :key="index" :class="'content'+(index+1)" id="small" >
        <td id="last">
          <strong id="id">{{item.id}}</strong>
          <strong id="name">{{item.name}}</strong>
          <strong id="major">{{item.major}}</strong>
          <strong id="plan"> {{item.plan}}</strong>
          <u id="info">{{item.info}}</u>
          <form>
            <input type="checkbox" value="hei" name="01" id="choice">
          </form>

        </td>
        <br><br><br><br>
      </tr>
      </table>
      <div style="background-color: #CFCFCF; height: 80px ;width:1000px;margin-top: -80px;z-index: -1"></div>
    </div>
    <p id="shen"> ...</p>
  </div>

</template>



<style scoped>
#shen{
  margin-left: 50px;
  font-size: 30px;
}
.whole{
  position: relative;
  left: 20px;
}
#id{
  position: relative;
  left: -40px;
}
#name{
  position: relative;
  left: 125px;
}
#major{
  position: relative;
  left: 250px;
}
#plan{
  margin-left: 140px;
  position: relative;
  left: 200px;
}
#info{
  margin-left: -180px;
  position: relative;
  left: 550px;
}
#choice{
  position: relative;
  top: -30px;
  left: 910px;
}
#small{
  position: relative;
  left: 50px;

  top: 30px;
  color: black;
  word-spacing: 140px;
}

  #top{

    height: 80px;
    width: 1000px;
    background-color: #CFCFCF;
    word-spacing: 140px;
    top: 30px;
    color: black;

  }
.main{
  margin-left: 35px;
  margin-top: 10px;
}
  #title{
    margin-left: -150px;
    text-align: center;
    font-size: 30px;
    font-family: 黑体;
    margin-top: 10px;

  }
  #none{
    margin-left: 700px;
    margin-top: -55px;
  }
  #done{

    margin-left: 700px;
    color: #4191EE;
  }
  #remain{
    font-size: 18px;
    color: red;
    margin-left: 850px;
    margin-top: -45px;
  }
</style>
