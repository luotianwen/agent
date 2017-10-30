/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.brand.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.product.entity.Product;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;
import com.thinkgem.jeesite.modules.agent.brand.dao.BrandDao;

/**
 * 品牌Service
 *
 * @author luotianwen
 * @version 2017-10-29
 */
@Service
@Transactional(readOnly = true)
public class BrandService extends CrudService<BrandDao, Brand> {

    public Brand get(String id) {
        return super.get(id);
    }

    public Brand getByName(String warehousename) {
        return dao.getByName(warehousename);
    }

    public List<Brand> findList(Brand brand) {
        return super.findList(brand);
    }

    public Page<Brand> findPage(Page<Brand> page, Brand brand) {
        return super.findPage(page, brand);
    }

    @Transactional(readOnly = false)
    public void save(Brand brand) {
        super.save(brand);
    }

    @Transactional(readOnly = false)
    public void delete(Brand brand) {
        super.delete(brand);
    }

    @Transactional(readOnly = false)
    public int saveOrUpdate() {
        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        String str = Cont.post(Cont.BAND, map);
        BackData j = JSON.parseObject(str, BackData.class);

        if (j.getRows() == null || j.getRows().size() == 0) {
            return 0;
        }
        for (Object p1 : j.getRows()) {
            Brand p = JSON.parseObject(p1.toString(), Brand.class);
            Brand p2 = getByName(p.getWarehousename());
            if (p2 == null) {
                p.setId(null);
            } else {
                p.setId(p2.getId());
            }
            save(p);

        }

        return 1;

    }
}