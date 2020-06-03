package com.thinkgem.jeesite.modules.agent.yzh.web;

import java.util.List;

public class YzhOrderData {
    /**
     * RESPONSE_STATUS : true
     * RESULT_DATA : {"order_split":true,"order_key":"4396953714","order_total_price":459.48,"order_product_price":459.48,"order_shipment_fee":0,"order_entry":[{"product_id":347820,"num":2,"sold_price":163.95},{"product_id":347823,"num":1,"sold_price":131.58}]}
     */
    private String response_status;
    private ResultDataBean result_data;

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public ResultDataBean getResult_data() {
        return result_data;
    }

    public void setResult_data(ResultDataBean result_data) {
        this.result_data = result_data;
    }

    public static class ResultDataBean {
        /**
         * order_split : true
         * order_key : 4396953714
         * order_total_price : 459.48
         * order_product_price : 459.48
         * order_shipment_fee : 0
         * order_entry : [{"product_id":347820,"num":2,"sold_price":163.95},{"product_id":347823,"num":1,"sold_price":131.58}]
         */

        private boolean order_split;
        private String order_key;
        private double order_total_price;
        private double order_product_price;
        private int order_shipment_fee;
        private List<OrderEntryBean> order_entry;

        public boolean isOrder_split() {
            return order_split;
        }

        public void setOrder_split(boolean order_split) {
            this.order_split = order_split;
        }

        public String getOrder_key() {
            return order_key;
        }

        public void setOrder_key(String order_key) {
            this.order_key = order_key;
        }

        public double getOrder_total_price() {
            return order_total_price;
        }

        public void setOrder_total_price(double order_total_price) {
            this.order_total_price = order_total_price;
        }

        public double getOrder_product_price() {
            return order_product_price;
        }

        public void setOrder_product_price(double order_product_price) {
            this.order_product_price = order_product_price;
        }

        public int getOrder_shipment_fee() {
            return order_shipment_fee;
        }

        public void setOrder_shipment_fee(int order_shipment_fee) {
            this.order_shipment_fee = order_shipment_fee;
        }

        public List<OrderEntryBean> getOrder_entry() {
            return order_entry;
        }

        public void setOrder_entry(List<OrderEntryBean> order_entry) {
            this.order_entry = order_entry;
        }

        public static class OrderEntryBean {
            /**
             * product_id : 347820
             * num : 2
             * sold_price : 163.95
             */

            private int product_id;
            private int num;
            private double sold_price;

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getNum() {
                return num;
            }

            public void setNum(int num) {
                this.num = num;
            }

            public double getSold_price() {
                return sold_price;
            }

            public void setSold_price(double sold_price) {
                this.sold_price = sold_price;
            }
        }
    }
}
