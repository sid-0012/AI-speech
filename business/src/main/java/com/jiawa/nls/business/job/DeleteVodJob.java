package com.jiawa.nls.business.job;

import cn.hutool.core.util.IdUtil;
import com.jiawa.nls.business.service.FiletransService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DeleteVodJob {

    @Resource
    private FiletransService filetransService;

    @Scheduled(cron = "0 0 1/5 * * ?")
    public void cron() {
        try {
            // 增加日志流水号
            MDC.put("LOG_ID", IdUtil.getSnowflakeNextIdStr());
            log.info("删除VOD跑批开始");
            long start = System.currentTimeMillis();
            filetransService.deleteVodJob();
            log.info("删除VOD跑批结束，耗时：{}毫秒", System.currentTimeMillis() - start);
        } catch (Exception e) {
            log.error("删除VOD跑批异常", e);
        }
    }
}
