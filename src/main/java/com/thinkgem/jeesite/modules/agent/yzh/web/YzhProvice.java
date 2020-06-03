package com.thinkgem.jeesite.modules.agent.yzh.web;

import java.io.Serializable;
import java.util.List;

public class YzhProvice implements Serializable {

    private String response_status;
    private List<Resultdatabean> result_data;

    public String getResponse_status() {
        return response_status;
    }

    public void setResponse_status(String response_status) {
        this.response_status = response_status;
    }

    public List<Resultdatabean> getResult_data() {
        return result_data;
    }

    public void setResult_data(List<Resultdatabean> result_data) {
        this.result_data = result_data;
    }

    public static class Resultdatabean  implements Serializable{
        /**
         * code : 23
         * name : 海南
         * type : province
         */

        private int code;
        private String name;
        private String type;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }
    }
}
