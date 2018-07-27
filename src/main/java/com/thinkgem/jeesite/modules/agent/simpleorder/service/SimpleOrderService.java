/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.simpleorder.service;

import com.alibaba.fastjson.JSON;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.BackData;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.TmOrder;
import com.thinkgem.jeesite.modules.agent.agent.entity.Agent;
import com.thinkgem.jeesite.modules.agent.agent.service.AgentService;
import com.thinkgem.jeesite.modules.agent.brand.entity.Brand;
import com.thinkgem.jeesite.modules.agent.job.OrderJob;
import com.thinkgem.jeesite.modules.agent.simpleorder.dao.SimpleOrderDao;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
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

    @Value("#{APP_PROP['tm.name']}")
    String name;
    @Value("#{APP_PROP['tm.pwd']}")
    String pwd;

    private void data(SimpleOrder simpleOrder) {
        Map map = new HashMap();
        map.put("sign", Cont.SIGN);
        map.put("order_sn", simpleOrder.getOrderId());
        map.put("name", name);
        map.put("pwd", pwd);
        String str = Cont.post(Cont.DELIVER, map);
        logger.error(str);
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
                def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRES_NEW);// 事物隔离级别，开启新事务
                TransactionStatus status = transactionManager.getTransaction(def); // 获得事务状态
                try {
                    double moneys = p.getPostage();
                    Agent agent = new Agent();
                    agent.setId(simpleOrder.getAgentid());
                    agent.setMoney(moneys);
                    Double money = agentService.get(agent).getMoney();
                    if (money >= moneys) {
                        agentService.reduceMoney(agent);
                    }
                    simpleOrder.setCourier(p.getDelivery());
                    simpleOrder.setDelivernumber(p.getExpressno());
                    simpleOrder.setDelivermoney(p.getPostage());
                    simpleOrder.setTotalmoney(simpleOrder.getMoney()*simpleOrder.getNum() + p.getPostage());
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
}