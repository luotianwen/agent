/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.agent.yzh.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.agent.Cont;
import com.thinkgem.jeesite.modules.agent.simpleorder.entity.SimpleOrder;
import com.thinkgem.jeesite.modules.agent.simpleorder.service.SimpleOrderService;
import com.thinkgem.jeesite.modules.agent.yzh.web.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.agent.yzh.entity.YzhOrder;
import com.thinkgem.jeesite.modules.agent.yzh.dao.YzhOrderDao;

/**
 * 云中鹤订单管理Service
 *
 * @author 云中鹤订单管理
 * @version 2020-06-02
 */
@Service
@Transactional(readOnly = true)
public class YzhOrderService extends CrudService<YzhOrderDao, YzhOrder> {

    public YzhOrder get(String id) {
        return super.get(id);
    }

    public List<YzhOrder> findList(YzhOrder yzhOrder) {
        return super.findList(yzhOrder);
    }

    public Page<YzhOrder> findPage(Page<YzhOrder> page, YzhOrder yzhOrder) {
        return super.findPage(page, yzhOrder);
    }


    @Transactional(readOnly = false)
    public void save(YzhOrder yzhOrder) {
        if (StringUtils.isEmpty(yzhOrder.getId())) {
            String WID = Global.getConfig("yzh.WID");
            String accessToken = Global.getConfig("yzh.accessToken");
            String yzhURL = Global.getConfig("yzh.Url");
            long timestamp = System.currentTimeMillis();
            Map map = new HashMap();
            map.put("wid", WID);
            //MD5(wid + accessToken + 时间戳)，值再转大写
            map.put("token", Cont.md5(WID + accessToken + timestamp).toUpperCase());
            map.put("timestamp", timestamp + "");

            map.put("thirdOrder", yzhOrder.getThirdorder());
            map.put("pid_nums", yzhOrder.getPidNums());
            map.put("receiverName", yzhOrder.getReceivername());
            map.put("province", yzhOrder.getProvince());
            map.put("city", yzhOrder.getCity());
            map.put("county", yzhOrder.getCounty());
            map.put("address", yzhOrder.getAddress());
            map.put("mobile", yzhOrder.getMobile());
            map.put("remark", yzhOrder.getRemark());

            String str = Cont.post(yzhURL + Cont.submit, map).toLowerCase();
            ObjectMapper objectMapper = new ObjectMapper();
            YzhOrderData j = null;

            try {
                j = objectMapper.readValue(str, YzhOrderData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (j.getResponse_status().equals("true")) {
                yzhOrder.setOrderKey(j.getResult_data().getOrder_key());
                yzhOrder.setOrderProductPrice(j.getResult_data().getOrder_product_price());
                yzhOrder.setOrderTotalPrice(j.getResult_data().getOrder_total_price());
                yzhOrder.setOrderShipmentFee(j.getResult_data().getOrder_shipment_fee());
            }


        }
        super.save(yzhOrder);
    }

    @Transactional(readOnly = false)
    public void delete(YzhOrder yzhOrder) {
        super.delete(yzhOrder);
    }

    @Autowired
    private SimpleOrderService simpleOrderService;

    @Transactional(readOnly = false)
    public void kd(YzhOrder yzhOrder) throws Exception {
        String WID = Global.getConfig("yzh.WID");
        String accessToken = Global.getConfig("yzh.accessToken");
        String yzhURL = Global.getConfig("yzh.Url");
        long timestamp = System.currentTimeMillis();
        Map map = new HashMap();
        map.put("wid", WID);
        //MD5(wid + accessToken + 时间戳)，值再转大写
        map.put("token", Cont.md5(WID + accessToken + timestamp).toUpperCase());
        map.put("timestamp", timestamp + "");
        map.put("thirdOrder", yzhOrder.getThirdorder());
        String str = Cont.post(yzhURL + Cont.orderTrack, map).toLowerCase();
        ObjectMapper objectMapper = new ObjectMapper();
        YzhWuliu j = null;

        try {
            j = objectMapper.readValue(str, YzhWuliu.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (j.getResponse_status().equals("true")) {
            if (StringUtils.isEmpty(j.getResult_data().getShipment_name())) {
                return;
            }
            yzhOrder.setShipmentName(j.getResult_data().getShipment_name());
            yzhOrder.setShipmentOrder(j.getResult_data().getShipment_order());
            dao.updatekt(yzhOrder);
            SimpleOrder simpleOrder = new SimpleOrder();
            simpleOrder.setOrderId(yzhOrder.getThirdorder());
            simpleOrder = simpleOrderService.getOrderId(simpleOrder);
            simpleOrder.setCourier(j.getResult_data().getShipment_name());
            simpleOrder.setDelivernumber(j.getResult_data().getShipment_order());
            simpleOrder.setDelivermoney(yzhOrder.getOrderShipmentFee());
            simpleOrderService.fast(simpleOrder);

        }


    }

    @Transactional(readOnly = false)
    public void saveafter(YzhOrder yzhOrder) {

        String WID = Global.getConfig("yzh.WID");
        String accessToken = Global.getConfig("yzh.accessToken");
        String yzhURL = Global.getConfig("yzh.Url");
        long timestamp = System.currentTimeMillis();
        Map map = new HashMap();
        map.put("wid", WID);
        //MD5(wid + accessToken + 时间戳)，值再转大写
        map.put("token", Cont.md5(WID + accessToken + timestamp).toUpperCase());
        map.put("timestamp", timestamp + "");
        map.put("thirdOrder", yzhOrder.getThirdorder());

        map.put("productId", yzhOrder.getProductid() + "");
        map.put("amount", yzhOrder.getAmount() + "");
        map.put("type", yzhOrder.getType() + "");
        map.put("reason", yzhOrder.getReason() + "");
        map.put("userRemark", yzhOrder.getUserremark() + "");
        String str = Cont.post(yzhURL + Cont.afterSalesubmit, map).toLowerCase();
        ObjectMapper objectMapper = new ObjectMapper();
        YzhAfterData j = null;

        try {
            j = objectMapper.readValue(str, YzhAfterData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (j.getResponse_status().equals("true")) {
            yzhOrder.setServiceOrder(j.getResult_data().getService_order());
            dao.updateafter(yzhOrder);
        }

    }

    @Transactional(readOnly = false)
    public void returnedAddress(YzhOrder yzhOrder) {
        String WID = Global.getConfig("yzh.WID");
        String accessToken = Global.getConfig("yzh.accessToken");
        String yzhURL = Global.getConfig("yzh.Url");
        long timestamp = System.currentTimeMillis();
        Map map = new HashMap();
        map.put("wid", WID);
        //MD5(wid + accessToken + 时间戳)，值再转大写
        map.put("token", Cont.md5(WID + accessToken + timestamp).toUpperCase());
        map.put("timestamp", timestamp + "");
        map.put("serviceOrder", yzhOrder.getServiceOrder());
        String str = Cont.post(yzhURL + Cont.afterSaledetail, map).toLowerCase();
        ObjectMapper objectMapper = new ObjectMapper();
        YzhAfterDetailData j = null;

        try {
            j = objectMapper.readValue(str, YzhAfterDetailData.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (j.getResponse_status().equals("true")) {
            yzhOrder.setReturnedAddress(j.getResult_data().getReturned_address());
            yzhOrder.setReceiverName2(j.getResult_data().getReceiver_name());
            yzhOrder.setReceiverMobile(j.getResult_data().getReceiver_mobile());
            yzhOrder.setNewOrder(j.getResult_data().getNew_order());
            yzhOrder.setReceiverStatus(j.getResult_data().getStatus());
            dao.returnedAddress(yzhOrder);
        }
    }
}