<template>
  <h1>HELP</h1>
  {{ resp }}
  <a-input v-model:value="resp" @change="onChange"/>
</template>
<script setup>
import axios from "axios";
import {ref} from "vue";
import {message} from "ant-design-vue";

const resp = ref();

axios.get("/nls/query", {
  params: {
    mobile: "1"
  }
}).then(response => {
  let data = response.data
  if (data.success) {
    resp.value = JSON.stringify(data.content);
  } else {
    message.error(data.message)
  }
})

const onChange = () => {
  console.log(resp.value)
}
</script>
