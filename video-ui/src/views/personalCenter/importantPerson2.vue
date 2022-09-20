<!--我的粉丝界面-->
<template>
  <div id="fans">我的关注</div>
  <form-table
      v-model="formModel"
      :data="data"
      :loading="loading"
      table-height="440px"
      @query="loadData">
    <template #form>
      <el-form-item label="用户昵称查询：">
        <el-input v-model="formModel.nickName" placeholder="请输入用户名"></el-input>
      </el-form-item>
    </template>
    <template #column>
      <el-table-column label="" prop="avatar">
        <template v-slot="scope">
          <div @click="getHeCenter(scope.row.id)">
            <el-image :src="scope.row.avatar"  class="avatar" />
          </div>
        </template>
      </el-table-column>
      <el-table-column label="" prop="nickName"/>
      <el-table-column label="" prop="focusStatus">
        <template v-slot="scope">
          <el-button type="danger" v-if="scope.row.focusStatus==1" @click="showAdd(scope.row)">
            +关注
          </el-button>
          <el-button v-if="scope.row.focusStatus==2" @click="showDeleteDialog(scope.row)">
            已关注
          </el-button>
          <el-button type="danger" v-if="scope.row.focusStatus==3" @click="showAdd(scope.row)">
            回关
          </el-button>
          <el-button v-if="scope.row.focusStatus==4" @click="showDeleteDialog(scope.row)">
            互相关注
          </el-button>
        </template>
      </el-table-column>
    </template>
  </form-table>
  <el-dialog v-model="deleteDialogVisible"  width="20%" draggable>
    <div class="deleteDialog">
      <span>确认取消关注吗</span>
      <br>
      <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="deleteDialogVisible = false">取 消</el-button>
          <el-button type="danger" @click="deleteQuestion">确 定</el-button>
        </span>
    </div>
  </el-dialog>
  <el-dialog v-model="Add"  width="20%" draggable>
    <div class="deleteDialog">
      <span>确认关注吗</span>
      <br>
      <span slot="footer" class="dialog-footer">
          <el-button type="primary" @click="Add = false">取 消</el-button>
          <el-button type="danger" @click="ConfirmAttention">确 定</el-button>
        </span>
    </div>
  </el-dialog>
</template>
<script>
export default {
  name: "importantPerson2"
}
</script>
<script setup>
import {onMounted, ref } from 'vue'
import request from "../../http/Request.js";
import useUser from "../../http/stores/user.js"
import {toRaw} from "@vue/reactivity";
const data=ref([
  {
    avatar:"https://img0.baidu.com/it/u=324201646,1175190975&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1661533200&t=148f23f79b0e12cbb1c82c750eda1dc3",
    nickName:"小夏",
    focusStatus:2,
  },
  {
    avatar:"https://img0.baidu.com/it/u=324201646,1175190975&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1661533200&t=148f23f79b0e12cbb1c82c750eda1dc3",
    nickName:"小夏",
    focusStatus:4,
  }
])
import { useRouter } from 'vue-router'
const router = useRouter()
const user=useUser().user
const endData=ref([])
const account=ref({})
const deleteDialogVisible = ref(false);
const formModel = ref({});
const loading = ref(false)
const Add=ref(false);
/*const pageInfo = ref({
  page: 1,
  pageSize: 10,
  total: 0
})*/
function loadData(){
  findAll(toRaw(formModel.value))

}
const findAll = (InquireData) => {
  console.log(InquireData)
  data.value=endData.value
  console.log(data.value)
  let da=ref([])
  if(InquireData.nickName!=''&&InquireData.nickName!=null){
    let arrae=toRaw(data.value);
    console.log(arrae)
    for (let i = 0; i < arrae.length; i++) {
      console.log("arrae[i].nickName===InquireData.nickName:"+arrae[i].nickName===InquireData.nickName)
      if(arrae[i].nickName===InquireData.nickName){
        da.value.push(arrae[i])
      }
    }
    data.value=da.value
  }
  console.log(da.value)

}

const showDeleteDialog = (row) => {
  deleteDialogVisible.value = true
  account.value=row
  console.log(account.value)
}

const showAdd = (row) => {
  Add.value = true
  account.value=row
  console.log(account.value)
}
const deleteQuestion=()=>{
  deleteDialogVisible.value = false
  request.get("/importantPerson/unsubscribe?accountId="+user.id+"&foucId="+
      account.value.id).then(rs=>{
  })
  account.value.focusStatus=account.value.focusStatus-1
  console.log(account.value)
  // data.value=data.value.filter(item=>item.id!==account.value.id)
}
const ConfirmAttention=()=>{
  Add.value=false
  request.get("/importantPerson/attention?id="+user.id+"&focusId="+account.value.id).then(rs=>{

  })
  account.value.focusStatus=account.value.focusStatus+1
  console.log(account.value)
}
const loadfocus=()=>{
  request.get("/importantPerson/accountList?accountId="+user.id).then(rs=>{
     data.value=rs.data.data
      endData.value=data.value
    console.log("rs:"+toRaw(rs.data.data))
    /*let arrae=toRaw(content.value);
    content.value=''
    for (let i = 0; i < arrae.length; i++) {
      if(arrae[i].from==user.value.username&&arrae[i].to==chatUser.value.username){//说明是自己发的消息
        createContent(null,arrae[i].from,arrae[i].text,arrae[i].time)
      }else if(arrae[i].from==chatUser.value.username&&arrae[i].to==user.value.username){
        createContent(arrae[i].from,null,arrae[i].text,arrae[i].time)
      }
      console.log(arrae[i])
    }*/
  })
}
const getHeCenter=(id)=>{
  router.push({name:'heCenter',params:{userId:id}})
}
const init=()=>{
  loadfocus()
}
onMounted(() => {
  init()
})
</script>
<style scoped>
.avatar{
  width:100px;
  height:100px;
  border-radius:100%;
  overflow:hidden;

}

el-form-item{
  width: 40%;
}
.deleteDialog{
  text-align: center;
}
.deleteDialog span{
  font-size: 15px;
  margin-bottom: 10px;
}
.deleteDialog span button{
  margin: 10px;
}
.bottom{
  float: right;
  margin-right: 20px;
  z-index: 999;
}
i el-icon{
  font-size: 20px;
}
el-input{
  width: 200px;
}
#fans{
  line-height: 30px;
  font-size: 18px;
  border-bottom: 20px;
  border-bottom: 1px solid #e5e9ef;
}
</style>