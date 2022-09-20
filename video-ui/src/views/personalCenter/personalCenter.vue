<template>
  <el-menu
      default-active="setting"
      default-openeds='setting'
      class="el-menu-demo"
      mode="horizontal"
      router
      @select="handleSelect"
  >
    <el-menu-item index="home">主页</el-menu-item>
    <el-menu-item index="setting">设置</el-menu-item>
    <el-menu-item index="importantPerson">我的粉丝</el-menu-item>
    <el-menu-item index="importantPerson2">我的关注</el-menu-item>
    <el-menu-item index="information">消息</el-menu-item>
<!--    <el-menu-item index="collect">收藏</el-menu-item>-->
  </el-menu>
  <div class="h-6" />
  <el-container>
  <el-main>
    <router-view></router-view>
  </el-main>
  </el-container>
</template>

<script>
export default {
  name: "personalCenter"
}
</script>
<script setup>
import { ref } from 'vue'

const activeIndex = ref('7')

</script>
<style scoped>

</style>