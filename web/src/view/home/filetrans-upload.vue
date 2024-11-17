<template>
  <a-modal v-model:open="open" title="" @ok="pay" ok-text="结算" cancel-text="取消">
    <p>
      <a-space>
        <a-button type="primary" @click="selectFile">
          <span><UploadOutlined /> 选择音频</span>
        </a-button>
        <a-button type="primary" @click="selectDemoFile" :loading="uploadLoading">
          没有音频？使用示例音频
        </a-button>
      </a-space>
      <input type="file"
             style="display: none"
             ref="fileUploadCom"
             accept=".mp3,.wav,.m4a"
             @change="uploadFile"/>
    </p>
    <p>
      已选择文件：{{filetrans.name}}<span v-show="filetrans.amount !== 0">，金额：<b style="color: red; font-size: 18px">{{filetrans.amount}}</b> &nbsp;元</span>
    </p>
    <p>
      <a-progress :percent="Number(filetrans.percent.toFixed(1))"/>
    </p>
    <p>
      音频语言：
      <a-select v-model:value="filetrans.lang" style="width: 120px">
        <a-select-option v-for="o in FILETRANS_LANG_ARRAY" :value="o.code">{{o.desc}}</a-select-option>
      </a-select>
    </p>
    <p>
      支付方式：
      <a-radio-group name="radioGroup" v-model:value="filetrans.channel">
        <a-radio value="A"><img src="/image/alipay.jpg" alt="支付宝" style="height: 50px;"/></a-radio>
      </a-radio-group>
    </p>
  </a-modal>

  <the-alipay ref="theAlipayCom" @afterPay="handleAfterPay"></the-alipay>
</template>
<script setup>
import {onMounted, onUnmounted, ref} from 'vue';
import {message, notification} from "ant-design-vue";
import axios from "axios";
import store from "../../store/index.js";
import TheAlipay from "../../components/the-alipay.vue";

let emit = defineEmits(['after-pay', 'afterPay']);

const open = ref(false);
const FILETRANS_LANG_ARRAY = ref(window.FILETRANS_LANG_ARRAY);

// onMounted(() => {
//   console.log(1);
// });
//
// onUnmounted(() => {
//   console.log(2);
// });

// 在每次打开上传对话框时，都对重要的变量进行初始化
const init = () => {
  filetrans.value = {
    name: "",
    percent: 0,
    amount: 0,
    lang: "",
    audio: "",
    fileSign: "",
    vod: "",
    channel: ""
  }

  if (fileUploadCom.value) {
    console.log("fileUploadCom")
    fileUploadCom.value.value = "";
  }
  // setTimeout(() => fileUploadCom.value.value = "", 1000);
}

const showModal = () => {
  open.value = true;
  init();
};

// -------------- 选择文件 ---------------
const fileUploadCom = ref();
const selectFile = () => {
  fileUploadCom.value.value = "";
  fileUploadCom.value.click();
}

// -------------- 上传文件 ---------------
let uploadAuth;
let uploadAddress;
let videoId;
let filetrans = ref();

