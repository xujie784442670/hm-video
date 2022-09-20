<template>
    <div class="body">
        <!--搜索区域-->
        <div class="search">
            <el-row>
                <el-col :span="24">
                    <el-form label-suffix=":" inline @keydown="enter">
                        <el-form-item label="广告发送者">
                            <el-input v-model="formData.nickName" placeholder="请输入发送者"/>
                        </el-form-item>
                        <el-form-item label="广告发送者">
                            <el-select v-model="formData.status" placeholder="请选择广告状态" clearable>
                                <el-option v-for="item in adStatus" :label="item.label" :value="item.value"/>
                            </el-select>
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
                    v-for="item in advertises"
                    :column="2"
                    border
                >
                    <el-descriptions-item label="广告发送者" :width="200" :min-width="200" align="center">
                        {{ item.nickName }}
                    </el-descriptions-item>
                    <el-descriptions-item label="广告海报" align="center">
                        <el-image
                            :src="item.posterUrl"
                            :alt="'海报'"
                            loading="lazy"
                            fit="contain"
                            hide-on-click-modal
                            :preview-src-list="[item.posterUrl]"/>
                    </el-descriptions-item>
                    <el-descriptions-item label="广告状态" :width="200" :min-width="200" align="center">
                        <el-tag type="success" v-if="item.status===0">正在使用</el-tag>
                        <el-tag type="danger" v-if="item.status===1">已下架</el-tag>
                    </el-descriptions-item>
                    <el-descriptions-item label="广告内容" align="center">
                        <video v-if="item.url" :src="item.url" controls/>
                    </el-descriptions-item>
                    <template #title>
                        <div class="emptyDiv"></div>
                        <el-tag size="large">广告</el-tag>
                    </template>
                    <template #extra v-if="item.status===0">
                        <div class="emptyDiv"></div>
                        <el-button type="danger" @click="clickDown(item)">
                            <el-icon>
                                <Delete/>
                            </el-icon>
                            下架
                        </el-button>
                    </template>
                </el-descriptions>
                <el-dialog
                    v-model="confirmDown"
                    title="系统消息"
                    width="30%"
                    destroy-on-close
                    center
                >
                    <h3 style="text-align: center;color: red">是否下架该广告？</h3>
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
            <el-col :span="22">
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
            <el-button type="success" @click="clickAdd">
                <el-icon>
                    <Plus/>
                </el-icon>
                新增广告
            </el-button>
            <el-dialog
                v-model="confirmAdd"
                title="新增广告"
                width="30vw"
                destroy-on-close
                center
            >
                <div class="add-body">
                    <el-form label-suffix=":">
                        <el-form-item label="请上传广告海报">
                            <input type="file" id="file" class="add"/>
                        </el-form-item>
                        <el-form-item label="请输入广告外链">
                            <el-input class="add" v-model="url" placeholder="请输入广告外链"/>
                        </el-form-item>
                    </el-form>
                </div>
                <template #footer>
                    <el-button type="primary" @click="cancel" style="margin-right: 10px">
                        取消
                    </el-button>
                    <el-button type="danger" @click="addAdvertise">
                        确认
                    </el-button>
                </template>
            </el-dialog>
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
    import { Plus, Delete } from '@element-plus/icons-vue'
    import { addNewAd, getAdvertiseManageVo, logicDeleteAdvertise } from '../Controller.js'
    import { ElMessage } from 'element-plus'
    import useUser from '../../../http/stores/user.js'

    /*region 查询区域*/
    //广告状态
    const adStatus = ref([
        {
            label: '正在使用',
            value: 0,
        },
        {
            label: '已下架',
            value: 1,
        },
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

    /*region 显示区域*/
    //广告信息
    const advertises = ref([])
    //表格是否有数据
    const hasData = ref(false)
    //本页信息查询函数
    const findData = () => {
        const queryData = formData.value
        const pageData = pageInfo.value
        getAdvertiseManageVo({ queryData, pageData }).then(rs => {
            if (rs.data.code === 0) {
                pageInfo.value.page = rs.data.page
                pageInfo.value.pageSize = rs.data.pageSize
                pageInfo.value.total = rs.data.total
                advertises.value = rs.data.data
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
            hasData.value = !advertises.value[0];
        })
    }
    //确认下架窗口
    const confirmDown = ref(false)
    //需要下架的广告
    const currentItem = ref({})
    //点击下架按钮
    const clickDown = (item) => {
        currentItem.value = item
        confirmDown.value = true
    }
    //下架确认按钮
    const down = () => {
        logicDeleteAdvertise(currentItem.value.id).then(rs => {
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
        }).catch((e) => {
            ElMessage.error({
                message: e.data.message,
                grouping: true,
            })
        }).finally(() => {
            confirmDown.value = false
            currentItem.value = {}
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
    //当分页情况发生改变时的重载数据函数
    const reload = () => {
        findData()
    }
    /*endregion*/

    /*region 新增广告区域*/
    //是否显示新增广告
    const confirmAdd = ref(false)
    //新增广告数据
    const addData = new FormData()
    const url = ref('')

    //新增广告按钮
    const clickAdd = () => {
        confirmAdd.value = true
    }
    //取消
    const cancel = () => {
        url.value = ''
        confirmAdd.value = false
    }
    //确认新增广告
    const addAdvertise = () => {
        addData.append('file', document.querySelector('#file').files[0])
        addData.append('url', url.value)
        const user = useUser()
        addNewAd(addData, user.user.id).then(rs => {
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
            } else {
                ElMessage.error({
                    message: rs.data,
                    grouping: true,
                })
            }
        }).finally(() => {
            url.value = ''
            addData.delete('file')
            addData.delete('url')
            confirmAdd.value = false
            findData()
        })
    }
    /*endregion*/
    //加载页面时调用函数
    onMounted(() => {
        findData()
    })

</script>

<style scoped>
    /*region 显示区域*/
    /*信息展示区域样式*/
    .el-scrollbar {
        height: 560px;
    }

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

    /*鼠标指向某一条信息时的动画*/
    .el-descriptions:hover {
        cursor: pointer;
        box-shadow: 0 0 10px #cccccc;
    }

    /*endregion*/

    /*region 分页区域*/
    /*分页区域样式*/
    .pagination {
        margin-top: 15px;
    }

    /*endregion*/

    /*region 新增题目区域*/
    .add-body {
        display: flex;
        justify-content: center;
        align-items: start;
        width: 30vw;
        transform: translateX(-5%);
    }

    .add {
        width: 200px;
    }

    /*endregion*/

    /*region 全局组件样式*/
    /*按钮样式*/
    .el-button {
        color: black;
    }

    .el-button:hover {
        box-shadow: 0 0 5px #cccccc;
    }

    /*占位div样式*/
    .emptyDiv {
        height: 10px
    }

    /*endregion*/
</style>