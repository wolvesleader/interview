package com.quincy.java.designpatterns.strategy;

/**
 * author:quincy
 * Date:2019-07-22
 */
public class StrategyContainer {

    private Strategy strategy;

    public StrategyContainer(Strategy strategy){
        this.strategy = strategy;
    }

    public String choiceStrategy(){
        return this.strategy.process();
    }


}
