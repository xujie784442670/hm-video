<template>
    <div class="body">
        <!--搜索区域-->
        <div class="search">
            <el-row>
                <el-col :span="24">
                    <el-form label-suffix=":" inline @keydown="enter">
                        <el-form-item label="视频标题">
                            <el-input v-model="formData.title" placeholder="请输入视频标题"/>
                        </el-form-item>
                        <el-form-item label="视频类型">
                            <el-select v-model="formData.typeId" placeholder="请选择视频类型" clearable>
                                <el-option v-for="item in typeList" :key="item.id" :label="item.type" :value="item.id"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="视频发布者">
                            <el-input v-model="formData.nickName" placeholder="请输入视频发布者"/>
                        </el-form-item>
                        <el-form-item label="视频状态">
                            <el-select v-model="formData.state" placeholder="请选择视频状态" clearable>
                                <el-option v-for="item in stateList" :key="item.value" :label="item.label"
                                           :value="item.value"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="视频创建时间">
                            <el-date-picker
                                v-model="formData.createTimes"
                                type="daterange"
                                range-separator="至"
                                start-placeholder="开始时间"
                                end-placeholder="结束时间"/>
                        </el-form-item>
                        <el-form-item>
                            <el-button type="danger" @click="reset">重置</el-button>
                            <el-button type="primary" @click="query">查询</el-button>
                        </el-form-item>
                    </el-form>
                </el-col>
            </el-row>
        </div>
        <!--信息展示区域-->
        <el-scrollbar>
            <div class="show">
                <h1 v-show="hasData" style="text-align: center">暂无数据</h1>
                <el-descriptions
                    v-for="item in videos"
                    border
                >
                    <el-descriptions-item label="视频标题" :width="200" :min-width="200" align="center">
                        {{ item.title }}
                    </el-descriptions-item>
                    <el-descriptions-item label="视屏类型" :width="200" :min-width="200" align="center">
                        {{ item.videoType }}
                    </el-descriptions-item>
                    <el-descriptions-item label="视频封面" align="center">
                        <el-image
                            :src="item.coverUrl"
                            :alt="item.title"
                            loading="lazy"
                            fit="contain"
                            hide-on-click-modal
                            :preview-src-list="[item.coverUrl]"/>
                    </el-descriptions-item>
                    <el-descriptions-item label="视频发布者" :width="200" :min-width="200" align="center">
                        {{ item.nickName }}
                    </el-descriptions-item>
                    <el-descriptions-item label="视频创建时间" :width="200" :min-width="200" align="center">
                        {{ item.createTime }}
                    </el-descriptions-item>
                    <el-descriptions-item label="视频内容" align="center">
                        <video :src="item.videoUrl" controls/>
                    </el-descriptions-item>
                    <el-descriptions-item label="视频状态" align="center">
                        <el-tag v-if="item.status === 0 && item.logicDelete === 0">审核中...</el-tag>
                        <el-tag type="success" v-if="item.status === 1 && item.logicDelete === 0">已通过审核</el-tag>
                        <el-tag type="danger" v-if="item.status === 2 && item.logicDelete === 0">未通过审核</el-tag>
                        <el-tag type="danger" v-if="item.logicDelete === 1">已下架</el-tag>
                    </el-descriptions-item>
                    <template #title>
                        <div class="emptyDiv"></div>
                        <el-tag size="large">{{ item.videoType }}视频</el-tag>
                    </template>
                    <template #extra>
                        <div class="emptyDiv"></div>
                        <el-popconfirm
                            confirm-button-type="success"
                            confirm-button-text="通过"
                            cancel-button-type="danger"
                            cancel-button-text="打回"
                            hide-icon
                            v-if="item.status === 0 && item.logicDelete === 0"
                            @confirm="clickPass(item)"
                            @cancel="clickBack(item)"
                        >
                            <template #reference>
                                <el-button type="primary">
                                    <el-icon>
                                        <EditPen/>
                                    </el-icon>
                                    审核
                                </el-button>
                            </template>
                        </el-popconfirm>
                        <el-button type="danger" @click="clickDown(item)"
                                   v-if="item.status===1 && item.logicDelete===0">
                            <el-icon>
                                <Delete/>
                            </el-icon>
                            下架
                        </el-button>
                    </template>
                </el-descriptions>
                <!--下架视频-->
                <el-dialog
                    v-model="confirmDown"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否下架该视频？</h3>
                    <template #footer>
                        <el-button type="primary" @click="confirmDown = false" style="margin-right: 10px">
                            取消
                        </el-button>
                        <el-button type="danger" @click="down">
                            确认
                        </el-button>
                    </template>
                </el-dialog>
                <!--审核通过-->
                <el-dialog
                    v-model="confirmPass"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否通过该申请？</h3>
                    <template #footer>
                        <el-button type="primary" @click="confirmPass = false" style="margin-right: 10px">
                            取消
                        </el-button>
                        <el-button type="danger" @click="pass">
                            确认
                        </el-button>
                    </template>
                </el-dialog>
                <!--审核打回-->
                <el-dialog
                    v-model="confirmBack"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否打回该申请？</h3>
                    <template #footer>
                        <el-button type="primary" @click="confirmBack = false" style="margin-right: 10px">
                            取消
                        </el-button>
                        <el-button type="danger" @click="back">
                            确认
                        </el-button>
                    </template>
                </el-dialog>
            </div>
        </el-scrollbar>
        <!--分页区域-->
        <el-row class="pagination">
            <el-col :span="24">
                <el-pagination
                    layout="total ,prev, pager, next, sizes ,jumper"
                    background
                    :pager-count="5"
                    :page-sizes="[10,20,30,40,50]"
                    v-model:current-page="pageInfo.page"
                    v-model:page-size="pageInfo.pageSize"
                    :total="pageInfo.total"/>
            </el-col>
        </el-row>
    </div>
