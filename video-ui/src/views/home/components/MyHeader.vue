<template>
  <div class="header">
    <div class="header_left" >
      <el-carousel  trigger="click" height="550px" arrow="always">
        <el-carousel-item v-for="item in shows" :key="item">
          <el-image :src="item.url" alt="图片" />
          <!--<el-image :src="item.url"/>-->
        </el-carousel-item>
      </el-carousel>
    </div>
    <div class="header_right" >
      <ul class="video_ul" >
        <li v-for="item in randomArr" :key="item" >
          <div class="lb-1">
            <img :src="item.coverUrl" title="镇站之宝" class="lb-1-img-1"/>
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
  </div>
</template>

<script>
export default {
  name: "myHeader"
}
</script>

<style scoped>

</style>
<script setup>
import {Edit,Headset}  from '@element-plus/icons-vue'
import {onMounted, ref} from "vue";
import request from "../../../http/Request.js";
const randVideos=ref([
  {url:"src/assets/shows/l1.webp",title:"B站历史上意义最重大的10部动画！"},
  {url:"src/assets/img/liebiao/l2.webp",title:"B站历史上意义最重大的10部动画！"},
  {url:"src/assets/img/liebiao/l3.webp",title:"B站历史上意义最重大的10部动画！"},
  {url:"src/assets/img/liebiao/l4.webp",title:"B站历史上意义最重大的10部动画！"},
])
const randomArr=ref()
onMounted(()=>{

  request.post("/video/list",{})
      .then(rs=>{
        console.log("获取视频")
        console.log(rs)
        randVideos.value=rs.data.data.list
        console.log(randVideos)
        // randVideos.value.
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
        randomArr.value = getRandomArr(randVideos,4);
      })

})

const shows=ref([
  {url:"src/assets/img/lunbo/11.webp"},
  {url:"src/assets/img/lunbo/11.webp"},
  {url:"src/assets/img/lunbo/11.webp"},
  {url:"src/assets/img/lunbo/11.webp"}
])
</script>
<style scoped>
body,html{
  height: 100%;
  width: 100%;
}
.header {
  margin-left: 10px;
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
  top: 50px;
  width: 30%;
  height: 100%;
  display: inline-block;
}
.video_ul{
  width: 100%;
  height: 100%;
}
.video_ul li{
  list-style: none;
  width: 170px;
  height: 220px;
  margin: 20px 30px 0 0;
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
  width: 120px;
  height: 12px;
  color: #99A2AA;
  font-size: 12px;
  /*margin: 0px 8px 12px 0px;*/
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