/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.stock.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;
import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.stock.entity.Stock;
import com.thinkgem.jeesite.modules.agent.stock.dao.StockDao;

/**
 * 库存Service
 *
 * @author luotianwen
 * @version 2017-10-29
 */
@Service
@Transactional(readOnly = true)
public class StockService extends CrudService<StockDao, Stock> {

    public Stock get(String id) {
        return super.get(id);
    }

    public Stock getByName(String warehousename, String articleno, String size) {
        return dao.getByName(warehousename, articleno, size);
    }

    public List<Stock> findList(Stock stock) {
        return super.findList(stock);
    }

    public Page<Stock> findPage(Page<Stock> page, Stock stock) {
        return super.findPage(page, stock);
    }

    @Transactional(readOnly = false)
    public void save(Stock stock) {
        super.save(stock);
    }

    @Transactional(readOnly = false)
    public void delete(Stock stock) {
        super.delete(stock);
    }

    @Autowired
    private BrandService brandService;

    public int saveOrUpdate() {

        List<Brand> brands = brandService.findList(new Brand());
        if (null == brands || 0 == brands.size()) {
            return 0;
        }
        for (Brand b : brands) {
            Map map = new HashMap();
            map.put("sign", Cont.SIGN);
            map.put("wareHouseName", b.getWarehousename());
            String str = Cont.post(Cont.STOCK, map);
            BackData j = JSON.parseObject(str, BackData.class);
            if (j.getRows() == null || j.getRows().size() == 0) {
                return 0;
            }
            for (Object p1 : j.getRows()) {
                Stock p = JSON.parseObject(p1.toString(), Stock.class);
                Stock p2 = getByName(p.getWarehousename(), p.getArticleno(), p.getSize());
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
}