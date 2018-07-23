/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.product.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;
import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.job.ProductJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.product.entity.Product;
import com.thinkgem.jeesite.modules.agent.product.dao.ProductDao;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 商品管理Service
 *
 * @author luotianwen
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {
    private static Logger logger = LoggerFactory.getLogger(ProductService.class);
    @Autowired
    private BrandService brandService;

    public Product get(String id) {
        return super.get(id);
    }

    public Product getPid(String pid) {
        return super.dao.getPid(pid);
    }

    public List<Product> findList(Product product) {
        return super.findList(product);
    }

    public Page<Product> findPage(Page<Product> page, Product product) {
        return super.findPage(page, product);
    }

    @Transactional(readOnly = false)
    public void save(Product product) {
        super.save(product);
    }
    @Autowired
    private DataSourceTransactionManager transactionManager;
    @Transactional(readOnly = false)
    public void delete(Product product) {
        super.delete(product);
    }

    private void data(int page) {

        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        map.put("page", page + "");
        map.put("rows", "300");
        String str = Cont.post(Cont.PRODUCT, map);
        System.out.println(str);
        BackData j = JSON.parseObject(str, BackData.class);
        if (j.getRows() != null && j.getRows().size() > 0) {
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();

            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务

            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            try {
            for (Object p1 : j.getRows()) {
                Product p = JSON.parseObject(p1.toString(), Product.class);
                Product p2 = getPid(p.getId());
                if (p2 == null) {
                    p.setId(null);
                } else {
                    p.setId(p2.getId());
                }
                save(p);
            }
                transactionManager.commit(status);
            } catch (Exception e) {
                logger.error(e.getMessage());
                transactionManager.rollback(status);
            }
            int tpage = j.getTotal() / 300 + 1;
            if (page < tpage) {
                try {
                    Thread.sleep(Cont.SECONDS);
                } catch (InterruptedException e) {

                }
                data(page + 1);
            }
        }
    }


    public int saveOrUpdate() {
        data(1);
        return 1;
    }


}