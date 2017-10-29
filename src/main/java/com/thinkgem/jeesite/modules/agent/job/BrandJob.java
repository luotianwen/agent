package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 货源任务
 */
public class BrandJob {
    @Autowired
    private BrandService brandService;
    public void exect(){
        System.out.println("货源更新开始");
        brandService.saveOrUpdate();
        System.out.println("货源更新结束");
    }
}
