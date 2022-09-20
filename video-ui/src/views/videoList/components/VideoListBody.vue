<template>
    <!--列表栏开始-->
    <div class="liebiao">
        <div class="liebiao-1">
            <img src="/logo.png" style="width: 50px"/>
            <form action="#" method="post">
                <input type="text" placeholder="元初" v-model="s"/>
                <input type="button" id="btn" value="搜索" @click="search(s)"/>
            </form>
        </div>
        <!--
        <ul class="lb-mulu" id="lb">
          <li class="zhonghe"><a href="#">综合</a></li>
          <li><a href="#">视频<span>99+</span></a></li>
          <li><a href="#">番剧<span>1</span></a></li>
          <li><a href="#">影视<span>19</span></a></li>
          <li><a href="#">直播<span>32</span></a></li>
          <li><a href="#">专栏<span>99+</span></a></li>
          <li><a href="#">话题<span>99+</span></a></li>
          <li><a href="#">用户<span>99+</span></a></li>
          <li><a href="#">相簿<span>99+</span></a></li>
          <li class="xian"></li>
        </ul>
        -->
        <div class="liebiao-2">
            <ul>
                <li :style="topIndex===1? bgcolor :'' "><a href="#" @click="zongHe">综合排序</a></li>
                <li :style="topIndex===2? bgcolor :'' "><a href="#" @click="viewsCount">播放最多</a></li>
                <li :style="topIndex===3? bgcolor :'' "><a href="#" @click="latest">最新发布</a></li>
                <li :style="topIndex===4? bgcolor :'' "><a href="#" @click="mostBarrage">最多弹幕</a></li>
                <li :style="topIndex===5? bgcolor :'' "><a href="#" @click="mostCollection">最多收藏</a></li>
            </ul>
            <!--
             <ul>
              <li><a href="#">全部时长</a></li>
              <li><a href="#">10分钟以下</a></li>
              <li><a href="#">10-30分钟</a></li>
              <li><a href="#">30-60分钟</a></li>
              <li><a href="#">60分钟以上</a></li>
            </ul>
            -->
            <ul class="liebiao-2-xia">
                <li :style="bottomIndex ===1? bgcolor :'' "><a href="#" @click="allType">全部分区</a></li>
                <li :style="bottomIndex ===2? bgcolor :'' "><a href="#" @click="liveType">生活</a></li>
                <li :style="bottomIndex ===3? bgcolor :'' "><a href="#" @click="animateType">动画</a></li>
                <li :style="bottomIndex ===4? bgcolor :'' "><a href="#" @click="musicType">音乐</a></li>
                <li :style="bottomIndex ===5? bgcolor :'' "><a href="#" @click="gameType">游戏</a></li>
                <li :style="bottomIndex ===6? bgcolor :'' "><a href="#">舞蹈</a></li>
                <li :style="bottomIndex ===7? bgcolor :'' "><a href="#">科技</a></li>
                <li :style="bottomIndex ===8? bgcolor :'' "><a href="#">数码</a></li>
                <li :style="bottomIndex ===9? bgcolor :'' "><a href="#">鬼畜</a></li>
                <li :style="bottomIndex ===10? bgcolor :'' "><a href="#">时尚</a></li>
                <li :style="bottomIndex ===11? bgcolor :'' "><a href="#">广告</a></li>
                <li :style="bottomIndex ===12? bgcolor :'' "><a href="#">娱乐</a></li>
                <li :style="bottomIndex ===13? bgcolor :'' "><a href="#">纪录片</a></li>
                <li :style="bottomIndex ===14? bgcolor :'' "><a href="#">电影</a></li>
                <li :style="bottomIndex ===15? bgcolor :'' "><a href="#">电视剧</a></li>
            </ul>
            <div class="liebiao-2-tu-1"></div>
            <div class="liebiao-2-tu-2"></div>
            <!--<a href="#" class="shouqi">收起<img src="img/liebiao/jiantou.png" alt="" /></a>-->
        </div>
        <ul class="lb-zhengwen">
            <li v-for="item in videos2">
                <div class="lb-1">
                    <img :src="item.coverUrl" title="item.title" class="lb-1-img-1"
                         @click="playVideo(item.videoUrl,item.id)"/>
                    <!--<img src="img/liebiao/shijian.png" class="lb-1-img-2"/>-->
                </div>
                <div class="lb-2">
                    <a href="#" class="lb-title">{{ item.title }}</a>
                </div>
                <span class="lb-left">{{ item.views }}</span>
                <span class="lb-right">{{ item.createTime }}</span><br/>
                <span class="barrage">
          <a href="#" class="lb-up">{{ item.nickName }}</a>
          <span>
            <el-icon><ChatLineSquare/></el-icon>
            {{ item.barrages }}
          </span>
        </span>
            </li>
        </ul>
    </div>

