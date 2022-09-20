<template>
    <div id="login-all">
        <div class="login-register">
            <div class="contain">
                <div class="big-box" :class="{active:isLogin}">
                    <div class="big-contain" key="bigContainLogin" v-if="isLogin">
                        <div class="btitle">账户登录</div>
                        <div class="bform">
                            <input type="text" placeholder="用户名" v-model="username"
                                   onkeyup="value=value.replace('^[a-zA-Z0-9_\u4e00-\u9fa5]+$')">
                            <span class="errTips" v-if="emailError">* 用户名填写错误 *</span>
                            <input type="password" placeholder="密码" v-model="password">
                            <span class="errTips" v-if="emailError">* 密码填写错误 *</span>
                        </div>
                        <div class="btns">
                            <button class="bbutton2" @click="commonLogin">登录</button>
                            <button class="bbutton1" @click="register">注册</button>
                        </div>
                    </div>
                    <div class="big-contain" key="bigContainRegister" v-else>
                        <div class="btitle">邮箱登录</div>
                        <div class="bform">
                            <div class="email">
                                <span class="errTips" v-if="existed">* 用户名已经存在！ *</span>
                                邮&emsp;箱 :<input type="email" placeholder="邮箱" v-model="email">
                            </div>
                            <div class="verifyCode">
                                验证码:<input type="text" placeholder="验证码" v-model="verifyCode">
                            </div>
                        </div>
                        <div class="btns">
                            <button class="bbutton2" @click="emailLogin">登录</button>
                            <input :class="isClick ? disab: bbutton1" @click="getVerifyCode" type="button" v-model="msg"
                                   :disabled="isClick"/>
                            <button class="bbutton1" @click="register">注册</button>
                        </div>
                    </div>
                </div>
                <div class="small-box" :class="{active:isLogin}">
                    <div class="small-contain" key="smallContainRegister" v-if="isLogin">
                        <div class="stitle">你好，朋友!</div>
                        <p class="scontent">开始登录，和我们一起旅行</p>
                        <button class="sbutton" @click="changeType">邮箱登录</button>
                    </div>
                    <div class="small-contain" key="smallContainLogin" v-else>
                        <div class="stitle">欢迎回来!</div>
                        <p class="scontent">与我们保持联系，请登录你的账户</p>
                        <button class="sbutton" @click="changeType">账户登录</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</template>


<script>
</script>

