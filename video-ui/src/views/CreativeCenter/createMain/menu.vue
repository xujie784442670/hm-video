<template>

  <el-row class="tac">
    <el-col :span="12">




      <el-menu
          :default-active="itemMenu"
          class="el-menu-vertical-demo"
          :router="true"

      >
        <el-menu-item index="fileUpload">
        <el-button   style="background: deepskyblue;width:100px;color: #f8f8f8;text-align: center;position: relative;left: 10px;padding: 10px;margin: 10px; " type="primary" plain>
          <el-icon class="el-icon--right"><Upload /></el-icon>
          &nbsp;投稿
        </el-button>
        </el-menu-item>


        <el-menu-item index="contManage">
          <el-icon><VideoPlay/></el-icon>
          <span>内容管理</span>
        </el-menu-item>

          <el-sub-menu index="2">
            <template #title>

              <el-icon><ChatDotRound /></el-icon>
              <span>互动管理</span>
            </template>
            <el-menu-item-group>
              <el-menu-item index="commentMa">评论管理</el-menu-item>
              <el-menu-item index="barrageManagement">弹幕管理</el-menu-item>
            </el-menu-item-group>

          </el-sub-menu>

        <el-menu-item index="main">
          <el-icon><DataLine/></el-icon>
          <span>数据中心</span>
        </el-menu-item>

      </el-menu>
    </el-col>
  </el-row>

</template>

<script>
export default {
  name: "createMenu"
}

</script>
<script  setup>
import {
  ChatDotRound,
  DataLine,
  VideoPlay,
  Upload,
  House

} from '@element-plus/icons-vue'



</script>

<style scoped>
.el-col-12 {
  max-width: 100%;
  flex: 0 0 100%;
}
.ic{
  height: 2em;
  width: 3em;
}
#das{
  width: 120px;
  height: 80px;
  line-height: 50px;
  background: aqua;
  cursor: pointer;
  position: relative;
  left: 72px;
}
.element.style {
  height: 30px;
  align-items: center;
  vertical-align: middle;
}

</style>