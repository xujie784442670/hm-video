<template>
  <div class="main">
    <div class="container">
      <div class="title">
        <h1>{{videoInfo.title}}</h1>
        <!--<a href="#">动画</a>><a href="#">综合</a>-->
        <p class="p-shang">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{videoInfo.createTime}}  &nbsp;&nbsp;最高全站日排行名</p>
      </div>
      <div class="video-wrapper">
        <canvas id="J-danmuCanvas" ></canvas>
        <slot name="video"></slot>
        <!--<slot class="J-danmuVideo" name="video" :src="src"></slot>-->
        <!--<video :src="{src.}"-->
        <!--       id="J-danmuVideo" controls></video>-->
      </div>
      <div class="tool-box">
        <button id="closeDanmu">关闭弹幕</button>
        <input id="danmuValue" type="text" class="danmu-input" v-model="message" placeholder="请输入弹幕"/>
        <button id="sendDanmu" class="danmu-btn" :disabled="hasValue">发送</button>
        <input id="danmuColor"
               value="#ffffff"
               type="color"
               class="color-input"/>
        <input id="danmuFontSize" type="range"/>
        <span>
          <svg v-if="!isCollection" class="icon shoucang"  aria-hidden="true" @click="resolveShouCang">
            <use xlink:href="#icon-shoucang" ></use>
          </svg>
          <svg v-if="isCollection" class="icon shoucang"  aria-hidden="true" @click="resolveShouCang">
            <use xlink:href="#icon-shoucang-kuai" ></use>
          </svg>
          <svg v-if="!isLike" class="icon" aria-hidden="true" @click="resolveLike">
            <use xlink:href="#icon-dianzan"></use>
          </svg>
          <svg v-if="isLike" class="icon" aria-hidden="true" @click="resolveLike">
            <use xlink:href="#icon-dianzan_kuai"></use>
          </svg>
          <svg  class="icon" aria-hidden="true" @click="isReport=true">
            <use xlink:href="#icon-jubao"></use>
          </svg>
        </span>
      </div>
    </div>
    <el-dialog
        v-model="isReport"
        title="举报"
        width="30%"
        destroy-on-close
        center
    >
      <el-form>
        <el-form-item label="请输入举报理由">
          <el-input v-model="reportContent"/>
        </el-form-item>
      </el-form>
      <template #footer>
      <span class="dialog-footer">
        <el-button @click="isReport = false">取消</el-button>
        <el-button type="primary" @click="report"
        >提交举报信息</el-button
        >
      </span>
      </template>
    </el-dialog>
    <div class="right">
      <div class="info">
        <div class="avatar">
          <el-avatar  :src="up.avatar" :size="50"/>
        </div>
        <div class="info1">
          <div class="name">
            <a href="#" id="a1">{{up.nickName}}</a><a href="#"></a>
            <span><el-icon><Message/></el-icon>发消息</span>
          </div>
          <div class="self">
            <span>个人QQ:2540328429</span>
          </div>
          <div class="panel1">
            <el-button color="#f9c2e8" v-if="!isFocus"  @click="resolveFocus">关注</el-button>
            <el-button color="#f9c2e8" v-if="isFocus" @click="resolveFocus">取消关注</el-button>
          </div>
        </div>
      </div>
      <div class="tuijian">
        <ul >
          <li v-for="item in randomArr">
            <img class=".lb-1" :src="item.coverUrl" width="300" @click="playVideo(item.videoUrl,item.id)">
            <div class="lb-2">
              <a href="#" class="lb-title">{{item.title}}</a>
              <!--<span class="lb-left">{{item.views}}</span>-->
              <span class="lb-right">{{item.createTime}}</span><br />
              <a href="#" class="lb-up">{{item.nickName}}</a>
            </div>
          </li>
        </ul>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Danmu",
}
</script>
<script setup>
import {onMounted, onUnmounted, reactive, ref, watch} from 'vue'
import {useRouter} from "vue-router"
import {Message,Star} from '@element-plus/icons-vue'
import Barrages from '../index.js'
import request from '../../../http/Request.js'
import useUser from  '../../../http/stores/user.js'
import {ElMessage} from "element-plus";
const user = useUser();
const router=useRouter()
const props = defineProps({
  text: {
    type: String,
    default: 'Button'
  },
  src: {
    type: String,
    default: ''
  },
  videoId:{
    type:Number
  }
});
const message=ref();
const randomArr=ref()
const isLike=ref(false)
const isCollection=ref(false)
const hasValue=ref(true);
const isFocus=ref(false)
const up=ref({})
const videoInfo=ref({
  title:'',
  createTime:''
})
const recommandVideos=ref()
const isReport=ref(false)
const reportContent=ref('')
function init(){
  request.post("/likes/findVideoLike",{
    videoId:props.videoId,
    accountId:user.user.id,
    commentID:'',
    type:0
  })
      .then(rs=>{
        if ( rs.data.code===0){
          isLike.value=true
          console.log(isLike)
        }
      })
  console.log(user.user.id)
}

