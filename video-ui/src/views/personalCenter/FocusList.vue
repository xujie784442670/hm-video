<template>
  <el-table :data="tableData" border style="width: 100%" >
    <el-table-column prop="avatar" label="头像" width="180">
      <template #default="scope">
        <el-avatar :src="scope.row.avatar"/>
      </template>
    </el-table-column>
    <el-table-column prop="nickName" label="昵称"/>
    <el-table-column label="操作">
      <template #default="scope">
        <el-button type="danger" @click="delFocus(scope.row)">取消关注</el-button>
        <!--<el-avatar :src="scope.row.avatar"/>-->
      </template>
    </el-table-column>

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

request.get("/importantPerson/getFocus",{params:{id:user.id}})
    .then(rs=>{
      console.log(rs)
      tableData.value=rs.data.data
    })

function delFocus(row){
  console.log(row)
    request.post("/importantPerson/delOne",{accountId:user.id,focusId:row.id})
        .then(rs=>{
          console.log(rs)
        })
  request.get("/importantPerson/getFocus",{params:{id:user.id}})
      .then(rs=>{
        console.log(rs)
        tableData.value=rs.data.data
      })
}
</script>

<style scoped>

</style>