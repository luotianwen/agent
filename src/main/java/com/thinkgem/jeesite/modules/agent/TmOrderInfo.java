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
         * status : 0
         * trade_id : 30006092
         * orders : [{"order_id":36812455,"goods_no":"11400792","size1":"MISC","size2":"MISC","price":54.45,"post_fee":12}]
         * order_sn : 20180805120429472
         * info : 下单成功
         */

        private String status;
        private String trade_id;
        private String order_sn;
        private String info;
        private List<OrdersBean> orders;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getTrade_id() {
            return trade_id;
        }

        public void setTrade_id(String trade_id) {
            this.trade_id = trade_id;
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

        public List<OrdersBean> getOrders() {
            return orders;
        }

        public void setOrders(List<OrdersBean> orders) {
            this.orders = orders;
        }

        public static class OrdersBean {
            /**
             * order_id : 36812455
             * goods_no : 11400792
             * size1 : MISC
             * size2 : MISC
             * price : 54.45
             * post_fee : 12.0
             */

            private int order_id;
            private String goods_no;
            private String size1;
            private String size2;
            private double price;
            private double post_fee;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getGoods_no() {
                return goods_no;
            }

            public void setGoods_no(String goods_no) {
                this.goods_no = goods_no;
            }

            public String getSize1() {
                return size1;
            }

            public void setSize1(String size1) {
                this.size1 = size1;
            }

            public String getSize2() {
                return size2;
            }

            public void setSize2(String size2) {
                this.size2 = size2;
            }

            public double getPrice() {
                return price;
            }

            public void setPrice(double price) {
                this.price = price;
            }

            public double getPost_fee() {
                return post_fee;
            }

            public void setPost_fee(double post_fee) {
                this.post_fee = post_fee;
            }
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
