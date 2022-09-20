<template>
  <div class="space-right-bottom ps ps--active-y">
    <div class="router-view">
      <div class="d">
        <div class="bili-im">
        <div data-v-f135e21e="" class="left">
          <div data-v-f135e21e="" class="title"><span data-v-f135e21e="">近期消息</span></div>
          <div data-v-f135e21e="" class="list-container ps"><!---->
            <div data-v-f135e21e="" class="list">
              <div v-for="item in data" @click="chatmessage(item)" class="list-item">
                <div data-v-5eb7422a="" :title=item.nickName class="avatar" :style="'background-image: url('+item.avatar+')'"></div>
                <div data-v-5eb7422a="" class="verify-box"><!---->
                  <div data-v-5eb7422a="" class="company"></div></div>
                <div data-v-5eb7422a="" class="name-box">
                  <div data-v-5eb7422a="" :title=item.nickName class="name">{{item.nickName}}</div>
                  <div data-v-5eb7422a="" :title=item.lastmessage class="last-word"><!----><!---->
                  {{item.lastmessage}}
                  </div>
                </div>
                <div data-v-5eb7422a="" class="close">
                  <svg data-v-5eb7422a="" viewBox="0 0 40 40" class="css-1dtzbno">
                    <path d="M22.83,20,38.42,4.41a2,2,0,1,0-2.83-2.83h0L20,17.17,4.41,1.58A2,2,0,0,0,1.58,4.41L17.17,20,1.58,35.59a2,2,0,0,0,2.83,2.83L20,22.83,35.59,38.42a2,2,0,1,0,2.83-2.83Z">
                    </path>
                  </svg>
                </div>
              </div>
              <div data-v-f135e21e="" class="list-loading" style="display: none;">
                <div data-v-f135e21e="" class="lds-spinner">
                  <div></div>
                  <div></div>
                  <div></div><div></div>
                  <div></div><div></div>
                  <div></div><div></div>
                  <div></div><div></div>
                  <div></div><div></div>
                </div>
              </div>
              <div data-v-f135e21e="" class="list-load-error" style="display: none;">加载失败，<span data-v-f135e21e="">点击重试</span></div>
            </div>
            <div class="ps__rail-x" style="left: 0px; bottom: 0px;">
              <div class="ps__thumb-x" tabindex="0" style="left: 0px; width: 0px;"></div>
            </div>
            <div class="ps__rail-y" style="top: 0px; right: 0px; height: 233px;">
              <div class="ps__thumb-y" tabindex="0" style="top: 0px; height: 0px;"></div>
            </div>
          </div>
        </div>
        <div class="right" id="right" >
            <message :chat-user="chatUser"></message>
        </div>
      </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import {onMounted,ref} from "vue";
import Message from "../../../../components/Message.vue"
import request from "../../../../http/Request.js";
import userUser from "../../../../http/stores/user.js"
import {toRaw} from "@vue/reactivity";
const user1=userUser().user
const data=ref([
  /*{
    username: 'admin',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'管理员',
    lastmessage:'你好你好哦i恩菲送到你法大师傅大师傅而非我们分配为革命热舞苹果皮让我那个我'
  },
  {
    username: 'xiaowang',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'小王',
    lastmessage:'你好'
  },
  {
    username: 'xiaoli',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'小李',
    lastmessage:'你好'
  },
  {
    username: 'xiaoxia',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'小夏',
    lastmessage:'你好'
  },
  {
    username: 'xiaoxia',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'小夏',
    lastmessage:'你好'
  },
  {
    username: 'xiaoxia',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'小夏',
    lastmessage:'你好'
  },
  {
    username: 'xiaoxia',
    avatar: "background-image: url('https://ts3.cn.mm.bing.net/th?id=OIP-C.ST4QhlqZeK6sk5IGQD5irAHaHa&w=250&h=250&c=8&rs=1&qlt=90&o=6&pid=3.1&rm=2')",
    nickName:'小夏',
    lastmessage:'你好'
  },*/
])
const chatUser=ref({
})
const user=user1
const chatmessage =(chuser)=>{
  chatUser.value=chuser;
  console.log(chatUser.value)
}
const init=()=>{
  console.log("username:"+user.username)
  request.get("/message/lastHistory?username="+user.username).then(rs=>{
    data.value=rs.data.data.list
    console.log("data.va:"+data.value)
  })
  chatUser.value=data.value
  console.log("chatUser:"+chatUser.value)
  if(chatUser.value==undefined){
    document.getElementById("right").style.display="none";
  }
}
onMounted(() => {
  init()
})

