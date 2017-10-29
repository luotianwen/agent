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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.product.entity.Product;
import com.thinkgem.jeesite.modules.agent.product.dao.ProductDao;

/**
 * 商品管理Service
 *
 * @author luotianwen
 * @version 2017-10-24
 */
@Service
@Transactional(readOnly = true)
public class ProductService extends CrudService<ProductDao, Product> {
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

    @Transactional(readOnly = false)
    public void delete(Product product) {
        super.delete(product);
    }

    @Transactional(readOnly = false)
    public int saveOrUpdate() {

        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        String str = Cont.post(Cont.PRODUCT, map);
        List<BackData> j = JSON.parseArray(str, BackData.class);
        if (j == null || j.size() == 0) {
            return 0;
        }
        for (BackData bc : j) {
            if (bc.getRows() == null || bc.getRows().size() == 0) {
                continue;
            }
            for (Object p1: bc.getRows()) {
                Product p=(Product)p1;
                Product p2 = getPid(p.getId());
                if (p2 == null) {
                    p.setId(null);
                } else {
                    p.setId(p2.getId());
                }
                save(p);

            }
        }
        return 1;
    }


}