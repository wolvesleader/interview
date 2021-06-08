package com.quincy.hbase;


public class HouNiaoSpecVo {
    private String specSku;
    private String specNum;
    private String specDesc;
    private String weight;
    private String specPrice;
    private String startExpDate;
    private String endExpDate;
    private String stock;
    private String deliverMoney;

    public String getSpecSku() {
        return specSku;
    }

    public void setSpecSku(String specSku) {
        this.specSku = specSku;
    }

    public String getSpecNum() {
        return specNum;
    }

    public void setSpecNum(String specNum) {
        this.specNum = specNum;
    }

    public String getSpecDesc() {
        return specDesc;
    }

    public void setSpecDesc(String specDesc) {
        this.specDesc = specDesc;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getSpecPrice() {
        return specPrice;
    }

    public void setSpecPrice(String specPrice) {
        this.specPrice = specPrice;
    }

    public String getStartExpDate() {
        return startExpDate;
    }

    public void setStartExpDate(String startExpDate) {
        this.startExpDate = startExpDate;
    }

    public String getEndExpDate() {
        return endExpDate;
    }

    public void setEndExpDate(String endExpDate) {
        this.endExpDate = endExpDate;
    }

    public String getStock() {
        return stock;
    }

    public void setStock(String stock) {
        this.stock = stock;
    }

    public String getDeliverMoney() {
        return deliverMoney;
    }

    public void setDeliverMoney(String deliverMoney) {
        this.deliverMoney = deliverMoney;
    }
}