<script setup>
    import request from "../http/Request.js";
    import { useRouter } from "vue-router"
    import { ref } from "vue";
    import Request from "../http/Request.js";
    import { ElMessage } from "element-plus";
    import useUser from "../http/stores/user.js"

    const user = useUser()
    const username = ref('')
    const password = ref('')
    const type = ref(0)
    const email = ref('')
    const verifyCode = ref('')
    const msg = ref("获取验证码")
    const test = ref({
        name: "黎凡",
    })
    console.log(test.value.name)
    const isLogin = ref(false)
    const router = useRouter();
    const count = ref(60)
    const isClick = ref(false)
    const disab = ref("disab")
    const bbutton1 = ref("bbutton1")
    let curCount = ref(count)

    function register () {
        router.push({ name: "register" })
    }

    function changeType () {
        console.log("1")
        isLogin.value = !isLogin.value
        username.value = ''
        password.value = ''

    }

    //用户名的失去光标判断
    // function changeName(userName) {
    //   let name = userName;
    //   if (name == '' || name == undefined) {
    //     // 用户名为空时，input框获得焦点
    //     this.$refs.input1.focus();
    //   } else {
    //     console.log(name);
    //   }
    // }
    //密码框失去光标判断
    // function changeName1(pass) {
    //   if (pass == '' || pass == undefined) {
    //     // 密码为空时，input框获得焦点
    //     this.$refs.input2.focus();
    //   } else {
    //     console.log(pass);
    //   }
    // }
    function changeName2 (mail) {
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
        if (email.value !== '' && !regEmail.test(email.value)) {
            this.$message({
                message: '邮箱格式不正确',
                type: 'error',
            })
            email.value = ''
        }
    }

    const commonLogin = () => {
        if (password === '') {
            ElMessage.error('请输入密码');
        } else {
            const loginData = ref({
                username: username.value,
                password: password.value,
            })
            request.post("/user/login?type=0", loginData.value).then(
                rs => {
                    if (rs.data.code == 0) {
                        const data = rs.data.data
                        user.user = data.user
                        user.token = data.token
                        user.roles = data.roles
                        user.permissions = data.permissions
                        if (user.user.username === 'root') {
                            ElMessage({
                                message: '成功登录到元初视频后台管理网站！',
                                type: 0,
                            })
                            router.push({ name: "manage" })
                        } else {
                            ElMessage({
                                message: '成功登录到元初视频网站！',
                                type: 0,
                            })
                            router.push({ name: "home" })
                        }
                    } else {
                        ElMessage.error(rs.data.msg)
                    }
                }).catch(error => {
                    ElMessage.error("登录失败")
                },
            )
            ;
            console.log(username.value);//打印出input框输入的值
            console.log(password.value);
        }
    }

    function emailLogin () {
        // email.value = this.$refs.input3.value;
        // verifycode.value = this.$refs.input4.value;
        if (email.value == '') {
            ElMessage.error('请输入邮箱');
        } else {
            console.log(email.value);
            console.log(verifyCode.value);
            const loginData = ref({
                email: email.value,
                verifyCode: verifyCode.value,
            })
            request.post("/user/login?type=1", loginData.value).then(
                rs => {
                    if (rs.data.code === 0) {
                        const data = rs.data.data
                        user.user = data.user
                        user.token = data.token
                        user.roles = data.roles
                        user.permissions = data.permissions
                        if (user.user.username === 'root') {
                            ElMessage({
                                message: '成功登录到元初视频后台管理网站！',
                                type: 0,
                            })
                            router.push({ name: "manage" })
                        } else {
                            ElMessage({
                                message: '成功登录到元初视频网站！',
                                type: 0,
                            })
                            router.push({ name: "home" })
                        }
                    } else {
                        ElMessage.error(rs.data.msg)
                    }
                }).catch(error => {
                    ElMessage.error("登录失败")
                },
            )

        }

    }

    const getVerifyCode = () => {
        var regEmail = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/
        if (email.value !== '' && !regEmail.test(email.value)) {
            request.get("/mail/verifycode", { params: { email: email.value } }).then(rs => {
                if (rs.data.code === 0) {
                    ElMessage({ message: "以发送验证码,请及时查看填写!" })
                }
            })
            isClick.value = true
            msg.value = "后可重新获取"
            let InterValObj = window.setInterval(SetRemainTime, 1000); // 启动计时器timer处理函数，1秒执行一次
//timer处理函数
            function SetRemainTime () {
                if (curCount.value === 0) {//超时重新获取验证码
                    window.clearInterval(InterValObj);// 停止计时器
                    count.value = 60
                    curCount.value = count.value
                    isClick.value = false
                    msg.value = "重新获取验证码"
                } else {
                    curCount.value--;
                    msg.value = curCount.value + "秒后可重新获取";
                }
            }
        }
    }

</script>


