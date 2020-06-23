package com.websocket.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ToolUtil {
    /**
     * 随机生成10位广告编号
     *
     * @param input
     * @return
     */
    public static String shuffle(String input) {
        List<Character> characters = new ArrayList<Character>();
        for (char c : input.toCharArray()) {
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while (characters.size() != 0) {
            int randPicker = (int) (Math.random() * characters.size());
            output.append(characters.remove(randPicker));
        }
        System.out.println(output.toString());
        return output.substring(0, 10);
    }

    /**
     * 计算溢价后的价格
     */
    public static BigDecimal premiumPrice(BigDecimal premium, BigDecimal price,BigDecimal rate) {
        BigDecimal a = premium.divide(new BigDecimal(100)).add(new BigDecimal(1));
        BigDecimal b = a.multiply(price);
        BigDecimal c =b.multiply(rate);
        BigDecimal value = c.setScale(2, BigDecimal.ROUND_DOWN);
        return value;
    }
    //计算是否是被整除
    public static Boolean divisionNumber(BigDecimal x,BigDecimal y) {
        BigDecimal c=x.divide(y,2,BigDecimal.ROUND_HALF_DOWN);
        String d=c.toString();
        Boolean state=false;
        int e= d.indexOf(".");
         BigDecimal num=new BigDecimal(d.substring(d.indexOf(".")+1,d.length()));
        if (num.compareTo(new BigDecimal(0))==1){
            state=true;
        }else {
            state=false;
        }
        return  state;
    }
    public static void main(String[] args) {
        System.out.println(divisionNumber(new BigDecimal(2),new BigDecimal(2)));
    }
}
