package com.jiawa.nls.business.controller.web;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.vod.upload.resp.UploadVideoResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.vod.model.v20170321.CreateUploadVideoResponse;
import com.aliyuncs.vod.model.v20170321.GetMezzanineInfoResponse;
import com.aliyuncs.vod.model.v20170321.SearchMediaResponse;
import com.jiawa.nls.business.properties.DemoProperties;
import com.jiawa.nls.business.req.GetUploadAuthReq;
import com.jiawa.nls.business.resp.CommonResp;
import com.jiawa.nls.business.resp.GetUploadAuthResp;
import com.jiawa.nls.business.util.VodUtil;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/web/vod")
public class WebVodController {

    private static final Logger LOG = LoggerFactory.getLogger(WebVodController.class);

    @Resource
    private DemoProperties demoProperties;

    @PostMapping("/get-upload-auth")
    public CommonResp<Object> getUploadAuth(@Valid @RequestBody GetUploadAuthReq req) throws Exception {
        LOG.info("获取上传凭证开始: ");
        CommonResp<Object> commonResp = new CommonResp<>();
        DefaultAcsClient client = VodUtil.initVodClient();
        String title = req.getKey() + "-" + req.getName();
        SearchMediaResponse searchMediaResponse = VodUtil.searchByTitle(title);
        if (searchMediaResponse.getTotal() > 0) {
            LOG.info("该文件已上传过 = {}", title);
            SearchMediaResponse.Media media = searchMediaResponse.getMediaList().get(0);
            String vid = media.getMediaId();
            GetMezzanineInfoResponse getMezzanineInfoResponse = VodUtil.getMezzanineInfo(vid);
            String fileUrl = getMezzanineInfoResponse.getMezzanine().getFileURL();
            // 直接返回原始地址，不带过期时间等参数
            fileUrl = fileUrl.split("\\?")[0];
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("fileUrl", fileUrl);
            jsonObject.put("videoId", vid);
            commonResp.setContent(jsonObject);
        } else {
            try {
                CreateUploadVideoResponse videoResponse = VodUtil.createUploadVideo(client, title);
                GetUploadAuthResp authResp = new GetUploadAuthResp();
                authResp.setUploadAuth(videoResponse.getUploadAuth());
                authResp.setUploadAddress(videoResponse.getUploadAddress());
                authResp.setVideoId(videoResponse.getVideoId());
                LOG.info("授权码 = {}", videoResponse.getUploadAuth());
                LOG.info("地址 = {}", videoResponse.getUploadAddress());
                LOG.info("videoId = {}", videoResponse.getVideoId());
                commonResp.setContent(authResp);
            } catch (Exception e) {
                LOG.error("获取上传凭证错误", e);
            }
        }
        LOG.info("获取上传凭证结束");
        return commonResp;
    }

    @GetMapping("/cal-amount/{videoId}")
    public CommonResp<BigDecimal> calAmount(@PathVariable String videoId) {
        BigDecimal amount = VodUtil.calAmount(videoId);
        return new CommonResp<>(amount);
    }

    /**
     * 上传示例音频，如果上传过，直接取上传过的示例音频，并计算出收费金额、时长等
     * @return
     * @throws Exception
     */
    @GetMapping("/upload-demo")
    public CommonResp<DemoProperties> uploadDemo() throws Exception {
        String title = demoProperties.getKey() + "-" + demoProperties.getName();
        SearchMediaResponse searchMediaResponse = VodUtil.searchByTitle(title);
        String vid = "";
        // 有就直接从VOD拿，没有就先上传VOD，得到vid
        if (searchMediaResponse.getTotal() > 0) {
            LOG.info("该文件已上传过 = {}", title);
            SearchMediaResponse.Media media = searchMediaResponse.getMediaList().get(0);
            vid = media.getMediaId();
        } else {
            // File file = ResourceUtils.getFile("classpath:" + demoProperties.getName());
            // UploadVideoResponse videoResponse = VodUtil.uploadLocalFile(title, file.getAbsolutePath());
            // 上面两行只在本地起作用，生产打包后，demo.wav会被打进jar包里，导致file.getAbsolutePath()报错
            // 所以修改demo.name配置为全路径，如：demo.name=/Users/temp/nls/demo.wav，并手动放入demo.wav文件，生产也需要手动放入demo.wav文件
            UploadVideoResponse videoResponse = VodUtil.uploadLocalFile(title, demoProperties.getName());
            vid = videoResponse.getVideoId();

            // 需要延迟2秒，才能拿到刚上传的音频的时长，否则金额计算出来是0
            Thread.sleep(2000);
        }
        demoProperties.setVid(vid);

        // 获取音频地址
        GetMezzanineInfoResponse getMezzanineInfoResponse = VodUtil.getMezzanineInfo(vid);
        String fileUrl = getMezzanineInfoResponse.getMezzanine().getFileURL();
        // 直接返回原始地址，不带过期时间等参数
        fileUrl = fileUrl.split("\\?")[0];
        demoProperties.setAudio(fileUrl);

        // 计算收费金额
        BigDecimal amount = VodUtil.calAmount(vid);
        demoProperties.setAmount(amount);
        return new CommonResp<>(demoProperties);
    }
}
