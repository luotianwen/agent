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

    private int data(int page) {

        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        map.put("page", page);
        map.put("rows", 300);
        String str = Cont.post(Cont.PRODUCT, map);
        BackData j = JSON.parseObject(str, BackData.class);
        if (j.getRows() == null || j.getRows().size() == 0) {
            return 0;
        }

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
        int tpage = j.getTotal() / 300 + 1;
        if (page < tpage) {
            data(page + 1);
        }
        return 1;
    }

    @Transactional(readOnly = false)
    public int saveOrUpdate() {
        data(1);
        return 1;
    }


}