/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.dlyb.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.utils.IdGen;
import com.thinkgem.jeesite.common.utils.MyBeanUtils;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.TmOrder;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.brand.service.BrandService;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProduct;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.stock.entity.Stock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.dlyb.entity.DlybProductLog;
import com.thinkgem.jeesite.modules.agent.dlyb.dao.DlybProductLogDao;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 动力越博商品日志Service
 * @author 罗天文
 * @version 2018-07-22
 */
@Service
@Transactional(readOnly = true)
public class DlybProductLogService extends CrudService<DlybProductLogDao, DlybProductLog> {

	public DlybProductLog get(String id) {
		return super.get(id);
	}
	
	public List<DlybProductLog> findList(DlybProductLog dlybProductLog) {
		return super.findList(dlybProductLog);
	}
	
	public Page<DlybProductLog> findPage(Page<DlybProductLog> page, DlybProductLog dlybProductLog) {
		return super.findPage(page, dlybProductLog);
	}
	public List<DlybProductLog> findLastList(String no) {
		DlybProductLog dlybProductLog=new DlybProductLog();
		dlybProductLog.setArticleno(no);
		return dao.findLastList(dlybProductLog);
	}
	@Transactional(readOnly = false)
	public void save(DlybProductLog dlybProductLog) {
		super.save(dlybProductLog);
	}
	
	@Transactional(readOnly = false)
	public void delete(DlybProductLog dlybProductLog) {
		super.delete(dlybProductLog);
	}
	@Autowired
	private DataSourceTransactionManager transactionManager;
	@Autowired
	private DlybProductService dlybProductService;
	public void saveOrUpdate() {
		List<DlybProduct> dlybProducts = dlybProductService.getAll();
		for (DlybProduct d:dlybProducts
			 ) {
			data(d);
			try {
				Thread.sleep(1000*3);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	private void data(DlybProduct  d) {
		Map map = new HashMap();
		map.put("sign", Cont.SIGN);
		map.put("wareHouseName", "天马总仓1仓");
		map.put("articleno", d.getArticleno());

		//String str ="{\"total\":3,\"page\":\"1\",\"rows\":[{\"sex\":\"女\",\"marketprice\":199.00,\"ukSize\":\"L\",\"brandName\":\"阿迪达斯\",\"warehouse_goods_no\":\"CW3549\",\"size\":\"A/L\",\"innerNum\":\"39\",\"warehouse_size\":\"A/L\",\"discount\":\"5.8\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"CW3549\",\"quarter\":\"18Q2\"},{\"sex\":\"女\",\"marketprice\":199.00,\"ukSize\":\"M\",\"brandName\":\"阿迪达斯\",\"warehouse_goods_no\":\"CW3549\",\"size\":\"A/M\",\"innerNum\":\"54\",\"warehouse_size\":\"A/M\",\"discount\":\"5.8\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"CW3549\",\"quarter\":\"18Q2\"},{\"sex\":\"女\",\"marketprice\":199.00,\"ukSize\":\"S\",\"brandName\":\"阿迪达斯\",\"warehouse_goods_no\":\"CW3549\",\"size\":\"A/S\",\"innerNum\":\"43\",\"warehouse_size\":\"A/S\",\"discount\":\"5.8\",\"wareHouseName\":\"天马总仓1仓\",\"division\":\"服\",\"articleno\":\"CW3549\",\"quarter\":\"18Q2\"}]}";//
		String str =Cont.post(Cont.STOCK, map);
		logger.info(str);
		BackData j = JSON.parseObject(str, BackData.class);

		if (j.getRows() != null && j.getRows().size() > 0) {
			DefaultTransactionDefinition def = new DefaultTransactionDefinition();
			def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
			TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
			Stock p;
			DlybProduct dp;
			List<DlybProductLog> ds;
			DlybProductLog dsl;
			DlybProductLog dlybProductLog;
			double m=0;
			try {
				   Object p1=j.getRows().get(0);
					p= JSON.parseObject(p1.toString(), Stock.class);
					logger.error(p1.toString());
					dp=dlybProductService.getByNo(p.getArticleno());
				    m=p.getDiscount();
					if(null!=dp){
						if(null!=dp.getDiscount()){
							m=p.getDiscount()-dp.getDiscount();
						}
						dp.setMarketprice(p.getMarketprice());
						dp.setDiscount(p.getDiscount());
						dlybProductService.save(dp);
					   ds=this.findLastList(p.getArticleno());
                       if(ds!=null&&ds.size()>0){
						   if(m!=0) {
							   this.deleteByDate(ds.get(ds.size() - 1));
						   }
					     }
						if(m!=0) {
							dlybProductLog = new DlybProductLog();
							MyBeanUtils.copyBean2Bean(dlybProductLog, dp);
							dlybProductLog.setId(null);
							dlybProductLog.setChangediscount(m);
							dlybProductLog.setState(m > 0 ? "1" : "0");
							this.save(dlybProductLog);
						}

					}
				transactionManager.commit(status);
			} catch (Exception e) {
				logger.error(e.getMessage());
				transactionManager.rollback(status);
			}


		}

	}

	private void deleteByDate(DlybProductLog dlybProductLog) {
		dao.deleteByDate(dlybProductLog);
	}
}