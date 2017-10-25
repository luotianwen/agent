package com.thinkgem.jeesite.modules.agent;

import com.thinkgem.jeesite.modules.agent.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class TestJob {

    @Autowired
    private ProductService productService;

    public void test(){

        System.out.println(new Date()+"定时更新任务开始");
        productService.saveOrUpdate();
        System.out.println(new Date()+"定时结束任务开始");
    }
}
