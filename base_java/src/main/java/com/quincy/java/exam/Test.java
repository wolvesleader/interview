package com.quincy.java.exam;

import java.util.*;

/**
 * 前提：
 * 有一个指标数据文件kpis.txt，格式样例如下（内容仅供参考，不需要相同）：
 * [西安]
 * 用电量=123
 * 人数=100
 * [宝鸡]
 * 用电量=300
 * 人数=250
 * 时间=2020-07-10 12:01:02
 * 备注=备注说明
 * 方括号内为指标所属单位，下方为该单位具有的指标和指标值，单位名称、指标和指标值均为字符串类型，指标不包含空格和特殊符号，指标值不包含换行符。
 *
 * 需求：
 * 实现一个程序，接收用户指令输入，读写上述文件，实现以下2点功能：
 * 1、 用户输入“读取 单位名称”指令时，显示该单位下所有指标和指标值；
 * 2、 用户输入“”写入 单位名称 指标名称 指标值”指令时，对应单位下增加指标和指标值，如果输入的单位不存在，需要自动增加，如果输入的指标名称在该单位下已经存在，则仅替换指标值。
 * 用户输入方式不限，能实现上述功能均可。
 *
 * 时限要求：
 * 一小时内完成。
 * Java语言开发，实现方式不限。
 * 收件箱：llbxdks@163.com
 */

public class Test {
    public static void main(String[] args) {

        HashMap<String,Area> hashMap = new HashMap<>();
        Area area = new Area();
        area.setAreaName("西安");
        area.setElectricityConsumption("100");
        area.setNumberPeople("1000");
        area.setTime(new Date());
        area.setRemarks("一天用电量");
        Scanner scanner = new Scanner(System.in);
        hashMap.put("西安",area);
        System.out.println("请输入要查询的地区");
        String next = scanner.next();
        Area area1 = hashMap.get(next);
        if (Objects.isNull(area1)) {
            System.out.println("改地区没有添加，是否要添加 1.添加，2.退出");
            String choose = scanner.next();
            if ("1".equals(choose)){
                System.out.println("请输入用电量");
                String ele = scanner.next();
                System.out.println("请输入用电人数");
                String peo = scanner.next();
                System.out.println("请输入用备注信息");
                String re = scanner.next();
                Area add = new Area();
                add.setAreaName(next);
                add.setRemarks(re);
                add.setElectricityConsumption(ele);
                add.setNumberPeople(peo);
                add.setTime(new Date());
                hashMap.put(next,add);
                System.out.println(hashMap.get(next).toString());
                System.out.println("录入成功");
            }else {
                System.exit(0);
            }
        }else{
            System.out.println(area1.toString());
        }
    }
}

class Area{
    private String id;
    private String areaName;
    private String electricityConsumption;
    private String numberPeople;
    private Date time;
    private String remarks;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getElectricityConsumption() {
        return electricityConsumption;
    }

    public void setElectricityConsumption(String electricityConsumption) {
        this.electricityConsumption = electricityConsumption;
    }

    public String getNumberPeople() {
        return numberPeople;
    }

    public void setNumberPeople(String numberPeople) {
        this.numberPeople = numberPeople;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public String toString() {
        return "Area{" +
                "id='" + id + '\'' +
                ", areaName='" + areaName + '\'' +
                ", electricityConsumption='" + electricityConsumption + '\'' +
                ", numberPeople='" + numberPeople + '\'' +
                ", time=" + time +
                ", remarks='" + remarks + '\'' +
                '}';
    }
}
