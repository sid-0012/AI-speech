<template>
  <a-modal v-model:open="open" title="" @ok="handleOk" footer="" style="width: 800px; top: 20px">
    <p>
      <a-space>
        <a-button type="primary" @click="genSubtitle">下载字幕<FileTextOutlined /></a-button>
        <a-button type="primary" @click="genText">下载纯文本<FileTextOutlined /></a-button>
      </a-space>
    </p>
    <a-table :dataSource="subtitles"
             :columns="columns"
             :pagination="pagination"
             @change="handleTableChange"
             :loading="loading">
      <template #bodyCell="{ column, record }">
        <template v-if="column.dataIndex === 'time'">
          {{formatMs(record.begin)}} ~ {{formatMs(record.end)}}
        </template>
      </template>
    </a-table>
  </a-modal>
  <the-download ref="theDownloadCom"></the-download>
</template>
<script setup>
import { ref } from 'vue';
import {message, notification} from "ant-design-vue";
import axios from "axios";
import TheDownload from "../../components/the-download.vue";
const open = ref(false);
const filetrans = ref();
filetrans.value = {};
const showModal = (_filetrans) => {
  filetrans.value = _filetrans;
  handleQuery();
  open.value = true;
};
const handleOk = e => {
  console.log(e);
  open.value = false;
};

//----------------- 列表查询 -----------------
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 5,
});
const loading = ref(false);
const subtitles = ref();
subtitles.value = [];

const columns = [{
//   title: '开始时间',
//   dataIndex: 'begin',
// }, {
//   title: '结束时间',
//   dataIndex: 'end',
// }, {
  title: '时间',
  dataIndex: 'time',
}, {
  title: '字幕',
  dataIndex: 'text',
}];

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    };
  }
  loading.value = true;
  subtitles.value = [];
  axios.get("/nls/web/filetrans-subtitle/query", {
    params: {
      page: param.page,
      size: param.size,
      filetransId: filetrans.value.id
    }
  }).then((response) => {
    loading.value = false;
    let data = response.data;
    if (data.success) {
      subtitles.value = data.content.list;
      // 设置分页控件的值
      pagination.value.current = param.page;
      pagination.value.total = data.content.total;
    } else {
      notification.error({description: data.message});
    }
  });
};

const handleTableChange = (pagination) => {
  // console.log("看看自带的分页参数都有啥：" + pagination);
  handleQuery({
    page: pagination.current,
    size: pagination.pageSize
  });
};

/**
 * 时间格式化，将"毫秒"格式化成"时:分:秒"
 * @param second
 * @returns {string}
 */
const formatMs = (ms) => {
  const second = ms/1000;
  return Tool.formatSecond(second);
};

//------------------- 下载 ------------------
const theDownloadCom = ref();
const genSubtitle = () => {
  loading.value = true;
  axios.get('/nls/web/filetrans-subtitle/gen-subtitle', {
    params: {
      filetransId: filetrans.value.id
    }
  }).then((response) => {
    loading.value = false;
    const data = response.data;
    if (data.success) {
      theDownloadCom.value.downloadItem(data.content, filetrans.value.name + "-" + filetrans.value.id + ".srt");
    } else {
      message.error(data.message);
    }
  });
};
const genText = () => {
  loading.value = true;
  axios.get('/nls/web/filetrans-subtitle/gen-text', {
    params: {
      filetransId: filetrans.value.id
    }
  }).then((response) => {
    loading.value = false;
    const data = response.data;
    if (data.success) {
      theDownloadCom.value.downloadItem(data.content, filetrans.value.name + "-" + filetrans.value.id + ".txt");
    } else {
      message.error(data.message);
    }
  });
};

// 使用 defineExpose 向外暴露指定的数据和方法
defineExpose({
  showModal,
})
</script>
