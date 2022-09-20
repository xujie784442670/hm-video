<template>
    <div class="top">
        <div class="back" style="position: absolute;left: 20px;font-size: 20px" @click="back">
            <el-icon style="transform: translateY(3px)">
                <ArrowLeft/>
            </el-icon>
            返回
        </div>
        <div class="left">
            <table>
                <tr>
                    <td>
                        <el-image :src="chatUser.avatar" class="avatar"/>
                    </td>
                    <td>
                        <el-main>{{ chatUser.nickName }}</el-main>
                    </td>
                </tr>
            </table>
        </div>
        <div class="right">
            <table>
                <tr>
                    <td colspan="">
                        <el-button v-if="status==1" @click="focuscl">关注</el-button>
                        <el-button v-if="status==2" @click="fanscl">已关注</el-button>
                    </td>
                    <td colspan="">
                        <el-button @click="show()">发消息</el-button>
                    </td>
                </tr>
                <tr>
                    <td>获赞数：{{ numberoflikes }}</td>
                    <td>关注数：{{ focus }}</td>
                    <td>粉丝数：{{ fans }}</td>
                </tr>
            </table>
        </div>
    </div>
    <div class="botm">

        <ul class="lb-zhengwen">
            <li v-for="item in videos2">

                <div class="lb-1">
                      <img :src="item.coverUrl" title="item.title" class="lb-1-img-1" @click="playVideo(item.videoUrl,item.id)"/>
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
    <el-dialog
        v-model="dialogVisible"
        title="Tips"
        width="82%"
        :before-close="handleClose"
    >
        <span><messqge :chat-user="chatUser"/></span>
        <!--    <template #footer>
              <span class="dialog-footer">

              </span>
            </template>-->
    </el-dialog>
</template>
<script setup>
    import { onMounted, ref } from "vue";
    import useUser from "../../http/stores/user.js"
    import request from "../../http/Request.js"
    import { useRoute, useRouter } from "vue-router";
    import Messqge from "../../components/Message.vue"
    import { ArrowLeft } from '@element-plus/icons-vue'

    const focus = ref('')
    const fans = ref('')
    const user = useUser().user
    const numberoflikes = ref('')
    const videos2 = ref([
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        }, {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
        {
            coverUrl: "https://ts1.cn.mm.bing.net/th?id=OIP-C.QPH1IBosDYBqaU3O6wV3YAHaEo&w=316&h=197&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2",
            title: "ewqe",
            views: "wqe",
            createTime: "wqe",
            nickName: "qwe",
            barrages: "wqe",
        },
    ])
    const chatUserid = useRoute().params.userId
    const chatUser = ref({})
    const status = ref('1')
    const dialogVisible = ref(false)
    const router = useRouter()

    const back = () => {
        router.go(-1)
    }

    const show = () => {
        dialogVisible.value = true
        request.get("/user/findDeatil?id=" + chatUserid).then(rs => {
            chatUser.value = rs.data.data
        })
    }
    const playVideo=(url,id)=>{
      // console.log(url)
      // console.log(id)
      console.log("跳转")
      router.push({name:"videoPlay",
        params:{videoUrl:url,videoId:id}})
      request.get("/video/findOne",{params:{videoId:props.videoId}})
          .then(rs=>{
            up.value=rs.data.data.video.account
            videoInfo.value=rs.data.data.video
            const focusData={accountId:user.user.id,focusId:up.value.id}
            console.log(focusData)
            setTimeout( request.post("/importantPerson/findOne",focusData)
                .then(rs=>{
                  console.log("关注用户")
                  console.log(rs)
                  if (rs.data.code === 0){
                    isFocus.value=true
                  }
                }),200)
          })
      //   window.location.reload()
    }
    const init = () => {
        console.log(user.username)
        console.log(chatUserid)
        request.get("/user/findDeatil?id=" + chatUserid).then(rs => {
            chatUser.value = rs.data.data
            console.log("chatUser:" + rs.data.data)
            request.get('/video/findVideosByAccountId?id=' + chatUserid).then(rs => {
                if (rs.data.code === 0) {
                    console.log(rs)
                    videos2.value = rs.data.data
                  videos2.value=videos2.value.filter((item)=>{
                    if(item.status===1 && item.logicalDelete === 0)
                      return item
                  })
                }
            })
        })
        request.get("/user/findFocusLike?id=" + chatUserid).then(rs => {
            console.log(rs.data)
            numberoflikes.value = rs.data.numberoflikes;
            focus.value = rs.data.focus;
            fans.value = rs.data.fans;
        })
        request.get("/user/findstatus?userId=" + user.id + "&user2Id=" + chatUserid).then(rs => {
            status.value = rs.data.data
        })
    }

    function focuscl () {
        request.post("/importantPerson/insert", { accountId: user.id, focusId: chatUserid }).then(rs => {
            // console.log(rs.data)
            status.value = 2
            fans.value++;
        })

    }

    function fanscl () {
        request.get("/importantPerson/unsubscribe?accountId=" + user.id + "&foucId=" + chatUserid).then(rs => {
            // console.log(rs.data)
            status.value = 1
            fans.value--;
        })
    }

    onMounted(() => {
        init()
    })
