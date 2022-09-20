<template>
    <div class="header">
        <div class="hleft">
            <el-button link size="large" @click="goHome">
        <span>
          首页<el-icon><House/></el-icon>
        </span>
            </el-button>
            <el-button link size="large" @click="router.push({name:'videoList'})">
                <span>视频</span>
            </el-button>
        </div>
        <div class="h_shuru">
            <el-input class="inp" type="text" name="shuru" placeholder="想看就看,随便搜"/>
            <div class="fangda">
                <el-icon>
                    <Search/>
                </el-icon>
            </div>
        </div>
        <div class="hright">
            <div class="tou">
                <el-button link circle v-if="!isLogin" @click="goLogin">登录</el-button>
                <a v-if="isLogin" href="#">
                    <el-avatar :src="user.avatar" class="tou-img"/>
                </a>
                <div class="tou-1" v-if="isLogin">
                    <p class="tou-p">{{ user.nickName }}</p>
                    <div class="tdiv-3">
                        <div class="tdiv-3-1" @click="router.push({name:'FocusList'})">
                            <span>关注</span>
                            <p>{{ info.focus }}</p>
                        </div>
                        <div class="tdiv-3-1" @click="router.push({name:'fansList'})">
                            <span>粉丝</span>
                            <p>{{ info.fans }}</p>
                        </div>
                        <!--<div class="tdiv-3-1">-->
                        <!--  <span>动态</span>-->
                        <!--  <p></p>-->
                        <!--</div>-->
                    </div>
                    <ul id="tou-ul">
                        <li id="tli" @click="router.push({name:'personalCenter'})"><a href="#">个人中心</a></li>
                        <li id="tli" @click="router.push({name:'contManage'})"><a href="#">投稿管理</a></li>
                        <li id="tli" @click="logout">
                            退出登陆
                        </li>
                    </ul>
                </div>
            </div>
            <ul class="rul">
                <el-dropdown style="margin-top: 22px">
                    <li class="xiaoxi"><a href="#">消息</a>
                        <div class="xiaoxi-1" v-if="isLogin">
                            <ul>
                                <li @click="myMessage()"><a href="#">我的消息</a></li>
                            </ul>
                        </div>
                    </li>
                    <template #dropdown v-if="!isLogin" class="card1">
                        <el-card shadow="always">
                            <h2 @click="goLogin">请登录后再来查看</h2>
                        </el-card>
                    </template>
                </el-dropdown>
                <!--<el-dropdown style="margin-top: 22px">-->
                <!--  <li class="dongtai"><a href="#">动态</a>-->
                <!--    <div class="dongtai-1" v-if="isLogin">-->
                <!--      <div class="dongtai-1-top">-->
                <!--        <span>视频动态</span>-->
                <!--      </div>-->
                <!--      <div class="dongtai-1-bottom">-->
                <!--        <div class="dongtai-xiaoxi">-->
                <!--          暂时没有新动态了哦！-->
                <!--          <div class="dongtai-tip">历史动态</div>-->
                <!--        </div>-->
                <!--        <div class="dongtai-1-bottom-bottom">-->
                <!--          <div class="dtdiv">-->
                <!--            <div class="imgzuo">-->
                <!--              <img src="img/header/dl1.webp"/>-->
                <!--              <img src="img/header/d1.svg" class="imgbiao"/>-->
                <!--            </div>-->
                <!--            <p class="shangzuo">九九练字高效</p>-->
                <!--            <p class="shangyou">31分钟前</p>-->
                <!--            <p class="xiaxia">重启百万粉丝练字计划，"每日一字"重磅来袭！！！</p>-->
                <!--            <img src="img/header/dr1.webp" class="imgyou"/>-->
                <!--          </div>-->
                <!--          <a href="#">查看全部</a>-->
                <!--        </div>-->
                <!--      </div>-->
                <!--    </div>-->
                <!--  </li>-->
                <!--  <template #dropdown v-if="!isLogin" class="card1">-->
                <!--    <el-card shadow="always">-->
                <!--      <h2 @click="goLogin">请登录后再来查看</h2>-->
                <!--    </el-card>-->
                <!--  </template>-->
                <!--</el-dropdown>-->
                <el-dropdown style="margin-top: 22px">
                    <li class="shoucang"><a href="#">收藏</a>
                        <div class="shoucang-1">
                            <div class="shoucang-1-right" v-if="isLogin">
                                <div class="scr-shang">
                                    <div class="spdiv" v-for="item in collections" @click="playVideo(item)">
                                        <img :src="item.video.coverUrl"/>
                                        <p class="pshang">{{ item.video.title }}</p>
                                        <p class="pxia">{{ item.video.nickName }}</p>
                                    </div>
                                </div>
                                <!--<div class="scr-xia">-->
                                <!--  <div class="scr-xia-left">-->
                                <!--    查看全部-->
                                <!--  </div>-->
                                <!--  <div class="scr-xia-right">-->
                                <!--    播放全部-->
                                <!--  </div>-->
                                <!--</div>-->
                            </div>
                        </div>
                    </li>
                    <template #dropdown v-if="!isLogin" class="card1">
                        <el-card shadow="always">
                            <h2 @click="goLogin">请登录后再来查看</h2>
                        </el-card>
                    </template>
                </el-dropdown>
                <el-dropdown style="margin-top: 22px">
                    <li class="lishi"><a href="#">历史</a>
                        <div class="lishi-1" v-if="isLogin">
                            <div class="lishi-1-top">
                                <p><span class="zhuan1">视频历史</span></p>
                            </div>
                            <div class="lishi-1-bottom">
                                <!--<p>今天</p>-->
                                <div class="spdiv" v-for="item in historys" @click="playVideo(item)">
                                    <img :src="item.video.coverUrl"/>
                                    <p class="pshang">{{ item.video.title }}</p>
                                    <p class="pxia">
                                        {{ item.video.nickName }}&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{ item.createTime }}</p>
                                </div>
                                <a href="#">查看全部</a>
                            </div>
                        </div>
                    </li>
                    <template #dropdown v-if="!isLogin" class="card1">
                        <el-card shadow="always">
                            <h2 @click="goLogin">请登录后再来查看</h2>
                        </el-card>
                    </template>
                </el-dropdown>

                <el-dropdown style="margin-top: 22px">
                    <li @click="goToCreate"><a href="#">创作中心</a></li>
                    <template #dropdown v-if="!isLogin" class="card1">
                        <el-card shadow="always">
                            <h2 @click="goLogin">请登录后再来查看</h2>
                        </el-card>
                    </template>
                </el-dropdown>
            </ul>
        </div>
    </div>