</script>

<script>
export default {
  name: "right"
}
</script>

<style scoped>
.space-right-bottom{
  overflow: inherit !important;
}
.space-right-bottom {
  padding: 10px;
  position: relative;
  -webkit-box-flex: 1;
  -ms-flex: 1;
  flex: 1;
  height: calc(100% - 66px);
  overflow: hidden;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  background-color: rgba(255,255,255,0.5);
}
.ps {
  overflow: hidden !important;
  overflow-anchor: none;
  -ms-overflow-style: none;
  touch-action: auto;
  -ms-touch-action: auto;
}
* {
  margin: 0;
  padding: 0;
}
div {
  display: block;
}
.d{
  height: calc(100vh - 128px);
}
.bili-im{
  font-size: 12px;
  color: #666;
  background-color: #fff;
  -webkit-box-shadow: 0 2px 4px 0 rgb(121 146 185 / 54%);
  box-shadow: 0 2px 4px 0 rgb(121 146 185 / 54%);
  border-radius: 4px;
  height: 100%;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  position: relative;
}
.bili-im .left{
  width: 240px;
  border-right: 1px solid #e9eaec;
  position: relative;
}
.bili-im .left .title{
  padding-left: 24px;
  line-height: 35px;
  height: 36px;
  border-bottom: 1px solid #e9eaec;
  -webkit-user-select: none;
  -moz-user-select: none;
  -ms-user-select: none;
  user-select: none;
  overflow: hidden;
}
.bili-im .left .list-container{
  height: calc(100% - 36px);
  position: relative;
  overflow: hidden;
}
.bili-im * {
  -webkit-box-sizing: border-box;
  box-sizing: border-box;
}
.list{
  overflow-y:auto
}
.list-item{
  width: 100%;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: horizontal;
  -webkit-box-direction: normal;
  -ms-flex-direction: row;
  flex-direction: row;
  padding: 19px 24px;
  position: relative;
  overflow: hidden;
  cursor: pointer;
}
.active{
  background-color: #e4e5e6;
}
.list-item .avatar{
  display: block;
  width: 40px;
  height: 40px;
  border-radius: 50%;
  -ms-flex-negative: 0;
  flex-shrink: 0;
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-image: url(//static.hdslb.com/images/member/noface.gif);
  margin-right: 8px;
  position: relative;
}
.list-item .name-box{
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-orient: vertical;
  -webkit-box-direction: normal;
  -ms-flex-direction: column;
  flex-direction: column;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
}
.list-item .name{
  color: #333;
  font-size: 14px;
  min-height: 16px;
  line-height: 1;
}
.list-item .last-word{
  color: #999;
  padding: 8px 0;
  margin-bottom: -6px;
}
.list-item .last-word, .list-item .name{
  overflow: hidden;
  width: 155px;
  white-space: nowrap;
  text-overflow: ellipsis;
}
.list-item .close {
  position: absolute;
  top: 0;
  bottom: 0;
  left: 0;
  display: -webkit-box;
  display: -ms-flexbox;
  display: flex;
  -webkit-box-pack: center;
  -ms-flex-pack: center;
  justify-content: center;
  -webkit-box-align: center;
  -ms-flex-align: center;
  align-items: center;
  width: 24px;
  -webkit-transform: translateX(-100%);
  transform: translateX(-100%);
  opacity: 0;
  -webkit-transition: 300ms;
  transition: 300ms;
  color: #999;
}
</style>