<style scoped>
    #login-all {
        position: fixed;
        height: 100%;
        width: 100%;
        top: 0;
        left: 0;
        background-image: linear-gradient(
            90deg,
            cyan,
            cadetblue,
            greenyellow
        );
        background-size: 400%;
        animation: myanimation 10s infinite
    }

    @keyframes myanimation {
        0% {
            background-position: 0% 50%;
        }
        50% {
            background-position: 100% 50%;
        }
        108% {
            background-position: 0% 50%;
        }
    }

    .email, .verifyCode {
        width: 100%;
        display: flex;
        padding: 20px;
        justify-content: center;
        justify-items: center;
    }

    .email input {
        margin-left: 40px;
    }

    .verifyCode input {
        margin-left: 40px;
    }

    .disab {
        background-color: #C0C0C0;
        width: 20%;
        height: 40px;
        border-radius: 24px;
        margin-left: 20px;
        margin-right: 20px;
        border: none;
        outline: none;
        color: #fff;
        font-size: 0.9em;
        cursor: pointer;
    }

    .login-register {
        width: 100vw;
        height: 100vh;
        box-sizing: border-box;
    }

    .contain {
        width: 60%;
        height: 60%;
        position: relative;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background-color: #fff;
        border-radius: 20px;
        box-shadow: 0 0 3px #f0f0f0,
        0 0 6px #f0f0f0;
    }

    .big-box {
        width: 70%;
        height: 100%;
        position: absolute;
        display: flex;
        top: 0;
        left: 30%;
        transform: translateX(0%);
        transition: all 1s;
    }

    .big-contain {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .btitle {
        font-size: 1.5em;
        font-weight: bold;
        color: rgb(57, 167, 176);
    }

    .bform {
        width: 100%;
        height: 40%;
        padding: 2em 0;
        display: flex;
        flex-direction: column;
        justify-content: space-around;
        align-items: center;
    }

    .bform .errTips {
        display: block;
        width: 50%;
        text-align: left;
        color: red;
        font-size: 0.7em;
        margin-left: 1em;
    }

    .bform input {
        width: 50%;
        height: 30px;
        border: none;
        outline: none;
        border-radius: 10px;
        padding-left: 2em;
        background-color: #f0f0f0;
    }

    .bbutton {
        width: 20%;
        height: 40px;
        border-radius: 24px;
        border: none;
        outline: none;
        position: relative;
        left: -25%;
        background-color: rgb(57, 167, 176);
        color: #fff;
        font-size: 0.9em;
        cursor: pointer;
    }

    .btns {
        display: flex;
        justify-items: center;
        justify-content: center;
        width: 80%;
    }

    .bbutton1 {
        width: 20%;
        height: 40px;
        border-radius: 24px;
        margin-left: 20px;
        margin-right: 20px;
        border: none;
        outline: none;
        background-color: rgb(57, 167, 176);
        color: #fff;
        font-size: 0.9em;
        cursor: pointer;
    }

    .bbutton2 {
        width: 20%;
        height: 40px;
        border-radius: 24px;
        margin-right: 20px;
        border: none;
        outline: none;
        background-color: rgb(57, 167, 176);
        color: #fff;
        font-size: 0.9em;
        cursor: pointer;
    }

    .small-box {
        width: 30%;
        height: 100%;
        background: linear-gradient(135deg, rgb(57, 167, 176), rgb(56, 183, 145));
        position: absolute;
        top: 0;
        left: 0;
        transform: translateX(0%);
        transition: all 1s;
        border-top-left-radius: inherit;
        border-bottom-left-radius: inherit;
    }

    .small-contain {
        width: 100%;
        height: 100%;
        display: flex;
        flex-direction: column;
        justify-content: center;
        align-items: center;
    }

    .stitle {
        font-size: 1.5em;
        font-weight: bold;
        color: #fff;
    }

    .scontent {
        font-size: 0.8em;
        color: #fff;
        text-align: center;
        padding: 2em 4em;
        line-height: 1.7em;
    }

    .sbutton {
        width: 60%;
        height: 40px;
        border-radius: 24px;
        border: 1px solid #fff;
        outline: none;
        background-color: transparent;
        color: #fff;
        font-size: 0.9em;
        cursor: pointer;
    }

    .big-box.active {
        left: 0;
        transition: all 0.5s;
    }

    .small-box.active {
        left: 100%;
        border-top-left-radius: 0;
        border-bottom-left-radius: 0;
        border-top-right-radius: inherit;
        border-bottom-right-radius: inherit;
        transform: translateX(-100%);
        transition: all 1s;
    }


</style>
