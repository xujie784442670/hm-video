<template>
    <banner/>
    <MyHeader/>
    <div v-for="item in videos">
        <Videos :id="item.typeId"/>
    </div>

    <Footer/>
    <div class="toolbar">
        <el-button>
            <el-icon size="20">
                <Headset/>
            </el-icon>
            客服
        </el-button>
        <el-button>顶部</el-button>
    </div>
</template>

<script>
    import Top from "./components/Top.vue";

    export default {
        name: "Home",
        components: { Top },
    }
</script>
<script setup>

    import MyHeader from "./components/MyHeader.vue";
    import { Headset } from "@element-plus/icons-vue";
    import Videos from "./components/Videos.vue";
    import Footer from "./components/footer.vue";
    import { ref } from "vue";
    import Banner from "../video/components/Banner.vue";
    import request from "../../http/Request.js";

    const videos = ref([
        { typeId: 1 },
        { typeId: 2 },
        { typeId: 3 },
        { typeId: 4 },
        { typeId: 5 },
        { typeId: 6 },
    ])

    request.post("videoTypes/findVideoTypes").then(rs => {
        videos.value = rs.data.data
    })
</script>
<style scoped>
    body, html {
        height: 100%;
        width: 100%;
    }

    .toolbar {
        width: 50px;
        height: 100px;
        position: fixed;
        left: 1400px;
        top: 650px;
        z-index: 20;
    }

    .el-button {
        margin: 0;
        font-size: 15px;
        width: 50px;
    }
</style>