package com.thinkgem.jeesite.modules.agent.yzh.web;

import java.util.List;

public class YzhWuliu {

    /**
     * RESPONSE_STATUS : true
     * RESULT_DATA : {"third_order":"1115810578","shipment_name":"申通","shipment_order":"3308392920868","status":"signed","last_modify_time":"2016-05-22 17:41:19","contents":[{"time":"2016-05-22 17:41:19","description":"已签收,签收人是: 本人"},{"time":"2016-05-22 17:41:10","description":"广东福田梅林营业点 的派件员 邹双林 正在派件"},{"time":"2016-05-19 13:36:54","description":"广东福田梅林营业点 正在进行 货件留仓 扫描"},{"time":"2016-05-19 12:49:48","description":"广东福田梅林营业点 正在进行 疑难件 扫描,原因是： 客户要求自取"},{"time":"2016-05-19 10:36:33","description":"广东福田梅林营业点 的派件员 邹双林 正在派件"},{"time":"2016-05-19 09:05:05","description":"快件已到达 广东福田梅林营业点"},{"time":"2016-05-19 05:43:22","description":"由 广东深圳公司(0755-26907755) 发往 广东福田梅林营业点"},{"time":"2016-05-19 04:03:22","description":"快件已到达 广东深圳公司(0755-26907755)"},{"time":"2016-05-18 23:20:12","description":"由 广东广州公司(020-83963555) 发往 广东深圳中转部"},{"time":"2016-05-18 16:48:06","description":"广东广州公司(020-83963555) 的收件员 市场部马务佐丹奴 已收件"}]}
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
         * third_order : 1115810578
         * shipment_name : 申通
         * shipment_order : 3308392920868
         * status : signed
         * last_modify_time : 2016-05-22 17:41:19
         * contents : [{"time":"2016-05-22 17:41:19","description":"已签收,签收人是: 本人"},{"time":"2016-05-22 17:41:10","description":"广东福田梅林营业点 的派件员 邹双林 正在派件"},{"time":"2016-05-19 13:36:54","description":"广东福田梅林营业点 正在进行 货件留仓 扫描"},{"time":"2016-05-19 12:49:48","description":"广东福田梅林营业点 正在进行 疑难件 扫描,原因是： 客户要求自取"},{"time":"2016-05-19 10:36:33","description":"广东福田梅林营业点 的派件员 邹双林 正在派件"},{"time":"2016-05-19 09:05:05","description":"快件已到达 广东福田梅林营业点"},{"time":"2016-05-19 05:43:22","description":"由 广东深圳公司(0755-26907755) 发往 广东福田梅林营业点"},{"time":"2016-05-19 04:03:22","description":"快件已到达 广东深圳公司(0755-26907755)"},{"time":"2016-05-18 23:20:12","description":"由 广东广州公司(020-83963555) 发往 广东深圳中转部"},{"time":"2016-05-18 16:48:06","description":"广东广州公司(020-83963555) 的收件员 市场部马务佐丹奴 已收件"}]
         */

        private String third_order;
        private String shipment_name;
        private String shipment_order;
        private String status;
        private String last_modify_time;
        private List<ContentsBean> contents;

        public String getThird_order() {
            return third_order;
        }

        public void setThird_order(String third_order) {
            this.third_order = third_order;
        }

        public String getShipment_name() {
            return shipment_name;
        }

        public void setShipment_name(String shipment_name) {
            this.shipment_name = shipment_name;
        }

        public String getShipment_order() {
            return shipment_order;
        }

        public void setShipment_order(String shipment_order) {
            this.shipment_order = shipment_order;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getLast_modify_time() {
            return last_modify_time;
        }

        public void setLast_modify_time(String last_modify_time) {
            this.last_modify_time = last_modify_time;
        }

        public List<ContentsBean> getContents() {
            return contents;
        }

        public void setContents(List<ContentsBean> contents) {
            this.contents = contents;
        }

        public static class ContentsBean {
            /**
             * time : 2016-05-22 17:41:19
             * description : 已签收,签收人是: 本人
             */

            private String time;
            private String description;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }
        }
    }
}
