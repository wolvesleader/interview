package com.quincy.java.optimize.java.strategyreconsitution;

/**
 * author:quincy
 * Date:2019-07-22
 */
public class LoanVo {

    private int duration ; //贷款时长

    private double commitment; //贷款金额

    private double riskRating; //风险系数

    private double creditRating;//信用系数

    private int circleTime; //循环次数


    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getCommitment() {
        return commitment;
    }

    public void setCommitment(double commitment) {
        this.commitment = commitment;
    }

    public double getRiskRating() {
        return riskRating;
    }

    public void setRiskRating(double riskRating) {
        this.riskRating = riskRating;
    }

    public double getCreditRating() {
        return creditRating;
    }

    public void setCreditRating(double creditRating) {
        this.creditRating = creditRating;
    }

    public int getCircleTime() {
        return circleTime;
    }

    public void setCircleTime(int circleTime) {
        this.circleTime = circleTime;
    }

}
