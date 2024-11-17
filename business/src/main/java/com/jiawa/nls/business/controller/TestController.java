package com.jiawa.nls.business.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.jiawa.nls.business.req.DemoQueryReq;
import com.jiawa.nls.business.resp.CommonResp;
import com.jiawa.nls.business.resp.DemoQueryResp;
import com.jiawa.nls.business.service.DemoService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestController {

    @Resource
    private DemoService demoService;

    @NacosValue(value = "${test.name:TTT}", autoRefreshed = true)
    private String testName;

    @GetMapping("/hello")
    public CommonResp<String> hello() {
        // return "Hello World!!!";
        return new CommonResp<>("Hello World!!! " + testName);
    }

    @GetMapping("/count")
    public CommonResp<Integer> count() {
        // return demoService.count();
        return new CommonResp<>(demoService.count());
    }

    @GetMapping("/query")
    public CommonResp<List<DemoQueryResp>> query(@Valid DemoQueryReq req) {
        List<DemoQueryResp> demoList = demoService.query(req);
        // CommonResp<List<Demo>> commonResp = new CommonResp<>();
        // commonResp.setContent(demoList);
        // return commonResp;

        return new CommonResp<>(demoList);
    }
}
