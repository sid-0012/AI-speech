<template>
  <a-card>
    <a-row :gutter="[0, 20]">
      <a-col :span="4">
        <a-statistic title="在线人数" :value="statistic.onlineCount || 0">
        </a-statistic>
      </a-col>
      <a-col :span="4">
        <a-statistic title="注册人数" :value="statistic.registerCount || 0">
        </a-statistic>
      </a-col>
      <a-col :span="4">
        <a-statistic title="订单数" :value="statistic.orderCount || 0">
        </a-statistic>
      </a-col>
      <a-col :span="4">
        <a-statistic title="订单金额" :value="statistic.orderAmount || 0">
        </a-statistic>
      </a-col>
      <a-col :span="4">
        <a-statistic title="语音识别次数" :value="statistic.filetransCount || 0">
        </a-statistic>
      </a-col>
      <a-col :span="4">
        <a-statistic title="语音识别时长" :value="formatSecond(statistic.filetransSecond || 0)">
        </a-statistic>
      </a-col>
    </a-row>
  </a-card>
  <br/>
  <br/>
  <a-row>
    <a-col :span="8" id="registerCountList-col">
      <div id="registerCountList" style="width: 100%;height:250px;"></div>
    </a-col>
    <a-col :span="8" id="filetransCountList-col">
      <div id="filetransCountList" style="width: 100%;height:250px;"></div>
    </a-col>
    <a-col :span="8" id="filetransSecondList-col">
      <div id="filetransSecondList" style="width: 100%;height:250px;"></div>
    </a-col>
    <a-col :span="8" id="orderCountList-col">
      <div id="orderCountList" style="width: 100%;height:250px;"></div>
    </a-col>
    <a-col :span="8" id="orderAmountList-col">
      <div id="orderAmount" style="width: 100%;height:250px;"></div>
    </a-col>
  </a-row>
  <!--<pre>{{statistic}}</pre>-->
</template>
<script setup>

import {ref} from "vue";
import axios from "axios";
import {notification} from "ant-design-vue";

const statistic = ref();
statistic.value = {};

axios.get("/nls/admin/report/query-statistic").then(response => {
  // console.log(response);
  let data = response.data;
  if (data.success) {
    statistic.value = data.content;

    formatAndRender30Chart("registerCountList", "注册人数", statistic.value.registerCountList);
    formatAndRender30Chart("filetransCountList", "语音识别次数", statistic.value.filetransCountList);
    formatAndRender30Chart("filetransSecondList", "语音识别时长", statistic.value.filetransSecondList);
    formatAndRender30Chart("orderCountList", "订单数", statistic.value.orderCountList);
    formatAndRender30Chart("orderAmountList", "订单金额", statistic.value.orderAmountList);
  } else {
    notification.error({description: data.message});
  }
});

// ----------------- 图表显示 -----------------
const formatAndRender30Chart = (id, title, data30) => {
  let dates = [];
  let nums = [];
  for (let i = 0; i < data30.length; i++) {
    const record = data30[i];
    dates.push(record.date);
    nums.push(record.num);
  }
  console.log(dates);
  console.log(nums);
  render30Chart(id, title, dates, nums);
}

const render30Chart = (id, title, xAxios, yAxios) => {
  // 发布生产后出现问题：切到别的页面，再切回首页，报表显示不出来
  // 解决方法：把原来的id=registerCount的区域清空，重新初始化
  const dom = document.getElementById(id + '-col');
  if (dom) {
    dom.innerHTML = '<div id="' + id + '" style="width: 100%;height:250px;"></div>';
  }
  const myChart = echarts.init(document.getElementById(id));
  const option = {
    title: {
      text: title,
    },
    xAxis: {
      data: xAxios
    },
    yAxis: {},
    series: [{
      data: yAxios,
      type: 'bar'
    }]
  };
  myChart.setOption(option);
};

const formatSecond = (second) => {
  return Tool.formatSecond(second);
};
</script>
