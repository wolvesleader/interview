package com.quincy.java.optimize.java.strategyreconsitution;

/**
 * author:quincy
 * Date:2019-07-22
 * 定期贷款到期还款计算规则
 */
public class CapitalStrategyTermLoad extends CapitalStrategy {

    @Override
    public double capital(LoanVo loan) {
        return loan.getDuration() * loan.getRiskRating() * loan.getCommitment();
    }
}
