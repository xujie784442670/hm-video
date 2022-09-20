<template>
    <div style="letter-spacing: 1px" class="bod">
        <!--<P style="text-align: right;">-->
        <!--  <ruby>元初-->
        <!--    <rt>YuanChu</rt>-->
        <!--  </ruby>-->
        <!--</P>-->

        <div class="head">
            <!-- 评论区域如果当前用户有头像则显示头像,没有则显示默认头像 -->
            <img :src="user.user.avatar" alt=""/>
            <!--<img v-else src="https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg" alt=""/>-->
            <!-- 评论框 -->
            <input
                type="text"
                placeholder="请发布一条友善的评论 . . ."
                ref="input"
                @focus="obtain"

                v-model="commentData.firstComments"
                @keyup.enter="sumbit"
            />
            <!--@blur="lose"-->
            <!-- 发布按钮 -->
            <button @click="sumbit">发表评论</button>
        </div>
        <div class="content">
            <!-- 一级评论 -->
            <div class="first" v-for="(item, index) in commentData.comments" :key="index">
                <a href="javascript:;" class="first-img">
                    <!-- 如果当前用户有头像则显示头像,没有则显示默认头像 -->
                    <!-- <img :src="`http://` + item.picture" /> -->
                    <img v-if="item.picture" :src="`http://` + item.picture" alt=""/>
                    <img :src="item.avatar" :alt="item.nickName"/>
                </a>
                <div class="first-content">
                    <!-- 一级评论用户昵称 -->
                    <h3 class="first-username">{{ item.nickName }}</h3>
                    <!-- 一级评论发布时间 -->
                    <span class="first-time">{{ item.createTime }}</span>
                    <!-- 一级评论评论内容 -->
                    <p class="first-comment">
                        {{ item.content }}
                    </p>
                    <!-- 一级评论评论点赞 -->
                    <div
                        class="first-right"
                        @mouseleave="delete_leave(item)"
                        @mouseover="delete_after(item)"
                    >
	            <span
                    class="delete"
                    v-if="item.delete"
                    @click="comment_delete(item, '父级', index)"
                >删除</span
                >
                        <span class="comments" @click="comment_input(item)">
              <svg class="icon" aria-hidden="true">
                <use xlink:href="#icon-pinglun"></use>
              </svg>
                            <!--评论-->
            </span>
                        <!-- 一级评论点赞数 -->
                        <span
                            class="praise "
                            :class="item.like"
                            @click="comment_like(item)"
                        >
              <svg v-if="!item.like" class="icon" aria-hidden="true">
                  <use xlink:href="#icon-dianzan"></use>
                </svg>
                <svg v-if="item.like" class="icon" aria-hidden="true">
                  <use xlink:href="#icon-dianzan_kuai"></use>
                </svg>
	              <span>{{ item.likes }}</span>
	            </span>
                    </div>
                    <!-- 回复一级评论 -->
                    <div class="reply-comment" v-if="item.display" @blur="lose(item)">
                        <input
                            type="text"
                            placeholder="请输入评论 . . ."
                            v-model="commentData.childComments"
                            @keyup.enter="test(item, '父级', index)"
                        />
                        <!-- 0为回复一级评论 reply_sumbit1(item, '父级', index)-->
                        <button @click="test(item,'父级',index)">发表评论</button>
                    </div>
                    <!-- 次级评论 -->
                    <div class="second">
                        <ul>
                            <li v-for="(sons, sons_index) in item.sons" :key="sons_index">
                                <div class="top">
                                    <!-- 次级评论头像,该用户没有头像则显示默认头像 -->
                                    <a href="JavaScript:;" class="second-img">
                                        <img :src="sons.avatar"/>
                                        <!-- <img v-if="sons.picture" :src="`http://` + sons.picture" /> -->
                                        <!--<img v-else src="../../assets/logo.png"/>-->
                                    </a>
                                    <div class="second-content">
                                        <!-- 次级评论用户昵称 -->
                                        <h3 class="second-username">{{ sons.nickName }}</h3>
                                        <!-- 次级评论评论时间 -->
                                        <span class="second-time">{{ sons.createTime }}</span>
                                        <!-- 次级评论内容 xxx回复xxx：评论内容-->
                                        <p class="second-comment">
	                      <span class="second-reply">
	                        <span class="to_reply">{{ sons.nickName }}</span>
	                        回复
	                        <span class="to_reply">{{ item.nickName }}</span
                            >：
	                      </span>
                                            {{ sons.content }}
                                        </p>
                                        <!-- 次级评论评论点赞 -->
                                        <div
                                            class="second-right"
                                            @mouseleave="delete_leave(sons)"
                                            @mouseover="delete_after(sons)"
                                        >
	                      <span
                              class="delete"
                              v-if="sons.delete"
                              @click="comment_delete(sons, '子级',index,sons_index)"
                          >删除</span
                          >
                                            <span class="comments" @click="comment_input(sons)"
                                            >

                        <svg class="icon" aria-hidden="true">
                          <use xlink:href="#icon-pinglun"></use>
                        </svg>
                      </span>
                                            <!-- 次级评论点赞数 -->
                                            <span
                                                class="praise"
                                                :class="sons.like"
                                                @click="comment_like(sons)"
                                            >
                        <svg v-if="!sons.like" class="icon" aria-hidden="true">
                          <use xlink:href="#icon-dianzan"></use>
                        </svg>
                        <svg v-if="sons.like" class="icon" aria-hidden="true">
                          <use xlink:href="#icon-dianzan_kuai"></use>
                        </svg>
                        <span>{{
                                sons.likes
                            }}</span></span
                                            >
                                        </div>
                                    </div>
                                </div>
                                <!-- 回复次级评论 -->
                                <div class="reply-comment reply_li" v-if="sons.display" @blur="lose(sons)">
                                    <input
                                        type="text"
                                        placeholder="请输入评论 . . ."
                                        v-model="commentData.childComments"
                                        @keyup.enter="reply_sumbit(sons,'子级',index,sons_index)"

                                    />
                                    <!-- 1为回复次级评论 -->
                                    <button @click="test1(sons,'子级',index,sons_index)">发表评论</button>

                                </div>
                            </li>
                        </ul>
                    </div>
                    <!--  三级评论-->
                    <div class="third">

                    </div>
                </div>
            </div>
            <!-- 暂无评论的空状态 -->
            <el-empty description="暂无评论" v-if="commentData.show"></el-empty>
        </div>
    </div>
