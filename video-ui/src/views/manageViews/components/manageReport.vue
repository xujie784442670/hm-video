<template>
    <div class="body">
        <!--搜索区域-->
        <div class="search">
            <el-row>
                <el-col :span="24">
                    <el-form label-suffix=":" inline @keydown="enter">
                        <el-form-item label="举报人">
                            <el-input v-model="formData.nickName" placeholder="请输入举报人"/>
                        </el-form-item>
                        <el-form-item label="视频类型">
                            <el-select v-model="formData.typeId" placeholder="请选择被举报视频类型" clearable>
                                <el-option v-for="item in typeList" :key="item.id" :label="item.type" :value="item.id"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="视频标题">
                            <el-input v-model="formData.title" placeholder="请输入被举报视频标题"/>
                        </el-form-item>
                        <el-form-item label="举报状态">
                            <el-select v-model="formData.status" placeholder="请选择举报状态" clearable>
                                <el-option v-for="item in statusList" :key="item.value" :label="item.label"
                                           :value="item.value"/>
                            </el-select>
                        </el-form-item>
                        <el-form-item label="举报时间">
                            <el-date-picker
                                v-model="formData.createTimes"
                                type="daterange"
                                range-separator="至"
                                start-placeholder="开始时间"
                                end-placeholder="结束时间"
                            />
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
                    v-for="item in reports"
                    border
                >
                    <el-descriptions-item label="举报人" :width="200" :min-width="200" align="center">
                        {{ item.nickName }}
                    </el-descriptions-item>
                    <el-descriptions-item label="被举报视频标题" :width="200" :min-width="200" align="center">
                        《{{ item.title }}》
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
                    <el-descriptions-item label="被举报视屏类型" :width="200" :min-width="200" align="center">
                        <el-tag>{{ item.videoType }}</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="举报时间" :width="200" :min-width="200" align="center">
                        {{ item.createTime }}
                    </el-descriptions-item>
                    <el-descriptions-item label="视频内容" align="center">
                        <video :src="item.videoUrl" controls/>
                    </el-descriptions-item>
                    <el-descriptions-item label="举报状态" align="center">
                        <el-tag v-if="item.status === 0">审核中...</el-tag>
                        <el-tag type="success" v-if="item.status === 1">已通过</el-tag>
                        <el-tag type="danger" v-if="item.status === 2">未通过</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="举报内容" label-align="center">
                        {{ item.content }}
                    </el-descriptions-item>
                    <template #title>
                        <div class="emptyDiv"></div>
                        <el-tag size="large">举报</el-tag>
                    </template>
                    <template #extra v-if="item.status !== 2">
                        <div class="emptyDiv"></div>
                        <el-popconfirm
                            confirm-button-type="danger"
                            confirm-button-text="账号封禁"
                            cancel-button-type="danger"
                            cancel-button-text="视频下架"
                            hide-icon
                            @confirm="clickBanned"
                            @cancel="clickDown"
                            v-if="item.accountLogicDelete===0 || item.videoLogicDelete === 0"
                            :width="190"
                        >
                            <template #reference>
                                <el-button type="success" @click="clickPass(item)">
                                    <el-icon>
                                        <Finished/>
                                    </el-icon>
                                    通过
                                </el-button>
                            </template>
                        </el-popconfirm>
                        <el-button type="danger" @click="clickBack(item)" v-if="item.status === 0">
                            <el-icon>
                                <CloseBold/>
                            </el-icon>
                            打回
                        </el-button>
                    </template>
                </el-descriptions>
                <!--打回举报-->
                <el-dialog
                    v-model="confirmBack"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否打回该举报？</h3>
                    <template #footer>
                        <el-button type="primary" @click="confirmBack = false" style="margin-right: 10px">
                            取消
                        </el-button>
                        <el-button type="danger" @click="back">
                            确认
                        </el-button>
                    </template>
                </el-dialog>
                <!--封禁帐号-->
                <el-dialog
                    v-model="confirmBan"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否封禁该视频上传者的账号？</h3>
                    <template #footer>
                        <el-button type="primary" @click="confirmBan = false" style="margin-right: 10px">
                            取消
                        </el-button>
                        <el-button type="danger" @click="banned">
                            确认
                        </el-button>
                    </template>
                </el-dialog>
                <!--视频下架-->
                <el-dialog
                    v-model="confirmDown"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否下架该举报视频？</h3>
                    <template #footer>
                        <el-button type="primary" @click="confirmDown = false" style="margin-right: 10px">
                            取消
                        </el-button>
                        <el-button type="danger" @click="down">
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
                    :total="pageInfo.total"
                    @size-change="reload"
                    @current-change="reload"
                />
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
    import { Finished, CloseBold } from '@element-plus/icons-vue'
    import {
        getReportManageVo,
        findVideoTypes,
        updateReportStatus,
        logicDeleteAccount,
        logicDeleteVideo,
    } from '../Controller.js'
    import { ElMessage } from 'element-plus'

    /*region 显示区域*/
    //视频信息
    const reports = ref([])
    //表单是否有数据
    const hasData = ref(false)
    //本页信息查询函数
    const findData = () => {
        const queryData = formData.value
        const pageData = pageInfo.value
        getReportManageVo({ queryData, pageData }).then(rs => {
            if (rs.data.code === 0) {
                pageInfo.value.page = rs.data.page
                pageInfo.value.pageSize = rs.data.pageSize
                pageInfo.value.total = rs.data.total
                reports.value = rs.data.data
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
            hasData.value = !reports.value[0];
        })
    }
    //当分页情况发生改变时的重载数据函数
    const reload = () => {
        findData()
    }
    //当前的举报对象
    const currentItem = ref({})
    //确认打回窗口
    const confirmBack = ref(false)
    //点击打回按钮展开对话框
    const clickBack = (item) => {
        currentItem.value = item
        confirmBack.value = true
    }
    //点击确认打回按钮事件
    const back = () => {
        updateReportStatus(currentItem.value.id, 2).then(rs => {
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
    //点击通过按钮触发事件
    const clickPass = (item) => {
        currentItem.value = item
    }
    //确认封禁窗口
    const confirmBan = ref(false)
    //点击账号封禁按钮展开对话框
    const clickBanned = () => {
        if (currentItem.value.accountLogicDelete === 1) {
            ElMessage.warning({
                message: '该账号已被封禁！',
                grouping: true,
            })
        } else {
            confirmBan.value = true
        }
    }
    //封号按钮
    const banned = () => {
        logicDeleteAccount(currentItem.value.accountId).then(rs => {
            updateReportStatus(currentItem.value.id, 1).then(() => {
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
            })
        }).finally(() => {
            confirmBan.value = false
            findData()
        })
    }
    //确认下架窗口
    const confirmDown = ref(false)
    //点击视频下架按钮展开对话框
    const clickDown = () => {
        if (currentItem.value.videoLogicDelete === 1) {
            ElMessage.warning({
                message: '该视频已下架！',
                grouping: true,
            })
        } else {
            confirmDown.value = true
        }
    }
    //视频下架按钮
    const down = () => {
        logicDeleteVideo(currentItem.value.videoId).then(rs => {
            updateReportStatus(currentItem.value.id, 1).then(() => {
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
            })
        }).finally(() => {
            confirmDown.value = false;
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
    //视频类型数组
    const typeList = ref([])
    const findTypeList = () => {
        findVideoTypes().then(rs => {
            typeList.value = rs.data.data
        })
    }
    //状态列表
    const statusList = ref([
        { label: '审核中', value: 0 },
        { label: '已通过', value: 1 },
        { label: '未通过', value: 2 },
    ])
    //表单查询对象
    const formData = ref({})
    //查询按钮
    const query = () => {
        findData()
    }
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

    //加载时查询数据
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