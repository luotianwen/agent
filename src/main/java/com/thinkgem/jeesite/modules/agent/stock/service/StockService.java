/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.stock.service;

import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.druid.sql.dialect.oracle.ast.stmt.OracleSelectRestriction;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.MyBeanUtils;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;
import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductLog;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStock;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductStockLog;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductStockLogService;
import com.thinkgem.jeesite.modules.agent.dlyb.service.DlybProductStockService;
import com.thinkgem.jeesite.modules.agent.job.StockJob;
import com.thinkgem.jeesite.modules.agent.product.entity.Product;
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
import com.thinkgem.jeesite.modules.agent.stock.entity.Stock;
import com.thinkgem.jeesite.modules.agent.stock.dao.StockDao;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 库存Service
 *
 * @author luotianwen
 * @version 2017-10-29
 */
@Service
@Transactional(readOnly = true)
public class StockService extends CrudService<StockDao, Stock> {
    private static Logger logger = LoggerFactory.getLogger(StockService.class);

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

    @Transactional(readOnly = false)
    public void copy() {
        dao.copyold();
        dao.deleteold();
    }

    @Autowired
    private BrandService brandService;
    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Autowired
    private DlybProductStockService dlybProductStockService;
    @Autowired
    private DlybProductStockLogService dlybProductStockLogService;

    private void data(int page, Brand b) {


        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        map.put("page", page + "");
        map.put("rows", "300");
        map.put("wareHouseName", b.getWarehousename());
        //String str ="{\"total\":1,\"rows\":[  {\"wareHouseName\":\"天马总仓1仓\",\"sex\":\"男\",\"division\":\"鞋\",\"marketprice\":1399.0,\"ukSize\":\"40\",\"articleno\":\"AH2122\",\"brandName\":\"阿迪达斯\",\"discount\":5.4,\"quarter\":\"\",\"innerNum\":10,\"size\":\"40\"}]} " ;
        String str = Cont.post(Cont.STOCK, map);
        BackData j = JSON.parseObject(str, BackData.class);
        if (j.getRows() != null && j.getRows().size() > 0) {

            List<Stock> list = Lists.newArrayList();
            List<DlybProductStock> dlybProductStocks = Lists.newArrayList();
            List<DlybProductStockLog> dlybProductStockLogs = Lists.newArrayList();
            DlybProductStockLog dlybProductStockLog;
            int jj = 0;
            for (Object p1 : j.getRows()) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                Stock p = JSON.parseObject(p1.toString(), Stock.class);
                p.setId(IdGen.uuid());
                DlybProductStock dlybProductStock = new DlybProductStock();
                dlybProductStock.setArticleno(p.getArticleno());
                dlybProductStock.setSpec(p.getUksize());

                try {
                    dlybProductStock = dlybProductStockService.getByNo(dlybProductStock);
                    if (dlybProductStock != null) {
                        //logger.error(dlybProductStock.toString());
                        jj = p.getInnernum() - dlybProductStock.getNum();
                        if (jj != 0) {
                            dlybProductStockLog = new DlybProductStockLog();
                            MyBeanUtils.copyBean2Bean(dlybProductStockLog, dlybProductStock);
                            dlybProductStockLog.setBeforenum(dlybProductStock.getNum());
                            dlybProductStockLog.setChangenum(jj);
                            dlybProductStockLog.setNum(p.getInnernum());
                            dlybProductStock.setNum(p.getInnernum());
                            dlybProductStockLog.setState(jj > 0 ? "1" : "0");
                            dlybProductStockLog.setId(null);
                            dlybProductStockLogService.save(dlybProductStockLog);
                            dlybProductStock.setNum(p.getInnernum());
                            dlybProductStockService.save(dlybProductStock);
                        }
                    }
                    transactionManager.commit(status);
                } catch (Exception e) {
                    logger.error(dlybProductStock.toString() + e.getMessage());
                    transactionManager.rollback(status);
                }
                list.add(p);
            }
            if (null != list && list.size() > 0) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try {
                    saveList(list);
                    transactionManager.commit(status);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    transactionManager.rollback(status);
                }
            }
        }
        int tpage = j.getTotal() / 300 + 1;
        if (page < tpage) {
            Cont.ThreadSleep();
            data(page + 1, b);
        }
    }

    @Transactional(readOnly = false)
    void saveList(List<Stock> list) {
        dao.saveList(list);
    }


    public int saveOrUpdate() {
        List<Brand> brands = brandService.findList(new Brand());
        if (null != brands && 0 < brands.size()) {
            for (Brand b : brands) {
                data(1, b);
            }
        }

        DefaultTransactionDefinition def = new DefaultTransactionDefinition();
        def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
        TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态

        try {
            if (isLastDayOfMonth()) {
                logger.error("移交数据失败");
                this.copy();
                logger.error("移交数据成功");
            }
            transactionManager.commit(status);
        } catch (Exception e) {
            logger.error(e.getMessage());
            transactionManager.rollback(status);
        }
        return 1;
    }

    /**
     * 判断给定日期是否为月末的一天
     *
     * @return true:是|false:不是
     */
    public static boolean isLastDayOfMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.DATE, (calendar.get(Calendar.DATE) + 1));
        if (calendar.get(Calendar.DAY_OF_MONTH) == 1) {
            return true;
        }
        return false;
    }

    public Page<Stock> findPage2(Page<Stock> page, Stock entity) {
        entity.setPage(page);
        page.setList(dao.findList2(entity));
        return page;

    }

    public List<Stock> findList2(Stock stock) {
        return dao.findList2(stock);
    }
}