</template>

<script>

</script>

<script setup>

    import { ref } from "vue";
    import request from "../../http/Request.js";
    import useUser from "../../http/stores/user.js"
    import router from "../../router.js";
    import { ElMessage } from "element-plus";

    const user = useUser();
    const commentData = ref({
        current: new Date(),	//时间
        picture: '',
        firstComments: '',
        comments: [],
        childComments: '',
        show: false,	//暂无条件显示
    })
    const props = defineProps({
        videoId: {
            type: Number,
            required: true,
        },
    })
    const up = ref({})
    request.get("/video/findOne", { params: { videoId: props.videoId } }).then(rs => {
        up.value = rs.data.data.video.account
    })

    const obtain = () => {

    }
    // 评价框 失焦
    const lose = (m) => {
        m.display = false
        commentData.value.firstComments = ''
        commentData.value.childComments = ''
    }
    // 发表评论
    const sumbit = () => {
        commentData.value.current = new Date();	//获取当前时间年月日、时分秒

        const comment = {
            nickName: user.user.nickName,
            createTime: commentData.value.current.getFullYear() + '-' + (commentData.value.current.getMonth() + 1) +
                '-' + commentData.value.current.getDate() + ' ' + commentData.value.current.getHours() + ':' +
                commentData.value.current.getMinutes() + ':' + commentData.value.current.getSeconds(),
            content: commentData.value.firstComments,
            avatar: user.user.avatar,
            // delete: true,
            flag: false,
            like: ref(false),
            sons: [],
            likes: 0,
            display: false,	//显示评论区
            videoId: props.videoId,
            accountId: user.user.id,
        }
        request.post("/comments/insert", comment).then(rs => {
            console.log(rs)
            console.log("添加评论")
        })
        commentData.value.comments.push(comment)
        commentData.value.firstComments = ''
    }

    function test (item, type, index) {
        // console.log("测试")
        // console.log(item)
        // console.log(type)
        // console.log(index)
        const comment = {
            nickName: user.user.nickName,
            createTime: commentData.value.current.getFullYear() + '年' + (commentData.value.current.getMonth() + 1) +
                '月' + commentData.value.current.getDate() + '日' + commentData.value.current.getHours() + ':' +
                commentData.value.current.getMinutes() + ':' + commentData.value.current.getSeconds(),
            content: commentData.value.childComments,
            delete: true,
            flag: false,
            like: false,
            likes: 0,
            avatar: user.user.avatar,
            display: false,	//显示评论区
            to_username: item.username,
        }
        commentData.value.comments[index].sons.splice(index, 0, comment)
        commentData.value.firstComments = ''
        commentData.value.childComments = ''
        m.display = false
    }

    // 评价展示
    const comment_input = (m) => {
        m.display = true
    }
    const comment_like = (c) => {
        if (user.token === undefined) {
            router.push({ name: "login" })
            ElMessage.error("请在登录后,进行该操作")
        }
        // console.log(c)
        let commentLike = { accountId: user.user.id, commentId: c.id, type: 1 }
        if (c.like) {
            c.likes--
            request.post("/likes/delCommentLike", commentLike).then(rs => {
                // console.log(rs)
            })
        } else {
            request.post("/likes/addCommentLike", commentLike).then(rs => {
                // console.log(rs);
            })
            c.likes++
        }
        c.like = !c.like

    }
    const comment_delete = (m, n, w, t) => {
        // console.log(121)
        if (n == '子级') {
            commentData.value.comments[w].sons.splice(t, 1);
        } else if (n == '父级') {
            commentData.value.comments.splice(w, 1)
        } else {

        }
    }
    const delete_leave = (m) => {
        // m.delete = false
    }
    const delete_after = (m) => {

    }

    //次级评论
    function test1 (sons, type, index, sonsIndex) {
        // console.log("评论")
        // console.log(sons)
        // console.log(type)
        // console.log(index)	//父级的下标
        // console.log(sonsIndex)	//子级的下标
        const comment = {
            nickName: user.user.nickName,
            createTime: commentData.value.current.getFullYear() + '-' + (commentData.value.current.getMonth() + 1) +
                '-' + commentData.value.current.getDate() + ' ' + commentData.value.current.getHours() + ':' +
                commentData.value.current.getMinutes() + ':' + commentData.value.current.getSeconds(),
            content: commentData.value.childComments,
            // delete: true,
            flag: false,
            like: ref(false),
            sons: [],
            likes: 0,
            pid: sons.pid,
            display: false,	//显示评论区
            to_username: sons.username,
            avatar: user.user.avatar,
            videoId: commentData.value.videoId,
            accountId: user.user.id,
        }
        commentData.value.comments[index].sons.splice(sonsIndex, 0, comment)
        request.post("/comments/insert", comment).then(rs => {
            // console.log(rs)
            // console.log("添加评论")
        })
        commentData.value.firstComments = ''
        commentData.value.childComments = ''
        sons.display = false

    }

    request.get("/comments/list", { params: { videoId: props.videoId } }).then(rs => {
        // console.log(rs)
        commentData.value.comments = rs.data.data
        console.log("评论列表")
        console.log(rs)
        for (let i = 0; i < commentData.value.comments.length; i++) {
            request.post("/likes/findCommentLike", {
                accountId: user.user.id,
                commentId: commentData.value.comments[i].id,
                type: 1,
            }).then(rs => {

                if (rs.data.code === 0) {
                    commentData.value.comments[i].like = true
                } else {
                    commentData.value.comments[i].like = false
                }
            })
            for (let j = 0; j < commentData.value.comments[i].sons.length; j++) {
                commentData.value.comments[i].sons[j]
                request.post("/likes/findCommentLike", {
                    accountId: user.user.id,
                    commentId: commentData.value.comments[i].sons[j].id,
                    type: 1,
                }).then(rs => {
                    if (rs.data.code == 0) {
                        commentData.value.comments[i].sons[j].like = true
                    } else {
                        commentData.value.comments[i].sons[j].like = false
                    }
                })
            }
        }
        commentData.value.videoId = rs.data.data[0].videoId
    })

    const created = () => {

    }
