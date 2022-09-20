<template>
<!--  <div id="background" class="show">-->
  <div style="width: 30%;margin-top: 5%">
      <el-form-item label="修改密码" prop="pass">
        <el-input type="password" v-model="password" autocomplete="off"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="update">提交</el-button>
      </el-form-item>
  </div>
<!--  </div>-->
</template>

<script>
export default {
  name: "countcontrol",
  data() {
    var checkAge = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('校验码不能为空'));
      }
      setTimeout(() => {
        if ((value)!=="yuanchu666") {
          callback(new Error('校验码错误'));
        } else {

          callback();

        }
      }, 1000);
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if(value.length<=6){
          callback(new Error("密码长度不能低于6位"))
        }
        if (this.ruleForm.checkPass !== '' ) {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {

      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        verify: [
          { validator: checkAge, trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
}
</script>

<script setup>
import { ref } from  'vue'
import request from '../../http/Request.js'
import useUser from "../../http/stores/user.js"
import {ElMessage} from "element-plus";
const user=useUser().user
const password=ref()
const update=()=>{
  request.post("/user/updatePassword",{id:user.id,password:password.value})
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


</style>