</template>

<script>
    export default {
        name: "Banner",
    }
</script>
<script setup>
    import { House, Search } from '@element-plus/icons-vue'
    import useUser from '../../../http/stores/user.js'
    import request from "../../../http/Request.js";
    import { onMounted, ref } from "vue";
    import router from "../../../router.js";

    const user = useUser().user
    const log = useUser()
    const login = useUser()
    const info = ref({
        focus: 0,
        fans: 0,
    })
    const isLogin = ref(false)
    if (login.token !== undefined) {
        console.log(1)
        isLogin.value = true
    } else {
        isLogin.value = false
    }
    const logout = () => {
        console.log(111111111)
        log.token = void 0
        log.user = {}
        // router.push({name:"home"})
        window.location.reload()
    }

    function goToCreate () {
        router.push({ name: 'create' })
    }

    function myMessage () {
        router.push({ name: 'information' })
    }

    function goLogin () {
        router.push({ name: "login" })
    }

    const collections = ref()
    const historys = ref()

    function init () {
        request.post("/collection/list", { accountId: user.id }).then(rs => {
            console.log(rs)
            if (rs.data.code === 0) {
                collections.value = rs.data.data
            }
        })
        request.post("/record/list", { accountId: user.id }).then(rs => {
            if (rs.data.code === 0) {
                console.log(rs)
                historys.value = rs.data.data
                console.log(historys)
            }
        })
        setTimeout(request.get("/user/findDeatil", { params: { id: user.id } }).then(rs => {
            console.log(rs)
            if (rs.data.code === 0) {
                info.value = rs.data.data
            }
        }), 100)
    }

    function goHome () {
        router.push({ name: "home" })
    }

    function playVideo (item) {
        router.push({
            name: "videoPlay",
            params: { videoUrl: item.video.videoUrl, videoId: item.video.id },
        })
    }

    onMounted(() => {
        init()

    })
