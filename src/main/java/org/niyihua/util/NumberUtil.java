package org.niyihua.util;

import cn.hutool.core.util.StrUtil;

import java.math.BigDecimal;

public class NumberUtil {

    //是否是反数
    public static boolean isRevertNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] str = num.toString().split("\\.");
        if(str.length!=2){
            return false;
        }
        if(StrUtil.equals(str[0],new StringBuilder(str[1]).reverse().toString())){
            return true;
        }
        return false;
    }


    //是否是阶梯数
    public static boolean isStepNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String str = num.toString().replace(".", "");
        char[] chars = str.toCharArray();
        if(chars.length<=1){
            return false;
        }
        for (int i = 1; i < chars.length; i++) {
            if((chars[i]-chars[i-1])!=1){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
    }

    //叠字数
    public static boolean isDDNumber(BigDecimal num){
        return true;
    }

    //对字数
    public static boolean isDuiZiNumber(BigDecimal num){
        String[] str = num.toString().split("\\.");
        return true;
    }

    //todo 倍数  3.09 是倍数吗
    public static boolean isMutiNumber(BigDecimal num){
        if(num==null){
            return false;
        }
        String[] str = num.toString().split("\\.");
        if(str.length!=2){
            return false;
        }
        int i0 = Integer.parseInt(str[0]);
        int i1 = Integer.parseInt(str[1]);
        if(i0==0){
            return false;
        }
        if(i1%i0==0&&i1/i0>0){
            return true;
        }
        return false;
    }


}
