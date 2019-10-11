package com.quincy.java.optimize.java.strategyreconsitution;

/**
 * author:quincy
 * Date:2019-07-22
 * 建议信用额度贷款到期计算规则
 */
public class CapitalStrategyAdvisedLine extends CapitalStrategy {

    @Override
    public double capital(LoanVo loan) {
        return loan.getCreditRating()  * loan.getCommitment();
    }
}
