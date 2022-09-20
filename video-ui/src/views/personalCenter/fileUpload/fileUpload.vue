<template>
    <!--  <input type="file" @change="getFile($event)">
      <input type="button" value="上传" @click="upload()">-->
    <video :src="videoSrc" controls="controls" width="500" height="400" class="updata"></video>
    <div class="video-title">
        <div class="input input-container">
            <div class="input-instance">
                <input type="file" id="files" @change="getBigectURL($event)" class="input-val" accept=".mp4,.wmv"/>
            </div>
        </div>
    </div>
    <div id="uploadProgress">
        <div id="upload">
            <div id="label">{{ value1.toFixed(2) }}%</div>
        </div>
    </div>

    <div class="video-title">
        <div class="section-title-container section-title">
            <p class="section-title-deg">
                <span>*</span>
            </p>
            <h3 class="section-title-content-main">标题</h3></div>
        <div class="input input-container">
            <div class="input-instance">
                <input type="text" v-model="title" class="input-val"></div>
        </div>
    </div>
    <div class="video-title">
        <div class="section-title-container section-title">
            <p class="section-title-deg">
                <span>*</span>
            </p>
            <h3 class="section-title-content-main">类型</h3></div>
        <div class="input input-container">
            <div class="input-instance">
                <select v-model="selected" @change="selectChanged($event)" class="input-val">
                    <option v-for="option in options" :key="option.id" :value="option.id" :label="option.type">
                        {{ option.type }}
                    </option>
                </select></div>
        </div>
    </div>
    <div class="video-title">
        <input type="button" value="投稿" class="button" @click="upload()"></div>


</template>
<script setup>
    import { onMounted, ref } from "vue";
    import useUser from "../../../http/stores/user.js"

    const account = useUser().user
    const data = ref({
        formData: new FormData(),
    })
    import request from "../../../http/Request.js"

    const value1 = ref(0)
    const title = ref()
    const type = ref()
    const selected = ref('A')
    const options = ref([
        { type: 'One', id: '1' },
        { type: 'Two', id: '2' },
        { type: 'Three', id: '3' },
    ])
    const selectChanged = (params) => {
        console.log(params.target.value)
        selected.value = params.target.value
    }
    const upload = () => {
        console.log("title:" + title.value);
        console.log("data.value.formData:" + data.value.formData.get("file"));
        if (title.value == undefined || title == '') {
            alert("请输入标题！！！");
        }
        if (data.value.formData.get("file") == null) {
            alert("请选择视频文件！！！");
        }
        const jsonData = {
            accountId: account.id,
            title: title.value,
            typeId: selected.value,
        }
        data.value.formData.append(
            "video",
            new Blob([JSON.stringify(jsonData)], { type: "application/json" }),
        )
        if (!(title.value == undefined || title == '')) {
            if (data.value.formData.get("file") != null) {
                request.post("/upload/uploadVideo", data.value.formData,
                    {
                        "Content-Type": "multipart/form-data",
                    },
                ).then(resp => {
                    if (resp.status == 200) {
                        alert("投稿成功！！等待审核中");
                        window.location.reload()
                    }
                })
            }
        }
    }
    const videoSrc = ref()
    const getBigectURL = (event) => {

  let file = event.target.files[0];
  let fileName = file.name;
  let index = fileName.lastIndexOf(".");
  if (index != -1) {
    let suffix = fileName.substr(index + 1).toLowerCase();
    if (suffix== 'mp4') {
      data.value.formData.append("file",file);
    }
  }
  console.log('getBigectURL', event)
  var elem = document.getElementById("upload");
  var max = document.getElementById("files").files[0].size;
  var current = event.target.files[0]
  var fileReader = new FileReader()
  fileReader .readAsDataURL(current)
  fileReader.onprogress=function (evt){
    elem.style.width = evt.loaded/max*100 + '%';
    value1.value=evt.loaded/max*100;
    document.getElementById("label").innerHTML = evt.loaded/max*100 + '%';
  }

        var that = this
        fileReader.onload = function (e) {
            videoSrc.value = e.currentTarget.result
            console.log(videoSrc.value)
        }
    }
    const init = () => {
        request.post("/videoTypes/findVideoTypes").then(rs => {
            // data.value=rs.data.data.list
            options.value = rs.data.data
            selected.value = options.value[0].id
            console.log(rs.data.data)
        })
        console.log(account.id)
    }
    onMounted(() => {
        init()
    })
</script>
<script>
    export default {
        name: "fileUpload",
    }

</script>

<style scoped>
    .updata {
        display: flex;
        align-items: center;
        margin: 24px 0 0 20px;
        color: #00a1d6;
        /*height: 100%;*/
        min-width: 1000px;
        max-width: 1100px;
        margin: 16px auto 0;
        background-color: #fff;
        box-sizing: border-box;
        font-size: 0;
        font-family: PingFang SC, Source Han Sans CN, Microsoft YaHei, Arial, Helvetica, sans-serif;
        -webkit-font-smoothing: antialiased;
        font-variant-ligatures: normal;
        font-variant-caps: normal;
        font-variant-numeric: normal;
        font-variant-east-asian: normal;
        text-rendering: optimizelegibility;
    }

    .uplada-btn {
        display: flex;
        color: #fff;
        margin: 20px auto;
        width: 200px;
        height: 44px;
        cursor: pointer;
        background: #00a1d6;
        border-radius: 4px;
        transition: background-color .3s ease;
        text-align: center;
        line-height: 40px;
        white-space: nowrap;
    }

    .video-title {
        display: flex;
        align-items: center;
        margin: 24px 0 0 20px;
        color: #00a1d6;
        /*height: 100%;*/
        min-width: 1000px;
        max-width: 1100px;
        margin: 16px auto 0;
        background-color: #fff;
        box-sizing: border-box;
        font-size: 0;
        font-family: PingFang SC, Source Han Sans CN, Microsoft YaHei, Arial, Helvetica, sans-serif;
        -webkit-font-smoothing: antialiased;
        font-variant-ligatures: normal;
        font-variant-caps: normal;
        font-variant-numeric: normal;
        font-variant-east-asian: normal;
        text-rendering: optimizelegibility;
    }

    .section-title-container {
        display: inline-flex;
        align-items: center;
        position: relative;
        flex-wrap: wrap;
        width: 134px;
    }

    .input-container {
        background: hsla(0, 0%, 84.7%, 0);
        border: 1px solid #ccd0d7;
        border-radius: 4px;
        padding: 0 12px;
        display: flex;
        align-items: center;
        flex-wrap: wrap;
        transition: border .3s ease;
    }

    .video-title .input {
        flex: 1;
    }

    .section-title-container .section-title-content-main {
        font-size: 14px;
        color: #212121;
        line-height: 21px;
        font-weight: 400;
    }

    .section-title-container .section-title-deg {
        font-size: 16px;
        color: #ff3b30;
        line-height: 16px;
        width: 12px;
    }

    .input-container .input-instance {
        flex: 1;
        min-width: 200px;
        margin: 6px 0;
    }

    .input-container .input-instance .input-val {
        display: block;
        width: 100%;
        color: #222;
        line-height: 22px;
        height: 22px;
        font-size: 14px;
        outline: 0;
        -webkit-appearance: none;
        -moz-appearance: none;
        appearance: none;
        border: 0;
        background-color: transparent;
    }

    .button {
        padding: 10px 20px;
        border-radius: 5px;
        background: none;
        border: 1px #555 solid;
        color: #333;
        display: flex;
    }

</style>