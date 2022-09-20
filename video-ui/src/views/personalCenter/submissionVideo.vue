<template>
  <el-table :data="tableData" border style="width: 100%" >
    <el-table-column prop="title" label="标题" width="180" />
    <el-table-column prop="type" label="类型" width="180" />
    <el-table-column prop="coverUrl" label="投稿封面">
      <template #default="scope">
        <el-image :src="scope.row.coverUrl"/>
      </template>
    </el-table-column>
    <el-table-column prop="videoUrl" label="投稿内容" >
      <template #default="scope">
        <video width="300" :src="scope.row.videoUrl" controls/>
      </template>
    </el-table-column>
    <el-table-column prop="createTime" label="投稿时间" />
  </el-table>
</template>

<script>
export default {


  name: "submissionVideo"
}
</script>

<script setup>
  import {ref} from "vue";
  import request from "../../http/Request.js";
  import useUser from "../../http/stores/user.js"
  const user=useUser().user
  const tableData=ref()

  request.post("/video/list",{accountId:user.id})
      .then(rs=>{
        console.log(rs)
        tableData.value=rs.data.data.list
      })
</script>

<style scoped>

</style>