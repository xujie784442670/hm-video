<template>
  <div class="top"  >
    <div class="title">
      <el-row>
        <el-col :span="2" :offset="1">
          <el-button  link bg :icon="House" color="#f98aec">首页</el-button>
        </el-col>
        <el-col :span="1">
          <el-button link bg color="#f98aec" >视频</el-button>
        </el-col>
        <el-col :span="6" :offset="5">
          <div class="is">
            <input placeholder="请输入查询内容" class="inp" /><el-button class="inpb"  slot="append" :icon="Search"/>
          </div>
        </el-col>
        <el-col :span="1">
          <el-button type="primary" v-if="!isLogin"  circle @click="login">登录</el-button>
          <el-avatar  src="src/assets/avatar/g2.jpg" v-if="isLogin" />
        </el-col>
        <el-col :span="1">
          <el-button  link type="primary" bg :icon="Message" color="#f98aec">消息</el-button>
        </el-col>
        <el-col :span="1" :offset="1">
          <el-button  link type="primary" bg :icon="Star" color="#f98aec">收藏</el-button>
        </el-col>
        <el-col :span="1" :offset="1">
          <el-button  link type="primary" bg :icon="Clock"  color="#f98aec">历史</el-button>
        </el-col>
        <el-col :span="2" :offset="1">
          <el-button  link type="primary" color="#f98aec">创作中心</el-button>
        </el-col>
      </el-row>
    </div>
    <div class="banner">
      <img src="src/assets/banner/banner1.webp" >
    </div>
  </div>

</template>

<script>
export default {
  name: "Top"
}
</script>

<script setup>
import {Search,House,Clock,Message,Star}  from '@element-plus/icons-vue'
import useUser from '../../../http/stores/user.js'
const user = useUser();
import {useRouter} from "vue-router";
const router = useRouter();
import {onMounted, ref} from "vue";
const shows=ref([
  {url:"/src/assets/shows/1.webp"},
  {url:"/src/assets/shows/2.webp"},
  {url:"/src/assets/shows/3.webp"},
  {url:"/src/assets/shows/4.webp"}
])
const isLogin=ref(false)
function login(){
    router.push({name:"login"})
}
onMounted(()=>{
  if (user.token){
    isLogin.value=true
  }
})
</script>

<style scoped>
html,body{
  width: 100%;
  height: 100%;
  margin: 0;
  padding: 0;
  border: 0;
}
.is{
  width: 100%;
}
.inp{
  line-height: 28px;
  border-right: 0;
  border-top: #E5E9EF 1px solid;
  border-left: #E5E9EF 1px solid;
  border-bottom: #E5E9EF 1px solid;
  border-radius: 10px 0  0 10px ;
}
.inp:focus {
  outline:none;
}
.inpb{
  border-left: 0;
  border-right: #E5E9EF 1px solid;
  border-top: #E5E9EF 1px solid;
  border-bottom: #E5E9EF 1px solid;
  border-radius: 0 10px 10px 0;
}
el-button span{
  color: #FFFFFF;
}
.title{
  width: 100%;
  position: absolute;
  top: 0;
  z-index: 3;
}
.header {
  width: 100%;
  height: 550px;
}
.header .header_left{
  position: relative;
  width: 60%;
  height: 100%;
  display: inline-block;
}
.header .header_right{
  position: absolute;
  margin-left: 30px;
  top: 200px;
  width: 30%;
  height: 100%;
  display: inline-block;
}
.banner{
  width: 100%;
  height: 200px;
  z-index: -1;
}
.banner img{
  width: 100%;
}
.video_ul{
  width: 100%;
  height: 100%;
  display: flex;
}
.video_ul li{
  list-style: none;
  width: 170px;
  height: 220px;
  margin: 20px 30px 0 0;
  border: 1px solid #E5E9EF;
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
  height: 60px;
  margin-bottom: 8px;
  padding: 8px 10px 0px;
}
.video_ul li .lb-1 .lb-1-img-1{
  width: 170px;
  height: 100px;
  border-radius: 4px;
}
.video_ul li .lb-left{
  width: 57px;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  margin: 0px 8px 12px 0px;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti1.png) no-repeat 0px 2px;
}
.video_ul li .lb-right{
  width: 57px;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  margin: 0px 8px 12px 0px;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti2.png) no-repeat 0px 2px;
}
.video_ul li .lb-up{
  display: inline-block;
  width: 57px;
  height: 15px;
  color: #99A2AA;
  font-size: 12px;
  padding-left: 16px;
  background: url(src/assets/img/liebiao/ti3.png) no-repeat 0px 2px;
}
</style>