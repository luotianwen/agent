package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 动力越博
 */
@Component
public class DlybJob {
    private static Logger logger = LoggerFactory.getLogger(DlybJob.class);
    @Autowired
    private DlybProductLogService dlybProductLogService;
    public void execute(){
        logger.info("折扣开始");
        dlybProductLogService.saveOrUpdate();
        logger.info("折扣结束");
    }

}