function resolveLike(){
  if (user.token !== undefined){
  }else {
    console.log(11111111)
    router.push({name:"login"})
    ElMessage.error("请在登录后,进行该操作")
  }
  isLike.value=!isLike.value
  if (isLike.value){
    const like=ref({
      videoId:props.videoId,
      accountId:user.user.id,
      type:0
    })
    request.post("/likes/addVideoLike",like.value)
        .then(rs=>{
          console.log(rs)
        })
  }else{
    const like=ref({
      videoId:props.videoId,
      accountId:user.user.id,
      type:0
    })
    request.post("/likes/delVideoLike",like.value)
        .then(rs=>{
          console.log(rs)
        })
  }
}
function resolveShouCang(){
  isCollection.value=!isCollection.value
  if (isCollection.value){
    const collect=ref({
      videoId:props.videoId,
      accountId:user.user.id,
    })
    request.post("/collection/insert",collect.value)
        .then(rs=>{
          console.log(rs)
        })
  }else{
    const collect=ref({
      videoId:props.videoId,
      accountId:user.user.id,
    })
    request.post("/collection/delete",collect.value)
        .then(rs=>{
          console.log(rs)
        })
  }
}
function resolveFocus(){
  console.log("处理关注逻辑")
  if (isFocus.value){
    request.post("importantPerson/delOne",{accountId:user.user.id,focusId:up.value.id})
        .then(rs=>{
          console.log(rs)
          isFocus.value=false
        })
  }else{
    request.post("importantPerson/insert",{accountId:user.user.id,focusId:up.value.id}).then(rs=>{
      isFocus.value=true
    })
  }
}
const report=()=>{
   const data={
     accountId: user.user.id,
     videoId:props.videoId,
     content:reportContent.value
   }
   request.post("/report/insert",data)
       .then(rs=>{
         console.log(rs)
         console.log(rs.data.code === 0)
          if (rs.data.code=== 0){
            console.log(rs)
            ElMessage.success(rs.data.msg)
          }
       })
  isReport.value=false
}
const playtime=ref(0)
//先获取一次播放时长
request.post("/record/findOne",{accountId:user.user.id,videoId:props.videoId})
    .then(rs=>{
      if (rs.data.code===0){
        console.log(rs)
        playtime.value=rs.data.playTime
      }
    })
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
onMounted(()=>{
  init()
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

  request.post("/video/list",{})
      .then(rs=>{
        console.log("获取视频")
        recommandVideos.value=rs.data.data.list
        //打乱数组顺序
        const getArrRandomly = (arr) => {
          var len = arr.length;
          for (var i = len - 1; i >= 0; i--) {
            var randomIndex = Math.floor(Math.random() * (i + 1));
            var itemIndex = arr[randomIndex];
            arr[randomIndex] = arr[i];
            arr[i] = itemIndex;
          }
          return arr;
        }
        //截取打乱后的数组的前10（num）位
        const getRandomArr = (arr=[].value,num) => {
          const tmpArr = getArrRandomly(arr.value);
          let arrList = [];
          for (let i = 0; i < num; i++) {
            arrList.push(tmpArr[i]);
          };
          console.log(arrList);
          return arrList
        }
        randomArr.value = getRandomArr(recommandVideos,2);
      })

  request.post("/collection/findOne",{
    videoId:props.videoId,
    accountId:user.user.id
  }).then(rs=>{
    console.log(rs.data.code);
    if ( rs.data.code===0){
      isCollection.value=true
      console.log(isCollection)
    }
  })

  const video=document.getElementById('J-danmuVideo')
  const canvas=document.getElementById('J-danmuCanvas')
  let data= request.post("/barrage/list",{data:{videoId:props.videoId}}).then(rs=>{
    // console.log(rs)
    data=rs.data.data
    // console.log(data)
  })
  // console.log("===========")
  // console.log(data)
  let barrages
  setTimeout(()=>{
    barrages =new Barrages({
      canvas,
      video,
      data
    })
  },1000)
  /**
   * content 内容
   * fontSize 字体大小
   * color 颜色
   * time 出现时间
   * speed 速度
   * @param len
   * @returns {*[]}
   */
  function getData(len){
    let  data=[];
    for (let i=0;i<len;i++){
      data.push({
        value: `第${i}条弹幕`,
        fontSize:26,
        color:'red',
        time:Math.floor(Math.random()*20),
        speed: 2
      })
    }
    return data;
  }
  setTimeout(request.post("/record/findOne",{accountId:user.user.id,videoId:props.videoId})
      .then(rs=>{
        if (rs.data.code===0){
          console.log(rs)
          playtime.value=rs.data.playTime
        }
      }),100)
  // request.post("/focus/findOne",{accountId:user.user.id,focusId:})

  video.currentTime=playtime.value
  console.log("视频时间")
  console.log(video.currentTime)
  video.addEventListener('play',()=>{
    barrages.isPlay=true
    if (playtime.value > video.currentTime){
      video.currentTime=playtime.value
    }
    console.log("当前播放到"+video.currentTime)
    request.post("/record/insert",{accountId:user.user.id,videoId:props.videoId})
        .then(rs=>{
          // console.log(rs)
        })
    setTimeout(barrages.render(),200)
  })
  video.addEventListener('pause',()=>{
    barrages.pause()
    // console.log(video.currentTime)
    request.post("/record/update",{accountId:user.user.id,videoId:props.videoId,playTime:Math.floor(video.currentTime)})
        .then(rs=>{
          console.log(rs)
        })
    //暂停后更新播放时长

    console.log("查询到的播放时长"+playtime.value)
  })
  const sendBtn = document.getElementById('sendDanmu');
  const closeBtn = document.getElementById('closeDanmu');
  const content = document.getElementById("danmuValue");

  const color = document.getElementById("danmuColor");
  const size = document.getElementById('danmuFontSize');
  //监听时候输入弹幕内容,如果有内容,则可以发送
    watch(message,(val)=>{
      if (val===undefined || val===''){
        // console.log(1)
        hasValue.value=true
      }else{
        hasValue.value=false
      }
    }, {immediate:true} )
  sendBtn.onclick=(()=>{
    // console.log(content.value)
    // console.log(color.value)
    // console.log(size.value)
    const newData={
      content:content.value,
      fontSize:size.value,
      color:color.value,
      time:video.currentTime,
      speed:2
    }
    barrages.setBarrage(newData)
    const barrageData={
      videoId:props.videoId,
      accountId:user.user.id,
      content:content.value,
      fontSize:size.value,
      color:color.value,
      time:video.currentTime,
    }
    request.post("/barrage/insert",barrageData)
        .then(rs=>{
          console.log(rs)
        })
    content.value=""
    color.value="#cccccc"
    size.value="10"
    message.value=""
  })
  let hide=true;
  closeBtn.onclick=(()=>{
      canvas.hidden=hide;
      hide=!hide;
  } )

})

