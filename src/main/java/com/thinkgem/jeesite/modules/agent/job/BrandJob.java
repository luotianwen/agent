package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.gen.util.GenUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 货源任务
 */
@Component
public class BrandJob {
    private static Logger logger = LoggerFactory.getLogger(BrandJob.class);
    @Autowired
    private BrandService brandService;
    public void execute(){
        logger.info("货源更新开始");
        brandService.saveOrUpdate();
        logger.info("货源更新结束");
    }
}