</script>
<style>
    .bod {
        margin-left: 50px;
        margin-top: 50px;
    }

    /* 评论框 */
    .head {
        background-color: rgb(248, 248, 248);
        position: relative;
        height: 75px;
        width: 50%;
        border-radius: 5px;
    }

    .head img {
        width: 55px;
        height: 55px;
        border-radius: 50%;
        position: absolute;
        top: 10px;
        left: 13px;
    }

    /* 评论框 */
    .head input {
        position: absolute;
        top: 13px;
        left: 80px;
        height: 45px;
        border-radius: 5px;
        outline: none;
        width: 65%;
        font-size: 20px;
        padding: 0 20px;
        border: 2px solid #f8f8f8;
    }

    /* 发布评论按钮 */
    .head button {
        position: absolute;
        top: 13px;
        right: 20px;
        width: 120px;
        height: 48px;
        border: 0;
        border-radius: 5px;
        font-size: 20px;
        font-weight: 500;
        color: #fff;
        background-color: rgb(118, 211, 248);
        cursor: pointer;
        letter-spacing: 2px;
    }

    /* 鼠标经过字体加粗 */
    .head button:hover {
        font-weight: 600;
    }

    /* 评论内容区域 */
    .content .first {
        display: flex;
        width: 60%;
        position: relative;
        padding: 10px 10px 0px 0;
        text-align: left;
    }

    .first .first-img {
        flex: 1;
        text-align: center;
    }

    .first img {
        width: 50px;
        height: 50px;
        border-radius: 50%;
    }

    .first-username,
    .second-username {
        color: #504f4f;
    }

    .first-content {
        flex: 9;
    }

    .first-time,
    .second-time {
        color: #767575;
    }

    .first-comment,
    .second-comment {
        margin-top: 5px;
    }

    /* 右边点赞和评论 */
    .first-right,
    .second-right {
        position: absolute;
        right: 1%;
        top: 10px;
    }

    .first-right span,
    .second-right span {
        margin-right: 20px;
        cursor: pointer;
    }

    /* 删除评论 */
    .delete:hover {
        color: red;
    }

    /* 评论字体图标 */
    /*.comments::before {*/
    /*  !* 想使用的icon的十六制编码，去掉&#x之后的 *!*/
    /*  content: "\e8b9";*/
    /*  !* 必须加 *!*/
    /*  font-family: "iconfont";*/
    /*  margin-right: 4px;*/
    /*  font-size: 16px;*/
    /*}*/

    /* 点赞字体图标 */
    /*.praise::before {*/
    /*  !* 想使用的icon的十六制编码，去掉&#x之后的 *!*/
    /*  content: "\ec7f";*/
    /*  !* 必须加 *!*/
    /*  font-family: "iconfont", serif;*/
    /*  margin-right: 4px;*/
    /*  font-size: 19px;*/
    /*}*/
    /*.praise span:hover{*/
    /*  color: #aafa70;*/
    /*}*/
    .praise .icon:hover {
        color: #C0C0C0;
    }

    .comments .icon:hover {
        color: #C0C0C0;
    }

    .second {
        background-color: #f3f3f3;
        margin-top: 10px;
    }

    .second li {
        padding: 10px 10px 10px 0;
        border-bottom: 1px solid rgb(237, 237, 237);
    }

    .second .top {
        display: flex;
        position: relative;
    }

    .second-img {
        flex: 1;
        text-align: center;
    }

    .to_reply {
        color: rgb(106, 106, 106);
    }

    .second-content {
        flex: 9;
    }

    .second .reply_li {
        margin-left: 70px;
    }

    /* 评论框 */
    .reply-comment {
        margin: 10px 0 0 0;
    }

    .reply-comment input {
        height: 40px;
        border-radius: 5px;
        outline: none;
        width: 70%;
        font-size: 18px;
        padding: 0 20px;
        /* border: 2px solid #f8f8f8; */
        border: 2px solid skyblue;
    }

    /* 发布评论按钮 */
    .reply-comment button {
        width: 15%;
        height: 43px;
        border: 0;
        border-radius: 5px;
        font-size: 18px;
        font-weight: 500;
        color: #fff;
        background-color: rgb(118, 211, 248);
        cursor: pointer;
        letter-spacing: 2px;
        margin-left: 20px;
    }

    /* 鼠标经过字体加粗 */
    .reply-comment button:hover {
        font-weight: 600;
    }

    /* 评论点赞颜色 */
    .comment-like {
        color: red;
    }
</style>
