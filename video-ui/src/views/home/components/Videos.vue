
<template>
  <div class="main">
    <div class="main_left">
        <span>{{videoType}}</span>
      <ul class="video_ul" >
        <li v-for="item in randVideos" :key="item" >
          <div class="lb-1">
            <img :src="item.coverUrl" title="B站几大镇站之宝" class="lb-1-img-1" @click="playVideo(item.videoUrl,item.id)"/>
          </div>
          <div class="lb-2">
            <a href="#" class="lb-title">{{item.title}}</a>
          </div>
          <span class="lb-left">{{item.views}}</span>
          <span class="lb-right">{{item.createTime}}</span><br />
          <a href="#" class="lb-up">{{item.nickName}}</a>
        </li>
      </ul>
    </div>
    <div class="main_right">
      <p>排行耪</p> <span>更多</span>
      <ul>
        <li v-for="(item,index) in ranks  " :key="index" >
            <span>{{index+1}}</span>
            <a href="#">{{item.title}}</a>
          <div  class="img-rank" >
            <img :src="item.coverUrl" v-if="index===0">
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
export default {
  name: "Videos",

}
</script>
<script setup>
import {ref, defineProps, onMounted} from "vue";
 import axios from "axios";
 import request from "../../../http/Request.js";
import {useRouter} from "vue-router";
 const props = defineProps({
   id:{
   type:Number,
   required:true
   }
 });
// {url:"src/assets/img/index/b1.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b2.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b3.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b4.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b5.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b6.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b7.webp",title:"B站历史上意义最重大的10部动画！"},
// {url:"src/assets/img/index/b8.webp",title:"B站历史上意义最重大的10部动画！"},
 const randVideos=ref([

 ])
 const ranks=ref([
   {rank:1,title:"测试12333333"},
   {rank:2,title:"测试12333333"},
   {rank:3,title:"测试12333333"},
   {rank:4,title:"测试12333333"},
   {rank:5,title:"测试12333333"},
   {rank:6,title:"测试12333333"},
   {rank:7,title:"测试12333333"},
   {rank:8,title:"测试12333333"},
   {rank:9,title:"测试12333333"},
   {rank:10,title:"测试12333333"},
 ])
const router = useRouter();
 const videoType=ref()
 const video1=ref()
const playVideo=(url,id)=>{
  console.log(url)
  console.log(id)
  router.push({name:"videoPlay",
    params:{videoUrl:url,videoId:id}})
}
 const loadVideos =()=>{
   request.get("/video/list/"+props.id).then(rs=>{
     randVideos.value=rs.data.data.list

     console.log(randVideos)
   })
   setTimeout(()=>{
     ranks.value = randVideos.value.sort((a, b) => {
       return     b.views - a.views
     })
     video1.value=randVideos.value[0]
     videoType.value=video1.value.type
   },500)
 }

onMounted(()=>{
   loadVideos()
 })
</script>

<style scoped>
*{
  margin: 0;
}
.main {
  margin-left: 10px;
  width: 98%;
  margin-top: 40px;
  height: 500px;
  /*border: 2px solid #99A2AA;*/
}
.main .main_left{
  position: relative;
  width: 80%;
  height: 100%;
  display: inline-block;
}
.main .main_left span{
  margin-left: 50px;
}
.img-rank{
  margin-top: 4px;
  display: flex;
  justify-items: center;
  justify-content: center;
}
.main .main_right{
  position: absolute;
  right: 50px;
  /*margin-left: 40px;*/
  width: 15%;
  height: 100%;
  display: inline-block;
}
.main .main_right a{
  text-align: left;
}
.main .main_right img{
  height: 80px;
}
.main .main_right p{
  display: inline-block;
  margin-right: 30px;
}
.main .main_right span{
  margin-left: 70px;
  width: 60px;
  /*background: url(src/assets/img/index/zuojian.png) no-repeat 40px 3px;*/
}
.main .main_right span:hover{
  /*background-color: #41ccd1*/
  font-size: 18px;
}
.main .main_right ul{
  border: 1px solid #C0C0C0;
  border-radius: 10px;
  padding: 0;
  margin-bottom: 0;
  margin-top: 0;
  /*margin-left: -30px;*/
  width: 100%;
  height: 450px;
  /*margin: 25px 0 0 0;*/
}
.main .main_right li{
  list-style: none;
  border-radius: 3px;
  height: 35px;
  position: relative;
  margin-bottom: 1px;
}
.main .main_right li span{
  border: none;
  text-align: center  ;
  margin: 5px;
  width: 30px;
  color: #c9ccd0;
  float: left;
}
.main .main_right li:first-child{
  height: 130px;
}
.main .main_right li:first-child span{
  color:  #ff473D;
}
.main .main_right li:nth-child(2) span{
  color: #ff6a29;
}
.main .main_right li:nth-child(3) span{
  color: #ff9214;
}
.main .main_right li:nth-child(2n-1){
  background-color: #f6f7f8;
}
.main .main_right li a {
  margin-left: 30px;
  text-decoration: none;
}
.video_ul{
  width: 100%;
  height: 100%;
}
.video_ul li{
  list-style: none;
  width: 268px;
  height: 225px;
  margin: 20px 15px 0 0;
  /*border: 1px solid #E5E9EF;*/
  float: left;
  border-radius: 4px;
}
.video_ul li .lb-1{
  width: 168px;
  height: 100px;
  position: relative;
}
.video_ul li .lb-2{
  width: 148px;
  height: 40px;
  margin-top: 10px;
  margin-bottom: 4px;
  padding: 8px 10px 0;
}
.video_ul li .lb-1 .lb-1-img-1{
  width: 270px;
  height: 116px;
  border-radius: 4px;
}
.video_ul li .lb-left{
  width: 57px;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  margin: 0 8px 12px 0;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti1.png) no-repeat 0 2px;
}
.video_ul li .lb-right{
  width: 57px;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  margin: 0 8px 12px 0;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti2.png) no-repeat 0 2px;
}
.video_ul li .lb-up{
  display: inline-block;
  width: 57px;
  height: 15px;
  color: #99A2AA;
  font-size: 12px;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti3.png) no-repeat 0 2px;
}
</style>