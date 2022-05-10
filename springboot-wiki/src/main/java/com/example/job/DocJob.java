package com.example.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;

import com.example.service.IDocService;
import com.example.utils.SnowFlake;

@Component
public class DocJob {
    private static final Logger LOG = LoggerFactory.getLogger(DocJob.class);
    @Resource
    IDocService docService;
    @Resource
    private SnowFlake snowFlake;

    /**
     * 定时更新电子书信息,30秒更新一次
     * 
     * @throws InterruptedException
     */
    @Scheduled(cron = "*/30 * * * * ?")
    public void cron() throws InterruptedException {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docService.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }
}