</script>
<style scoped>
a{
  text-decoration: none;
  color: #0d0aa1;
  font-family: PingFang SC, HarmonyOS_Regular, Helvetica Neue, Microsoft YaHei, sans-serif;
}
body,html{
  width: 100%;
  height: 100%;
}
body{

  margin: 10px;
}
input,
button{
  outline: none;
  border: none;
  vertical-align: middle;
  box-sizing: border-box;
}
.main{
  width: 100%;
  height: 600px;
  margin-top: 20px;
  display: flex;
}
.danmu-btn:hover{
  color: skyblue;
  cursor: pointer;
}
button:hover{
  color: skyblue;
}
.main .container{
  position: relative;
  /*top:150px;*/
  width: 1200px;
  display: inline-block;
  box-shadow: 3px 1px 5px 0 #447393;
  margin-left: 50px;
}
.video-wrapper{
  position: relative;
}
.tuijian{
  width: 100%;
  height: 500px;
}
.tuijian ul ,.tuijian li{
  list-style: none;
  padding: 0;
}
.tuijian ul li{
  width: 300px;
  height: 220px;
  border: #E5E9EF 1px solid;
  margin-bottom: 20px;
  /*margin-left: 50px;*/
}
.tuijian a{
  color: #99A2AA;
  font-size: 12px;
}
/*.video-wrapper video{*/
/*  width: 100%;*/
/*  height: 100%;*/
/*  !* height: 800px; *!*/
/*}*/
.tuijian li .lb-1{
  width: 168px;
  height: 100px;
  position: relative;
}
.tuijian li .lb-2{
  width: 100%;
  height: 40px;
  /*margin-top: 10px;*/
  margin-bottom: 4px;
  padding: 8px 10px 0;
}
.tuijian li .lb-1 .lb-1-img-1{
  width: 270px;
  height: 116px;
  border-radius: 4px;
}
.tuijian li .lb-left{
  width: 57px;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  margin: 0 8px 12px 0;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti1.png) no-repeat 0 2px;
}
.tuijian li .lb-right{
  /*width: 150px;*/
  width: 100%;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  margin: 0 8px 12px 0;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti2.png) no-repeat 0 2px;
}
.tuijian li .lb-up{
  display: inline-block;
  width: 57px;
  height: 15px;
  color: #99A2AA;
  font-size: 12px;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti3.png) no-repeat 0 2px;
}
video{
  width: 100%;
  height: 100%;
  /* height: 800px; */
}
.video-wrapper canvas{
  position: absolute;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
}
.container .tool-box{
  position: absolute;
  left: 50px;
  height: 38px;
  margin-top: 10px;
}
.container .tool-box span{
  line-height: 30px;
  margin-left: 250px;
}
.container .tool-box span .shoucang{
  font-size: 30px;
  margin-left: 250px;
}
.container .tool-box span .shoucang:hover{
  color: #f9c2e7;
}
.container .tool-box span .icon{
  margin-left: 15px;
  font-size: 30px;
  vertical-align: top;
}
.container .tool-box span .icon:hover{
  color: #f9c2e7;
}
.container input,
.container button{
  height: 100%;
  margin-right: 10px;
  border-radius: 5px;
  margin-bottom: 10px;
}

