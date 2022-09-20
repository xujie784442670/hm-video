<template>
  <div style="padding: 10px; margin-bottom: 50px" class="message">
    <el-row>
      <el-col :span="20">
        <div style="width: 950px; margin: 0 auto; background-color: white;
                    border-radius: 5px; box-shadow: 0 0 10px #ccc">
          <div style="text-align: center; line-height: 50px;">
            {{ chatUsername}}
          </div>
          <div style="height: 350px; overflow:auto; border-top: 1px solid #ccc" v-html="content" id="contents"></div>
          <div style="height: 90px">
            <textarea v-model="text" style="height: 50px; width: 95%; padding: 20px; border: none; border-top: 1px solid #ccc;
             border-bottom: 1px solid #ccc; outline: none"></textarea>
            <div style="text-align: right; padding-right: 10px">
              <el-button type="primary" size="mini" @click="send">发送</el-button>
            </div>
          </div>
        </div>
      </el-col>
    </el-row>
  </div>
</template>
<script setup>
import {onMounted, ref,watch,computed} from "vue";
import { toRaw } from '@vue/reactivity'
import request from "../http/Request.js";
import userUser from "../http/stores/user.js"
const user1=userUser().user
const user=user1
const chatUsername=ref()
const isCollapse=ref(false)
// const chatUser= ref({
//   username:"admin",
//   nickname:"管理员",
//   avatar:"https://ts4.cn.mm.bing.net/th?id=OIP-C.CBSLTkaar6S3UeNVGnZpXgHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2"
// })
const props = defineProps({
  user:Object,
  chatUser:Object
})
// if(props.user!=undefined){
//   user.value.username=toRaw(props.user).username
//   user.value.avatar=toRaw(props.user).avatar
//   user.value.nickname=toRaw(props.user).nickname
// }
if(props.chatUser!=undefined){
  props.chatUser.username=toRaw(props.chatUser).username
  props.chatUser.avatar=toRaw(props.chatUser).avatar
  props.chatUser.nickname=toRaw(props.chatUser).nickname
}
const text=ref("")
const messages=ref([])
const content=ref([

])
let socket
const timestampToTime=(timestamp)=>{
  var date = new Date(timestamp * 1000);//时间戳为10位需*1000，时间戳为13位的话不需乘1000
  var Y = date.getFullYear() + '-';
  var M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1):date.getMonth()+1) + '-';
  var D = (date.getDate()< 10 ? '0'+date.getDate():date.getDate())+ ' ';
  var h = (date.getHours() < 10 ? '0'+date.getHours():date.getHours())+ ':';
  var m = (date.getMinutes() < 10 ? '0'+date.getMinutes():date.getMinutes()) + ':';
  var s = date.getSeconds() < 10 ? '0'+date.getSeconds():date.getSeconds();
  return Y+M+D+h+m+s;
}

