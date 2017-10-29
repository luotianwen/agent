package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class ProductJob {
    @Autowired
    private ProductService productService;

    public void exect(){
        System.out.println(new Date()+"定时更新商品开始");
        productService.saveOrUpdate();
        System.out.println(new Date()+"定时结束商品开始");
    }
}
