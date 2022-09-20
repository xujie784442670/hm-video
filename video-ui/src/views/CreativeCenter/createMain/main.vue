
<template>
 <span style=" color: #3dc537; font-size: 30px;  font-family: 华文新魏;position: relative;left: 470px">核 心 数 据 预 览</span>
  <el-main  style="background: white"
  date:data
  >
  <el-row :gutter="22">

    <el-col :span="6">
      <el-card shadow="hover" class="el-main"><el-icon
      style="position: relative;top: 2px"
      ><Star /></el-icon>收藏
        {{data.collectionNum}}</el-card>

    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" class="el-main">
        <svg class="icon" aria-hidden="true">
          <use xlink:href="#icon-dianzan"/>
        </svg>

        点赞  {{data.likeNum}}</el-card>
    </el-col>

    <el-col :span="6" >
      <el-card shadow="hover" class="el-main">
        <el-icon
            style="position: relative;top: 2px"><ChatDotSquare /></el-icon>评论数
        {{data.videoComentNum}}
      </el-card>
    </el-col>
    <el-col :span="6">
      <el-card shadow="hover" class="el-main">

        <el-icon
            style="position: relative;top: 2px"> <Comment /></el-icon>弹幕数
        {{data.barrageNum}}
      </el-card>
    </el-col>
  </el-row>


  </el-main>





<!--  <div id="container"> </div>-->
</template>


<script>
export default {
  name: "createMain"
}

</script>

<script setup>
import {
  VideoPlay,
  Star,
  ChatDotSquare,
  User,
  Comment
} from '@element-plus/icons-vue'

import useUser from    '../../../http/stores/user.js'
import request from "../../../http/Request.js";

import {onMounted, ref} from "vue";
const  user=useUser().user
const data=ref({
  collectionNum:0,
  likeNum:0,
  videoComentNum:0,
  barrageNum:0
})


const init=()=>{
  console.log(user.id)
  request.get("/date/dateCreate",{params:{aid:user.id}}).then(rs=>{
    data.value=rs.data.data

    console.log(rs.data.data)
  })
}
onMounted(()=>{
  init()
})
</script>

<style scoped>
.el-main{
  background-color: #baf3cd;
  border-radius: 22px;

}

.el-col{
  position: relative;


}
.body-style{
  width: 80px;
  height: 60px;
}
.el-col .active{

}
</style>
