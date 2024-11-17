<template>
  <a style="display: none" :href='downloadUrl' ref="downloadA" target="_blank" download>下载</a>
</template>

<script setup>
import { defineComponent, ref } from 'vue';
import axios from "axios";

const downloadUrl = ref();
const downloadA = ref();

// 两种下载方式都可以，方法一会打开新窗口下载，页面会一闪
// 方法一 下载TXT会变成在浏览器直接打开，不友好
const download = (url) => {
  console.log("调用下载组件下载");
  downloadUrl.value = url;
  downloadA.value.download = url.substring(url.lastIndexOf("/") + 1);
  setTimeout(() => downloadA.value.click(), 50);
};

// 方法二 下载字幕和下载TXT都支持
// 下载文件，txt也能下载，而不是在浏览器里直接打开
// name是下载的名称
const downloadItem = (url, name = null) => {
  console.log("调用下载组件下载");
  axios.get(url, { responseType: "blob" }).then(response => {
    const blob = new Blob([response.data]);
    downloadUrl.value = URL.createObjectURL(blob);
    if (name == null) {
      downloadA.value.download = url.substring(url.lastIndexOf("/") + 1);
    } else {
      downloadA.value.download = name;
    }
    setTimeout(() => downloadA.value.click(), 50);
  }).catch(console.error);
};

// 使用 defineExpose 向外暴露指定的数据和方法
defineExpose({
  download,
  downloadItem,
})
</script>

<style scoped>
</style>