const uploader = new AliyunUpload.Vod({
  //userID，必填，只需有值即可。
  userId:"122",
  //分片大小默认1 MB (1048576)，不能小于100 KB
  partSize: 104858,
  //并行上传分片个数，默认5
  parallel: 5,
  //网络原因失败时，重新上传次数，默认为3
  retryCount: 3,
  //网络原因失败时，重新上传间隔时间，默认为2秒
  retryDuration: 2,
  //是否上报上传日志到视频点播，默认为true
  enableUploadProgress: true,
  //开始上传
  'onUploadstarted': function (uploadInfo) {
    console.log("文件上传开始:" + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
    //从视频点播服务获取的uploadAuth、uploadAddress和videoId，设置到SDK里
    uploader.setUploadAuthAndAddress(uploadInfo, uploadAuth, uploadAddress, videoId);
  },
  //文件上传成功
  'onUploadSucceed': function (uploadInfo) {
    console.log("文件上传成功: " + uploadInfo.file.name + ", endpoint:" + uploadInfo.endpoint + ", bucket:" + uploadInfo.bucket + ", object:" + uploadInfo.object);
    let fileUrl = uploadInfo.endpoint.replace("https://", "https://" + uploadInfo.bucket + ".") + "/" + uploadInfo.object;
    console.log("文件地址: " + fileUrl);
    filetrans.value.audio = fileUrl;
  },
  //文件上传失败
  'onUploadFailed': function (uploadInfo, code, message) {
    console.log("文件上传失败: file:" + uploadInfo.file.name + ",code:" + code + ", message:" + message);
  },
  //文件上传进度，单位：字节
  'onUploadProgress': function (uploadInfo, totalSize, loadedPercent) {
    console.log("文件上传中 :file:" + uploadInfo.file.name + ", fileSize:" + totalSize + ", percent:" + Math.ceil(loadedPercent * 100) + "%");
    // 进度条
    filetrans.value.percent = loadedPercent * 100;
  },
  //上传凭证超时
  'onUploadTokenExpired': function (uploadInfo) {
    console.log("onUploadTokenExpired");
    //实现时，根据uploadInfo.videoId调用刷新视频上传凭证接口重新获取UploadAuth
    //从点播服务刷新的uploadAuth，设置到SDK里

    uploader.resumeUploadWithAuth(uploadAuth);
  },
  //全部文件上传结束
  'onUploadEnd':function(uploadInfo){
    console.log("文件上传结束");
    // 上传结束后，清空上传控件里的值，否则多次选择同一个文件会不触发change事件
    fileUploadCom.value.value = "";
  }
});

const uploadFile = () => {
  const file = fileUploadCom.value.files[0];
  console.log(file)

  // 音频文件最大为500M
  let max = 500 * 1024 * 1024;
  let size = file.size;
  if (size > max) {
    notification['warning']({
      message: '系统提示',
      description: "文件大小超过最大限制，最大为500M",
    });
    return;
  }

  // 初始化
  filetrans.value = {
    name: file.name,
    percent: 0,
    amount: 0,
    channel: "A"
  }

  // 调用后端接口获取上传凭证
  let key = b64_md5(file.name + file.type + file.size + file.lastModified);
  filetrans.value.fileSign = key;
  axios.post("/nls/web/vod/get-upload-auth", {
    name: file.name,
    key: key
  }).then(response => {
    let data = response.data;
    if (data.success) {
      let content = data.content;
      if (content.fileUrl) {
        console.log("文件已上传过，地址：", content.fileUrl);
        filetrans.value.percent = 100;
        videoId = content.videoId;
        filetrans.value.audio = content.fileUrl
      } else {
        console.log("获取上传凭证成功：", content);
        uploadAuth = content.uploadAuth;
        uploadAddress = content.uploadAddress;
        videoId = content.videoId;
        uploader.addFile(file, null, null, null, null);
        uploader.startUpload();
      }
      filetrans.value.vod = videoId;
      setTimeout(calAmount, 500)
    } else {
      notification['error']({
        message: '系统提示',
        description: "上传文件失败",
      });
    }
  })
}

// -------------- 计算收费金额 ---------------
const calAmount = () => {
  axios.get("/nls/web/vod/cal-amount/" + videoId).then(response => {
    let data = response.data;
    if (data.success) {
      filetrans.value.amount = data.content;
    } else {
      notification['error']({
        message: '系统提示',
        description: "计算收费金额异常",
      });
    }
  })
}

// -------------- 结算 ---------------
const pay = e => {
  console.log("准备结算：", JSON.stringify(filetrans.value));
  if (Tool.isEmpty(filetrans.value.audio)) {
    notification['error']({
      message: '系统提示',
      description: "请先上传音频文件",
    });
    return;
  }
  if (Tool.isEmpty(filetrans.value.lang)) {
    notification['error']({
      message: '系统提示',
      description: "请选择【音频语言】",
    });
    return;
  }
  if (filetrans.value.amount === 0) {
    notification['error']({
      message: '系统提示',
      description: "金额为0，不能转换",
    });
    return;
  }

  axios.post('/nls/web/filetrans/pay', filetrans.value).then((response)=>{
    let resp = response.data;
    if (resp.success) {
      notification['success']({
        message: '系统提示',
        description: "下单成功，订单号：" + resp.content.orderNo,
      });
      // let divForm = document.getElementsByTagName('divform');
      // if (divForm.length) {
      //   document.body.removeChild(divForm[0])
      // }
      // const div = document.createElement('divform');
      // // 支付宝返回的form
      // div.innerHTML = resp.content.channelResult;
      // document.body.appendChild(div);
      // document.forms[0].setAttribute('target', '_blank');
      // document.forms[0].submit();
      if (filetrans.value.channel === "A") {
        payInfo.value = {
          amount: filetrans.value.amount,
          desc: "语音识别结算",
          qrcode: resp.content.channelResult,
          orderNo: resp.content.orderNo
        };
        theAlipayCom.value.handleOpen(payInfo.value);
      }
    } else {
      notification['error']({
        message: '系统提示',
        description: "下单失败",
      });
    }
  })
};

// ------------- 扫码支付 --------------
const theAlipayCom = ref();
const payInfo = ref();
const handleAfterPay = () => {
  open.value = false;
  emit('after-pay');
}

// ------------- 选择示例文件 --------------
const uploadLoading = ref(false);
const selectDemoFile = () => {
  uploadLoading.value = true;
  axios.get('/nls/web/vod/upload-demo').then((response)=>{
    uploadLoading.value = false;
    let resp = response.data;
    if (resp.success) {
      let demo = resp.content;
      filetrans.value = {
        name: demo.name,
        percent: 100,
        amount: demo.amount,
        lang: demo.lang,
        fileSign: demo.key,
        audio: demo.audio,
        vod: demo.vid,
        channel: 'A'
      };
    } else {
      notification['error']({
        message: '系统提示',
        description: "示例文件上传失败",
      });
    }
  })
};

// 使用 defineExpose 向外暴露指定的数据和方法
defineExpose({
  showModal,
})
</script>
