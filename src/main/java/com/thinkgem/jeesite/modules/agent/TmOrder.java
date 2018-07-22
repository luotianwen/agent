package com.thinkgem.jeesite.modules.agent;

public class TmOrder {
    /**
     * goods_no : 548187-065
     * order_sn_sub :
     * expressno :
     * order_sn : 1441180111646
     * delivery : 申通
     * order_status : 0
     * size2 : M
     * postage : 2.0
     * size1 : M
     */
    private String goods_no;
    private String order_sn_sub;
    private String expressno;
    private String order_sn;
    private String delivery;
    private int order_status;
    private String size2;
    private double postage;
    private String size1;
    public String getGoods_no() {
        return goods_no;
    }

    public void setGoods_no(String goods_no) {
        this.goods_no = goods_no;
    }

    public String getOrder_sn_sub() {
        return order_sn_sub;
    }

    public void setOrder_sn_sub(String order_sn_sub) {
        this.order_sn_sub = order_sn_sub;
    }

    public String getExpressno() {
        return expressno;
    }

    public void setExpressno(String expressno) {
        this.expressno = expressno;
    }

    public String getOrder_sn() {
        return order_sn;
    }

    public void setOrder_sn(String order_sn) {
        this.order_sn = order_sn;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getSize2() {
        return size2;
    }

    public void setSize2(String size2) {
        this.size2 = size2;
    }

    public double getPostage() {
        return postage;
    }

    public void setPostage(double postage) {
        this.postage = postage;
    }

    public String getSize1() {
        return size1;
    }

    public void setSize1(String size1) {
        this.size1 = size1;
    }
}
