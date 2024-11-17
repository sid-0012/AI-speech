<template>
  <filetrans-subtitle ref="filetransSubtitleCom"></filetrans-subtitle>
  <p>
    <a-space>
      <a-button type="primary" @click="handleQuery()">刷新列表</a-button>
    </a-space>
  </p>
  <a-table :dataSource="filetranss"
           :columns="columns"
           :pagination="pagination"
           @change="handleTableChange"
           :loading="loading">
    <template #bodyCell="{ column, record }">
      <template v-if="column.dataIndex === 'status'">
        <span v-for="item in FILETRANS_STATUS" :key="item.code">
          <span v-if="item.code === record.status">
            {{item.desc}}
          </span>
        </span>
      </template>
      <template v-else-if="column.dataIndex === 'payStatus'">
        <span v-for="item in FILETRANS_PAY_STATUS" :key="item.code">
          <span v-if="item.code === record.payStatus">
            {{item.desc}}
          </span>
        </span>
      </template>
      <template v-else-if="column.dataIndex === 'lang'">
        <span v-for="item in FILETRANS_LANG" :key="item.code">
          <span v-if="item.code === record.lang">
            {{item.desc}}
          </span>
        </span>
      </template>
      <template v-else-if="column.dataIndex === 'second'">
        {{formatSecond(record.second)}}
      </template>
      <template v-else-if="column.dataIndex === 'operation'">
        <a-space>
          <a-button v-if="record.status === FILETRANS_STATUS.SUBTITLE_SUCCESS.code"
            type="primary" @click="showSubtitleModal(record)" size="small">
            查看字幕
          </a-button>
        </a-space>
      </template>
    </template>
  </a-table>
</template>
<script setup>
import {ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";
import FiletransSubtitle from "./filetrans-subtitle.vue";

const filetransSubtitleCom = ref();

const showSubtitleModal = (filetrans) => {
  filetransSubtitleCom.value.showModal(filetrans);
};

//----------------- 列表查询 -----------------
// 分页的三个属性名是固定的
const pagination = ref({
  total: 0,
  current: 1,
  pageSize: 10,
});
const loading = ref(false);
const filetranss = ref();
filetranss.value = [];
const FILETRANS_STATUS = window.FILETRANS_STATUS;
const FILETRANS_PAY_STATUS = window.FILETRANS_PAY_STATUS;
const FILETRANS_LANG = window.FILETRANS_LANG;


const columns = [{
  title: '名称',
  dataIndex: 'name',
}, {
  title: '支付状态',
  dataIndex: 'payStatus',
}, {
  title: '状态',
  dataIndex: 'status',
}, {
  title: '语言',
  dataIndex: 'lang',
}, {
  title: '时长',
  dataIndex: 'second',
}, {
  title: '操作',
  dataIndex: 'operation',
}];

const handleQuery = (param) => {
  if (!param) {
    param = {
      page: 1,
      size: pagination.value.pageSize
    };
  }
  loading.value = true;
  filetranss.value = [];
  axios.get("/nls/admin/filetrans/query", {
    params: {
      page: param.page,
      size: param.size
    }
  }).then((response) => {
    loading.value = false;
    let data = response.data;
    if (data.success) {
      filetranss.value = data.content.list;
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
 * 时间格式化，将"秒"格式化成"时:分:秒"
 * @param second
 * @returns {string}
 */
const formatSecond = (second) => {
  return Tool.formatSecond(second);
};

handleQuery();
</script>
