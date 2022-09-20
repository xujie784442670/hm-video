<template>
  <el-input
      v-model="input2"
      class="w-50 m-2"
      placeholder="搜索稿件"
      :suffix-icon="Search"
      style="width: 160px;height: 30px;z-index: 1;position: absolute;right: 100px;top:95px"
  />
  <div style="height: 100px;    background: white; padding: 0;scroll-behavior: smooth;">

    <div id="qx">
        <i class="bcc-iconfont bcc-icon-ic_MenuButton-tick"></i>
        <input type="checkbox" name="默认" aria-hidden="true" value="false">
        <span class="bcc-checkbox-label">全选 </span>


        <el-button   style=" position: relative;left: 40px; border: #cccccc 1px" type="danger" round> 删除</el-button>
    </div>
    <span id="gg">评 论 管 理</span>
    <el-input
        v-model="search"
        class="w-50"
        placeholder="搜索评论关键字"
        style="width: 160px;height: 30px;z-index: 1;position: absolute;right: 400px;top:15px"
    />
    <el-button @click="filt" style="margin-left: 20px">搜索</el-button>
    <el-select v-model="va" class="m-2" placeholder="请选择你要管理的视频" >
      <el-option
          v-for="item in vide"
          :key="item.id"
          :label="item.title"
          :value="item.id"
      />
    </el-select>
  </div>
  <div >
    <el-table class="left-z"
              ref="multipleTableRef"
              :data="barrage2"
              style="width: 100%;height: 65%;background: #FFFFFF "
    >
      <!--<el-table-column type="selection" width="55" />-->
      <el-table-column property="id" border width: label="发送者" prop="nickName"/>
      <el-table-column property="avatar" border width: label="头像" prop="avatar">
        <template #default="scope">
          <el-avatar :src="scope.row.avatar"/>
        </template>
      </el-table-column>
      <el-table-column property="content" prop="content" border label="评论内容" show-overflow-tooltip />
      <el-table-column property="createTime" prop="createTime" border label="评论时间"  sortable >
        <template #default="scope">
          <span>{{scope.row.createTime}}</span>
        </template>
      </el-table-column>
      <el-table-column   border label="操作" show-overflow-tooltip>
        <template #default="scope">
          <el-button
              size="mini" type="danger"
              @click="showDialog(scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-dialog v-model="isDialog" title="Tips" width="30%" draggable>
      <span>确定要删除该评论吗</span>
      <template #footer>
        <span class="dialog-footer">
          <el-button @click="isDialog = false">取消</el-button>
          <el-button type="danger" @click="deleteComment(delComment)"
          >确认</el-button
          >
        </span>
      </template>
    </el-dialog>
  </div>
  <!--分页-->



</template>
<script>
export default {
  name: "barrageManagement"
}

</script>
<script setup>

import {
  Search,
  Refresh
} from '@element-plus/icons-vue'
import {onMounted, ref, watch} from "vue";
import request from "../../http/Request.js";
import userUser from '../../http/stores/user.js';
import {ElMessage, ElMessageBox} from "element-plus";
const user=userUser().user
const data=ref()
const va=ref()
const vide=ref()
const isDialog=ref(false)
const delComment=ref()
const barrage2=ref()
const search=ref()
const  deleteComment=(row)=>{
  console.log(row)
  request.get("/comments/delComment?commentId="+row.id).then().then(rs=>{
        if (rs.data.code===0){
          ElMessage.success(rs.data.msg)
        }
        request.get("/comments/list1",{params:{videoId:va.value}}).then(rs=>{
          data.value=rs.data.data
          barrage2.value=data.value
        })
      })
  isDialog.value=false

}
const showDialog=(row)=>{
  console.log(row)
  delComment.value=row
  isDialog.value=true
}
const filt=()=>{
  barrage2.value=data.value.filter((item)=>{
    if (item.content.includes(search.value)){
      return  item
    }
  })
}
// deleteBarrage(scope.row)
const init=()=>{

  request.get("/video/findVideosByAccountId",{params:{id:user.id}})
      .then(rs=>{
        if (rs.data.code===0){
          console.log(rs)
          vide.value=rs.data.data
        }
      })
}
watch(va,()=>{
  console.log("videoID:"+va.value)
  request.get("/comments/list1",{params:{videoId:va.value}}).then(rs=>{
    console.log("发生了变化")
    data.value=rs.data.data
    barrage2.value=data.value
    console.log(rs.data.data)
  })
})
onMounted(() => {
  init()
})

</script>

<style scoped>

#gg{
  color: #3dc537; justify-content: center;
  background-color: white;border-bottom: 1px solid white;
  height: 100%;
  width: 59px;
  font-family: 华文新魏;
  font-size: 35px;
  text-align: center;
  margin-left: 440px;
  margin-right: 300px;
  margin-top: 30px;
  position: relative;
  top: 10px;
}
#top-a{
  position: relative;
  top: 1px;
}

.left-z{

  user-select: none;
  background-color: #ffffff;
}
</style>