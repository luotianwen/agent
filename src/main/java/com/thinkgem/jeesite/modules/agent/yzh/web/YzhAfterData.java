package com.thinkgem.jeesite.modules.agent.yzh.web;

import java.io.Serializable;

public class YzhAfterData {
    private String response_status;
    private String error_code;
    private String       error_message;
    private ResultDataBean result_data;

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getError_message() {
        return error_message;
    }

    public void setError_message(String error_message) {
        this.error_message = error_message;
    }

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public ResultDataBean getResult_data() {
        return result_data;
    }

    public void setResult_data(  ResultDataBean result_data) {
        this.result_data = result_data;
    }

    public static class ResultDataBean  implements Serializable {

        /**
         * third_order : “2134768446464”
         * service_order : 1489721
         * product_id : “49879687”
         * amount : “1”
         * type : “returned”
         * refund_amount : “89.59”
         *  status : “completed”
         */

        private String third_order;
        private String service_order;
        private String product_id;
        private String amount;
        private String type;
        private String refund_amount;
        private String status;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getThird_order() {
            return third_order;
        }

        public void setThird_order(String third_order) {
            this.third_order = third_order;
        }

        public String getService_order() {
            return service_order;
        }

        public void setService_order(String service_order) {
            this.service_order = service_order;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
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
    }
}