</template>

<script>
    export default {
        name: "manageVideo",
    }
</script>

<script setup>
    import { onMounted, ref } from 'vue'
    import { EditPen, Delete } from '@element-plus/icons-vue'
    import { findVideoTypes, getVideoManageVo, logicDeleteVideo, updateVideoStatus } from '../Controller.js'
    import { ElMessage } from 'element-plus'

    /*region 显示区域*/
    //视频信息
    const videos = ref([])
    //本页信息查询函数
    const findData = () => {
        const queryData = formData.value
        const pageData = pageInfo.value
        getVideoManageVo({ queryData, pageData }).then(rs => {
            if (rs.data.code === 0) {
                pageInfo.value.page = rs.data.page
                pageInfo.value.pageSize = rs.data.pageSize
                pageInfo.value.total = rs.data.total
                videos.value = rs.data.data
            } else if (rs.data.code === 1) {
                ElMessage.error({
                    message: rs.data.message,
                    grouping: true,
                })
            }
        }).catch((e) => {
            ElMessage.error({
                message: e.message,
                grouping: true,
            })
        }).finally(() => {
            hasData.value = !videos.value[0];
        })
    }
    //当前的视频对象
    const currentItem = ref({})
    //控制通过窗口的显示
    const confirmPass = ref(false)
    //点击通过按钮触发事件
    const clickPass = (item) => {
        currentItem.value = item
        confirmPass.value = true
    }
    //审核通过按钮
    const pass = () => {
        updateVideoStatus(currentItem.value.id, 1).then(rs => {
            if (rs.data.code === 0) {
                ElMessage.success({
                    message: rs.data.message,
                    grouping: true,
                })
            } else if (rs.data.code === 1) {
                ElMessage.error({
                    message: rs.data.message,
                    grouping: true,
                })
            }
        }).finally(() => {
            confirmPass.value = false
            findData()
        })
    }
    //控制打回窗口的显示
    const confirmBack = ref(false)
    //点击打回按钮触发事件
    const clickBack = (item) => {
        currentItem.value = item
        confirmBack.value = true
    }
    //审核打回按钮
    const back = () => {
        updateVideoStatus(currentItem.value.id, 2).then(rs => {
            if (rs.data.code === 0) {
                ElMessage.success({
                    message: rs.data.message,
                    grouping: true,
                })
            } else if (rs.data.code === 1) {
                ElMessage.error({
                    message: rs.data.message,
                    grouping: true,
                })
            }
        }).finally(() => {
            confirmBack.value = false
            findData()
        })
    }
    //控制下架窗口的显示
    const confirmDown = ref(false)
    //点击下架按钮触发事件
    const clickDown = (item) => {
        currentItem.value = item
        confirmDown.value = true
    }
    //下架按钮
    const down = () => {
        logicDeleteVideo(currentItem.value.id).then(rs => {
            if (rs.data.code === 0) {
                ElMessage.success({
                    message: rs.data.message,
                    grouping: true,
                })
            } else if (rs.data.code === 1) {
                ElMessage.error({
                    message: rs.data.message,
                    grouping: true,
                })
            }
        }).finally(() => {
            confirmDown.value = false
            findData()
        })
    }
    /*endregion*/

    /*region 分页区域*/
    //分页信息
    const pageInfo = ref({
        page: 1,
        pageSize: 10,
        total: 0,
    })
    /*endregion*/

    /*region 查询区域*/
    //表单是否有数据
    const hasData = ref(false)
    //视频类型数组
    const typeList = ref([])
    const findTypeList = () => {
        findVideoTypes().then(rs => {
            typeList.value = rs.data.data
        })
    }
    //视频状态数组
    const stateList = [
        {
            label: '审核中',
            value: 0,
        },
        {
            label: '已通过审核',
            value: 1,
        },
        {
            label: '未通过审核',
            value: 2,
        },
        {
            label: '已下架',
            value: 3,
        },
    ]
    //表单查询对象
    const formData = ref({})
    //查询按钮
    const query = () => { findData() }
    //回车键
    const enter = (e) => {
        if (e.keyCode === 13) {
            query()
        }
    }
    //重置按钮点击事件
    const reset = () => {
        formData.value = []
        findData()
    }
    /*endregion*/
    onMounted(() => {
        findData()
        findTypeList()
    })


</script>

<style scoped>
    /*占位div样式*/
    .emptyDiv {
        height: 10px
    }

    .el-scrollbar {
        height: 510px;
    }

    /*信息展示区域样式*/
    .show {
        width: calc(100% - 10px);
    }

    /*信息展示区域隐藏滚动条*/
    .show::-webkit-scrollbar {
        display: none;
    }

    /*限制图片大小*/
    .el-image {
        width: 250px;
        height: 150px;
    }

    /*限制视频大小*/
    video {
        width: 250px;
        height: 150px;
    }

    /*设置每两条信息之间的间隔*/
    .el-descriptions {
        margin-top: 10px;
        margin-bottom: 10px;
    }

    /*动画*/
    .el-descriptions:hover {
        cursor: pointer;
        box-shadow: 0 0 10px #cccccc;
    }

    .el-button {
        color: black;
    }

    .el-button:hover {
        box-shadow: 0 0 5px #cccccc;
    }

    /*分页区域样式*/
    .pagination {
        margin-top: 15px;
    }

</style>