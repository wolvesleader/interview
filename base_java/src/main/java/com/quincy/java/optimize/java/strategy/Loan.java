package com.quincy.java.optimize.java.strategy;

/**
 * author:quincy
 * Date:2019-07-21
 * 贷款分为：定期贷款，循环贷款，建议信用额度贷款
 * 定期贷款到期还款计算规则  贷款时长 * 风险系数 * 贷款金额 1
 * 循环贷款到期还款计算规则  循环贷款次数 * 风险系数 * 贷款金额 2
 * 建议信用额度贷款到期计算规则 信用系数 * 贷款金额  3
 * 需求：根据不同的贷款类型，然后计算出还款金额
 *
 * 该计算规则不适用与真实的场景，为了说明代码优化列举的例子
 */
public class Loan {


    private int duration ; //贷款时长

    private double commitment; //贷款金额

    private double riskRating; //风险系数

    private double creditRating;//信用系数

    private int circleTime; //循环次数

    private int loadType;//贷款类型

    public Loan(int loadType){
        this.loadType = loadType;
    }


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

    /**
     * if...else语句过多，可以考虑重构
     * 还款金额
     * @return
     */
    public double capital(){

        if (loadType == 1){
            //贷款时长 * 风险系数 * 贷款金额
           return duration * riskRating * commitment;
        }else if(loadType == 2){
            //循环贷款次数 * 风险系数 * 贷款金额
           return circleTime * riskRating * commitment;
        }else if(loadType == 3){
            //信用系数 * 贷款金额
           return creditRating * commitment;
        }else{
            System.out.println("不支持贷款方式");
        }
        return 0.0;
    }


    public static void main(String[] args) {
        Loan loan = new Loan(1);

    }


}