</template>

<script>
    export default {
        name: "VideoListBody",
    }
</script>

<script setup>

    import { onMounted, ref } from "vue";
    import { ChatLineSquare } from "@element-plus/icons-vue";
    import request from "../../../http/Request.js";
    import { useRouter } from 'vue-router'

    const router = useRouter()
    const topIndex = ref(1)
    const bottomIndex = ref(1)
    const videos = ref()
    const videos2 = ref()
    const s = ref('')
    // const videosCopy=ref()
    const bgcolor = {
        "display": "block",
        "background-color": "#00A1D6",
        "border-radius": "4px",
    }

    const playVideo = (url, id) => {
        // console.log(url)
        // console.log(id)
        console.log("跳转")
        router.push({
            name: "videoPlay",
            params: { videoUrl: url, videoId: id },
        })
        request.get("/video/findOne", { params: { videoId: props.videoId } }).then(rs => {
            up.value = rs.data.data.video.account
            videoInfo.value = rs.data.data.video
            const focusData = { accountId: user.user.id, focusId: up.value.id }
            console.log(focusData)
            setTimeout(request.post("/importantPerson/findOne", focusData).then(rs => {
                console.log("关注用户")
                console.log(rs)
                if (rs.data.code === 0) {
                    isFocus.value = true
                }
            }), 200)
        })
        //   window.location.reload()
    }

    function zongHe () {
        videos2.value.sort((a, b) => {
            return b.typeId - a.typeId
        })
        topIndex.value = 1
    }

    function viewsCount () {
        videos2.value.sort((a, b) => {
            return b.views - a.views
        })
        topIndex.value = 2
    }

    function latest () {
        videos2.value.sort((a, b) => {
            return b.createTime < a.createTime ? -1 : 1
        })
        topIndex.value = 3
    }

    function mostBarrage () {
        videos2.value.sort((a, b) => {
            return b.barrages - a.barrages
        })
        topIndex.value = 4
    }

    function mostCollection () {
        videos2.value.sort((a, b) => {
            return b.collections - a.collections
        })
        topIndex.value = 5
    }

    const allType = () => {
        bottomIndex.value = 1
        videos2.value = videos.value
    }
    const liveType = () => {
        bottomIndex.value = 2
        videos2.value = videos.value.filter((item) => {
            return item.type === '生活'
        })
    }
    const animateType = () => {
        bottomIndex.value = 3
        videos2.value = videos.value.filter((item) => {
            return item.type === '动画'
        })
    }
    const musicType = () => {
        bottomIndex.value = 4
        videos2.value = videos.value.filter((item) => {
            return item.type === '音乐'
        })
    }
    const gameType = () => {
        bottomIndex.value = 5
        videos2.value = videos.value.filter((item) => {
            return item.type === '游戏'
        })
    }
    const search = (s) => {
        videos2.value = videos.value.filter((item) => {
            if (item.title.includes(s)) {
                return item
            }
        })
    }
    onMounted(() => {
        request.post("/video/list", {}).then(rs => {
            console.log(rs)
            videos.value = rs.data.data.list
            videos.value.sort((a, b) => {
                return b.typeId - a.typeId
            })
            videos2.value = videos.value
        })
    })
</script>

