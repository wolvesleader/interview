package com.quincy.java.optimize.java.strategyreconsitution;

/**
 * author:quincy
 * Date:2019-07-22
 * 循环贷款到期还款计算规则
 */
public class CapitalStrategyRevolver extends CapitalStrategy {

    @Override
    public double capital(LoanVo loan) {

        return loan.getCircleTime() * loan.getRiskRating() * loan.getCommitment();
    }
}
