/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.TmOrder;
import com.thinkgem.jeesite.modules.agent.TmOrderInfo;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;
import com.thinkgem.jeesite.modules.agent.job.OrderJob;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.SimpleOrderDao;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import org.apache.batik.transcoder.keys.StringKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 下单管理Service
 *
 * @author 罗天文
 * @version 2018-06-16
 */
@Service
@Transactional(readOnly = true)
public class SimpleOrderService extends CrudService<SimpleOrderDao, SimpleOrder> {
    private static Logger logger = LoggerFactory.getLogger(SimpleOrderService.class);
    @Autowired
    private AgentService agentService;


    public SimpleOrder get(String id) {
        return super.get(id);
    }

    public List<SimpleOrder> findList(SimpleOrder simpleOrder) {
        return super.findList(simpleOrder);
    }

    public Page<SimpleOrder> findPage(Page<SimpleOrder> page, SimpleOrder simpleOrder) {
        return super.findPage(page, simpleOrder);
    }

    @Transactional(readOnly = false)
    public void save(SimpleOrder simpleOrder) {

        super.save(simpleOrder);

    }

    @Transactional(readOnly = false)
    public void delete(SimpleOrder simpleOrder) {
        Agent agent = new Agent();
        agent.setId(simpleOrder.getAgentid());
        agent.setMoney(simpleOrder.getMoney());
        agentService.addMoney(agent);
        super.delete(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void deliver(SimpleOrder simpleOrder) throws Exception {
        if (simpleOrder.getState().equals("3")) {
            double moneys = simpleOrder.getTotalmoney() - simpleOrder.getMoney();
            Agent agent = new Agent();
            agent.setId(simpleOrder.getAgentid());
            agent.setMoney(moneys);
            Double money = agentService.get(agent).getMoney();
            if (money >= moneys) {
                agentService.reduceMoney(agent);
            } else {
                throw new Exception("金额不够");
            }
        }
        simpleOrder.preUpdate();
        dao.deliver(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void asave(SimpleOrder simpleOrder) throws Exception {
        double f = 0d;
        if (StringUtils.isEmpty(simpleOrder.getId())) {
            f = simpleOrder.getMoney();
        } else {
            SimpleOrder a = this.get(simpleOrder.getId());
            f = simpleOrder.getMoney() - a.getMoney();
        }
        Agent agent = new Agent();
        agent.setId(simpleOrder.getAgentid());
        agent.setMoney(f);
        Double money = agentService.get(agent).getMoney();
        if (money >= f) {
            agentService.reduceMoney(agent);
        } else {
            throw new Exception("金额不够");
        }
        super.save(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void isaccount(SimpleOrder simpleOrder) {
        dao.isaccount(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void account(String ids) {
        dao.account(ids.split(","));
    }


    String name="悠氧户外商城";
    @Value("#{APP_PROP['tm.pwd']}")
    String pwd;


    private void data(SimpleOrder simpleOrder) {
        String tradeId = simpleOrder.getTradeId();
        if (StringUtils.isEmpty(tradeId)) {
            tradeId = simpleOrder.getOrderId();
        }
        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        map.put("order_sn", tradeId);
        map.put("name", name);
        map.put("pwd", pwd);
        String str = Cont.post(Cont.DELIVER, map);
        logger.error(tradeId + "  orderid " + str);
        BackData j = JSON.parseObject(str, BackData.class);

        if (j.getRows() != null && j.getRows().size() > 0) {
            TmOrder p = null, r = null;
            for (Object o : j.getRows()
                    ) {
                r = JSON.parseObject(o.toString(), TmOrder.class);
                if (StringUtils.isNotBlank(r.getExpressno()) && simpleOrder.getOrderId().equals(r.getOrder_sn_sub())) {
                    p = r;
                    break;
                }
            }
            if (null != p && p.getStatus() == 1) {
                DefaultTransactionDefinition def = new DefaultTransactionDefinition();
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 事物隔离级别，开启新事务
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try {
                  /*  double moneys = p.getPostage();
                    Agent agent = new Agent();
                    agent.setId(simpleOrder.getAgentid());
                    agent.setMoney(moneys);
                    Double money = agentService.get(agent).getMoney();
                    if (money >= moneys) {
                        agentService.reduceMoney(agent);
                    }*/
                    simpleOrder.setCourier(p.getDelivery());
                    simpleOrder.setDelivernumber(p.getExpressno());
                  /*  simpleOrder.setDelivermoney(p.getPostage());
                    simpleOrder.setTotalmoney(simpleOrder.getMoney()*simpleOrder.getNum() + p.getPostage());*/
                    simpleOrder.preUpdate();
                    dao.Tmdeliver(simpleOrder);

                    transactionManager.commit(status);
                } catch (Exception e) {
                    logger.error(e.getMessage());
                    transactionManager.rollback(status);
                }
            }

        }

    }

    @Autowired
    private DataSourceTransactionManager transactionManager;

    @Transactional(readOnly = false)
    public void saveOrUpdate() {

        List<SimpleOrder> simpleOrders = dao.getOrderIdDeliver();
        for (SimpleOrder s : simpleOrders) {
            data(s);
            Cont.ThreadSleep();
        }
    }

    public SimpleOrder sum(SimpleOrder simpleOrder) {
        return dao.sum(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void aftersave(SimpleOrder simpleOrder) {
        dao.aftersave(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void aftersaveok(SimpleOrder simpleOrder) {
        dao.aftersaveok(simpleOrder);
    }

    @Transactional(readOnly = false)
    public void aftersavepass(SimpleOrder simpleOrder) {
        dao.aftersavepass(simpleOrder);
    }

    @Transactional(readOnly = false)
    public String tmOrder(String ids) {

        List<SimpleOrder> simpleOrders = dao.tmOrder(ids.split(","));
        if (null != simpleOrders && simpleOrders.size() == 0) {
            return "没有数据可下单";
        }
        StringBuffer sb = new StringBuffer();
        Map<String, List<SimpleOrder>> map = new HashMap<>();
        List<SimpleOrder> as = null;
        String key;
        for (SimpleOrder s : simpleOrders
                ) {

            if (StringUtils.isEmpty(s.getWarehouse())) {
                sb.append(s.getArticleno()).append("没有配置仓库");
            } else {
                key = s.getWarehouse() + "-" + s.getPhone() + "-" + s.getAddress();
                as = map.get(key);
                if (null == as) {
                    as = Lists.newArrayList();
                    as.add(s);
                } else {
                    as.add(s);
                }
                map.put(key, as);
            }
        }
        Set<Map.Entry<String, List<SimpleOrder>>> entrySet = map.entrySet();
        SimpleOrder so = null;
        String address[];
        List<TmOrderInfo.GoodsInfoBean> goodsInfoBeans;
        List<TmOrderInfo> os=Lists.newArrayList();
        for (Map.Entry<String, List<SimpleOrder>> entry : entrySet) {
            List<SimpleOrder> value = entry.getValue();
            so = value.get(0);
            address = so.getAddress().replace(" ", "").split(",");
            TmOrderInfo tmOrderInfo = new TmOrderInfo();
            tmOrderInfo.setOrder_sn(so.getOrderId());
            tmOrderInfo.setName(so.getConsignee());
            tmOrderInfo.setMobile(so.getPhone());
            tmOrderInfo.setAddress(so.getAddress());
            tmOrderInfo.setProvince(address[0]);
            tmOrderInfo.setCity(address[1]);
            tmOrderInfo.setArea(address[2]);
            tmOrderInfo.setDelivery(so.getCourier());
            tmOrderInfo.setWarehouse_name(so.getWarehouse());
            goodsInfoBeans = Lists.newArrayList();
            for (SimpleOrder s : value
                    ) {
                TmOrderInfo.GoodsInfoBean goodsInfoBean = new TmOrderInfo.GoodsInfoBean();
                goodsInfoBean.setAmount(s.getNum());
                goodsInfoBean.setGoods_no(s.getTmarticleno());
                goodsInfoBean.setOrder_sn_sub(s.getOrderId());
                goodsInfoBean.setSize(s.getTmspec());
                goodsInfoBeans.add(goodsInfoBean);
            }
            tmOrderInfo.setGoods_info(goodsInfoBeans);
            os.add(tmOrderInfo);
        }
        if(null==os||os.size()==0){
            return "没有数据可下单";
        }
        Map map2 = new HashMap();
        map2.put("sign", Cont.SIGN);
        map2.put("orders_info", JSON.toJSONString(os));
        map2.put("name", name);
        map2.put("pwd", pwd);
        // System.out.println(map2.toString());
        logger.error("idata:"+map2.toString());
        String str = Cont.post(Cont.ORDER, map2);
        logger.error("rdata:"+str);
        try {
            List<TmOrderInfo.Result> js = JSON.parseArray(str, TmOrderInfo.Result.class);
            DefaultTransactionDefinition def = new DefaultTransactionDefinition();
            def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);// 事物隔离级别，开启新事务
            TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
            List<TmOrderInfo.GoodsInfoBean> goodsInfoBeans2= Lists.newArrayList();
            SimpleOrder s1;
            try{
                for (TmOrderInfo.Result j:js
                     ) {
                    if (j.getStatus().equals("0")) {
                        for ( TmOrderInfo o:os
                             ) {
                            if(o.getOrder_sn().equals(j.getOrder_sn())){
                                goodsInfoBeans2=o.getGoods_info();
                                for(TmOrderInfo.GoodsInfoBean go:goodsInfoBeans2){
                                     s1=new SimpleOrder();
                                     s1.setOrderId(go.getOrder_sn_sub());
                                     s1.setTradeId(j.getOrder_sn());
                                    dao.updateTradeId(s1);

                                }
                            }
                        }

                    } else {
                        sb.append(j.getOrder_sn()+","+j.getInfo());
                    }
                }
            transactionManager.commit(status);
        } catch (Exception e) {
            logger.error(e.getMessage());
            transactionManager.rollback(status);
        }

        }catch (Exception e){
            logger.error(e.getMessage());
            sb.append(str);
        }
        return sb.toString();
    }
}