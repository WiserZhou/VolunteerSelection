<template>
    <div id="container">
        <!-- 头部标题 -->
        <div id="header">
            <the-header></the-header>
        </div>
        <div id="body">
            <!-- 侧边栏 -->
            <div class="aside">
                <side-bar></side-bar>
            </div>
            <div class="rightWrapper">
                <current-position></current-position>
                <current-stage></current-stage>
                <div class="main">
                    <router-view></router-view>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
    import TheHeader from '../../header/TheHeader.vue';
    import SideBar from './content/SideBarTeacher.vue';
    import CurrentPosition from '../../header/CurrentPosition.vue';
    import CurrentStage from '../../header/CurrentStage.vue';
    import {RouterView} from 'vue-router';

</script>

<style scoped>
#container {
    position: fixed;
    width: 100%;
    height: 100%;
    left: 0;
    top: 0;
    overflow: auto;
}

#header {
    height: 120px;
}
/* 主体部分 */
#body {
    display: flex;
    height: calc(100% - 120px);
}
    /* 侧边栏 */
.aside {
    width: 300px;
    height: 100%;
}
    /* 右边部分 */
.rightWrapper {
    /* display: flex; */
    flex: 1;
    height: 100%;
    flex-direction: column;
}
.main {
    display: flex;
    height: calc(100% - 120px);
    justify-content: center;
    align-items: center;
}
</style>