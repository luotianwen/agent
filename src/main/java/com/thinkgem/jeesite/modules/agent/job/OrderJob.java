package com.thinkgem.jeesite.modules.agent.job;


import com.thinkgem.jeesite.modules.agent.product.service.ProductService;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.SimpleOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 订单同步快递数据
 */
@Component
public class OrderJob {
    private static Logger logger = LoggerFactory.getLogger(OrderJob.class);
    @Autowired
    private SimpleOrderService simpleOrderService;
    public void execute(){
        System.out.println("快递开始");
        logger.error("快递开始");
        simpleOrderService.saveOrUpdate();
        logger.error("快递结束");
    }
}
