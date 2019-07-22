package com.quincy.java.optimize.java.strategy;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * author:quincy
 * Date:2019-07-21
 */
public class Loan {

    private Date expiry;//有效期
    private String maturity;//到期日期

    private double outstanding;

    private double commitment;

    private double riskRating;

    private  List<Payment> payments;

    private double unusedPercentage;

    private  Date today;
    private  Date start;

    public double capital(){
        if (expiry == null && maturity != null){//定期贷款
           return commitment * duration() * riskFactor();
        }
        if(expiry != null && maturity == null){
           if (unusedPercentage != 1.0){
               return commitment * unusedPercentage * duration() * riskFactor();
           }else {
               return  (outstandingRiskAmount() * duration() * riskFactor())
                       + (unusedRiskAmount() * duration() * unusedRiskFactor());
           }
        }
        return 0.0;
    }

    public double duration(){
        if (expiry == null && maturity != null){//定期贷款
            return weightAverageDuration();
        }else if(expiry != null && maturity == null){
            return yearsTo(expiry);
        }
        return 0.0;
    }

    private double outstandingRiskAmount(){
        return outstanding;
    }

    private double unusedRiskAmount(){
        return commitment - outstanding;
    }

    private double weightAverageDuration(){
        double duration = 0.0;
        double weightAverage = 0.0;
        double sumOfPayments = 0.0;

        Iterator<Payment> iterator = payments.iterator();
        while (iterator.hasNext()){
            Payment payment = iterator.next();
            sumOfPayments += payment.amount();
            weightAverage += yearsTo(payment.date()) * payment.amount();
        }
        if (commitment != 0.0){
            duration = weightAverage / sumOfPayments;
        }

        return duration;
    }

    private double yearsTo(Date endDate){
        Date beginDate = (today == null ? start : today);
        return ( endDate.getTime() - beginDate.getTime() );
    }
    private double riskFactor(){
        return RiskFactor.getFactors().forRating(riskRating);
    }

    private double unusedRiskFactor(){
        return UnusedRiskFactor.getFactors().forRating(riskRating);
    }

}
