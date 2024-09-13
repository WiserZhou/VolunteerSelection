<template>
    <div class="wholeWrapper">
        <div class="feedbackWrapper">
            <p class="title">初步随机结果反馈统计</p>
            <div class="rowStyle">
                <div>
                    <p>满意人数：{{ feedbackData.satisfies }}</p>
                </div>
                <div>
                    <p>不满意人数：{{ feedbackData.noSatisfy }}</p>
                </div>
            </div>
            <div class="rowStyle">
                <div>
                    <p>未表态人数：{{ feedbackData.remained}}</p>
                </div>
                <div>
                    <p>满意率：{{ rate }}</p>
                </div>
            </div>
        </div>
        <div class="btnWrapper">
            <el-button type="primary" plain class="btnStyle">重新随机分配</el-button>
        </div>
    </div>
</template>

<script setup>
import {ref,computed} from 'vue';
const feedbackData = ref({
    satisfies: 100,
    noSatisfy: 50,
    remained: 50,
})
const rate = computed(() => {
    return feedbackData.value.satisfies / (feedbackData.value.satisfies + feedbackData.value.noSatisfy + feedbackData.value.remained);
})
</script>

<style scoped>
.wholeWrapper {
    display: flex;
    width: 100%;
    height: 100%;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.feedbackWrapper {
    display: flex;
    width: 50%;
    height: 50%;
    background-color: #F2F0F0;
    border-radius: 30px;
    flex-direction: column;
    justify-content: center;
    align-items: center;
}
.title {
    text-align: center;
    font: 30px 'Microsoft YaHei';
}
.rowStyle {
    display: flex;
    justify-content: center;
    align-items: center;
}
.rowStyle div {
    height: 20px;
    margin: 20px;
}
.rowStyle p {
    font: 20px 'Microsoft YaHei';
}
/* 按钮 */
.btnWrapper {
    display: flex;
    width: 50%;
    align-self: center;
    justify-content: flex-end;
    align-items: center;
}
.btnStyle {
    width: 200px;
    height: 50px;
    margin-top: 10px;
    font: 18px 'Microsoft YaHei';
}
</style>