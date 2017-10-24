package com.thinkgem.jeesite.modules.agent;

import com.thinkgem.jeesite.modules.agent.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

public class TestJob {

    @Autowired
    private ProductService productService;

    public void test(){

        System.out.println("定时更新任务开始");
        productService.saveOrUpdate();

    }
}
