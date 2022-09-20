<template>

    <!--引入粒子特效-->
    <div class="bkpro-login-canvas">
        <Particles
            id="tsparticles"
            :particlesInit="particlesInit"
            :particlesLoaded="particlesLoaded"
            :options="options"
        />
    </div>
    <div id="background">
        <div id="contain">
            <h1>Register</h1>

            <div class="form">
                <label>用户名：</label><input type="text" v-model.trim="username"><br/>
            </div>
            <div class="form">
                <label>昵称：</label><input type="text" v-model.trim="nickName"><br/>
            </div>
            <div class="form">
                <label>密码：</label><input type="password" v-model.trim="password"><br/>
            </div>
            <div class="form">
                <label>邮箱：</label><input type="email" v-model.trim="email"> <br/>
            </div>
            <div class="form">
                <label>验证码：</label><input type="tel" v-model.trim="verifyCode"><br/>
            </div>
            <div class="bottom">
                <button @click.prevent="register">提交</button>
                <button @click.prevent="sendVerifyCode">发送验证码</button>
            </div>
        </div>
    </div>

</template>

<script setup>
    import { ref } from "vue";
    import request from "../http/Request.js";
    import { useRouter } from "vue-router";
    import { ElMessage } from "element-plus";

    const router = useRouter()
    const username = ref('')
    const nickName = ref('')
    const password = ref('')
    const email = ref('')
    const verifyCode = ref('')

    const register = () => {
        const registerData = ref({
            username: username.value,
            nickName: nickName.value,
            password: password.value,
            email: email.value,
            verifyCode: verifyCode.value,
        })
        // console.log(registerData)
        request.put("/user/register", registerData.value).then(rs => {
            if (rs.data.code === 0) {
                router.push({ name: "login" })
                ElMessage({ message: "注册成功" })
            } else {
                ElMessage.error(rs.data.msg)
            }
        })
    }
    const sendVerifyCode = () => {
        request.get("/mail/verifycode", { params: { email: email.value } }).then(rs => {
            if (rs.data.code === 0) {
                ElMessage({ message: "以发送验证码,请及时查看填写!" })
            }
        })
    }
</script>
<script>
    import { defineComponent, ref } from "vue";

    export default defineComponent({
        setup () {
            const options = {
                background: {
                    color: {
                        value: "#000",//粒子颜色
                    },
                },
                fpsLimit: 60,
                interactivity: {
                    events: {
                        onClick: {
                            enable: true,
                            mode: "push",//可用的click模式有: "push", "remove", "repulse", "bubble"。
                        },
                        onHover: {
                            enable: true,
                            mode: "grab",//可用的hover模式有: "grab", "repulse", "bubble"。
                        },
                        resize: true,
                    },
                    modes: {
                        bubble: {
                            distance: 400,
                            duration: 2,
                            opacity: 0.8,
                            size: 40,
                        },
                        push: {
                            quantity: 4,
                        },
                        repulse: {
                            distance: 200,
                            duration: 0.4,
                        },
                    },
                },
                particles: {
                    color: {
                        value: "#ffffff",
                    },
                    links: {
                        color: "#ffffff",//'#dedede'。线条颜色。
                        distance: 150,//线条长度
                        enable: true,//是否有线条
                        opacity: 0.5,//线条透明度。
                        width: 1,//线条宽度。
                    },
                    collisions: {
                        enable: false,
                    },
                    move: {
                        direction: "none",
                        enable: true,
                        outMode: "bounce",
                        random: false,
                        speed: 4,//粒子运动速度。
                        straight: false,
                    },
                    number: {
                        density: {
                            enable: true,
                            area: 800,
                        },
                        value: 80,//粒子数量。
                    },
                    opacity: {
                        value: 0.5,//粒子透明度。
                    },
                    shape: {
                        type: "circle", //可用的粒子外观类型有："circle","edge","triangle", "polygon","star"
                    },
                    size: {
                        random: true,
                        value: 5,
                    },
                },
                detectRetina: true,
            };
            return {
                options,
            };
        },
    });
</script>
<style scoped>
    #background {
        width: 100%;
        height: 100%;
        background-size: 100% 100%;
        position: fixed;
        top: 0;
        z-index: 9;
        left: 0;
    }

    #contain {
        width: 580px;
        height: 600px;
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        background: #39a45d;
        text-align: center;
        border-radius: 20px;
    }

    #contain h1 {
        color: white;
    }

    .form {
        color: white;
        margin-left: 20%;
        margin-top: 60px;
        font-size: 20px;
        text-align: left;
    }

    label {
        float: left;
        width: 5em;
        margin-right: 1em;
        text-align: right;
    }

    input, textarea {
        margin-left: 10px;
        padding: 4px;
        border: solid 1px #ffffff;
        outline: 0;
        font: normal 13px/100% Verdana, Tahoma, sans-serif;
        width: 200px;
        height: 20px;
        background: #f1f1f190;
        box-shadow: rgba(0, 0, 0, 0.1) 0px 0px 8px;
    }

    input:hover, textarea:hover, input:focus, textarea:focus {
        border-color: #1ea10a;
    }

    button {
        position: relative;
        height: 33px;
        width: 150px;
        background: rgb(255, 255, 255);
        border-radius: 10px;
        margin-top: 38px;
        box-shadow: none;
        color: #000005;
        margin-left: 40px;
    }
</style>
