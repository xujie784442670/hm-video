<template>


    <div>
        <div style="width:14%;height: 130px;border-radius: 100px;display:inline-block;">
            <img
                style="width:100%;height: 100%;border-radius: 50%;object-fit: cover"
                :src="user.avatar"
                class="image"
            >
        </div>

        <br>

        <br>
        <el-button text @click="dialogVisible = true" style="position: relative ;left: 150px;bottom: 100px">更换头像
        </el-button>

        <el-dialog v-model="dialogVisible" title="更换头像" width="60%" :before-close="handleClose">

            <div>
                <div class="show">
                    <div class="show1">
                        <img ref="showing" :src="videoSrc" id="showimg" style="margin-left: 1px;margin-top: 3px">
                    </div>
                    <br>
                    <el-button class="upload" style="margin-left: 20px">图片选择
                        <input multiple="multiple" id="file" ref="file"
                               accept=".jpg,.png"
                               @change="getBigectURL($event)" type="file" name="userpic"
                               style="
              position: absolute;
              overflow: visible;
              left: 20px;
              top: 460px;
              opacity: 0;
             width: 10%;
             height: 70px;
             "
                        >
                    </el-button>
                    <button @click="upload()" style="height: 40px;position: relative; margin-left:50%;">确定</button>
                </div>
            </div>


            <template #footer>
     <span class="dialog-footer">
        <el-button type="primary" @click="dialogVisible = false" style="position: relative;bottom: 83px">确认</el-button
        >
      </span>
            </template>
        </el-dialog>
        <el-descriptions :column="3" border>

            <el-descriptions-item label="用户名" v-model="username"><p class="message">{{ user.nickName }}</p>
            </el-descriptions-item>
            <el-descriptions-item label="邮箱" v-model="email" class="message"><p class="message">{{ user.email }}</p>
            </el-descriptions-item>
        </el-descriptions>
    </div>

</template>

<script>
    export default {
        data () {
            return {
                dialogVisible: false,
                filename: null,
                f64: null,
                loadImage: "",
            };
        },
        methods: {
            handleClose (done) {
                this.$confirm('确认关闭？').then(_ => {
                    done();
                }).catch(_ => {});
            },
            changepic () {
                document.getElementById('file').onchange = function () {
                    var imgFile = this.files[0];
                    var fr = new FileReader();

                    fr.onload = function () {
                        let showing = document.getElementById('showimg')
                        let img = fr.result;
                        this.f64 = img;
                        this.filename = imgFile.name
                        showing.src = img;
                        showing.style.height = "100px";
                        showing.style.width = "100px";
                        showing.style.borderRadius = "100px"
                    };
                    fr.readAsDataURL(imgFile);
                }
            },
        },
        components: {
            // repassword
        },
    };

</script>

<script setup>
    import useUser from '../../http/stores/user.js'
    import request from "../../http/Request.js";

    const user = useUser().user
    request.post()

    import { onMounted, ref } from "vue";

    const Account = useUser().user
    const videoSrc = ref()
    const data = ref({
        formData: new FormData(),
    })
    const getBigectURL = (event) => {
        let file = event.target.files[0];
        let fileName = file.name;
        let index = fileName.lastIndexOf(".");
        if (index != -1) {
            let suffix = fileName.substr(index + 1).toLowerCase();
            if (suffix == 'png' || suffix == 'jpg') {
                data.value.formData.append("file", file);
            }
        }
        console.log('getBigectURL', event)
        var current = event.target.files[0]
        var fileReader = new FileReader()
        fileReader.readAsDataURL(current)
        var that = this
        fileReader.onload = function (e) {
            videoSrc.value = e.currentTarget.result
            let showing = document.getElementById('showimg')
            showing.style.height = "220px";
            showing.style.width = "220px";
            showing.style.borderRadius = "200px"
            console.log(data.value.formData.get("file"))
        }
    }
    const upload = () => {
        console.log(Account)
        data.value.formData.append(
            "account",
            new Blob([JSON.stringify(Account)], { type: "application/json" }),
        )
        request.post("/upload/uploadImage", data.value.formData,
            {
                "Content-Type": "multipart/form-data",
            },
        ).then(resp => {
            if (resp.status == 200) {
                user.avatar = resp.data.imgMysqlPath
                alert("success");
            }

        })
    }


</script>


<style scoped>


    .dialog-footer button:first-child {
        margin-right: 10px;
    }

    .message {
        width: 20em;
        overflow: hidden;
        text-overflow: ellipsis;
        white-space: nowrap
    }

    .upload {
        margin-left: 20%;
        width: 12%;
        text-align: center;
        color: white;
        height: 32px;
        border-radius: 3px;
        background: #1E90FF;
        cursor: pointer;
        outline: none;
        border-width: 0px;
        font-size: 17px;
        display: inline-block;
        padding: 4px 10px;
        line-height: 30px;
        position: relative;
        text-decoration: none;

    }

    button {
        margin-left: 70%;
        width: 15%;
        height: 35px;
        border-width: 0px;
        border-radius: 3px;
        background: #1E90FF;
        cursor: pointer;
        outline: none;
        color: white;
        font-size: 17px;
    }

    .show {
        margin: 0;
        padding: 0;
        width: 80%;
        height: 450px;
        border: 5px solid #18a0ec;
        transition: all 0.9s;
        border-radius: 10px;

    }

    .show1 {
        margin: 50px auto;
        width: 222px;
        height: 226px;
        border: 5px solid #18a0ec;
        transition: all 0.9s;
        border-radius: 150px;

    }

    .show1:hover {
        box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
        margin-top: 45px;
    }

    .show:hover {
        box-shadow: 0px 15px 30px rgba(0, 0, 0, 0.4);
        margin-top: 45px;

    }

    .texti {
        border: 1px solid #ccc;
        padding: 13px 14px;
        width: 30%;

        font-size: 14px;
        font-weight: 300;

        border-radius: 5px; /* 边框半径 */
        background: white; /* 背景颜色 */
        cursor: pointer; /* 鼠标移入按钮范围时出现手势 */
        outline: none; /* 不显示轮廓线 */


    }

    .texti:focus {
        border-color: #1e88e1;
        outline: 0;
        -webkit-box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6);
        box-shadow: inset 0 1px 1px rgba(0, 0, 0, .075), 0 0 8px rgba(102, 175, 233, .6)
    }

    textarea {
        resize: none;

    }

</style>