.container .danmu-input{
  border: 1px solid #ccc;
}
.danmu-btn{
  background-color: palevioletred;
  color: #fff;
  border: none;
  border-radius: 5px;
}
.container .title{
  width: 100%;
  height: 80px;
  /*border: 1px solid #E5E9EF;*/
}
.container .title h1{
  font-size: 18px;
  margin-left: 9px;
  color: #212121;
  font-weight: 500;
}
.container .title p{
  margin-top: 11px;
  margin-left: 10px;
  font-size: 12px;
  color: #999999;
}
.main .right{
  background-color: #FFFFFF;
  width:580px;
  height: 100%;
  margin-left: 50px;
  display: inline-block;
  /*border: #E5E9EF 1px solid;*/
}
.main .right .info{
  height: 200px;
  display: flex;
  flex-direction: column;
  width: 350px;
  box-shadow: 1px 1px 5px 0 rgba(1, 17, 17, 0.94);
  justify-items: center;
  justify-content: center;
  text-align: center;
}
.main .right .info .avatar{
  width: 100%;
  height: 50px;
  display: flex;
  justify-items: center;
  justify-content: center;
  margin-bottom: 10px;
}
.main .right .info1{
  width: 100%;
  display: flex;
  justify-content: center;
  justify-items: center;
  flex-direction: column;
  /*display: flex;*/
}
.main .right .info1 .name{
  height: 20px;
  text-align: left;
  display: flex;
  justify-content: center;
  justify-items: center;
  width: 100%;
}
.main .right .info1 span{
  vertical-align: middle;
}
.main .right .info1 .name span:hover{
  color: #c2f9ef;
  cursor: pointer;
}
.main .right .info1 .name a{
  margin-left: 20px;
}
.main .right .info1 .self{
  height: 50px;
  text-align: center;
  display: flex;
  justify-content: center;
  justify-items: center;
  width: 100%;
}
.panel1{
  width: 100%;
  display: flex;
  justify-items: center;
  justify-content: center;
}
.panel1 button{
    margin-left: 20px;
  color: #FFFFFF;
}
.panel1 button:hover{
    color: #c2f9ef;
}
/*.main .right .info1 .panel1 span{*/
/*  color: #FFFFFF;*/
/*}*/
#a1{
  color: #001369;
}
.main .right .info .self span{
  margin-left: 20px;
  margin-top: 10px;
  color: #C0C0C0;
}
</style>