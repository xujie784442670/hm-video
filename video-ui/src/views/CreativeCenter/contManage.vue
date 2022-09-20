<template>

    <div id="top-a" style="background-color: white;height: 59px;border-bottom: 1px solid rgb(230,220,224);">
        <span id="gg">视 频 管 理</span>
    </div>
    <el-input
        v-model="s"
        class="w-50 m-2"
        placeholder="搜索稿件"
        :suffix-icon="Search"
        style="width: 160px;height: 30px;z-index: 1;position: absolute;right: 100px;top:95px"
        @keyup.enter="searchVideo(s)"
    />
    <div style="height: 550px;    background: white; padding: 0;scroll-behavior: smooth;">
        <el-menu
            :default-active="activeIndex"
            class="el-menu-demo"
            mode="horizontal"
        >
            <el-menu-item index="1" class="e1" style="padding-right: 1px " @click="videosCopy=videos">
                <a>所有视频投稿&emsp;{{ count }}</a></el-menu-item>
            <el-menu-item index="2" class="e3" @click="videosCopy=videos.filter((item)=>{
        if(item.status === 1){return item}
    })"><a>已通过&emsp;{{ count2 }}</a></el-menu-item>
            <el-menu-item index="3" class="e4" @click="videosCopy=videos.filter((item)=>{
        if(item.status === 2){return item}
    })"><a>未通过&emsp;{{ count4 }}</a></el-menu-item>
            <el-menu-item index="3" class="e4" @click="fitl1"><a>进行中&emsp;{{ count1 }}</a></el-menu-item>
            <el-menu-item index="4" class="e4" @click="getLogic"><a>下架&emsp;{{ count3 }}</a></el-menu-item>

            <!--<el-select class="el-input__wrapper" v-model="value" clearable placeholder="全部分类">-->
            <!--  <el-option-->
            <!--      v-for="item in options"-->
            <!--      :key="item.value"-->
            <!--      :label="item.label"-->
            <!--      :value="item.value"-->
            <!--  />-->
            <!--</el-select>-->

            <!--<el-select  class="el-input__wrapper1"  v-model="value1" clearable placeholder="评论数排序">-->
            <!--  <el-option-->
            <!--      v-for="item in options1"-->
            <!--      :key="item.value1"-->
            <!--      :label="item.label1"-->
            <!--      :value="item.value1"-->
            <!--  />-->
            <!--</el-select>-->
        </el-menu>
        <div class="showMain">
            <ul>
                <li v-for="item in videosCopy">
                    <img :src="item.coverUrl" height="200">
                    <div class="info">
                        <span><b>{{ item.title }}</b></span>
                        <span class="icons">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-shipin"></use>
              </svg>
              {{ item.views }}
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-dianzan"></use>
              </svg>
              {{ item.likes }}
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-shoucang"></use>
              </svg>
              {{ item.collections }}
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-pinglun"></use>
              </svg>
              {{ item.comments }}
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-danmu"></use>
              </svg>
              {{ item.barrages }}
          </span>
                    </div>
                    <div class="opti">
                        <el-button type="primary" @click="giveTitle(item)">修改标题</el-button>
                        <el-button type="danger" @click="giveDelVideo(item)">删除</el-button>
                    </div>
                </li>
            </ul>
            <el-dialog v-model="editDialogVisible" title="Shipping address">
                <el-form-item label="修改标题">
                    <el-input v-model="title" autocomplete="off"/>
                </el-form-item>
                <template #footer>
      <span class="dialog-footer">
        <el-button @click="editDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="updateTitle"
        >确定</el-button
        >
      </span>
                </template>
            </el-dialog>
            <el-dialog v-model="delDialogVisible" title="Shipping address">
                <span>确定删除该视频吗?</span>
                <template #footer>
      <span class="dialog-footer">
        <el-button @click="delDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="delVideo"
        >确定</el-button
        >
      </span>
                </template>
            </el-dialog>
        </div>
    </div>
</template>

<script>
    export default {
        name: "contentManagement",
    }