</script>
<style scoped>
    * {
        padding: 0;
        margin: 0;
    }

    el-card {
        font-family: Microsoft Yahei, serif;
        font-size: 16px;
    }

    body, html {
        width: 100%;
        height: 100%;
    }

    .card1 {
        display: flex;
        justify-items: center;
        justify-content: center;
    }

    .card1 p {
        width: 100%;
        font-size: 20px;
        text-align: center;
    }

    li {
        list-style: none;
    }

    a {
        text-decoration: none;
    }

    .header {
        width: 100%;
        height: 58px;
        background: #FFFFFF;
        border-bottom: 1px solid #EEEEEE;
        box-shadow: 1px 2px 2px #EEEEEE;
        position: relative;
        z-index: 3;
        line-height: 58px;
    }

    .hleft {
        width: 600px;
        height: 58px;
        position: absolute;
        z-index: 2;
        left: 0;
    }

    .hleft span {
        font-size: 20px;
        margin-left: 20px;
    }

    .hleft span:hover {
        color: #41ccd1;
    }

    .header .h_shuru {
        width: 460px;
        height: 34px;
        position: absolute;
        left: 509px;
        z-index: 4;
        margin: auto;
    }

    .header .h_shuru input[type="text"] {
        height: 100%;
        width: 100%;
        background-color: #F4F4F4;
        border: 1px solid #E7E7E7;
        text-indent: 10px;
    }

    .header .h_shuru input[type="text"]:focus {
        outline: none;
    }

    .header .h_shuru .fangda {
        width: 17px;
        height: 17px;
        position: absolute;
        top: 22px;
        right: 10px;
        /*background: url(../img/header/fangda1.png) no-repeat;*/
        cursor: pointer;
    }

    .header .h_shuru .fangda i {
        width: 17px;
        height: 17px;
        position: absolute;
        top: -1px;
        right: -5px;
        /*background: url(../img/header/fangda1.png) no-repeat;*/
        cursor: pointer;
    }

    .header .h_shuru .fangda:hover {
        /*background: url(../img/header/fangda2.png) no-repeat 1px 0px;*/
    }

    .hright {
        width: 500px;
        height: 58px;
        position: absolute;
        right: 0px;
    }

    .hright .tou {
        width: 36px;
        height: 36px;
        display: flex;
        justify-content: center;
        justify-items: center;
        float: left;
        margin: 10px 20px;
    }

    .hright .tou img {
        width: 36px;
        height: 36px;
        border-radius: 18px;
        transition: all 0.2s;
        -webkit-transition: all 0.2s;
        transform: scale(1);
        -webkit-transform: scale(1);
        position: absolute;
        top: 10px;
        z-index: 999;
    }

    .hright .tou .tou-1 {
        width: 280px;
        height: 280px;
        position: absolute;
        top: 58px;
        left: -102px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        -webkit-transition: all 0.2s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .tou:hover .tou-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .tou:hover .tou-img {
        transform: scale(1.5);
        -webkit-transform: scale(1.5);
        top: 100px;
        font-family: "微软雅黑";
    }

    .hright .tou .tou-1 .tou-p {
        text-align: center;
        font-size: 16px;
        font-weight: 900;
        margin-top: 40px;
        line-height: 20px;
        color: #212121;
    }

    .hright .tou .tou-1 .tdiv-1 {
        width: 240px;
        height: 54px;
        padding: 0px 20px;
        position: relative;
        margin-top: 5px;
    }

    .hright .tou .tou-1 .tdiv-1 .szuo {
        color: #212121;
        font-size: 14px;
        float: left;
        line-height: 35px;
    }

    .hright .tou .tou-1 .tdiv-1 .syou {
        color: #999999;
        font-size: 12px;
        float: right;
        line-height: 35px;
    }

    .hright .tou .tou-1 .tdiv-1 img {
        width: 241px;
        height: 4px;
        position: absolute;
        top: 40px;
        left: 20px;
    }

    .hright .tou .tou-1 .tdiv-1 .tdiv-1-1 {
        width: 210px;
        height: 159px;
        position: absolute;
        top: -35px;
        right: -230px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        -webkit-transition: all 0.2s;
        background-color: #FFFFFF;
        z-index: 99;
        padding: 15px;
    }

    .hright .tou .tou-1 .tdiv-1:hover .tdiv-1-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .tou .tou-1 .tdiv-1 .tdiv-1-1 .p-title {
        font-size: 14px;
        margin-bottom: 10px;
    }

    .hright .tou .tou-1 .tdiv-1 .tdiv-1-1 p {
        font-size: 14px;
        line-height: 20px;
    }

    .hright .tou .tou-1 .tdiv-1 .tdiv-1-1 a {
        color: #00A1D6;
        font-size: 14px;
        position: absolute;
        bottom: -5px;
        font-weight: 700;

    }

    .hright .tou .tou-1 .tdiv-2 {
        height: 40px;
        padding: 0px 20px;
    }

    .hright .tou .tou-1 .tdiv-2 span {
        padding-right: 15px;
        font-size: 14px;
        cursor: pointer;
    }

    .hright .tou .tou-1 .tdiv-2 span:hover {
        color: #00A1D6;
    }

    .hright .tou .tou-1 .tdiv-2 span img {
        width: 22px;
        height: 22px;
        position: relative;
        top: 5px;
        left: 0px;
        margin-right: 5px;
        border-radius: 11px;
        cursor: pointer;
    }

    .hright .tou .tou-1 .tdiv-2 img {
        position: relative;
        width: 55px;
        height: 22px;
        top: 5px;
        left: 55px;
        cursor: pointer;
    }

    .hright .tou .tou-1 .tdiv-3 {
        height: 57px;
        border-top: 1px solid #EEEEEE;
        border-bottom: 1px solid #EEEEEE;
        margin-top: 5px;
        cursor: pointer;
    }

    .hright .tou .tou-1 .tdiv-3 .tdiv-3-1 {
        width: 133px;
        height: 57px;
        float: left;
        text-align: center;
    }

    .hright .tou .tou-1 .tdiv-3 .tdiv-3-1 span {
        display: block;
        font-size: 12px;
        line-height: 20px;
        margin-top: 8px;
    }

    .hright .tou .tou-1 .tdiv-3 .tdiv-3-1 p {
        font-size: 16px;
        font-family: fantasy;
        line-height: 20px;
        margin-top: -2px;
    }

    .hright .tou .tou-1 .tdiv-3 .tdiv-3-1:hover span {
        color: #00A1D6;
    }

    .hright .tou .tou-1 .tdiv-3 .tdiv-3-1:hover p {
        color: #00A1D6;
    }

    #tou-ul {
        height: 254px;
        width: 280px;
        padding: 7px 0px;
        border-bottom: 1px solid #EEEEEE;
    }

    #tou-ul li {
        height: 42px;
        text-indent: 54px;
        line-height: 42px;
    }

    #tou-ul li:hover {
        background-color: #F4F4F4;
        cursor: pointer;
    }

    #tou-ul li a {
        display: block;
        color: #212121;
        font-size: 14px;
        font-family: "微软雅黑";
    }

    .hright .tou .tou-1 .tdiv-4 {
        height: 44px;
        width: 280px;
        text-indent: 54px;
        color: #212121;
        font-size: 14px;
        font-family: "微软雅黑";
        margin: 7px 0px;
        line-height: 44px;
        cursor: pointer;
    }

    .hright .tou .tou-1 .tdiv-4:hover {
        background-color: #F4F4F4;
    }

    .hright .tou .tou-1 .tdiv-4 img {
        position: relative;
        height: 13px;
        width: 9px;
        left: 90px;
        top: 0px;
    }

    .hright .tou .tou-1 .tdiv-4 .tdiv-4-1 {
        height: 76px;
        width: 240px;
        padding: 8px 0px;
        position: absolute;
        top: 490px;
        right: -230px;
        line-height: 39px;
        border: 1px solid #EEEEEE;
        text-indent: 15px;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        -webkit-transition: all 0.2s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .tou .tou-1 .tdiv-4:hover .tdiv-4-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .tou .tou-1 .tdiv-4 .tdiv-4-1 ul {
        width: 100%;
        height: 100%;
    }

    .hright .tou .tou-1 .tdiv-4 .tdiv-4-1 ul li {
        height: 39px;
    }

    .hright .tou .tou-1 .tdiv-4 .tdiv-4-1 ul li:hover {
        background-color: #F4F4F4;
    }

    .hright .tou .tou-1 .tdiv-4 .tdiv-4-1 ul .tdiv-4-shang {
        color: #00A1D6;
    }

    .hright .tou .tou-1 .tdiv-4 .tdiv-4-1 ul .tdiv-4-shang img {
        position: relative;
        width: 13px;
        height: 9px;
        left: 130px;
    }

    .hright .tou .tou-1 .tdiv-5 {
        border-top: 1px solid #EEEEEE;
        height: 41px;
        padding: 7px 0px;
        line-height: 41px;
    }

    .hright .tou .tou-1 .tdiv-5 .tdiv-5-1 {
        text-indent: 54px;
        color: #212121;
        font-size: 14px;
        font-family: "微软雅黑";
        cursor: pointer;
    }

    .hright .tou .tou-1 .tdiv-5 .tdiv-5-1:hover {
        background-color: #F4F4F4;
    }

    .hright .rul {
        width: 290px;
        height: 58px;
        float: left;
    }

    .hright .rul li {
        float: left;
        margin: 0px 6px;
    }

    .hright .rul li a {
        color: #000000;
        font-size: 14px;
        font-family: "微软雅黑";
    }

    .hright .rul .huiyuan .huiyuan-1 {
        width: 235px;
        height: 289px;
        position: absolute;
        top: 58px;
        left: -40px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        padding: 14px;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .huiyuan:hover .huiyuan-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .huiyuan .huiyuan-1 h3 {
        font-size: 14px;
        font-weight: 900;
        line-height: 20px;
        margin: 5px 0px 12px;
    }

    .hright .rul .huiyuan .huiyuan-1 .huiyuan-zhong {
        width: 116px;
        height: 192px;
        float: left;

    }

    .hright .rul .huiyuan .huiyuan-1 .huiyuan-zhong img {
        width: 107px;
        height: 143px;
        cursor: pointer;
    }

    .hright .rul .huiyuan .huiyuan-1 .huiyuan-zhong .huiyuan-2 {
        margin-left: 10px;
    }

    .hright .rul .huiyuan .huiyuan-1 .huiyuan-zhong p {
        line-height: 20px;
        font-size: 14px;
        width: 108px;
        margin-left: 10px;
        margin-top: -10px;
    }

    .hright .rul .huiyuan .huiyuan-1 .huiyuan-zhong p:hover {
        color: #00A1D6;
    }

    .hright .rul .huiyuan .huiyuan-1 a {
        display: block;
        width: 160px;
        height: 32px;
        background-color: #00A1D6;
        line-height: 32px;
        position: absolute;
        text-align: center;
        color: #FFFFFF;
        bottom: 15px;
        left: 50px;
        border-radius: 4px;
    }

    .hright .rul .huiyuan .huiyuan-1 a:hover {
        background-color: #00B5E5;
    }

    .hright .rul .xiaoxi .xiaoxi-1 {
        width: 175px;
        height: 200px;
        position: absolute;
        top: 58px;
        left: -65px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        padding-top: 10px;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .xiaoxi .xiaoxi-2 {
        width: 175px;
        height: 150px;
        position: absolute;
        top: 58px;
        left: -65px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        padding-top: 10px;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .xiaoxi {
        position: relative;
    }

    .hright .rul .xiaoxi img {
        position: absolute;
        border-radius: 50%;
        top: 9px;
        left: 20px;
    }

    .hright .rul .xiaoxi .xiaoxi-1 ul li img {
        position: absolute;
        border-radius: 50%;
        top: 132px;
        left: 140px;
    }

    .hright .rul .xiaoxi:hover .xiaoxi-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .xiaoxi:hover .xiaoxi-2 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .xiaoxi .xiaoxi-1 ul {
        width: 100%;
        height: 200px;
    }

    .hright .rul .xiaoxi .xiaoxi-1 ul li {
        height: 37px;
        width: 100%;
        clear: both;
        line-height: 40px;
        text-indent: 10px;
        margin-left: 0px;
    }

    .hright .rul .xiaoxi .xiaoxi-2 ul li {
        height: 37px;
        width: 100%;
        clear: both;
        line-height: 40px;
        text-indent: 10px;
        margin-left: 0px;
    }

    .hright .rul .xiaoxi .xiaoxi-1 ul li:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .xiaoxi .xiaoxi-2 ul li:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .xiaoxi .xiaoxi-1 ul li a {
        display: block;
        color: #000000;
    }

    .hright .rul .xiaoxi .xiaoxi-2 ul li a {
        display: block;
        color: #000000;
    }

    .hright .rul .dongtai .dongtai-1 {
        width: 372px;
        height: 520px;
        position: absolute;
        top: 58px;
        left: -20px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .dongtai:hover .dongtai-1 {
        visibility: visible;
        opacity: 1;
    }

    .dongtai-2 {
        width: 200px;
        height: 100px;
        position: absolute;
        top: 58px;
        left: 100px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .dongtai:hover .dongtai-2 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-top {
        height: 48px;
        width: 100%;
        border-bottom: 1px solid #E7E7E7;
        line-height: 48px;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-top span {
        width: 70px;
        height: 24px;
        background-color: #00A1D6;
        border-radius: 35px;
        line-height: 24px;
        margin: 12px 10px;
        color: #FFFFFF;
        cursor: pointer;
        font-size: 12px;
        text-align: center;
        float: left;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-top p {
        font-size: 12px;
        cursor: pointer;
        color: #999999;
        float: left;
        margin: 0px 10px;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-top p:hover {
        color: #00A1D6;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-bottom {
        height: 430px;
        width: 372px;
        padding: 20px 0px;
        overflow: auto;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-bottom .dongtai-xiaoxi {
        width: 332px;
        height: 115px;
        text-indent: 95px;
        line-height: 95px;
        color: #999999;
        font-size: 14px;
        position: relative;
        border-bottom: 1px solid #E7E7E7;
        margin: 0px 20px;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-bottom .dongtai-xiaoxi .dongtai-tip {
        position: absolute;
        color: #999999;
        font-size: 12px;
        bottom: -10px;
        left: 130px;
        padding: 0px 10px;
        text-indent: 0px;
        height: 20px;
        line-height: 20px;
        background-color: #FFFFFF;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-bottom .dongtai-1-bottom-bottom {
        width: 355px;
        margin-top: 5px;
        padding-top: 5px;
    }

    .hright .rul .dongtai .dongtai-2 li {
        width: 70px;
        height: 37px;
        background-color: #FFFFFF;
        border-radius: 35px;
        line-height: 24px;
        margin: 12px 10px;
        color: #000000;
        cursor: pointer;
        font-size: 12px;
        text-align: center;
        float: left;
    }

    .hright .rul .dongtai .dongtai-2 li:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .dongtai .dongtai-2 .dongtai-1-top p {
        font-size: 12px;
        cursor: pointer;
        color: #999999;
        float: left;
        margin: 0px 10px;
    }

    .hright .rul .dongtai .dongtai-2 .dongtai-1-top p:hover {
        color: #00A1D6;
    }


    .dongtai-1-bottom-bottom .dtdiv {
        height: 60px;
        cursor: pointer;
        padding: 10px 0px 15px 20px;
        position: relative;
    }

    .dongtai-1-bottom-bottom .dtdiv:hover {
        background-color: #F4F4F4;
    }

    .dongtai-1-bottom-bottom .dtdiv .imgzuo {
        height: 36px;
        width: 36px;
        border-radius: 18px;
        position: relative;
        float: left;
    }

    .dongtai-1-bottom-bottom .dtdiv .imgzuo img {
        height: 36px;
        width: 36px;
        border-radius: 18px;
    }

    .dongtai-1-bottom-bottom .dtdiv .imgzuo .imgbiao {
        height: 12px;
        width: 12px;
        position: absolute;
        bottom: 5px;
        right: 5px;
    }

    .dongtai-1-bottom-bottom .dtdiv .shangzuo {
        font-size: 12px;
        position: absolute;
        top: -10px;
        left: 70px;
        color: #505050;
    }

    .dongtai-1-bottom-bottom .dtdiv .shangyou {
        font-size: 12px;
        position: absolute;
        top: -10px;
        left: 160px;
        color: #999999;
        letter-spacing: 1px;
    }

    .dongtai-1-bottom-bottom .dtdiv .xiaxia {
        font-size: 14px;
        width: 196px;
        position: absolute;
        top: 30px;
        left: 70px;
        line-height: 20px;
        font-family: "微软雅黑";

    }

    .dongtai-1-bottom-bottom .dtdiv .imgyou {
        position: absolute;
        top: 10px;
        right: 25px;
        border-radius: 2px;
    }


    .dongtai-1-bottom-bottom .dtdiv {
        height: 60px;
        cursor: pointer;
        padding: 10px 0px 15px 20px;
        position: relative;
    }

    .dongtai-1-bottom-bottom .dtdiv:hover {
        background-color: #F4F4F4;
    }

    .dongtai-1-bottom-bottom .dtdiv .imgzuo {
        height: 36px;
        width: 36px;
        border-radius: 18px;
        position: relative;
        float: left;
    }

    .dongtai-1-bottom-bottom .dtdiv .imgzuo img {
        height: 36px;
        width: 36px;
        border-radius: 18px;
    }

    .dongtai-1-bottom-bottom .dtdiv .imgzuo .imgbiao {
        height: 12px;
        width: 12px;
        position: absolute;
        bottom: 5px;
        right: 5px;
    }

    .dongtai-1-bottom-bottom .dtdiv .shangzuo {
        font-size: 12px;
        position: absolute;
        top: -10px;
        left: 70px;
        color: #505050;
    }

    .dongtai-1-bottom-bottom .dtdiv .shangyou {
        font-size: 12px;
        position: absolute;
        top: -10px;
        left: 160px;
        color: #999999;
        letter-spacing: 1px;
    }

    .dongtai-1-bottom-bottom .dtdiv .xiaxia {
        font-size: 14px;
        width: 196px;
        position: absolute;
        top: 30px;
        left: 70px;
        line-height: 20px;
        font-family: "微软雅黑";

    }

    .dongtai-1-bottom-bottom .dtdiv .imgyou {
        position: absolute;
        top: 10px;
        right: 25px;
        border-radius: 2px;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-bottom .dongtai-1-bottom-bottom a {
        display: block;
        width: 331px;
        height: 32px;
        background-color: #F4F4F4;
        line-height: 32px;
        margin: 15px auto 0px;
        text-align: center;
        border-radius: 4px;
        transition: all 0.3s;
        -webkit-transition: all 0.3s;
        color: #000000;
    }

    .hright .rul .dongtai .dongtai-1 .dongtai-1-bottom .dongtai-1-bottom-bottom a:hover {
        background-color: #E7E7E7;
    }

    .hright .rul .shoucang .shoucang-1 {
        width: 380px;
        position: absolute;
        top: 58px;
        left: -60px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        border-radius: 4px;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .shoucang:hover .shoucang-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-left {
        width: 150px;
        height: 100%;
        border-right: 1px solid #E7E7E7;
        float: left;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-left .scl-shang {
        margin-top: 15px;
        height: 46px;
        background-color: #00A1D6;
        color: #FFFFFF;
        font-family: "微软雅黑";
        font-size: 14px;
        line-height: 46px;
        text-align: center;
        cursor: pointer;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-left .scl-xia {
        height: 46px;
        color: #000000;
        font-family: "微软雅黑";
        font-size: 14px;
        line-height: 46px;
        text-align: center;
        cursor: pointer;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right {
        width: 371px;
        /*height: 100%;*/
        float: left;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang {
        height: 459px;
        border-bottom: 1px solid #E7E7E7;
        overflow: auto;
        padding-top: 15px;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang .spdiv {
        height: 76px;
        padding-left: 20px;
        cursor: pointer;
        transition: all 0.3s;
        -webkit-transition: all 0.3s;
        position: relative;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang .spdiv:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang .spdiv img {
        width: 108px;
        height: 62px;
        margin-top: 5px;
        float: left;
        margin-right: 12px;
        border-radius: 4px;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang .spdiv span {
        position: absolute;
        display: block;
        height: 20px;
        top: 45px;
        left: 88px;
        background-color: rgba(0, 0, 0, 0.5);
        line-height: 20px;
        color: #FFFFFF;
        padding: 0px 4px;
        font-size: 12px;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang .spdiv .pshang {
        color: #000000;
        font-size: 13px;
        line-height: 25px;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-shang .spdiv .pxia {
        line-height: 20px;
        font-size: 12px;
        color: #999999;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-xia {
        height: 46px;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-xia .scr-xia-left {
        width: 190px;
        height: 46px;
        float: left;
        text-align: center;
        line-height: 46px;
        font-size: 14px;
        font-family: "微软雅黑";
        cursor: pointer;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-xia .scr-xia-left:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-xia .scr-xia-right {
        float: left;
        width: 180px;
        height: 46px;
        text-align: center;
        line-height: 46px;
        font-size: 14px;
        font-family: "微软雅黑";
        color: #00A1D6;
        cursor: pointer;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-xia .scr-xia-right:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .shoucang .shoucang-1 .shoucang-1-right .scr-xia .scr-xia-right img {
        margin-right: 5px;
    }

    .hright .rul .lishi .lishi-1 {
        width: 373px;
        height: 520px;
        position: absolute;
        top: 58px;
        left: -80px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .lishi .lishi-2 {
        width: 203px;
        height: 150px;
        position: absolute;
        top: 58px;
        left: 90px;
        border: 1px solid #EEEEEE;
        box-shadow: 2px 2px 4px #EEEEEE;
        visibility: hidden;
        opacity: 0;
        transition: all 0.2s;
        transition-delay: 0.3s;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
        background-color: #FFFFFF;
        z-index: 99;
    }

    .hright .rul .lishi:hover .lishi-1 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .lishi:hover .lishi-2 {
        visibility: visible;
        opacity: 1;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-top {
        height: 50px;
        width: 100%;
        border-bottom: 1px solid #F4F4F4;
        line-height: 50px;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-top p {
        font-size: 12px;
        text-indent: 10px;
        width: 100%;
        height: 24px;
        cursor: pointer;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-top p .zhuan1 {
        display: inline-block;
        width: 70px;
        height: 24px;
        background-color: #00A1D6;
        border-radius: 35px;
        line-height: 24px;
        margin: 0px 10px;
        color: #FFFFFF;
        cursor: pointer;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-top p .zhuan2:hover {
        color: #00A1D6;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom {
        height: 469px;
        overflow: auto;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom p {
        padding-left: 20px;
        color: #999999;
        font-size: 12px;
        line-height: 37px;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom .spdiv {
        height: 76px;
        padding-left: 20px;
        cursor: pointer;
        transition: all 0.3s;
        -webkit-transition: all 0.3s;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom .spdiv:hover {
        background-color: #F4F4F4;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom .spdiv img {
        width: 108px;
        height: 62px;
        margin-top: 5px;
        float: left;
        margin-right: 12px;
        border-radius: 4px;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom .spdiv .pshang {
        color: #000000;
        font-size: 13px;
        line-height: 25px;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom .spdiv .pxia {
        line-height: 20px;
        font-size: 12px;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom a {
        display: block;
        width: 331px;
        height: 32px;
        background-color: #F4F4F4;
        line-height: 32px;
        margin: 15px auto;
        text-align: center;
        border-radius: 4px;
        transition: all 0.3s;
        -webkit-transition: all 0.3s;
        color: #000000;
    }

    .hright .rul .lishi .lishi-1 .lishi-1-bottom a:hover {
        background-color: #E7E7E7;
    }
</style>