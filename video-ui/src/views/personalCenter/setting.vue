<template>
  <!--  <a-layout class="logincontent">
      <a-form
          class="loginform"
          :model="user"
          :label-col="labelCol"
          :wrapper-col="wrapperCol">-->

  <div>
    <el-container style="height: 600px; border: 1px solid #eee">
      <el-aside width="200px" style="background-color: white">
        <el-menu :default-openeds="['1']">
          <template slot="title">
            <i class="el-icon-postcard"></i>信息修改
          </template>
          <el-menu-item-group>

            <router-link class="alink" to="/showinfo">
              <el-menu-item index="1-1">
                基本信息
              </el-menu-item>

            </router-link>

            <router-link class="alink" to="/infoeditor">
              <el-menu-item index="1-2">
                信息修改
              </el-menu-item>
            </router-link>

<!--
            <router-link class="alink" to="/imageUp">
              <el-menu-item index="1-3">

                头像修改

              </el-menu-item>
            </router-link>
-->

            <router-link class="alink" to="/countcontrol">
              <el-menu-item index="1-4">

                账号管理

              </el-menu-item>
            </router-link>
          </el-menu-item-group>


        </el-menu>
      </el-aside>


      <el-container


      >
        <el-main>
          <div style="width: 80%;margin: 0 auto">
            <router-view></router-view>
          </div>
        </el-main>
      </el-container>

    </el-container>

  </div>
  <!--    </a-form>
    </a-layout>-->
</template>

<script>
export default {
  name: "myspace",
  data() {

    return {}
  },
}
</script>


<style scoped>
.logincontent {
  display: flex;
  flex-direction: column;
  background-image: url("../img/3.jpeg");
  background-size: 100% 100%;
  background-attachment: fixed;

}

.el-header {
  background-color: #e5efe2;
  color: #333;
  line-height: 60px;
}

.el-aside {
  color: #333;
}

.router-link-active {
  text-decoration: none;
}

.alink {
  text-decoration: none;
}

.background {
  width: 100%;
  height: 100%; /**宽高100%是为了图片铺满屏幕 */
  z-index: -1;
  position: absolute;
}

.front {
  z-index: 1;
  position: absolute;
}


</style>














