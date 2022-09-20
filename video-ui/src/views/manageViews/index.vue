<template>
    <el-row>
        <div class="topTitle">
            <el-col :span="8" style="display: flex">
                <div class="titleImage"/>
                <div class="titleText"/>
            </el-col>
            <el-col :span="3" :offset="13">
                <div style="display: flex;align-items: center">
                    <el-avatar :size="45" :src="user.user.avatar" fit="cover" title="点击退出登录" @click="logout"/>
                    <div style="line-height: 50px;margin-left: 10px;margin-right: 10px;font-size: 24px">
                        {{ user.user.nickName }}
                    </div>
                </div>
            </el-col>
        </div>
    </el-row>
    <div class="manage">
        <div class="menu">
            <el-menu
                :default-active="itemMenu"
                class="el-menu-vertical-demo"
                background-color="#ccc"
                router
            >
                <el-menu-item index="dataStatistic">
                    <el-icon>
                        <PieChart/>
                    </el-icon>
                    <template #title>数据统计</template>
                </el-menu-item>
                <el-menu-item index="videoManage">
                    <el-icon>
                        <MessageBox/>
                    </el-icon>
                    <template #title>视频管理</template>
                </el-menu-item>
                <el-menu-item index="advertiseManage">
                    <el-icon>
                        <Discount/>
                    </el-icon>
                    <template #title>广告管理</template>
                </el-menu-item>
                <el-menu-item index="reportManage">
                    <el-icon>
                        <Warning/>
                    </el-icon>
                    <template #title>举报管理</template>
                </el-menu-item>
            </el-menu>
        </div>
        <div class="view">
            <div>
                <div class="emptyDiv"></div>
                <router-view></router-view>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "index",
    }
</script>
<script setup>
    import { PieChart, Warning, MessageBox, Discount } from '@element-plus/icons-vue'
    import { useRouter, useRoute } from 'vue-router'
    import { onMounted, ref } from 'vue'
    import useUser from '../../http/stores/user.js'
    import { ElMessage } from 'element-plus'

    const itemMenu = ref('')
    const router = useRouter()
    const route = useRoute()
    const user = useUser()
    const logout = () => {
        user.user = {}
        user.token = void 0
        ElMessage.success("成功退出元初视频后台管理")
        router.replace({ name: 'login' })
    }
    onMounted(() => {
        if (route.name === 'manage') {
            router.replace("/dataStatistic")
        }
        if (user.user.username !== 'root') {
            router.replace({ name: 'login' })
        }
        itemMenu.value = route.name
    })
</script>

<style scoped>
    .manage {
        width: 100vw;
        height: calc(100vh - 50px);
        display: flex;
    }

    .el-menu {
        width: 150px;
        height: calc(100vh - 50px);
    }

    .emptyDiv {
        height: 10px
    }

    .view {
        margin: 8px;
        width: 100vw;
    }

    .topTitle {
        width: 100vw;
        height: 50px;
        display: flex;
        background-image: url("../../assets/banner/banner1.webp");
    }

    .titleImage {
        width: 46px;
        height: 50px;
        background-image: url("/logo.png");
        background-size: 46px 50px;
        background-repeat: no-repeat;
    }

    .titleText {
        font-size: 36px;
    }

    .titleText::before {
        content: '元初视频后台管理';
    }

    .el-image {
        height: 50px;
    }

    .el-avatar {
        transition: all .5s ease;
        -moz-transition: all .5s linear;
        -webkit-transition: all .5s linear;
        -o-transition: all .5s linear;
    }

    .el-avatar:hover {
        cursor: pointer;
        transform: scale(1.2);
        -ms-transform: scale(1.2);
        -moz-transform: scale(1.2);
        -webkit-transform: scale(1.2);
        -o-transform: scale(1.2);
        transition: all .5s linear;
        -moz-transition: all .5s linear;
        -webkit-transition: all .5s linear;
        -o-transition: all .5s linear
    }

    @keyframes big {
        0% {
            scale: 1;
        }
        100% {
            scale: 1.2;
        }
    }
</style>