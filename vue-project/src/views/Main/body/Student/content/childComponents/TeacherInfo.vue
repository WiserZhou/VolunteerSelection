<template>
  <div>
    <span style="margin-top: 50px;position: relative;left: 50px">
      <div>
      <p class="wenben">姓名</p>
      <textarea v-model="Tea.username" class="put"  id="head1" :disabled="true"></textarea>
        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
      <div>
      <p class="wenben">职称</p>
      <textarea v-model="Tea.degree" class="put" id="head1" :disabled="true"></textarea>
        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
      <div>
      <p class="wenben">所属专业</p>
      <textarea v-model="Tea.major" class="put"  id="head2" :disabled="true"></textarea>

        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
      <div>
      <p class="wenben">研究方向</p>
      <textarea v-model="Tea.field" class="put" id="head2" :disabled="true"></textarea>

        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
      <div>
      <p class="wenben">联系方式</p>
      <textarea v-model="Tea.telephone" class="put"  id="head2" :disabled="true"></textarea>

        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
      <div>
      <p class="wenben">今年可选毕设题目</p>
      <textarea v-model="Tea.titles" class="put" :disabled="true"></textarea>

        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
          <div>
      <p class="wenben">个人简介</p>
      <textarea v-model="Tea.info" class="put" id="head2" :disabled="true" ></textarea>

        <hr width="600" align="left" style="margin-top: -18px;" >
    </div>
    </span>
    <div id="img">
      <p id="zhao">老师照片</p>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios'
import {reactive,ref,onMounted} from 'vue'
import { useRoute } from 'vue-router';
const route = useRoute();
let Tea = ref({
  name: '',
  level: ' ',
  major: '',
  dir: '',
  phone: '',
  title: '',
  info:"",
})
onMounted(() => {
    axios({
      url:"http://116.62.164.229/hit_teacher/getInfo/" + route.params.username,
      method:"get",
    }).then(response =>{
      Tea.value = response.data.data;
      console.log(response.data.data);
    }).catch(error =>{
      console.log(error.message);
    })
})



</script>

<style scoped>
#zhao{
  font-size: 20px;
  position: relative;
  top: 75px;
  color: black;
}
#img{
  text-align: center ;
  vertical-align: center;
  height: 180px;
  width: 130px;
  margin-top: -330px;
  margin-left: 700px;
  background-color: #D1D1D1;
}
#head2{
  margin-left: 42px;
}
#head1{
  margin-left: 20px;
}
.put{
  text-align: center;
  position: relative;
  left: 62px;
  margin-left: 150px;
  margin-top: -45px;
  vertical-align:middle;
  resize:none;
  width: 40%;
  height: 30%;
  border:none;
  outline: none;
  appearance: none;
}
.wenben{
  color: black;
  font-size: 26px;
}
</style>