<style scoped>
    li {
        list-style: none;
    }

    a {
        text-decoration: none;
    }

    .liebiao {
        height: 1335px;
        width: 980px;
        margin: 0px auto;
        font-family: "微软雅黑";
    }

    .liebiao .liebiao-1 {
        width: 600px;
        height: 45px;
        padding: 40px 0px;
        margin: 0px auto;
        line-height: 42px;
    }

    .liebiao .liebiao-1 img {
        float: left;
        margin-right: 29px;
    }

    input[type="text"] {
        width: 328px;
        height: 42px;
        border: 2px solid #CCD0D7;
        border-radius: 4px;
        margin-right: 10px;
        text-indent: 16px;
    }

    input[type="text"]:focus {
        outline: 1px solid #A6C7FF;
    }

    input[type="button"] {
        width: 90px;
        height: 42px;
        border-radius: 4px;
        border: none;
        color: #FFFFFF;
        cursor: pointer;
        font-size: 16px;
        text-indent: 25px;
        font-weight: 700;
        /*background: #00A1D6 url(src/assets/img/liebiao/fangda.png) no-repeat 15px center;*/
    }

    input[type="button"]:focus {
        outline: none;
    }

    input[type="button"]:hover {
        background-color: #00B5E5;
    }

    .liebiao .lb-mulu {
        height: 56px;
        width: 100%;
    }

    .liebiao .lb-mulu li {
        width: 108px;
        height: 100%;
        float: left;
        line-height: 56px;
        text-align: center;
        position: relative;
    }

    .liebiao .lb-mulu .zhonghe {
        padding-right: 2px;
    }

    .liebiao .lb-mulu .zhonghe a {
        width: 114px;
        color: #00A1D6;
        text-align: left;
        text-indent: 5px;
    }

    .liebiao .lb-mulu li a {
        display: block;
        color: #212121;
        font-size: 16px;
        height: 100%;
    }

    .liebiao .lb-mulu li:hover a {
        color: #00A1D6;
    }

    .liebiao .lb-mulu li span {
        color: #6D757A;
        font-size: 12px;
        position: absolute;
        top: -1px;
        left: 74px;
    }

    .liebiao .lb-mulu .xian {
        height: 3px;
        width: 42px;
        position: relative;
        top: -1px;
        left: 0px;
        /*background-color: #00A1D6;*/
        transition-duration: 200ms;

        z-index: 98;
    }

    .liebiao .liebiao-2 {
        border-bottom: 1px solid #CCD0D7;
        border-top: 1px solid #CCD0D7;
        height: 81px;
        padding: 20px 0px;
        position: relative;
    }

    .liebiao .liebiao-2 ul {
        width: 100%;
        height: 20px;
        padding-bottom: 10px;
    }

    .liebiao .liebiao-2 ul li {
        float: left;
        padding: 0px 8px;
        margin-right: 15px;
        line-height: 20px;
    }

    .liebiao .liebiao-2 .liebiao-2-xia li {
        margin-right: 8px;
    }

    .liebiao .liebiao-2 ul li a {
        font-size: 12px;
        color: #222222;
    }

    .liebiao .liebiao-2 ul li a:hover {
        color: #00A1D6;
    }

    .liebiao .liebiao-2 ul li:first-child {
        display: block;

        /*background-color: #00A1D6;*/
        border-radius: 4px;
    }

    .liebiao .liebiao-2 ul li:first-child a {
        color: #222222;
    }

    .liebiao .liebiao-2 .liebiao-2-tu-1 {
        width: 18px;
        height: 18px;
        position: absolute;
        /*background-image: url(src/assets/img/liebiao/tu1.png);*/
        top: 20px;
        right: 25px;
        cursor: pointer;
    }

    .liebiao .liebiao-2 .liebiao-2-tu-2 {
        width: 18px;
        height: 18px;
        position: absolute;
        /*background-image: url(src/assets/img/liebiao/tu2.png);*/
        top: 20px;
        right: 0px;
        cursor: pointer;
    }

    .liebiao .liebiao-2 .liebiao-2-tu-2:hover {
        /*background-image: url(src/assets/img/liebiao/tu3.png);*/
    }

    .liebiao .liebiao-2 .shouqi {
        display: block;
        width: 80px;
        height: 25px;
        position: absolute;
        right: 0px;
        bottom: 17px;
        color: #6D757A;
        font-size: 12px;
        text-align: center;
        line-height: 25px;
        border-radius: 5px;
    }

    .liebiao .liebiao-2 .shouqi:hover {
        background-color: rgb(229, 233, 239);
        color: #00A1D6;
    }

    .liebiao .lb-zhengwen {
        width: 980px;
        height: 920px;
    }

    .liebiao .lb-zhengwen li {
        width: 170px;
        height: 210px;
        margin: 20px 30px 0 0;
        border: 1px solid #E5E9EF;
        float: left;
        border-radius: 4px;
    }

    .liebiao .lb-zhengwen li:nth-child(5n) {
        margin-right: 0px;
    }

    .liebiao .lb-zhengwen li .lb-1 {
        width: 168px;
        height: 100px;
        position: relative;
    }

    .liebiao .lb-zhengwen li .lb-1 .lb-1-img-1 {
        width: 170px;
        height: 100px;
        border-radius: 4px;
    }

    .liebiao .lb-zhengwen li .lb-1 span {
        position: absolute;
        bottom: 0px;
        right: 0px;
        color: #FFFFFF;
        font-size: 12px;
        padding: 0px 5px;
        border-radius: 4px;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .liebiao .lb-zhengwen li .lb-1 .lb-1-img-2 {
        position: absolute;
        bottom: 3px;
        right: 6px;
        visibility: hidden;
        opacity: 0;
        border-radius: 4px;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
    }

    .liebiao .lb-zhengwen li .lb-1:hover span {
        visibility: hidden;
        opacity: 0;
    }

    .liebiao .lb-zhengwen li .lb-1:hover .lb-1-img-2 {
        visibility: visible;
        opacity: 1;
    }

    .liebiao .lb-zhengwen li .lb-2 {
        width: 148px;
        height: 40px;
        margin-top: 4px;
        margin-bottom: 8px;
        padding: 8px 10px 0px;
    }

    .liebiao .lb-zhengwen li .lb-2 a {
        font-size: 12px;
        color: #222222;
    }

    .liebiao .lb-zhengwen li .lb-2 a:hover {
        color: #00A1D6;
    }

    .liebiao .lb-zhengwen li .lb-2 span {
        color: #F25D8E;
    }

    .liebiao .lb-zhengwen li .lb-left {
        width: 57px;
        height: 12px;
        color: #99A2AA;
        font-size: 12px;
        margin: 0px 8px 20px 0px;
        padding-left: 16px;
        /*background: url(src/assets/img/liebiao/ti1.png) no-repeat 0px 2px;*/
    }

    .liebiao .lb-zhengwen li .lb-right {
        width: 100%;
        height: 12px;
        color: #99A2AA;
        font-size: 12px;
        /*margin: 0px 8px 12px 0px;*/
        padding-left: 16px;
        /*background: url(src/assets/img/liebiao/ti2.png) no-repeat 0px 2px;*/
    }

    .liebiao .lb-zhengwen li .lb-up {
        display: inline-block;
        width: 57px;
        height: 15px;
        color: #99A2AA;
        font-size: 12px;
        padding-left: 16px;
        /*background: url(src/assets/img/liebiao/ti3.png) no-repeat 0px 2px;*/
    }

    .liebiao .lb-zhengwen li .lb-up:hover {
        color: #00A1D6;
    }

    .liebiao .lb-zhengwen li .barrage {
        display: flex;
        margin-top: 4px;
        vertical-align: top;
        font-size: 12px;
        color: #99A2AA;
    }

    .liebiao .pager {
        width: 742px;
        height: 68px;
        margin-top: 11px;
        padding-left: 238px;
        padding-top: 32px;
    }

    .liebiao .pager .lb-biao {
        width: 520px;
        height: 43px;
    }

    .liebiao .pager .lb-biao li {
        width: 40px;
        height: 40px;
        float: left;
        border: 1px solid #DDDDDD;
        margin-right: 8px;
        border-radius: 4px;
        line-height: 40px;
        text-align: center;
        transition: all 0.3s;
        -webkit-transition: all 0.3s;
        cursor: pointer;
    }

    .liebiao .pager .lb-biao li:hover {
        background-color: #00A1D6;
    }

    .liebiao .pager .lb-biao li:hover a {
        color: #FFFFFF;
    }

    .liebiao .pager .lb-biao li a {
        color: #222222;
        font-size: 14px;
    }

    .liebiao .pager .lb-biao li:first-child {
        background-color: #00A1D6;
        border: none;
    }

    .liebiao .pager .lb-biao li:first-child a {
        color: #FFFFFF;
    }

    .liebiao .pager .lb-biao li:nth-child(8) {
        border: none;
    }

    .liebiao .pager .lb-biao li:nth-child(8):hover {
        background-color: #FFFFFF;
    }

    .liebiao .pager .lb-biao li:nth-child(8):hover a {
        color: #222222;
    }

    .liebiao .pager .lb-biao li:last-child {
        width: 70px;
        margin-right: 0px;
    }

</style>