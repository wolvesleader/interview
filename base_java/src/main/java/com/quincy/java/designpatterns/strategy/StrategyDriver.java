package com.quincy.java.designpatterns.strategy;

/**
 * author:quincy
 * Date:2019-07-22
 * 策略者模式
 * 把使用算法的责任和算法本身分割开来，委派给不同的对象管理。
 * 策略模式通常把一个系列的算法包装到一系列的策略类里面，作为一个抽象策略类的子类
 *
 * 应用，减少 if...else...逻辑
 */
public class StrategyDriver {
    public static void main(String[] args) {

        //之前需要使用if..else语句来判断
/*
        if ("ConcreteStrategyA".equals("")){

        }else if("ConcreteStrategyB".equals("")){

        }*/


        Strategy strategyA = new ConcreteStrategyA();

        StrategyContainer strategyContainer = new StrategyContainer(strategyA);

        System.out.println(strategyContainer.choiceStrategy());


    }
}
