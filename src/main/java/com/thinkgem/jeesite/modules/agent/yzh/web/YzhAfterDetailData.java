package com.thinkgem.jeesite.modules.agent.yzh.web;

public class YzhAfterDetailData {

    /**
     * RESPONSE_STATUS : true
     * RESULT_DATA : {"service_order":1305,"third_order":"test1841","system_order":"4406594506","product":347820,"amount":1,"type":"niffer","refund_amount":"0.00","status":"waiting_audit_shipment_neworder","new_order":"ASO2084406594506","new_order_status":"waiting_shipment","returned_address":"深圳市南山区科技园北区清华信息港综合楼406","receiver_name":"测试前台","receiver_mobile":"18812340000"}
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
         * service_order : 1305
         * third_order : test1841
         * system_order : 4406594506
         * product : 347820
         * amount : 1
         * type : niffer
         * refund_amount : 0.00
         * status : waiting_audit_shipment_neworder
         * new_order : ASO2084406594506
         * new_order_status : waiting_shipment
         * returned_address : 深圳市南山区科技园北区清华信息港综合楼406
         * receiver_name : 测试前台
         * receiver_mobile : 18812340000
         */

        private int service_order;
        private String third_order;
        private String system_order;
        private int product;
        private int amount;
        private String type;
        private String refund_amount;
        private String status;
        private String new_order;
        private String new_order_status;
        private String returned_address;
        private String receiver_name;
        private String receiver_mobile;

        public int getService_order() {
            return service_order;
        }

        public void setService_order(int service_order) {
            this.service_order = service_order;
        }

        public String getThird_order() {
            return third_order;
        }

        public void setThird_order(String third_order) {
            this.third_order = third_order;
        }

        public String getSystem_order() {
            return system_order;
        }

        public void setSystem_order(String system_order) {
            this.system_order = system_order;
        }

        public int getProduct() {
            return product;
        }

        public void setProduct(int product) {
            this.product = product;
        }

        public int getAmount() {
            return amount;
        }

        public void setAmount(int amount) {
            this.amount = amount;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getRefund_amount() {
            return refund_amount;
        }

        public void setRefund_amount(String refund_amount) {
            this.refund_amount = refund_amount;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getNew_order() {
            return new_order;
        }

        public void setNew_order(String new_order) {
            this.new_order = new_order;
        }

        public String getNew_order_status() {
            return new_order_status;
        }

        public void setNew_order_status(String new_order_status) {
            this.new_order_status = new_order_status;
        }

        public String getReturned_address() {
            return returned_address;
        }

        public void setReturned_address(String returned_address) {
            this.returned_address = returned_address;
        }

        public String getReceiver_name() {
            return receiver_name;
        }

        public void setReceiver_name(String receiver_name) {
            this.receiver_name = receiver_name;
        }

        public String getReceiver_mobile() {
            return receiver_mobile;
        }

        public void setReceiver_mobile(String receiver_mobile) {
            this.receiver_mobile = receiver_mobile;
        }
    }
}
