<template>
  <banner/>

  <Danmu :videoId="videoId">
    <template #video>

      <video :src="props.videoUrl"
             id="J-danmuVideo" controls />
    </template>
  </Danmu >
  <!--<video src="src/assets/video/cyberpunk.mp4" controls/>-->
  <comment :videoId="videoId"/>
</template>

<script>
import Top from "../home/components/Top.vue";
import Banner from "./components/Banner.vue";
import comment from "../comment/Comment.vue"
export default {
  name: "index",
  components: {Banner, Top},
  // props:{
  //   videoUrl:String
  // },
}
// console.log(videoUrl)
</script>
<script setup>
import Danmu from "./components/Danmu.vue";
import {defineProps} from "vue";
// const videoUrl = this.props[videoUrl];
const props = defineProps({
  videoUrl:{
    type:String,
    required:true
  },
  videoId:{
    type:Number,
    required:true
  }
});

console.log(props.videoUrl)
console.log(props.videoId)
</script>
<style scoped>
video{
  width: 100%;
  height: 500px;
}
</style>