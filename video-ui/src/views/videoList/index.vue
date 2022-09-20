<template>
    <banner/>
    <VideoListBody/>
</template>

<script>
import Banner from "../video/components/Banner.vue";
import VideoListBody from "./components/VideoListBody.vue";
export default {
  name: "index",
  components: {VideoListBody, Banner}
}
</script>

<style scoped>

</style>