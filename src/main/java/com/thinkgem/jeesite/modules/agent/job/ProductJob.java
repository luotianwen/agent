package com.thinkgem.jeesite.modules.agent.job;

import com.thinkgem.jeesite.modules.agent.product.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class ProductJob {
    private static Logger logger = LoggerFactory.getLogger(ProductJob.class);
    @Autowired
    private ProductService productService;

    public void execute(){
        logger.info(new Date()+"定时更新商品开始");
        productService.saveOrUpdate();
        logger.info(new Date()+"定时结束商品开始");
    }
}
