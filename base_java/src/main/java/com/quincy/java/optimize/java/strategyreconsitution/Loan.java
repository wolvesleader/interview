package com.quincy.java.optimize.java.strategyreconsitution;

/**
 * 优化目的减少if....else写法
 * author:quincy
 * Date:2019-07-21
 * 贷款分为：定期贷款，循环贷款，建议信用额度贷款
 *
 * 需求：根据不同的贷款类型，然后计算出还款金额
 *
 * 涉及到 单例模式
 * 策略者模式
 * 模版方法模式
 */
public class Loan {

    private CapitalStrategy capitalStrategy;
    private LoanVo loanVo;

    private Loan(LoanVo loanVo,CapitalStrategy capitalStrategy){
         this.capitalStrategy = capitalStrategy;
         this.loanVo = loanVo;
    }

    public double capital(){
       return capitalStrategy.capital(loanVo);
    }

    public static Loan newTermLoad(LoanVo loanVo){
        return new Loan(loanVo,new CapitalStrategyTermLoad());
    }

    public static Loan newRevolver(LoanVo loanVo){
        return new Loan(loanVo,new CapitalStrategyRevolver());
    }

    public static Loan newAdvisedLine(LoanVo loanVo){
        return new Loan(loanVo,new CapitalStrategyAdvisedLine());
    }

}
