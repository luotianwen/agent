package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.stock.service.StockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 定时更新仓库信息
 */
@Component
public class StockJob {
    private static Logger logger = LoggerFactory.getLogger(StockJob.class);
    @Autowired
    private StockService stockService;
    public void execute(){
        logger.error("库存更新开始");
        stockService.saveOrUpdate();
        logger.error("库存更新结束");
    }

}
