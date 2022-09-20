<template>


  <div class="con">
    <div class="lef">
      <el-form :model="ruleForm"
               :rules="rules"
               ref="ruleForm"
               label-width="100px"
               class="demo-dynamic"
      >
        <el-form-item
            label="昵称修改"
            prop="name"
            :rules="[
          { required: true, message: '请注意昵称长度在2~15个字符', trigger: 'blur' },
          { min: 2, max: 15, message: '长度在 2 到 5 个字符', trigger: 'blur' }
        ]"

        >
          <el-input v-model="nickname"></el-input>
        </el-form-item>
        <br>
        <el-form-item
            prop="email"
            label="邮箱"
            :rules="[
      { required: true, message: '请输入邮箱地址', trigger: 'blur' },
      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
    ]"
        >
          <el-input v-model="email"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="update">提交修改</el-button>
        </el-form-item>
      </el-form>
    </div>

  </div>


</template>

<script >
export default {
  name: "infoeditor",
  data() {
    return {
      ruleForm: {
        name: '',
        sex: '男',
        phone: '',
        city: '',
        motto: '',

      },
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          alert('submit!');
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },

  }

}
</script>

<script setup>
import { ref } from  'vue'
import request from '../../http/Request.js'
import useUser from "../../http/stores/user.js"
import {ElMessage} from "element-plus";
const user=useUser().user
const  nickname=ref()
const email=ref()



const update=()=>{
  console.log(user.id+" "+nickname.value)
  request.post("/user/updateNickname",{id:user.id,nickName:nickname.value})
      .then(rs=>{
        console.log(rs)
        if (rs.data.code===0){
          ElMessage.success(rs.data.msg)
        }else {
          ElMessage.error(rs.data.msg)
        }
      })
  request.post("/user/updateEmail",{id:user.id,email:email.value})
      .then(rs=>{
        console.log(rs)
        if (rs.data.code===0){
          ElMessage.success(rs.data.msg)
        }else {
          ElMessage.error(rs.data.msg)
        }
      })
}



</script>

<style scoped>
.con{
  display: flex;
  width: 100%;
  height: 100%;
  margin-top: 5%
}
.lef{
  width: 30%;
}
.righ{
  width: 50%;
  margin-left: 30px;
}
el-form-item{
  width: 100%;
}
.confir{
  height: 40px;
  position: relative;
  bottom: 39px;
  right: 50px;
}
.upload{
  margin-left: 20%;
  width: 20%;
  text-align: center;
  color: white;
  height: 32px;
  border-radius: 3px;
  background: #1E90FF;
  cursor: pointer;
  outline: none;
  border-width: 0px;
  font-size: 17px;
  display:inline-block;
  padding: 4px 10px;
  line-height:30px;
  position: relative;
  text-decoration: none;
}
.show button {
  margin-left: 70%;
  width: 108px;
  height: 40px;
  border-width: 0px;
  border-radius: 3px;
  background: #1E90FF;
  cursor: pointer;
  outline: none;
  color: white;
  font-size: 17px;
}
.show{
  width: 100%;
  height: 450px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 10px;

}
.show1{
  margin: 50px auto;
  width: 222px;
  height: 226px;
  border: 5px solid #18a0ec;
  transition: all 0.9s;
  border-radius: 150px;

}

.show1:hover{
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  transform: scale(1.2);
}

.show:hover{
  box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
  margin-left: 45px;

}
</style>