</script>
<script setup>
    import { Search } from '@element-plus/icons-vue'
    import { onMounted, ref } from 'vue'
    import request from "../../http/Request.js";
    import useUser from "../../http/stores/user.js"
    import { ElMessage } from "element-plus";

    const user = useUser().user
    const value = ref('')
    const value1 = ref('')
    const video = ref()
    const title = ref()
    const s = ref()
    const delId = ref()
    const delDialogVisible = ref(false)
    const options = [
        {
            value: '游戏',
            label: '游戏',
        },
        {
            value: '生活',
            label: '生活',
        },
        {
            value: '动画',
            label: '动画',
        },
        {
            value: '音乐',
            label: '音乐',
        },
        {
            value: '科技',
            label: '科技',
        },
    ]
    const options1 = [
        {
            value1: '投稿时间排序',
            label1: '投稿时间排序',
        },
        {
            value1: '弹幕排序',
            label1: '弹幕排序',
        },
        {
            value1: '评论数排序',
            label1: '评论数排序',
        },

    ]
    const editDialogVisible = ref(false)
    const videos = ref()
    let videosCopy = ref()
    const count = ref(0)
    const count1 = ref(0)
    const count2 = ref(0)
    const count3 = ref(0)
    const count4 = ref(0)
    request.get("/video/findVideosByAccountId?id=" + user.id).then(rs => {
        count.value = rs.data.data.length
        for (let i = 0; i < count.value; i++) {
            if (rs.data.data[i].status === 1 && rs.data.data[i].logicalDelete===0) {
                count2.value++
            } else if (rs.data.data[i].status === 0 && rs.data.data[i].logicalDelete === 0) {
                count1.value++
            } else if (rs.data.data[i].status === 2) {
                count4.value++
            }
            if (rs.data.data[i].logicalDelete === 1) {
                console.log(rs.data.data[i])
                count3.value++
            }
        }
        videos.value = rs.data.data
        videosCopy.value = videos.value
    })
    const searchVideo = (s) => {
        videosCopy.value = videos.value.filter((item) => {
            if (item.title.includes(s)) {
                return item
            }
        })
    }
    const filt1=()=>{
      videosCopy=videos.filter((item)=>{
        if(item.status === 0 && item.logicalDelete=== 0){return item}
      })
    }
    const getLogic = () => {
        console.log(1)
        videosCopy.value = videos.value.filter((item) => {
            if (item.logicalDelete === 1) {return item}
        })
    }
    const giveTitle = (item) => {
        editDialogVisible.value = true
        video.value = item
    }
    const giveDelVideo = (item) => {
        delDialogVisible.value = true
        delId.value = item.id
    }
    const updateTitle = () => {
        editDialogVisible.value = false
        const vdd = {
            id: video.value.id,
            title: title.value,
        }
        request.post("/video/updateTitle", vdd).then(rs => {
            console.log(rs)
            if (rs.data.code === 0) {
                ElMessage.success(rs.data.message)
            } else {
                ElMessage.error(rs.data.message)
            }
            title.value = ''
            request.get("/video/findVideosByAccountId?id=" + user.id).then(rs => {
                count.value = rs.data.data.length
                videos.value = rs.data.data
            })
        })
    }
    const delVideo = () => {
        delDialogVisible.value = false
        request.get("/video/logicalDel?id=" + delId.value).then(rs => {
            if (rs.data.code === 0) {
                ElMessage.success(rs.data.msg)
            } else {
                ElMessage.error(rs.data.msg)
            }
            delId.value = ''
            request.get("/video/findVideosByAccountId?id=" + user.id).then(rs => {
                count.value = rs.data.data.length
                count1.value = 0
                count2.value = 0
                count3.value = 0
                count4.value = 0
                for (let i = 0; i < count.value; i++) {
                    console.log(rs.data.data[i])
                    if (rs.data.data[i].status === 1) {
                        count2.value++
                    } else if (rs.data.data[i].status === 0) {
                        count1.value++
                    } else if (rs.data.data[i].status === 2) {
                        count4.value++
                    }
                    if (rs.data.data[i].logicalDelete === 1) {
                        count3.value++
                    }
                }
                videos.value = rs.data.data
                videosCopy.value = videos.value
            })
      })
}
</script>
<style scoped>
    .el-input__wrapper {

        padding: 0;
        width: 130px;
        height: 30px;
        position: absolute;
        left: 700px;
        top: 10px;

    }

    .el-input__wrapper1 {

        width: 130px;
        height: 30px;
        position: absolute;
        left: 900px;
        top: 10px;
    }

    a {
        text-decoration: none;

    }

    #gg {
        color: #3dc537;
        justify-content: center;
        background-color: white;
        border-bottom: 1px solid white;
        height: 100%;
        width: 59px;
        font-family: 华文新魏;
        font-size: 35px;
        text-align: center;
        margin-left: 370px;
        margin-right: 440px;
        margin-top: 20px;
    }

    .showMain {
        width: 100%;
        margin-top: 20px;
        overflow-y: auto;
        height: 450px;;
    }

    .showMain ul, .showMain li {
        list-style: none;
        padding-right: 10px;
    }

    .showMain ul li {
        display: flex;
        border: #E5E9EF 1px solid;
        width: 100%;
        height: 200px;
        /*float:left;*/
        margin-top: 15px;
        margin-right: 10px;
    }

    .showMain ul li div span {
        margin-top: 20px;
        margin-left: 20px;
    }

    .info {
        display: flex;
        flex-direction: column;
    }

    .showMain ul li .icons {
        display: flex;
        position: relative;
        width: 100%;
        margin-top: 130px;
    }

    .icons svg {
        padding-left: 10px;
    }

    .opti {
        display: flex;
        position: relative;
        margin-left: 650px;
        right: 100px;
        flex-direction: column;
    }

    .opti button {
        margin: 20px;
    }
</style>