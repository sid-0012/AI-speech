package com.jiawa.nls.business.controller.web;

import com.jiawa.nls.business.context.LoginMemberContext;
import com.jiawa.nls.business.req.FiletransPayReq;
import com.jiawa.nls.business.req.FiletransQueryReq;
import com.jiawa.nls.business.resp.CommonResp;
import com.jiawa.nls.business.resp.FiletransQueryResp;
import com.jiawa.nls.business.resp.OrderInfoPayResp;
import com.jiawa.nls.business.resp.PageResp;
import com.jiawa.nls.business.service.FiletransService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/web/filetrans")
public class WebFiletransController {

    @Resource
    private FiletransService filetransService;

    @PostMapping("/pay")
    public CommonResp<OrderInfoPayResp> pay(@Valid @RequestBody FiletransPayReq req) throws Exception {
        log.info("语音识别支付开始");
        OrderInfoPayResp orderInfoPayResp = filetransService.pay(req);
        log.info("语音识别支付结束");
        return new CommonResp<>(orderInfoPayResp);
    }

    @GetMapping("/query")
    public CommonResp<PageResp<FiletransQueryResp>> query(@Valid FiletransQueryReq req)  {
        req.setMemberId(LoginMemberContext.getId());
        PageResp<FiletransQueryResp> pageResp = filetransService.query(req);
        return new CommonResp<>(pageResp);
    }
}
