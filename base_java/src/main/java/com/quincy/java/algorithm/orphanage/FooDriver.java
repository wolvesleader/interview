package com.quincy.java.algorithm.orphanage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

/**
 * Created by quincy on 18/4/21.
 */
public class FooDriver {

    public static void main(String[] args) {
       //int[][] animals =  {{1,-5},{1,-1},{1,9},{1,9},{2,0},{2,1},{1,-8},{2,1},{1,-71},{1,-92},{1,18},{1,91},{1,61},{2,-1},{1,-35},{1,95},{1,-49},{1,9},{1,78},{2,0},{1,91},{1,-96},{2,-1},{2,0},{2,-1},{2,1},{1,38},{2,0},{1,45},{2,0},{1,-51},{2,1},{2,1},{2,-1},{1,39},{1,59},{1,45},{2,0},{1,-70},{2,0},{1,23},{1,88},{1,83},{1,69},{1,-78},{1,-3},{1,-9},{1,-20},{1,-74},{1,-62},{1,5},{1,55},{1,-36},{1,-21},{1,-94},{1,-27},{1,-69},{2,0},{1,-30},{1,-84},{2,0},{2,0},{2,-1},{1,92},{1,60},{2,1},{2,0},{1,-63},{2,0},{1,-87},{1,66},{2,0},{1,17},{2,0},{2,1},{1,-41},{1,-3},{1,-29},{1,72},{2,1},{1,35},{1,81},{1,-83},{1,-17},{1,36},{1,99},{1,-17},{1,8},{2,0},{1,80},{1,50},{1,80},{2,0},{1,-48},{1,-82},{1,-63},{1,2},{2,1},{1,-43},{1,59},{1,93},{1,55},{1,-93},{2,-1},{1,2},{1,13},{2,0}};
       // int[][] animals = {{1,-7},{1,-4},{1,1},{1,1},{2,0},{1,75},{2,1},{2,-1},{1,21},{1,-83},{2,0},{2,-1},{1,7},{1,-64},{1,73},{2,-1},{1,1},{2,1},{1,58},{1,34},{1,-13},{1,-78},{1,21},{1,50},{2,0},{1,-23},{1,76},{1,89},{2,-1},{1,5},{1,18},{1,-45},{2,1},{1,-15},{2,-1},{2,-1},{2,0}};
        int[][] animals = {{1,-3},{1,-6},{1,10},{1,3},{2,0},{1,19},{2,-1},{1,-81},{1,36},{2,0},{2,1},{1,66},{2,0},{1,-13},{2,0},{2,-1},{2,0},{1,29},{2,1},{2,1},{2,1},{1,56},{1,-99},{2,-1},{2,-1},{1,79},{1,-25},{1,-6},{1,63},{1,48},{1,-40},{1,56},{2,1},{1,28},{1,78},{1,20},{1,18},{1,20},{1,-92},{1,87},{2,0},{1,34},{2,-1},{1,96},{1,38},{2,0},{2,-1},{1,17},{1,13},{1,3},{1,-26},{2,0},{2,0},{2,-1},{2,1},{2,0},{1,-78},{1,57},{1,71},{1,-11},{2,-1},{1,-28},{1,-28},{1,-87},{1,-86},{1,-9},{1,50},{2,1},{2,0},{1,65},{1,-98},{1,-54},{2,0},{2,-1},{1,84},{1,-72},{1,-42},{1,77},{1,-61},{1,-61},{1,-11},{1,94},{2,1},{1,93},{2,-1},{2,-1},{1,43},{2,-1},{1,-72},{2,-1},{1,-31},{1,-41},{1,-85},{1,-2},{2,0},{1,94},{1,80},{1,-86},{1,-83},{1,-20},{1,49},{1,-47},{1,46},{1,34},{2,1},{2,0},{1,-41},{2,1},{2,-1},{1,-44},{1,100},{1,-85},{1,-25},{1,-8},{1,-69},{1,13},{1,82},{2,1},{1,-41},{1,-44},{1,22},{1,-72},{1,-16},{1,-11},{1,65},{1,-66},{1,25},{1,-31},{1,-63},{2,1},{1,86},{1,2},{1,6},{1,-42},{1,-9},{1,76},{1,54},{2,0},{2,1}};

        asylum(animals);

       // Arrays.sort();
    }

    public static ArrayList<Integer> asylum(int[][] ope) {
        Adopter adopter = new Adopter();
        ArrayList<Integer> result =  new ArrayList<Integer>();
        for (int i = 0;i < ope.length ; i ++){
            if(ope[i][0] == 1 && ope[i][1] > 0){//进入的是狗
                Animal dog = new Dog(ope[i][1],Animal.getOrder());
                adopter.getDogList().addFirst(dog);
            }else if(ope[i][0] == 1 && ope[i][1] < 0){
                Animal cat = new Cat(ope[i][1],Animal.getOrder());
                adopter.getCatList().addFirst(cat);
            }else if(ope[i][0] == 2 && ope[i][1] == 0){
                //收养最早进入收养锁的动物
                   Dog lastDog = (Dog)adopter.getDogList().peekLast();
                   Cat lastCat = (Cat)adopter.getCatList().peekLast();
                   if(lastCat != null && lastDog != null){
                       if(lastCat.time() > lastDog.time() ){
                           result.add(lastDog.number());
                           adopter.getDogList().remove(lastDog);
                       }else{
                           result.add(lastCat.number());
                           adopter.getCatList().remove(lastCat);
                       }
                   }else if(lastCat != null && lastDog == null){
                       result.add(lastCat.number());
                       adopter.getCatList().remove(lastCat);
                   }else if(lastCat == null && lastDog != null){
                       result.add(lastDog.number());
                       adopter.getDogList().remove(lastDog);
                   }
            }else if(ope[i][0] == 2 && ope[i][1] > 0){
                //收养最早进入的狗
                Animal lastDog = adopter.getDogList().peekLast();
                if(lastDog != null){
                    result.add(lastDog.number());
                    adopter.getDogList().remove(lastDog);
                }
            }else if(ope[i][0] == 2 && ope[i][1] < 0){
                //收养最早进入的猫
                Animal lastCat = adopter.getCatList().peekLast();
                if (lastCat != null){
                    result.add(lastCat.number());
                    adopter.getCatList().remove(lastCat);
                }
            }else{
                System.out.println("操作有误");
            }
        }


        for (Integer s: result
             ) {
            System.out.print(s + " ,");
        }

        System.out.println(result.size());

        return  result;

    }
}

class Dog extends Animal {
    private int number ;
    private int inTime;//进入收容所的时间

    public Dog(int number, int inTime) {
        this.number = number;
        this.inTime = inTime;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int number() {
        return getNumber();
    }

    @Override
    public long time() {
        return this.inTime;
    }
}

class Cat extends Animal {

    private int number ;
    private int inTime;//进入收容所的时间

    public Cat(int number, int inTime) {
        this.number = number;
        this.inTime = inTime;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public int number() {
        return getNumber();
    }

    @Override
    public long time() {
        return this.inTime;
    }
}

class Adopter {

    private LinkedList<Animal> catList = new LinkedList<>();
    private LinkedList<Animal> dogList = new LinkedList<>();


    public LinkedList<Animal> getCatList() {
        return catList;
    }

    public LinkedList<Animal> getDogList() {
        return dogList;
    }

}

 abstract class Animal {

    public static int order;

     public static int getOrder() {
         return order ++ ;
     }

     public abstract int number();

    public abstract long time();


}

