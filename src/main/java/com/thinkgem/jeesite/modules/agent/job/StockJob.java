package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.stock.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 定时更新仓库信息
 */
public class StockJob {
    @Autowired
    private StockService stockService;
    public void exect(){
        System.out.println("库存更新开始");
        stockService.saveOrUpdate();
        System.out.println("库存更新结束");
    }

}
