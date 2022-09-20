<template>
  <div class="common-layout">
    <el-container class="el-container">
      <el-header >
        <div class="div-header">

          <span id="zi"><router-link to="/home">创作中心</router-link></span>

          <el-icon><router-link to="/home">
            <House style="position: relative;left: 1300px;top: 15px"/>
                    </router-link>
          </el-icon>
        </div>

      </el-header>
      <el-container>
        <el-aside width="200px" class="dashed">
          <create-menu/>
        </el-aside>

        <el-main class="dashed">
          <router-view/></el-main>

      </el-container>
    </el-container>
  </div>
</template>

<script setup>
import CreateMenu from "./createMain/menu.vue";
import {
  House
} from '@element-plus/icons-vue'

import { onMounted, ref } from 'vue';
import {useRoute, useRouter} from 'vue-router';
const router = useRouter()
const route = useRoute()

const itemMenu = ref('')
onMounted(() => {
  if (route.name === 'create') {
    router.replace("/contManage")
  }
  itemMenu.value = route.name
})
</script>


<style scoped>
 .dashed {
  border-top: 1px solid  var(--el-border-color);
}
.el-container{
  height: 715px;
}
 .el-aside{
   background-color: #ffffff;
 }
 .create-menu{
   width: 200px;
 }
.el-main{
  background-color: #e9ebf5;
}
#zi{
  position: relative;
  top: 13px;
}
.el-header{
  background-color: #80f1f1;
  color: #000005;
  font-size: 30px;
 font-weight: bold;
  font-family: 华文新魏;
}
a{
  text-decoration: none;

}
 .el-header img{
   vertical-align: middle;
   text-align:center;
   width: 60px;
   height: 60px;
   margin-right: 20px;
 }

</style>