</script>
<script>
    export default {
        name: "heCenter",
    }
</script>

<style scoped>
    li {
        list-style: none;
    }

    .avatar {
        width: 80px;
        height: 80px;
        border-radius: 100%;
        overflow: hidden;
    }

    .top {
        margin: 10px 30px;
        width: 800px;
        height: 130px;
        margin: auto;
    }

    .top .left {
        float: left;
        width: 400px;
    }

    .top .right {
        float: right;
        margin: auto;
    }

    .top .back:hover {
        cursor: pointer;
    }

    .lb-zhengwen {
        width: 980px;
        height: 920px;
    }

    .lb-zhengwen li {
        width: 170px;
        height: 210px;
        margin: 20px 30px 0 0;
        border: 1px solid #E5E9EF;
        float: left;
        border-radius: 4px;
    }

    .lb-zhengwen li:nth-child(5n) {
        margin-right: 0px;
    }

    .lb-zhengwen li .lb-1 {
        width: 168px;
        height: 100px;
        position: relative;
    }

    .lb-zhengwen li .lb-1 .lb-1-img-1 {
        width: 170px;
        height: 100px;
        border-radius: 4px;
    }

    .lb-zhengwen li .lb-1 span {
        position: absolute;
        bottom: 0px;
        right: 0px;
        color: #FFFFFF;
        font-size: 12px;
        padding: 0px 5px;
        border-radius: 4px;
        background-color: rgba(0, 0, 0, 0.5);
    }

    .lb-zhengwen li .lb-1 .lb-1-img-2 {
        position: absolute;
        bottom: 3px;
        right: 6px;
        visibility: hidden;
        opacity: 0;
        border-radius: 4px;
        -webkit-transition: all 0.2s;
        -webkit-transition-delay: 0.3s;
    }

    .lb-zhengwen li .lb-1:hover span {
        visibility: hidden;
        opacity: 0;
    }

    .lb-zhengwen li .lb-1:hover .lb-1-img-2 {
        visibility: visible;
        opacity: 1;
    }

    .lb-zhengwen li .lb-2 {
        width: 148px;
        height: 40px;
        margin-top: 4px;
        margin-bottom: 8px;
        padding: 8px 10px 0px;
    }

    .lb-zhengwen li .lb-2 a {
        font-size: 12px;
        color: #222222;
    }

    .lb-zhengwen li .lb-2 a:hover {
        color: #00A1D6;
    }

    .lb-zhengwen li .lb-2 span {
        color: #F25D8E;
    }

    .lb-zhengwen li .lb-left {
        width: 57px;
        height: 12px;
        color: #99A2AA;
        font-size: 12px;
        margin: 0px 8px 20px 0px;
        padding-left: 16px;
    }

    .lb-zhengwen li .lb-right {
        width: 100%;
        height: 12px;
        color: #99A2AA;
        font-size: 12px;
        /*margin: 0px 8px 12px 0px;*/
        padding-left: 16px;
    }

    .lb-zhengwen li .lb-up {
        display: inline-block;
        width: 57px;
        height: 15px;
        color: #99A2AA;
        font-size: 12px;
        padding-left: 16px;
    }

    .lb-zhengwen li .lb-up:hover {
        color: #00A1D6;
    }

    .lb-zhengwen li .barrage {
        display: flex;
        margin-top: 4px;
        vertical-align: top;
        font-size: 12px;
        color: #99A2AA;
    }

    .dialog-footer button:first-child {
        margin-right: 10px;
    }
</style>