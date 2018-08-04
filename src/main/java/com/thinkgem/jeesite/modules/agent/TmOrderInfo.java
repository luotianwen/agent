package com.thinkgem.jeesite.modules.agent;

import java.util.List;

/**
 * 天马订单
 * @date now()
 * @author 罗天文
 */
public class TmOrderInfo {

    /**
     * order_sn : 201407227
     * name : 张三
     * tel : 0518-88888888
     * mobile : 13888888888
     * address : 连云港新浦区
     * province : 江苏省
     * city : 连云港市
     * area : 新浦区
     * post_code : 222000
     * delivery : 申通
     * warehouse_name : 天马总仓
     * goods_info : [{"order_sn_sub":"1668160","goods_no":"B24279","size":41,"amount":1}]
     */

    private String order_sn;
    private String name;
    private String tel;
    private String mobile;
    private String address;
    private String province;
    private String city;
    private String area;
    private String post_code;
    private String delivery;
    private String warehouse_name;
    private List<GoodsInfoBean> goods_info;

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getWarehouse_name() {
        return warehouse_name;
    }

    public void setWarehouse_name(String warehouse_name) {
        this.warehouse_name = warehouse_name;
    }

    public List<GoodsInfoBean> getGoods_info() {
        return goods_info;
    }

    public void setGoods_info(List<GoodsInfoBean> goods_info) {
        this.goods_info = goods_info;
    }
    public static class Result{

        /**
         * status : 6
         * order_sn : 123456
         * info : 库存不足
         * supply_count : 0
         * goods_no : 818097-404
         * size : 7
         */

        private String status;
        private String order_sn;
        private String info;
        private String supply_count;
        private String goods_no;
        private String size;
        private String  trade_id;

        public String getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(String trade_id) {
            this.trade_id = trade_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getOrder_sn() {
            return order_sn;
        }

        public void setOrder_sn(String order_sn) {
            this.order_sn = order_sn;
        }

        public String getInfo() {
            return info;
        }

        public void setInfo(String info) {
            this.info = info;
        }

        public String getSupply_count() {
            return supply_count;
        }

        public void setSupply_count(String supply_count) {
            this.supply_count = supply_count;
        }

        public String getGoods_no() {
            return goods_no;
        }

        public void setGoods_no(String goods_no) {
            this.goods_no = goods_no;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }
    }

    public static class GoodsInfoBean {
        /**
         * order_sn_sub : 1668160
         * goods_no : B24279
         * size : 41
         * amount : 1
         */

        private String order_sn_sub;
        private String goods_no;
        private String size;
        private int amount;

        public String getOrder_sn_sub() {
            return order_sn_sub;
        }

        public void setOrder_sn_sub(String order_sn_sub) {
            this.order_sn_sub = order_sn_sub;
        }

        public String getGoods_no() {
            return goods_no;
        }

        public void setGoods_no(String goods_no) {
            this.goods_no = goods_no;
        }

        public String getSize() {
            return size;
        }

        public void setSize(String size) {
            this.size = size;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }
    }
}