const send = () => {
  if (!props.chatUser) {
    this.$message({type: 'warning', message: "请选择聊天对象"})
    return;
  }
  if (!text.value) {
    this.$message({type: 'warning', message: "请输入内容"})
  } else {
    if (typeof (WebSocket) == "undefined") {
      console.log("您的浏览器不支持WebSocket");
    } else {
      console.log("您的浏览器支持WebSocket");
      // 组装待发送的消息 json
      // {from: "zhang", to: "admin", text: "聊天文本",time:"发送时间"}
      let time=new Date();
      let nowTime=timestampToTime(Date.parse(time)/1000);
      let message = {from: user.username, to: props.chatUser.username, text: text.value,time:nowTime}
      socket.send(JSON.stringify(message));  // 将组装好的json发送给服务端，由服务端进行转发
      messages.value.push({user: user.username, text: text.value})
      // 构建消息内容，本人消息
      createContent(null, user.username, text.value,nowTime)
      text.value = '';
    }
  }
}
const createContent=(remoteUser, nowUser, text,time)=> {
  let html
  // 当前用户消息
  if (nowUser) { // nowUser 表示是否显示当前用户发送的聊天消息，绿色气泡
    html = "<p style='text-align: center;margin: auto'>"+time+"</p>" +
        "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
        "  <div class=\"el-col el-col-22\" style=\"text-align: right; padding-right: 10px\">\n" +
        "    <div class=\"tip left\">" + text + "</div>\n" +
        "  </div>\n" +
        "  <div class=\"el-col el-col-2\">\n" +
        "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
        "    <img src="+user.avatar+" style=\"object-fit: cover;\">\n" +
        "  </span>\n" +
        "  </div>\n" +
        "</div>";
  } else if (remoteUser) {   // remoteUser表示远程用户聊天消息，蓝色的气泡
    html = "<p style='text-align: center;margin: auto'>"+time+"</p>" +
        "<div class=\"el-row\" style=\"padding: 5px 0\">\n" +
        "  <div class=\"el-col el-col-2\" style=\"text-align: right\">\n" +
        "  <span class=\"el-avatar el-avatar--circle\" style=\"height: 40px; width: 40px; line-height: 40px;\">\n" +
        "    <img src="+props.chatUser.avatar+" style=\"object-fit: cover;\">\n" +
        "  </span>\n" +
        "  </div>\n" +
        "  <div class=\"el-col el-col-22\" style=\"text-align: left; padding-left: 10px\">\n" +
        "    <div class=\"tip right\">" + text + "</div>\n" +
        "  </div>\n" +
        "</div>";
  }
  content.value += html;

  let contents=document.getElementById("contents")
  contents.scrollTop=contents.scrollHeight;
}
const loadMessages =()=>{
  console.log("请求历史消息:username:"+user.username+"chatUser:"+props.chatUser.username)
  request.get("/message/history?user="+user.username+"&chatUser="+props.chatUser.username).then(rs=>{
    content.value=rs.data.data.list
    console.log(rs.data.data.list)
    let arrae=toRaw(content.value);
    content.value=''
    for (let i = 0; i < arrae.length; i++) {
      if(arrae[i].from==user.username&&arrae[i].to==props.chatUser.username){//说明是自己发的消息
        createContent(null,arrae[i].from,arrae[i].text,arrae[i].time)
      }else if(arrae[i].from==props.chatUser.username&&arrae[i].to==user.username){
        createContent(arrae[i].from,null,arrae[i].text,arrae[i].time)
      }
      console.log(arrae[i])
    }
  })
}
const init=()=>{
  loadMessages()
  // this.user = sessionStorage.getItem("user") ? JSON.parse(sessionStorage.getItem("user")) : {}
  let username = user.username;
  console.log(username)
  /*let arrae=JSON.parse(JSON.stringify(this.content.value));
  console.log(arrae)
  for (const arraeKey in arrae) {
    console.log("arraeKey:"+arraeKey)
    console.log("arraeKey.from:"+arraeKey.from)
    if(arraeKey.from==username.value){

    }
  }*/
  console.log("usename:"+username)
  let _this = this;
  let ip;
  request.get("/message/ip").then(rs=>{
    ip=rs.data.ip;
    console.log(rs.data.ip);
    if (typeof (WebSocket) == "undefined") {
      console.log("您的浏览器不支持WebSocket");
    } else {
      console.log("您的浏览器支持WebSocket");
      let socketUrl = "ws://"+ip+":9000/imserver/" + username;
      if (socket != null) {
        socket.close();
        socket = null;
      }
      // 开启一个websocket服务
      socket = new WebSocket(socketUrl);
      //打开事件
      socket.onopen = function () {
        console.log("websocket已打开");
      };
      //  浏览器端收消息，获得从服务端发送过来的文本消息
      socket.onmessage = function (msg) {
        console.log("收到数据====" + msg.data)
        let data = JSON.parse(msg.data)  // 对收到的json数据进行解析， 类似这样的： {"users": [{"username": "zhang"},{ "username": "admin"}]}
        if (data.users) {  // 获取在线人员信息
          // _this.users = data.users.filter(user => user.username.value !== username)  // 获取当前连接的所有用户信息，并且排除自身，自己不会出现在自己的聊天列表里
        } else {
          // 如果服务器端发送过来的json数据 不包含 users 这个key，那么发送过来的就是聊天文本json数据
          //  // {"from": "zhang", "text": "hello"}
          if (data.from === props.chatUser.username) {
            messages.value.push(data)
            // 构建消息内容
            createContent(data.from, null, data.text,data.time)
          }
        }
      };
      //关闭事件
      socket.onclose = function () {
        console.log("websocket已关闭");
      };
      //发生了错误事件
      socket.onerror = function () {
        console.log("websocket发生了错误");
      }
    }
  })





}
watch(props,()=>{
  if(props.chatUser){
    chatUsername.value=props.chatUser.nickName
    init()
  }
},{
  deep:true
})
// onMounted(() => {
//   init()
// })
</script>
<script>
// import request from "@/utils/request";
let socket;
export default {
  name: "Message"

}
</script>
<style>
.message .tip {
  color: white;
  text-align: center;
  border-radius: 10px;
  font-family: sans-serif;
  padding: 10px;
  width:auto;
  display:inline-block !important;
  display:inline;
}
.message .right {
  background-color: deepskyblue;
}
.message .left {
  background-color: forestgreen;
}
/*::-webkit-scrollbar {
  width: 0 !important;
}
::-webkit-scrollbar {
  width: 0 !important;height: 0;
}*/
</style>